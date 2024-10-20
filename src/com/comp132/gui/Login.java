package com.comp132.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.comp132.componets.User;
import com.comp132.componets.UserOperator;
import com.comp132.helper.Logger;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;

	/*
	 * An array list holds all user objects
	 */
	ArrayList<User> users = new ArrayList<User>();

	/**
	 * User data operator
	 */
	UserOperator useroperator = new UserOperator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logger.deleteLogFile();
					Logger.writeLog("Start");
					
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		Logger.writeLog("Login starts...");
		setTitle("R UNO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setBounds(100, 100, 688, 471);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ToolTip.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lNewLabel = new JLabel("Username: ");
		lNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lNewLabel.setBounds(42, 106, 152, 14);
		contentPane.add(lNewLabel);

		tfUsername = new JTextField();
		tfUsername.setText("a");
		tfUsername.setBounds(118, 103, 225, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(42, 142, 152, 14);
		contentPane.add(lblPassword);

		tfPassword = new JTextField();
		tfPassword.setText("a");
		tfPassword.setColumns(10);
		tfPassword.setBounds(118, 139, 225, 20);
		contentPane.add(tfPassword);

		JButton bLogin = new JButton("LOGIN");
		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signinTheNewUser();
			}
		});
		bLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bLogin.setBounds(42, 186, 89, 23);
		contentPane.add(bLogin);

		JButton bNewUser = new JButton("CREATE NEW USER");
		bNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupTheNewUser();
			}
		});
		bNewUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bNewUser.setBounds(42, 260, 203, 23);
		contentPane.add(bNewUser);

		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOr.setBounds(70, 231, 31, 14);
		contentPane.add(lblOr);

		// get user data
		users = useroperator.getUserData();
	}

	/**
	 * store the new user if already sign not up
	 */
	protected void signupTheNewUser() {
		String username = tfUsername.getText();
		if (username.isBlank()) {
			JOptionPane.showMessageDialog(null, "Please, write a player name.");
			return;
		}
		if (tfPassword.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please, write a player password.");
			return;
		}

		// Users can create accounts with unique usernames and passwords
		boolean canAdd = true;
		for (User user : users) {
			if (username.equals(user.getName())) {
				canAdd = false;
				break;
			}
		}

		if (canAdd) {
			User user = new User(username, tfPassword.getText(), 0, 0, 0, 0);
			users.add(user);
			useroperator.saveUserData(users);

			Logger.writeLog("A new user is added.");
			goMainFrame(user);
		} else {
			Logger.writeLog("A new user is duplicated.");
			JOptionPane.showMessageDialog(this, "The usernaem is already exists!!!.", "Duplicated username",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * check the new user can singui
	 * Existing users can log in with their username and password to access the game.
	 */
	protected void signinTheNewUser() {
		if (tfUsername.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please, write a player name.");
			return;
		}
		if (tfPassword.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please, write a player password.");
			return;
		}

		User user = new User(tfUsername.getText(), tfPassword.getText(), 0, 0, 0, 0);

		if (useroperator.signinUser(users, user)) {
			Logger.writeLog("A new user signed in.(" + user.getName() + ")");
			goMainFrame(user);
		} else {
			Logger.writeLog("A new user NOT signed in.(" + user.getName() + ")");
			JOptionPane.showMessageDialog(null, "Cannot sign in.");
		}
	}

	private void goMainFrame(User user) {
		Logger.writeLog("Going to Main Frame...");
		Main frame = new Main(user);
		frame.setVisible(true);
		setVisible(false);
		dispose();
	}
}
