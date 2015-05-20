
package org.meruvian.yama.social.mervid.connect;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.social.mervid.api.Mervid;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;


public class MervidAdapter implements ApiAdapter<Mervid>{

	@Override
	public boolean test(Mervid api) {
		return false;
	}

	@Override
	public void setConnectionValues(Mervid api, ConnectionValues values) {
		User user = api.userOperations().getUser();
		values.setProviderUserId(user.getId());
		values.setDisplayName(user.getUsername());
		values.setProfileUrl(StringUtils.join("http://www.merv.id/admin/users/", user.getUsername()));
	}

	@Override
	public UserProfile fetchUserProfile(Mervid api) {
		User user = api.userOperations().getUser();
		
		return new UserProfileBuilder().setName(user.getUsername())
				.setFirstName(user.getName().getFirst()).setLastName(user.getName().getLast())
				.setEmail(user.getEmail()).setUsername(user.getUsername()).build();
	}

	@Override
	public void updateStatus(Mervid api, String message) {
	}

}
