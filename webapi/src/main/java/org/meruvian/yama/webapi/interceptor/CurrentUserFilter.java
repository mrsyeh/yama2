
package org.meruvian.yama.webapi.interceptor;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.meruvian.yama.web.SessionCredentials;
import org.springframework.stereotype.Component;

/**
 * Intercept request URI with path parameter {username}, replace parameter value
 * with current authorized username if parameter value equals "me"
 */
@Provider
@Component
@DetectCurrentUser
public class CurrentUserFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		UriInfo uriInfo = requestContext.getUriInfo();
		MultivaluedMap<String, String> parameters = uriInfo.getPathParameters();
		List<String> usernames = parameters.get("username");
		
		if (usernames == null) return;
		
		if (usernames.contains("me")) {
			usernames.set(usernames.indexOf("me"), SessionCredentials.getCurrentUsername());
		}
	}
}
