package com.comp132.componets;

import java.util.ArrayList;

import com.comp132.helper.FileHelper;
import com.comp132.helper.Logger;

/**
 * All user operation are take place in this class
 */
public class UserOperator {

	/*
	 * Handles user file operations
	 */
	FileHelper helper = new FileHelper("users.txt");

	/**
	 * Get the user data
	 */
	public ArrayList<User> getUserData() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			ArrayList<String> userlines = helper.readAllLines();

			for (String userline : userlines) {
				User user = getUserFromString(userline);
				users.add(user);
			}
		} catch (Exception e) {
			Logger.writeLog("An exception while getting all user data.");
			Logger.writeLog(e.getMessage());
		}
		Logger.writeLog("All user data is gotten");
		return users;
	}

	/**
	 * Save users data
	 */
	public void saveUserData(ArrayList<User> users) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			for (User user : users) {
				String s = user.getStringFromUser();
				lines.add(s);
			}

			helper.writeAllLines(lines);
		} catch (Exception e) {
			Logger.writeLog("An exception while writing all user data.");
			Logger.writeLog(e.getMessage());
		}
		Logger.writeLog("All user data is write.");
	}

	/**
	 * Check if the user is valid
	 * 
	 * @param users the user list in the system
	 * @param user  in the user in question
	 * @return true if the user is valid, otherwise false
	 */
	public boolean signinUser(ArrayList<User> users, User userin) {
		boolean yesUser = false;
		try {
			for (User user : users) {
				if (user.getName().equals(userin.getName()) && user.getPassword().equals(userin.getPassword())) {
					yesUser = true;
				}
			}

		} catch (Exception e) {
			Logger.writeLog("An exception while checking all user data.");
			Logger.writeLog(e.getMessage());
		}
		Logger.writeLog("User checking is done. (" + Boolean.toString(yesUser) + ")");
		return yesUser;
	}

	/**
	 * Construct a user object from string
	 * 
	 * @param str String reprenstation of UUser object
	 * @return user object
	 */
	public static User getUserFromString(String str) {
		String[] ss = str.split(";");
		User user = new User(ss[0], ss[1], Integer.parseInt(ss[2]), Integer.parseInt(ss[3]), Integer.parseInt(ss[4]),
				Integer.parseInt(ss[5]));
		return user;
	}

	/**
	 * Updates or Insert a user First search userUpdate in the user list if it
	 * found, deleted Finally add userUpdate ans save in the file
	 * 
	 * @param userUpdate
	 */
	public void updateUserData(User userUpdate) {
		ArrayList<User> users = getUserData();

		for (User user : users) {
			if (user.getName().equals(userUpdate.getName())) {
				users.remove(user);
				break;
			}
		}
		users.add(userUpdate);

		saveUserData(users);
	}
}
