package main.java.com.trello.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import main.java.com.trello.manager.dto.Board;
import main.java.com.trello.manager.dto.Card;
import main.java.com.trello.manager.dto.SubProject;
import main.java.com.trello.manager.dto.User;
import main.java.com.trello.manager.enums.PrivacyType;

public class ProjectManager {

	private static String baseUrl = "https://trello.com/boards?id=";

	Map<String, Board> boards;
	Map<String, SubProject> subProjects;
	Map<String, Card> cards;
	Map<String, User> users;

	public ProjectManager() {
		boards = new HashMap<String, Board>();
		subProjects = new HashMap<String, SubProject>();
		cards = new HashMap<String, Card>();
		users = new HashMap<String, User>();
	}

	public void addUser(String id, String name, String email) {
		User user = new User(id, name, email);

		users.put(id, user);
	}

	public void addUser(User user) {
		users.put(user.getId(), user);
	}

	public String createBoard(String name, PrivacyType privacy) {
		Board board = new Board();

		String boardId = UUID.randomUUID().toString();

		board.setId(boardId);
		board.setUrl(baseUrl + boardId);
		board.setName(name);
		board.setPrivacy(privacy == null ? PrivacyType.PUBLIC : privacy);
		board.setSubProjectIds(new HashSet<String>());
		board.setUserIds(new HashSet<String>());

		boards.put(boardId, board);

		System.out.println("Created board : " + boardId);

		return boardId;
	}

	public void updateBoardAttributes(String boardId, String name, PrivacyType privacy) {
		Board board = boards.get(boardId);

		if (name != null) {
			board.setName(name);
		}
		if (privacy != null) {
			board.setPrivacy(privacy);
		}
		return;
	}

	public void showBoard(String boardId) {
		if (boards.get(boardId) == null) {
			System.out.println("Board " + boardId + " does not exist");
			return;
		}
		StringBuilder sb = appendBoardInString(boardId);

		System.out.println(sb);
		return;
	}

	private StringBuilder appendBoardInString(String boardId) {
		StringBuilder sb = new StringBuilder();
		Board board = boards.get(boardId);
		if (board == null) {
			return sb;
		}

		sb.append(board.toString()).append(",lists = [");

		for (String subProjectId : board.getSubProjectIds()) {
			sb.append(appendSubProjectInString(subProjectId));
		}

		sb.append("]");

		// add users
		sb.append(",members = [");

		for (String userId : board.getUserIds()) {
			sb.append(users.get(userId).toString());
		}

		sb.append("]}");

		return sb;
	}

	private StringBuilder appendSubProjectInString(String subProjectId) {
		StringBuilder sb = new StringBuilder();
		SubProject subProject = subProjects.get(subProjectId);

		if (subProject == null) {
			return sb;
		}

		sb.append(subProject).append(", cards = [");

		for (String cardId : subProject.getCardIds()) {
			sb.append(appendCardInString(cardId));
		}

		sb.append("]}");
		return sb;
	}

	private StringBuilder appendCardInString(String cardId) {
		StringBuilder sb = new StringBuilder();
		String userId = cards.get(cardId).getAssignedUserId();

		if (cards.get(cardId) == null) {
			return sb;
		}

		sb.append(cards.get(cardId));
		sb.append(",assignedUser = ");
		sb.append(userId == null ? "Unassigned" : users.get(userId));
		
		sb.append("}");

		return sb;
	}

	public void showBoards() {
		if (boards.isEmpty()) {
			System.out.println("No Boards");
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (Entry<String, Board> board : boards.entrySet()) {
			sb.append(appendBoardInString(board.getKey()));
		}

		sb.append("]");
		System.out.println(sb);
		return;
	}

	public void addMember(String boardId, String userId) {
		Board board = boards.get(boardId);
		Set<String> userIds = board.getUserIds();
		userIds.add(userId);
		board.setUserIds(userIds);
		return;
	}

	public void removeMember(String boardId, String userId) {
		Board board = boards.get(boardId);
		Set<String> userIds = board.getUserIds();
		userIds.remove(userId);
		board.setUserIds(userIds);
		return;
	}

	public void deleteBoard(String boardId) {
		Board board = boards.get(boardId);

		for (String subProjectId : board.getSubProjectIds()) {
			deleteList(subProjectId);
		}

		boards.remove(boardId);
	}

	public String createList(String boardId, String name) {
		SubProject subProject = new SubProject();

		String subProjectId = UUID.randomUUID().toString();

		subProject.setId(subProjectId);
		subProject.setName(name);
		subProject.setCardIds(new HashSet<String>());
		subProjects.put(subProjectId, subProject);

		Board board = boards.get(boardId);
		Set<String> subProjectIds = board.getSubProjectIds();
		subProjectIds.add(subProjectId);
		board.setSubProjectIds(subProjectIds);

		System.out.println("Created list : " + subProjectId);
		return subProjectId;
	}

	public void updateListAttribute(String subProjectId, String name) {
		SubProject subProject = subProjects.get(subProjectId);
		subProject.setName(name);
	}

	public void deleteList(String subProjectId) {
		// TODO: need to delete this list from board too
		SubProject subProject = subProjects.get(subProjectId);

		for (String cardId : subProject.getCardIds()) {
			deleteCard(cardId);
		}

		subProjects.remove(subProjectId);
	}

	public void showList(String subProjectId) {
		if (subProjects.get(subProjectId) == null) {
			System.out.println("List " + subProjectId + " does not exist");
			return;
		}
		StringBuilder sb = appendSubProjectInString(subProjectId);

		System.out.println(sb);
		return;
	}

	public String createCard(String subProjectId, String name, String description) {
		Card card = new Card();

		String cardId = UUID.randomUUID().toString();

		card.setId(cardId);
		card.setName(name);
		card.setDescription(description);
		card.setAssignedUserId(null);
		cards.put(cardId, card);

		SubProject subProject = subProjects.get(subProjectId);
		Set<String> cardIds = subProject.getCardIds();
		cardIds.add(cardId);
		subProject.setCardIds(cardIds);

		System.out.println("Created card : " + cardId);
		return cardId;

	}

	public void updateCardAttributes(String cardId, String name, String description) {
		Card card = cards.get(cardId);

		if (name != null) {
			card.setName(name);
		}
		if (description != null) {
			card.setDescription(description);
		}
		return;
	}

	public void deleteCard(String cardId) {
		// TODO: need to remove this card from list too
		cards.remove(cardId);
		return;
	}

	public void showCard(String cardId) {
		if (cards.get(cardId) == null) {
			System.out.println("Card " + cardId + " does not exist");
			return;
		}
		StringBuilder sb = appendCardInString(cardId);

		System.out.println(sb);
		return;
	}

	public void assignCard(String cardId, String userId) {
		// for unassignment we will pass null in the same method
		Card card = cards.get(cardId);

		card.setAssignedUserId(userId);
		return;
	}

	public void moveCard(String cardId, String newSubProjectId) {
		// TODO: optimize
		SubProject newSubProject = subProjects.get(newSubProjectId);
		Set<String> cardIds = newSubProject.getCardIds();
		cardIds.add(cardId);
		newSubProject.setCardIds(cardIds);

		for (Entry<String, SubProject> subProject : subProjects.entrySet()) {
			if (subProject.getValue().getCardIds().contains(cardId)) {
				subProject.getValue().getCardIds().remove(cardId);
				break;
			}
		}
	}
}
