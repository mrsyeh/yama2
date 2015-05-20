
package org.meruvian.yama.core.commons;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Name {
	private String prefix;
	private String first;
	private String middle;
	private String last;

	@Column(name = "name_prefix")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "name_first")
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	@Column(name = "name_middle")
	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	@Column(name = "name_last")
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
}