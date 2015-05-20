
package org.meruvian.yama.social.facebook;

import java.io.ByteArrayInputStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.commons.FileInfo;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.social.core.AbstractSocialService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.ImageType;


public class FacebookService extends AbstractSocialService<Facebook> {
	public FacebookService(OAuth2ConnectionFactory<Facebook> facebook) {
		super(facebook);
	}

	@Override
	public User createUser(Connection<?> connection) {
		Facebook facebook = (Facebook) connection.getApi();
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		
		String randomUsername = RandomStringUtils.randomAlphanumeric(6);
		
		User user = new User();
		user.setUsername(StringUtils.join(profile.getFirstName(), profile.getLastName(), randomUsername));
		user.getName().setFirst(profile.getFirstName());
		user.getName().setLast(profile.getLastName());
		user.getName().setMiddle(profile.getMiddleName());
		user.setEmail(profile.getEmail());
		
		if (StringUtils.isBlank(profile.getEmail())) {
			user.setEmail(StringUtils.join(profile.getUsername(), "@facebook.com"));
		}
		
		user.setPassword(RandomStringUtils.randomAlphanumeric(8));
		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setDataBlob(new ByteArrayInputStream(
				facebook.userOperations().getUserProfileImage(ImageType.NORMAL)));
		user.setFileInfo(fileInfo);
		
		return user;
	}
}
