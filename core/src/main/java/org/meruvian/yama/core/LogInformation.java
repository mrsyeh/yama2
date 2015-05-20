
package org.meruvian.yama.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class LogInformation {
	private Date createDate = new Date();
	private Date lastUpdateDate;
	private String createBy;
	private String lastUpdateBy;
	private int activeFlag = ACTIVE;

	public final static int ACTIVE = 1;
	public final static int INACTIVE = 0;

	/**
	 * @return the createDate
	 */
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	@JsonIgnore
	@Column(name = "update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate
	 *            the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the createBy
	 */
	@JsonIgnore
	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the lastUpdateBy
	 */
	@JsonIgnore
	@Column(name = "update_by")
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 * @param lastUpdateBy
	 *            the lastUpdateBy to set
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * @return the activeFlag
	 */
	@JsonIgnore
	@Column(name = "active_flag")
	public int getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	@JsonIgnore
	@Transient
	public boolean isActive() {
		return (getActiveFlag() == ACTIVE);
	}

	@JsonIgnore
	@Transient
	public boolean isInactive() {
		return (getActiveFlag() == INACTIVE);
	}
}
