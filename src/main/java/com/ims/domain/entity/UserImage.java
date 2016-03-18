package com.ims.domain.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "user_image")
public class UserImage extends AbstractEntity {

	private static final long serialVersionUID = 7104999343017220223L;

	private String acutualImage;

	private String thumbnailImage;

	@Lob
	public String getAcutualImage() {
		return acutualImage;
	}

	public void setAcutualImage(String acutualImage) {
		this.acutualImage = acutualImage;
	}

	@Lob
	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

}
