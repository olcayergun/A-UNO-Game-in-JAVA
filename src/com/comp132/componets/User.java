package com.comp132.componets;

import java.util.ArrayList;

import com.comp132.componets.game.card.Card;

/**
 * User class
 */
public class User {
	public User(String name, String password, int score, int wins, int loses, int games) {
		super();
		this.name = name;
		this.password = password;
		this.score = score;
		this.wins = wins;
		this.loses = loses;
		this.games = games;

		hand.clear();
	}

	/*
	 * The name of the user
	 */
	private String name;

	/*
	 * The name of the user
	 */
	private String password;

	/*
	 * The total score of the user
	 */
	private int score;

	/*
	 * The wins amount of the user
	 */
	private int wins;

	/*
	 * The loses amount of the user
	 */
	private int loses;

	/*
	 * The games played amount of the user
	 */
	private int games;

	private ArrayList<Card> hand = new ArrayList<Card>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	/**
	 * Returns a string that includes the user info
	 * @return string of a user
	 */
	public String getStringFromUser() {
		return getName() + ";" + getPassword() + ";" + Integer.toString(getScore()) + ";" + Integer.toString(getWins())
				+ ";" + Integer.toString(getLoses()) + ";" + Integer.toString(getGames());
	}

	/**
	 * Create an user object from string
	 * @param s string that holds user's info.
	 * @return
	 */
	public static User getUserFromString(String s) {
		String ss[] = s.split(";");
		if (ss.length < 6) {
			return null;
		}
		User user = new User(ss[0], ss[1], Integer.parseInt(ss[2]), Integer.parseInt(ss[3]), Integer.parseInt(ss[4]),
				Integer.parseInt(ss[5]));
		return user;
	}
}
