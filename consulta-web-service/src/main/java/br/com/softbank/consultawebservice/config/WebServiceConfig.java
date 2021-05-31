package br.com.softbank.consultawebservice.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import br.com.softbank.consultawebservice.service.LoginService;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Autowired
	private LoginService loginService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
	}
	
	@Bean
	public XsdSchema customerSchema() {
		return new SimpleXsdSchema(new ClassPathResource("consulta-information.xsd"));
	}
	
	@Bean(name="consultas")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customerSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("ConsultaPort");
		definition.setTargetNamespace("http://softbank.com.br");
		definition.setLocationUri("/ws");
		definition.setSchema(customerSchema);
		return definition;
	}
	
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callbackHandler());
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return securityInterceptor;
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();	
		Map<String, String> map = new HashMap<>();	
		try {			
			loginService.findAll().stream().forEach(login -> {
				map.put(login.getUsuario(), login.getSenha());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		handler.setUsersMap(map);
		return handler;
	}
	
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
}
