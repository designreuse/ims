package com.ims.domain.entity;

import com.ims.domain.cfg.PersistenceListener;

import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@EntityListeners(value = { PersistenceListener.class })
@Audited
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long pkey;
	
	protected Boolean deletedFlag = false;
	
	protected String createdBy;
	
	protected Calendar createdTime;
	
	protected String changedBy;
	
	protected Calendar changedTime;
	
	protected Integer version;

	@Id
	@GeneratedValue
	public Long getPkey() {
		return pkey;
	}

	public void setPkey(Long pkey) {
		this.pkey = pkey;
	}

	@NotNull
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	@NotNull
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy != null)
			this.createdBy = createdBy;
	}

	@Column(name = "CREATED_TS")
	@NotNull
	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		if (createdTime != null)
			this.createdTime = createdTime;
	}

	@NotNull
	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		if (changedBy != null)
			this.changedBy = changedBy;
	}

	@Column(name = "CHANGED_TS")
	@NotNull
	public Calendar getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Calendar changedTime) {
		if (changedTime != null)
			this.changedTime = changedTime;
	}

	@Version
	@Column(name = "VERSION")
	@NotNull
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (pkey != null) {
			return pkey.equals(((AbstractEntity) o).getPkey());
		} else { 
			return false;
		}
	}
}