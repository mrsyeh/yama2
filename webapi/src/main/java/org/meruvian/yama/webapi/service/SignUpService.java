
package org.meruvian.yama.webapi.service;

import javax.ws.rs.Path;

import org.meruvian.yama.core.user.User;



@Path("/signup")
public interface SignUpService {
	User signUp(User user);
}
