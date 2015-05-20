
package org.meruvian.yama.web.security.oauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.meruvian.yama.core.application.Application;
import org.meruvian.yama.core.application.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;


@Service("clientDetailsService")
public class DefaultClientDetailsService implements ClientDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultClientDetailsService.class);
	
	@Inject
	private ApplicationRepository applicationRepository;

	private Collection<String> authorizedGrantTypes;
	private Collection<String> scopes;
	private Collection<String> resourceIds = new ArrayList<String>();
	
	@PostConstruct
	public void postConstruct() {
		if (authorizedGrantTypes == null) {
			authorizedGrantTypes = new ArrayList<String>();
			authorizedGrantTypes.add("authorization_code");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("implicit");
			authorizedGrantTypes.add("password");
		}
		
		if (scopes == null) {
			scopes = new ArrayList<String>();
			scopes.add("read");
			scopes.add("write");
			scopes.add("trust");
		}
	}
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Application application = applicationRepository.findById(clientId);
		if (application == null) return null;
		
		BaseClientDetails details = new BaseClientDetails();
		details.setClientId(application.getId());
		details.setClientSecret(application.getSecret());
		details.setAuthorizedGrantTypes(authorizedGrantTypes);
		details.setScope(scopes);
		details.setResourceIds(resourceIds);
		details.setRegisteredRedirectUri(application.getRegisteredRedirectUris());
		if (application.isAutoApprove())
			details.setAutoApproveScopes(Arrays.asList("true"));
		details.setAccessTokenValiditySeconds(application.getAccessTokenValiditySeconds());
		details.setRefreshTokenValiditySeconds(application.getRefreshTokenValiditySeconds());
		
		return details;
	}
}
