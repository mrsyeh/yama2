
package org.meruvian.yama.core;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public class DefaultPersistence {
	protected String id;
	protected LogInformation logInformation = new LogInformation();

	@Id()
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Embedded
	public LogInformation getLogInformation() {
		return logInformation;
	}

	public void setLogInformation(LogInformation logInformation) {
		this.logInformation = logInformation;
	}
}
