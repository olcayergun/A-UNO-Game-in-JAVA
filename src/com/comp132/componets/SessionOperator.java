package com.comp132.componets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.comp132.componets.game.Deck;
import com.comp132.componets.game.card.Card;
import com.comp132.componets.game.card.Card.CardColor;
import com.comp132.helper.FileHelper;
import com.comp132.helper.Logger;

/**
 * All user operation are take place in this class
 */
public class SessionOperator {

	/*
	 * Handles user file operations
	 */
	FileHelper helper = new FileHelper("session.txt");

	/**
	 * Get the user data
	 */
	public TreeMap<String, Session> getSessionData() {
		TreeMap<String, Session> sesions = new TreeMap<String, Session>();
		try {
			ArrayList<String> sessionlines = helper.readAllLines();

			for (String userline : sessionlines) {
				String[] ss = userline.split(";s;");
				User user = UserOperator.getUserFromString(ss[1]);
				Session session = new Session(ss[0], user, 0);
				sesions.put(user.getName(), session);
			}

			for (int i = 0; i < sessionlines.size(); i++) {
				String line = sessionlines.get(i);
				if ("BEGIN_SESSION".equals(line)) {
					i++;

					String name = sessionlines.get(i++);
					int numofcomputerUser = Integer.parseInt(sessionlines.get(i++));
					CardColor gameColor = CardColor.getCardColorFromString(sessionlines.get(i++));
					int gamePointer = Integer.parseInt(sessionlines.get(i++));
					Boolean gameDirection = Boolean.parseBoolean(sessionlines.get(i++));
					int userCardPosition = Integer.parseInt(sessionlines.get(i++));
					Card openedCard = Card.getCardFromString(sessionlines.get(i++));
					User user = User.getUserFromString(sessionlines.get(i++));
					Deck deck = Deck.getDeckFromString(sessionlines.get(i++));

					HashMap<Integer, User> computerUsers = new HashMap<Integer, User>();
					for (int j = 0; j < numofcomputerUser; j++) {
						String sUser = sessionlines.get(i++);
						String ss[] = sUser.split(":");
						User userComputer = User.getUserFromString(ss[1]);
						computerUsers.put(Integer.parseInt(ss[0]), userComputer);
					}

					if ("END_SESSION".equals(sessionlines.get(i++))) {
						Session session = new Session(name, user, numofcomputerUser, computerUsers, deck, gameColor,
								gamePointer, gameDirection, userCardPosition, openedCard);
						sesions.put(name, session);
					}
				}
			}
		} catch (Exception e) {
			Logger.writeLog("An exception while getting all session data.");
			Logger.writeLog(e.getMessage());
		}
		Logger.writeLog("All sessions data is gotten");
		return sesions;
	}

	/**
	 * Save users data
	 */
	public void saveSessionData(Session sessionUpdate) {

		TreeMap<String, Session> sessions = getSessionData();
		Session session = sessions.get(sessionUpdate.getName());
		if (session != null) {
			sessions.remove(sessionUpdate.getName());
		}

		sessions.put(sessionUpdate.getName(), sessionUpdate);

		ArrayList<String> lines = new ArrayList<String>();
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			Session value = entry.getValue();
			lines.add(value.getSessionDataInString());
		}

		try {
			helper.writeAllLines(lines);
		} catch (Exception e) {
			Logger.writeLog("An exception while writing all sessions data.");
			Logger.writeLog(e.getMessage());
		}
		Logger.writeLog("All sessions data is write.");
	}

}
