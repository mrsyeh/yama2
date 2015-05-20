
package org.meruvian.yama.webapi.service;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.application.Application;
import org.meruvian.yama.core.application.Application.GrantType;
import org.meruvian.yama.core.application.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RestApplicationService implements ApplicationService {
	@Inject
	private ApplicationRepository applicationRepository;
	
	@Override
	public Application getApplicationById(String id) {
		return applicationRepository.findById(id);
	}

	@Override
	@Transactional
	public Application saveApplication(Application application) {
		application.setId(null);
		application.setSecret(RandomStringUtils.randomAlphanumeric(64));
		application.getAuthorizedGrantTypes().add(GrantType.REFRESH_TOKEN.toString());
		
		return applicationRepository.save(application);
	}

	@Override
	@Transactional
	public Application updateApplication(Application application) {
		Application a = getApplicationById(application.getId());
		a.setDisplayName(application.getDisplayName());
		a.setNamespace(application.getNamespace());
		a.setDomain(application.getDomain());
		a.setSite(application.getSite());
		a.setRegisteredRedirectUri(application.getRegisteredRedirectUri());
		a.setAutoApprove(application.isAutoApprove());
		a.setAuthorizedGrantTypes(application.getAuthorizedGrantTypes());
		
		return a;
	}
	
	@Override
	@Transactional
	public Application generateNewSecret(String id) {
		Application a = getApplicationById(id);
		a.setSecret(RandomStringUtils.randomAlphanumeric(64));
		
		return a;
	}

	@Override
	@Transactional
	public void removeApplication(String id) {
		getApplicationById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}

	@Override
	public Page<Application> findApplicationByName(String name, Pageable pageable) {
		return applicationRepository.findByName(name, LogInformation.ACTIVE, pageable);
	}
}
