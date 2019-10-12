package main.java.com.trello.manager.dto;

public class Card {
	private String id;
	private String name;
	private String description;
	private String assignedUserId;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{id = ");
		builder.append(id);
		builder.append(", name = ");
		builder.append(name);
		builder.append(", description = ");
		builder.append(description);
		builder.append("}");
		return builder.toString();
	}

}
