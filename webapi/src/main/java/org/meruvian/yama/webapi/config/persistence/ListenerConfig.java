
package org.meruvian.yama.webapi.config.persistence;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ListenerConfig {
	@Bean
	public EventListenerRegistry eventListenerRegistry(HibernateEntityManagerFactory emf) {
		SessionFactoryImpl factory = (SessionFactoryImpl) emf.getSessionFactory();
		
		return factory.getServiceRegistry().getService(EventListenerRegistry.class);
	}
}
