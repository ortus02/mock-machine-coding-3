package main.java.com.trello.manager.dto;

import java.util.Set;

import main.java.com.trello.manager.enums.PrivacyType;

public class Board {
	private String id;
	private String name;
	private PrivacyType privacy;
	private String url;
	private Set<String> userIds;
	private Set<String> subProjectIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PrivacyType getPrivacy() {
		return privacy;
	}

	public void setPrivacy(PrivacyType privacy) {
		this.privacy = privacy;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<String> userIds) {
		this.userIds = userIds;
	}

	public Set<String> getSubProjectIds() {
		return subProjectIds;
	}

	public void setSubProjectIds(Set<String> subProjectIds) {
		this.subProjectIds = subProjectIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{id = ");
		builder.append(id);
		builder.append(", name = ");
		builder.append(name);
		builder.append(", privacy = ");
		builder.append(privacy);
		return builder.toString();
	}
	
	

}
