
package org.meruvian.yama.webapi.config.oauth;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Inject
	private DefaultTokenServices tokenServices;
	
	@Inject
	@Named("clientDetailsUserDetailsService")
	private UserDetailsService clientDetailsUserDetailsService;
	
	@Inject
	@Named("oauth2AuthenticationEntryPoint")
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Inject
	@Named("oauth2AccessDeniedHandler")
	private AccessDeniedHandler accessDeniedHandler;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		String[] authorizedUrl = {
				"/autoconfig", "/beans", "/configprops", "/dump", "/env", 
				"/health", "/info", "/metrics", "/mappings", "/shutdown",
				"/trace",	
				"/oauth/token", 
				"/api/**"
		};
		
		http
			.requestMatchers()
				.antMatchers(authorizedUrl)
				.and()
			.authorizeRequests()
				.antMatchers("/oauth/token").fullyAuthenticated()
				.antMatchers("/api/roles", "/api/roles/**").hasAuthority("ADMINISTRATOR")
				.antMatchers("/api/users/me", "/api/users/me/**").fullyAuthenticated()
				.antMatchers("/api/users", "/api/users/**").hasAuthority("ADMINISTRATOR")
				.antMatchers("/**").fullyAuthenticated()
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.userDetailsService(clientDetailsUserDetailsService)
			.anonymous().disable()
			.headers()
			.frameOptions().disable()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenServices);
	}
}
