package com.ims.domain.cfg;

import com.ims.domain.entity.AbstractEntity;
import com.ims.util.AppUtil;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.util.Calendar;

public class PersistenceListener {

	@PrePersist
	void onPreCreate(Object entity) {
		AbstractEntity baseEntity = (AbstractEntity) entity;
		if (baseEntity.getCreatedTime() == null)
			baseEntity.setCreatedTime(Calendar.getInstance());
		baseEntity.setChangedTime(Calendar.getInstance());
		if (baseEntity.getCreatedBy() == null || baseEntity.getCreatedBy().length() <= 0) {
			String createdBy = AppUtil.getCurrentLoggedinUser();
			baseEntity.setCreatedBy(createdBy);
			baseEntity.setChangedBy(createdBy);
		} else {
			baseEntity.setChangedBy(baseEntity.getCreatedBy());
		}

		if (baseEntity.getDeletedFlag() == null) {
			baseEntity.setDeletedFlag(false);
		}
	}

	@PreUpdate
	void onPreUpdate(Object entity) {
		AbstractEntity baseEntity = (AbstractEntity) entity;
		baseEntity.setChangedTime(Calendar.getInstance());
		String changedBy = AppUtil.getCurrentLoggedinUser();

		baseEntity.setChangedBy(changedBy);

		if (baseEntity.getDeletedFlag() == null) {
			baseEntity.setDeletedFlag(false);
		}
	}

}