
package org.meruvian.yama.webapi.service;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.meruvian.yama.social.core.SocialService;
import org.meruvian.yama.social.core.SocialServiceLocator;
import org.meruvian.yama.web.CredentialsService;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;


@Service
public class RestSocialSignInService implements SocialSignInService {
	public static final String OAUTH_AUTH_URL = "/oauth/authorize";
	
	@Inject
	private SocialServiceLocator socialServiceLocator;
	
	@Inject
	private UsersConnectionRepository connectionRepository;
	
	@Inject
	private CredentialsService credentialsService;
	
	@Context
	private HttpServletRequest request;
	
	@Override
	public Response socialSignIn(String provider) {
		String redirectUri = socialServiceLocator.getSocialService(provider).getAuthorizeUrl();
		
		return Response.seeOther(URI.create(redirectUri)).build();
	}

	@Override
	public Response socialSignInCallback(String provider, String code) {
		SocialService<?> socialService = socialServiceLocator.getSocialService(provider);
		Connection<?> connection = socialService.createConnection(code, null);
		
		if (socialService.isAuthorized(connection)) {
			List<String> userIds = connectionRepository.findUserIdsWithConnection(connection);
			
			if (userIds.size() == 1) { // Signin
				String userId = userIds.get(0);

				credentialsService.registerAuthentication(userId);
			}
		}
		
		return Response.seeOther(URI.create(getRedirectUrlAfterLogin())).build();
	}

	protected String getRedirectUrlAfterLogin() {
		HttpSession session = request.getSession(false);

		if (session != null) {
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				return savedRequest.getRedirectUrl();
			}
		}

		return "/";
	}
}
