package edu.buffalo.ktmb.bean;

import org.apache.solr.client.solrj.beans.Field;

public class AdResult {

	@Field
	private String id;
	
	@Field	
	private String title;
	
	@Field
	private String content;

	@Field
	private String description;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
