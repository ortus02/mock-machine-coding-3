package main.java.com.trello.manager.dto;

import java.util.Set;

public class SubProject {
	private String id;
	private String name;
	private Set<String> cardIds;

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

	public Set<String> getCardIds() {
		return cardIds;
	}

	public void setCardIds(Set<String> cardIds) {
		this.cardIds = cardIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{id = ");
		builder.append(id);
		builder.append(", name = ");
		builder.append(name);
		return builder.toString();
	}

}
