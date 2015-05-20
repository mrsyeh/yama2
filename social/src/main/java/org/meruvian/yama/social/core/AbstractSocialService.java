
package org.meruvian.yama.social.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.ApiBinding;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.util.MultiValueMap;


public abstract class AbstractSocialService<T> implements SocialService<T> {
	protected OAuth2ConnectionFactory<T> connectionFactory;
	
	protected String redirectUri;
	protected String scope;
	protected String state;
	
	public AbstractSocialService(OAuth2ConnectionFactory<T> cf) {
		this.connectionFactory = cf;
	}
	
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public Connection<T> createConnection(String authorizationCode, MultiValueMap<String, String> additionalParameters) {
		AccessGrant grant = connectionFactory.getOAuthOperations()
				.exchangeForAccess(authorizationCode, getParameters().getRedirectUri(), additionalParameters);
		
		return connectionFactory.createConnection(grant);
	}

	@Override
	public String getAuthorizeUrl() {
		String authUrl = connectionFactory.getOAuthOperations()
				.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, getParameters());
		
		return authUrl;
	}

	@Override
	public boolean isAuthorized(Connection<?> connection) {
		Object api = connection.getApi();
		if (api instanceof ApiBinding) {
			ApiBinding apiBinding = (ApiBinding) api;
			return apiBinding.isAuthorized();
		}
		
		return false;
	}
	
	@Override
	public OAuth2Parameters getParameters() {
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setRedirectUri(redirectUri);
		parameters.setScope(StringUtils.defaultString(scope));
		parameters.setState(StringUtils.defaultString(state));
		
		return parameters;
	}
	
	@Override
	public OAuth2ConnectionFactory<T> getConnectionFactory() {
		return connectionFactory;
	}
}
