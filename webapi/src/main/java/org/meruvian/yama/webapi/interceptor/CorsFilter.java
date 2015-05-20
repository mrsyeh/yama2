/**
 * 
 */
package org.meruvian.yama.webapi.interceptor;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

/**
 * @author dianw
 *
 */
@Provider
@Service
public class CorsFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		if (requestContext.getUriInfo().getPath().startsWith("/api-docs")) {
			headers.putSingle("Access-Control-Allow-Origin", "*");
		}
	}

}
