
package org.meruvian.yama.social.mervid.connect;

import org.meruvian.yama.social.mervid.api.Mervid;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


public class MervidConnectionFactory extends OAuth2ConnectionFactory<Mervid> {

	public MervidConnectionFactory(String appId, String appSecret) {
		super("mervid", new MervidServiceProvider(appId, appSecret), new MervidAdapter());
	}
}
