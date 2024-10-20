package com.comp132.componets;

import java.util.HashMap;
import java.util.Map;

import com.comp132.componets.game.Deck;
import com.comp132.componets.game.card.Card;
import com.comp132.componets.game.card.Card.CardColor;

/**
 * All session operations handles here
 */
public class Session {
	/**
	 * The name of session
	 */
	private String name;

	/*
	 * The USer player
	 */
	private User user;

	/**
	 * The number of computer users
	 */
	private int numofcomputerUser;

	/**
	 * the other player’s names with their card count on their hands which is keep
	 * in a user object
	 */
	private HashMap<Integer, User> computerUsers = new HashMap<Integer, User>();

	/**
	 * The Deck
	 */
	private Deck deck;

	/**
	 * The game direction
	 */
	private boolean gameDirection = true;

	/**
	 * the game color
	 */
	private CardColor gameColor;

	/**
	 * the pointer for who is playing
	 */
	private int gamePointer = 1;

	/**
	 * start position of the user's hand to show
	 */
	private int userCardPosition = 0;

	/**
	 * The opened card on the deck
	 */
	private Card openedCard;

	public Session(String name, User user, int numofcomputerUser, HashMap<Integer, User> computerUsers, Deck deck,
			CardColor gameColor, int gamePointer, boolean gameDirection, int userCardPosition, Card openedCard) {
		super();
		this.name = name;
		this.user = user;
		this.numofcomputerUser = numofcomputerUser;
		this.computerUsers = computerUsers;
		this.deck = deck;
		this.gameColor = gameColor;
		this.gamePointer = gamePointer;
		this.gameDirection = gameDirection;
		this.userCardPosition = userCardPosition;
		this.openedCard = openedCard;
	}

	public Session(String name, User user, int numofcomputerUser) {
		super();
		this.name = name;
		this.user = user;
		this.numofcomputerUser = numofcomputerUser;
		this.deck = new Deck();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getNumofcomputerUser() {
		return numofcomputerUser;
	}

	public HashMap<Integer, User> getComputerUsers() {
		return computerUsers;
	}

	public void setComputerUsers(HashMap<Integer, User> computerUsers) {
		this.computerUsers = computerUsers;
	}

	public boolean isGameDirection() {
		return gameDirection;
	}

	public void setGameDirection(boolean gameDirection) {
		this.gameDirection = gameDirection;
	}

	public CardColor getGameColor() {
		return gameColor;
	}

	public void setGameColor(CardColor gameColor) {
		this.gameColor = gameColor;
	}

	public int getGamePointer() {
		return gamePointer;
	}

	public void setGamePointer(int gamePointer) {
		this.gamePointer = gamePointer;
	}

	public int getUserCardPosition() {
		return userCardPosition;
	}

	public void setUserCardPosition(int userCardPosition) {
		this.userCardPosition = userCardPosition;
	}

	public Card getOpenedCard() {
		return openedCard;
	}

	public void setOpenedCard(Card openedCard) {
		this.openedCard = openedCard;
	}

	public void setNumofcomputerUser(int numofcomputerUser) {
		this.numofcomputerUser = numofcomputerUser;
	}

	//
	/**
	 * Get string form of a session
	 * 
	 * @return session in string
	 */
	public String getSessionDataInString() {
		StringBuffer sb = new StringBuffer();
		sb.append("BEGIN_SESSION\n");

		sb.append(getName()).append("\n");
		sb.append(Integer.toString(getNumofcomputerUser())).append("\n");
		sb.append(CardColor.getStringFromCardColor(getGameColor())).append("\n");
		sb.append(Integer.toString(getGamePointer())).append("\n");
		sb.append(Boolean.toString(isGameDirection())).append("\n");
		sb.append(Integer.toString(getUserCardPosition())).append("\n");
		sb.append(getOpenedCard()).append("\n");
		sb.append(getUser().getStringFromUser()).append("\n");
		sb.append(getDeck().getCardsInString()).append("\n");
		for (Map.Entry<Integer, User> entry : getComputerUsers().entrySet()) {
			User user = entry.getValue();
			int i = entry.getKey();
			sb.append(Integer.toUnsignedLong(i) + ":" + user.getStringFromUser()).append("\n");
		}

		sb.append("END_SESSION\n");

		return sb.toString();
	}
}