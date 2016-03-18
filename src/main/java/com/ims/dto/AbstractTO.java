package com.ims.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AbstractTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected List<String> modifiedFieldList;

	protected String[] ignoredFieldList;

	protected Long pkey;

	protected Boolean deletedFlag = false;

	protected String createdBy;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Calendar createdTime;

	protected String changedBy;

	protected Calendar changedTime;

	protected Integer version;

	public Long getPkey() {
		return pkey;
	}

	public void setPkey(Long pkey) {
		this.pkey = pkey;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy != null)
			this.createdBy = createdBy;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		if (createdTime != null)
			this.createdTime = createdTime;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		if (changedBy != null)
			this.changedBy = changedBy;
	}

	public Calendar getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Calendar changedTime) {
		if (changedTime != null)
			this.changedTime = changedTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<String> getModifiedFieldList() {
		return modifiedFieldList;
	}

	public void setModifiedFieldList(List<String> modifiedFieldList) {
		this.modifiedFieldList = modifiedFieldList;
	}

	public void addModifiedField(String field) {
		if (modifiedFieldList == null) {
			modifiedFieldList = new ArrayList<String>();
		}
		modifiedFieldList.add(field);
	}

	public String[] getIgnoredFieldList() {
		return ignoredFieldList;
	}

	public void setIgnoredFieldList(String[] ignoredFieldList) {
		this.ignoredFieldList = ignoredFieldList;
	}

}