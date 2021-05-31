package br.com.softbank.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import br.com.softbank.oauth2.service.CustomUserDetailsService;

@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
	
	@Value("${oauth2_client}")
	private String oauth2_client;
	@Value("${oauth2_secret}")
	private String oauth2_secret;

	@Autowired
	private AuthenticationManager authenticationManager;	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomUserDetailsService userDetailsService;	
			
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(oauth2_client)
			.secret(passwordEncoder.encode(oauth2_secret))
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("web", "mobile")
			.accessTokenValiditySeconds(60 * 60)
			.refreshTokenValiditySeconds(60 * 60 * 24);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
	}
	
}
