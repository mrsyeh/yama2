
package org.meruvian.yama.webapi.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.meruvian.yama.core.role.Role;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.webapi.interceptor.DetectCurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@DetectCurrentUser
public interface UserService {
	@GET
	@Path("/{username}")
	User getUserByUsernameOrId(@PathParam("username") String username);

	@GET
	Page<User> findUserByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);

	@DELETE
	@Path("/{username}")
	void removeUser(@PathParam("username") String username);

	@POST
	User saveUser(User user);
	
	@PUT
	@Path("/{username}")
	User updateUser(@PathParam("username") String username, User user);
	
	@POST
	@Path("/{username}/password")
	User updateUserPassword(@PathParam("username") String username, User user);
	
	@GET
	@Path("/{username}/roles")
	Page<Role> findRoleByUser(@PathParam("username") String username, Pageable pageable);
	
	@PUT
	@Path("/{username}/roles/{roleId}")
	boolean addRoleToUser(@PathParam("username") String username, @PathParam("roleId") String roleId);
	
	@DELETE
	@Path("/{username}/roles/{roleId}")
	boolean removeRoleFromUser(@PathParam("username") String username, @PathParam("roleId") String roleId);
	
	@DELETE
	@Path("/{username}/roles")
	boolean removeAllRoleFromUser(@PathParam("username") String username);
	
	@GET
	@Path("/{username}/photo")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response getUserPhoto(@PathParam("username") String username) throws FileNotFoundException;
	
	@POST
	@Consumes("image/*")
	@Path("/{username}/photo")
	boolean updateUserPhoto(@PathParam("username") String username, InputStream inputStream) throws IOException;
}
