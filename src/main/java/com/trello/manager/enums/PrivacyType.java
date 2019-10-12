package main.java.com.trello.manager.enums;

public enum PrivacyType {
	PRIVATE((byte) 1, "Private"), PUBLIC((byte) 2, "Public");

	private byte id;
	private String displayName;

	private PrivacyType(byte id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
