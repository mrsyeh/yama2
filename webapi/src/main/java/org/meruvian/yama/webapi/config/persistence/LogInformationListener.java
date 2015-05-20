
package org.meruvian.yama.webapi.config.persistence;

import java.util.Date;

import javax.inject.Inject;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.core.user.User;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.stereotype.Component;


@Component
public class LogInformationListener implements PreInsertEventListener, PreUpdateEventListener {
	@Inject
	public LogInformationListener(EventListenerRegistry registry) {
		registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
		registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(this);
	}
	
	private String getCurrentUserId() {
		User user = SessionCredentials.getCurrentUser();
		String userId = null;
		
		if (user != null) {
			userId = user.getId();
		}
		
		return userId;
	}
	
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		String userId = getCurrentUserId();
		
		if (event.getEntity() instanceof DefaultPersistence) {
			DefaultPersistence p = (DefaultPersistence) event.getEntity();
			LogInformation logInfo = p.getLogInformation();
		
			logInfo.setCreateDate(new Date());
			logInfo.setLastUpdateDate(new Date());
			logInfo.setCreateBy(userId);
			logInfo.setLastUpdateBy(userId);
			
			Object[] state = event.getState();
			
			for (int i = 0; i < state.length; i++) {
				if (state[i] instanceof LogInformation) {
					state[i] = logInfo;
					
					break;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		String userId = getCurrentUserId();
		
		if (event.getEntity() instanceof DefaultPersistence) {
			DefaultPersistence p = (DefaultPersistence) event.getEntity();
			LogInformation logInfo = p.getLogInformation();
			
			Object[] oldState = event.getOldState();
			
			for (int i = 0; i < oldState.length; i++) {
				if (oldState[i] instanceof LogInformation) {
					LogInformation oldLogInfo = (LogInformation) oldState[i];
					
					logInfo.setCreateDate(oldLogInfo.getCreateDate());
					logInfo.setLastUpdateDate(new Date());
					logInfo.setCreateBy(oldLogInfo.getCreateBy());
					logInfo.setLastUpdateBy(userId);
					
					break;
				}
			}
		}
		
		return false;
	}
}
