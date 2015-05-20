
package org.meruvian.yama.webapi.service;

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

import org.meruvian.yama.core.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Path("/api/roles")
@Produces(MediaType.APPLICATION_JSON)
public interface RoleService {
	@GET
	@Path("/{id}")
	Role getRoleById(@PathParam("id") String id);

	@GET
	Page<Role> findRoleByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);

	@DELETE
	@Path("/{id}")
	void removeRole(@PathParam("id") String id);

	@POST
	Role saveRole(Role role);
	
	@PUT
	@Path("/{id}")
	Role updateRole(Role role);
}
