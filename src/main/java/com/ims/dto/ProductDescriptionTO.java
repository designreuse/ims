package com.ims.dto;


public class ProductDescriptionTO {
	private int id;
	private int languageId;
	private String name;
	private String description;
	private String metaDescription;
	private String metaKeyword;
	private String tag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
