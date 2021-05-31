package pl.piomin.microservices.advanced.gateway.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerConfig implements SwaggerResourcesProvider {
	
	public static final String PATH_DOC = "/v2/api-docs";
	public static final String SWAGGER_VERSION = "2.0";

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		resources.add(swaggerResource("usuarios", "/api/usuarios".concat(PATH_DOC), SWAGGER_VERSION));
		resources.add(swaggerResource("laboratorios", "/api/laboratorios".concat(PATH_DOC), SWAGGER_VERSION));
		resources.add(swaggerResource("exames", "/api/exames".concat(PATH_DOC), SWAGGER_VERSION));
		resources.add(swaggerResource("relatorios", "/api/relatorios".concat(PATH_DOC), SWAGGER_VERSION));	
		resources.add(swaggerResource("consultas", "/api/consultas".concat(PATH_DOC), SWAGGER_VERSION));	
		resources.add(swaggerResource("files", "/api/files".concat(PATH_DOC), SWAGGER_VERSION));	
		resources.add(swaggerResource("oauth2", "/api/oauth2".concat(PATH_DOC), SWAGGER_VERSION));
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
