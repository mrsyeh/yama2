
package org.meruvian.yama.social.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.meruvian.yama.social.connection.SocialConnectionRepository;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public class SocialUsersConnectionService implements UsersConnectionRepository {
	private ConnectionFactoryLocator connectionFactoryLocator;
	private SocialConnectionRepository connectionRepository;
	private TextEncryptor textEncryptor = Encryptors.noOpText();
	private ConnectionSignUp connectionSignUp;

	public SocialUsersConnectionService(SocialServiceLocator locator, SocialConnectionRepository repository) {
		this.connectionFactoryLocator = locator;
		this.connectionRepository = repository;
	}
	
	@Override
	@Transactional
	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		List<String> localUserIds = connectionRepository.findUserIdByProviderAndProviderUserId(
				key.getProviderId(), key.getProviderUserId());
		
		if (localUserIds.size() == 0 && connectionSignUp != null) {
			String newUserId = connectionSignUp.execute(connection);
			if (newUserId != null) {
				createConnectionRepository(newUserId).addConnection(connection);
				return Arrays.asList(newUserId);
			}
		}
		
		return localUserIds;
	}

	@Override
	public Set<String> findUserIdsConnectedTo(String providerId,
			Set<String> providerUserIds) {
		List<String> connections = connectionRepository.findUserIdByProviderAndProviderUserIdIn(
				providerId, providerUserIds);
		
		return new HashSet<String>(connections);
	}

	@Override
	public SocialConnectionService createConnectionRepository(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId cannot be null");
		}

		return new SocialConnectionService(userId, connectionRepository, connectionFactoryLocator, textEncryptor);
	}
	
	public void setTextEncryptor(TextEncryptor textEncryptor) {
		this.textEncryptor = textEncryptor;
	}
	
	public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
		this.connectionSignUp = connectionSignUp;
	}
}
