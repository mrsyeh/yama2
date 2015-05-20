
package org.meruvian.yama.social.core;

import org.springframework.social.connect.ConnectionFactoryLocator;




public interface SocialServiceLocator extends ConnectionFactoryLocator {
	SocialService<?> getSocialService(String name);
	
	<T> SocialService<T> getSocialService(Class<T> apiType);
}
