package br.com.softbank.batch.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class Oauth2Config {
	
	@Value("${oauth2_client}")
	private String oauth2_client;
	@Value("${oauth2_secret}")
	private String oauth2_secret;
	@Value("${oauth2_url}")
	private String oauth2_url;
	@Value("${oauth2_user_name}")
	private String oauth2_user_name;
	@Value("${oauth2_pass_word}")
	private String oauth2_pass_word;
		
	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
	    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
	    resource.setAccessTokenUri(oauth2_url);
	    resource.setClientId(oauth2_client);
	    resource.setClientSecret(oauth2_secret);
	    resource.setClientAuthenticationScheme(AuthenticationScheme.header);    
	    
	    resource.setUsername(oauth2_user_name);
	    resource.setPassword(oauth2_pass_word);
	    resource.setGrantType("password");
	    resource.setScope(Arrays.asList("web"));
	    return resource;
	}
	
	@Bean
	 public OAuth2RestOperations restTemplate() {
	    return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
	}
}
