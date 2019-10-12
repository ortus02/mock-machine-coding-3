package main.java.com.trello.manager.dto;

public class User {
	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	private String id;
	private String name;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{id = ");
		builder.append(id);
		builder.append(", name = ");
		builder.append(name);
		builder.append(", email = ");
		builder.append(email);
		builder.append("}");
		return builder.toString();
	}

}
