
package org.meruvian.yama.social.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;


public class SocialServiceRegistry extends ConnectionFactoryRegistry implements SocialServiceLocator {
	private List<SocialService<?>> socialServices = new ArrayList<SocialService<?>>();
	
	public void addSocialService(SocialService<?> socialService) {
		super.addConnectionFactory(socialService.getConnectionFactory());
		socialServices.add(socialService);
	}
	
	public void addSocialServices(List<SocialService<?>> socialServices) {
		for (SocialService<?> socialService : socialServices) {
			addSocialService(socialService);
		}
	}
	
	@Override
	public void addConnectionFactory(ConnectionFactory<?> connectionFactory) {
		throw new IllegalArgumentException("Use addSocialService(socialService) instead");
	}

	@Override
	public SocialService<?> getSocialService(String name) {
		for (SocialService<?> service : socialServices) {
			if (service.getConnectionFactory().equals(getConnectionFactory(name))) {
				return service;
			}
		}
		
		return null;
	}

	@Override
	public <T> SocialService<T> getSocialService(Class<T> apiType) {
		for (SocialService<?> service : socialServices) {
			if (service.getConnectionFactory().equals(getConnectionFactory(apiType))) {
				return (SocialService<T>) service;
			}
		}
		
		return null;
	}
}
