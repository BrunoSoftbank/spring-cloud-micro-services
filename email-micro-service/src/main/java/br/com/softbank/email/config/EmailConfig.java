package br.com.softbank.email.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class EmailConfig {

	@Value("${EMAIL_FROM}")
	private String userName;
	@Value("${PASSWORD_EMAIL_FROM}")
	private String passWord;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);	     
	    mailSender.setUsername(userName);
	    mailSender.setPassword(passWord);

	    Properties properties = mailSender.getJavaMailProperties();
	    properties.put("mail.transport.protocol", "smtp");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.debug", "true");
	    mailSender.setJavaMailProperties(properties);   
	    return mailSender;
	}
	
	@Bean(name = "htmlTemplateEngine")
	public TemplateEngine templateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(this.htmlTemplateResolver());
		return templateEngine;
	}
	
	private ITemplateResolver htmlTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/template/html");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCheckExistence(true);
		templateResolver.setCacheable(false);
		return templateResolver;
	}
}
