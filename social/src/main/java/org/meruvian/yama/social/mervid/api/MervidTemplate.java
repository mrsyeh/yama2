
package org.meruvian.yama.social.mervid.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;


public class MervidTemplate extends AbstractOAuth2ApiBinding implements Mervid {
	private UserOperations userOperations;
	
	public MervidTemplate(String accessToken) {
		super(accessToken);
		
		this.userOperations = new UserTemplate(getRestTemplate());
	}
	
	@Override
	public UserOperations userOperations() {
		return userOperations;
	}

}
