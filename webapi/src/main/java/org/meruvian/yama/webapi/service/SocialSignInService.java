
package org.meruvian.yama.webapi.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/login/social")
public interface SocialSignInService {
	@GET
	@Path("/{provider}")
	Response socialSignIn(@PathParam("provider") String provider);
	
	@GET
	@Path("/{provider}/callback")
	Response socialSignInCallback(@PathParam("provider") String provider, @QueryParam("code") String code);
}
