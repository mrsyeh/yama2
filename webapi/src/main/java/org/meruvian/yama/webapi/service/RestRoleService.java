
package org.meruvian.yama.webapi.service;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.role.Role;
import org.meruvian.yama.core.role.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RestRoleService implements RoleService {
	@Inject
	private RoleRepository roleRepository;
	
	@Override
	public Role getRoleById(String id) {
		return roleRepository.findById(id);
	}

	@Override
	public Page<Role> findRoleByKeyword(String keyword, Pageable pageable) {
		return roleRepository.findByNameOrDescription(keyword, keyword, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public void removeRole(String id) {
		getRoleById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}

	@Override
	@Transactional
	public Role saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())) {
			role.setId(null);
			role.setName(StringUtils.upperCase(role.getName()));
			return roleRepository.save(role);
		}
		
		throw new BadRequestException("Id must be empty, use PUT method to update record");
	}

	@Override
	@Transactional
	public Role updateRole(Role role) {
		Role r = roleRepository.findById(role.getId());
		r.setName(StringUtils.upperCase(role.getName()));
		r.setDescription(role.getDescription());
		
		return r;
	}
}
