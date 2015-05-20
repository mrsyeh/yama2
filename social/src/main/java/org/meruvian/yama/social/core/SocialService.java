
package org.meruvian.yama.social.core;

import org.meruvian.yama.core.user.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.util.MultiValueMap;


public interface SocialService<T> {
	Connection<T> createConnection(String authorizationCode, MultiValueMap<String, String> additionalParameters);;
	
	User createUser(Connection<?> connection);
	
	String getAuthorizeUrl();
	
	boolean isAuthorized(Connection<?> connection);
	
	OAuth2Parameters getParameters();
	
	OAuth2ConnectionFactory<T> getConnectionFactory();
}
