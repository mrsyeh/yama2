
package org.meruvian.yama.webapi.config.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.webapi.service.commons.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class UserRegisteredListener implements PostInsertEventListener {
	private final Logger log = LoggerFactory.getLogger(UserRegisteredListener.class);
	
	@Inject
	public UserRegisteredListener(EventListenerRegistry registry) {
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
	}
	
	@Inject
	private EmailService emailService;
	
	@Override
	public void onPostInsert(PostInsertEvent event) {
		if (event.getEntity() instanceof User) {
			User user = (User) event.getEntity();
			sendConfirmationEmail(user);
		}
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return true;
	}
	
	@Async
	private void sendConfirmationEmail(User user) {
		log.debug("Sending confirmation email to {}", user.getUsername());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		
		emailService.sendEmail(user.getEmail(), "Complete your registration", 
				"email/user-signup.ftl", model);
	}
}
