
package org.meruvian.yama.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration(exclude = { SocialWebAutoConfiguration.class })
@ComponentScan({ "org.meruvian.yama" })
public class Application {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.setShowBanner(false);
		
		application.run(args);
	}
}
