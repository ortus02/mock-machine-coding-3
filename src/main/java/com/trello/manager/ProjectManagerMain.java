package main.java.com.trello.manager;

import main.java.com.trello.manager.dto.User;
import main.java.com.trello.manager.enums.PrivacyType;

public class ProjectManagerMain {
	public static void main(String[] args) {
		ProjectManager projectManager = new ProjectManager();
		
		User user1 = new User("user1", "Gaurav Chandak", "gaurav@workat.tech");
		User user2 = new User("user2", "User2", "user2@gmail.com");
		User user3 = new User("user3", "Sagar Jain", "sagar@workat.tech");
		projectManager.addUser(user1);
		projectManager.addUser(user2);
		projectManager.addUser(user3);

//		SHOW
		projectManager.showBoards();
//		BOARD CREATE work@tech
		String boardId1 = projectManager.createBoard("work@tech", null);
//		SHOW BOARD 5da1583ec25d2a7e246b0375
		projectManager.showBoard(boardId1);
//		SHOW
		projectManager.showBoards();
//		BOARD 5da1583ec25d2a7e246b0375 name workat.tech
		projectManager.updateBoardAttributes(boardId1, "workat.tech", null);
//		BOARD 5da1583ec25d2a7e246b0375 privacy PRIVATE
		projectManager.updateBoardAttributes(boardId1, "workat.tech", PrivacyType.PRIVATE);
//		SHOW BOARD 5da1583ec25d2a7e246b0375
		projectManager.showBoard(boardId1);
//		BOARD CREATE workat
		String boardId2 = projectManager.createBoard("workat", null);
//		SHOW
		projectManager.showBoards();
//		BOARD 5da1583ec25d2a7e246b0375 ADD_MEMBER user1
		projectManager.addMember(boardId1, user1.getId());
//		BOARD 5da1583ec25d2a7e246b0375 ADD_MEMBER user2
		projectManager.addMember(boardId1, user2.getId());
//		BOARD 5da1583ec25d2a7e246b0375 ADD_MEMBER user3
		projectManager.addMember(boardId1, user3.getId());
//		BOARD 5da1583ec25d2a7e246b0375 REMOVE_MEMBER user2
		projectManager.removeMember(boardId1, user2.getId());
//		SHOW BOARD 5da1583ec25d2a7e246b0375
		projectManager.showBoard(boardId1);
//		BOARD DELETE 5da1586caaaad00d9b2d7aa6
		projectManager.deleteBoard(boardId2);
//		SHOW BOARD 5da1586caaaad00d9b2d7aa6
		projectManager.showBoard(boardId2);
//		SHOW
		projectManager.showBoards();
//		LIST CREATE 5da1583ec25d2a7e246b0375 Mock Interviews
		String listId1 = projectManager.createList(boardId1, "Mock Interviews");
//		SHOW LIST 5da1583547c78c15a1408df2
		projectManager.showList(listId1);
//		LIST 5da1583547c78c15a1408df2 name Mock Interviews - Applied
		projectManager.updateListAttribute(listId1, "Mock Interviews - Applied");
//		SHOW LIST 5da1583547c78c15a1408df2
		projectManager.showList(listId1);
//		LIST CREATE 5da1583ec25d2a7e246b0375 Mock Interviews - Scheduled
		String listId2 = projectManager.createList(boardId1, "Mock Interviews - Scheduled");
//		SHOW BOARD 5da1583ec25d2a7e246b0375
		projectManager.showBoard(boardId1);
//		CARD CREATE 5da1583547c78c15a1408df2 abcd@gmail.com
		String cardId1 = projectManager.createCard(listId1, "abcd@gmail.com", null);
//		CARD CREATE 5da1583547c78c15a1408df2 abcda@gmail.com
		String cardId2 = projectManager.createCard(listId1, "abcda@gmail.com", null);
//		SHOW LIST 5da1583547c78c15a1408df2
		projectManager.showList(listId1);
//		CARD 5da1583547c78c15a14kj78g name abcde@gmail.com
		String cardId3 = projectManager.createCard(listId2, "abcde@gmail.com", null);
//		CARD 5da1583547c78c15a14kj78g description At 7PM
		projectManager.updateCardAttributes(cardId2, null, "description At 7PM");
//		SHOW LIST 5da1583547c78c15a1408df2
		projectManager.showList(listId1);
//		CARD 5da1583547c78c15a14kjsd8 ASSIGN gaurav@workat.tech
		projectManager.assignCard(cardId1, user1.getId());
//		SHOW CARD 5da1583547c78c15a14kjsd8
		projectManager.showCard(cardId1);
//		CARD 5da1583547c78c15a14kjsd8 MOVE 5da1583547c78c15a143hj34
		projectManager.moveCard(cardId1, listId2);
//		SHOW LIST 5da1583547c78c15a1408df2
		projectManager.showList(listId1);
//		SHOW LIST 5da1583547c78c15a143hj34
		projectManager.showList(listId2);
//		CARD 5da1583547c78c15a14kjsd8 UNASSIGN
		projectManager.assignCard(cardId1, null);
//		SHOW CARD 5da1583547c78c15a14kjsd8
		projectManager.showCard(cardId1);
//		SHOW
		projectManager.showBoards();
	}
}
