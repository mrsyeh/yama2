
package org.meruvian.yama.core.commons;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Address {
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;

	@Column(name = "address_street1")
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	@Column(name = "address_street2")
	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	@Column(name = "address_city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "address_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "address_zip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}