package com.comp132.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.comp132.componets.Session;
import com.comp132.componets.SessionOperator;
import com.comp132.componets.User;
import com.comp132.componets.UserOperator;
import com.comp132.helper.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	/*
	 * An array list holds all user objects
	 */
	ArrayList<User> users = new ArrayList<User>();

	/**
	 * User data operator
	 */
	UserOperator useroperator = new UserOperator();

	/**
	 * The signedin user
	 */

	private User user;

	/**
	 * Session data operator
	 */
	SessionOperator sessionOperator = new SessionOperator();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSessionName;
	private JTable tableUsers; // leaderboard
	private JComboBox<String> cbNumPlayer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main(null);
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
	public Main(User user) {
		Logger.writeLog("Main starts...");
		this.user = user;

		// get user data
		users = useroperator.getUserData();

		// By clicking on a specific user on the leaderboard, the user must see all the
		// tracked statistics on that specific user
		int total_wins = 0, total_loss = 0, total_score = 0;
		// get data for user table
		String[] columnNames = { "Username", "Total Score" };

		// get user data into 2 dimensional array
		Object[][] data = new Object[users.size()][2];
		for (int i = 0; i < users.size(); i++) {
			User _user = users.get(i);
			data[i][0] = _user.getName();
			data[i][1] = _user.getScore();

			total_wins += _user.getWins();
			total_loss += _user.getLoses();
			total_score += _user.getScore();
		}

		// Setting up statistics
		double wlRatio = 0.0;
		double avegScore = 0.0;
		try {
			wlRatio = total_wins / total_loss;
			avegScore = total_score / users.size();
		} catch (Exception e) {
			Logger.writeLog(e.getMessage());
		}

		setTitle("R UNO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ToolTip.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LEADER BOARD");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 145, 149, 24);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(331, 180, 243, 209);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextArea tfWinsLoses = new JTextArea();
		tfWinsLoses.setEditable(false);
		tfWinsLoses.setText("");
		tfWinsLoses.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tfWinsLoses.setBounds(143, 45, 84, 22);
		panel.add(tfWinsLoses);

		JTextArea tfTotalGamel = new JTextArea();
		tfTotalGamel.setText("");
		tfTotalGamel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tfTotalGamel.setEditable(false);
		tfTotalGamel.setBounds(143, 73, 84, 22);
		panel.add(tfTotalGamel);

		JTextArea tfScore = new JTextArea();
		tfScore.setText("");
		tfScore.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tfScore.setEditable(false);
		tfScore.setBounds(143, 102, 84, 22);
		panel.add(tfScore);

		tableUsers = new JTable(data, columnNames);
		tableUsers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableUsers.getColumnModel().getColumn(0).setPreferredWidth(80);
		// show the tracked statistics
		tableUsers.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					String userIn = tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString();

					for (User user : users) {
						if (user.getName().equals(userIn)) {
							Logger.writeLog("Update user statistic: ".concat(user.getName()));
							tfWinsLoses.setText(Integer.toString(user.getLoses()));
							tfTotalGamel.setText(Integer.toString(user.getWins()));
							tfScore.setText(Integer.toString(user.getScore()));
							break;
						}
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tableUsers);
		scrollPane.setBounds(10, 180, 265, 209);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("Wins/Losses :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 48, 97, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Total Played Games :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 76, 123, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Total Score :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(10, 105, 97, 14);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("User Statistics");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 23, 97, 14);
		panel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_1_1 = new JLabel("Average Score :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(10, 150, 123, 14);
		panel.add(lblNewLabel_1_1_1);

		JTextArea tfAveScore = new JTextArea();
		tfAveScore.setText(Double.toString(avegScore));
		tfAveScore.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tfAveScore.setEditable(false);
		tfAveScore.setBounds(143, 147, 84, 22);
		panel.add(tfAveScore);

		JTextArea tfWLRatio = new JTextArea();
		tfWLRatio.setText(Double.toString(wlRatio));
		tfWLRatio.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tfWLRatio.setEditable(false);
		tfWLRatio.setBounds(143, 176, 84, 22);
		panel.add(tfWLRatio);

		JLabel lblNewLabel_1_2_1 = new JLabel("Win/Loss Ratio :");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(10, 179, 97, 14);
		panel.add(lblNewLabel_1_2_1);

		JButton bContPrevGame = new JButton("Continue The Previous Game");
		bContPrevGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Users can continue an existing game saved
				// beforehand with a specified session name
				startoverLastGame();
			}
		});
		bContPrevGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bContPrevGame.setBounds(309, 93, 265, 48);
		contentPane.add(bContPrevGame);

		JButton bNewGame = new JButton("The New Game");
		bNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create a new game on the same page
				createNewSession();
			}
		});
		bNewGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bNewGame.setBounds(309, 11, 265, 48);
		contentPane.add(bNewGame);

		cbNumPlayer = new JComboBox<String>();
		cbNumPlayer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbNumPlayer.addItem("2");
		cbNumPlayer.addItem("3");
		cbNumPlayer.addItem("4");
		cbNumPlayer.addItem("5");
		cbNumPlayer.addItem("6");
		cbNumPlayer.addItem("7");
		cbNumPlayer.addItem("8");
		cbNumPlayer.addItem("9");
		cbNumPlayer.addItem("103");
		cbNumPlayer.setBounds(186, 35, 99, 24);
		contentPane.add(cbNumPlayer);

		JLabel lblNewLabel_2 = new JLabel("The # of Players");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(186, 11, 105, 26);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Session Name");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 11, 105, 26);
		contentPane.add(lblNewLabel_2_1);

		tfSessionName = new JTextField();
		tfSessionName.setText("aaaaa");
		tfSessionName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfSessionName.setBounds(10, 35, 160, 24);
		contentPane.add(tfSessionName);
		tfSessionName.setColumns(10);
	}

	/**
	 * start the last session of the user
	 */
	protected void startoverLastGame() {
		Logger.writeLog("Starting old session...");
		TreeMap<String, Session> sessions = new TreeMap<String, Session>();
		sessions = sessionOperator.getSessionData();
		Session session = null;

		for (String username : sessions.descendingKeySet()) {
			if (user.getName().equals(username)) {
				session = sessions.get(username);
			}
		}

		if (session == null) {
			Logger.writeLog("No old session for user: ".concat(user.getName()));
			JOptionPane.showMessageDialog(null, "No session is found for this user '" + user.getName() + "'.");
		} else {
			goGameSessionFrame(session);
		}
	}

	/**
	 * creates a new session and store to the session file
	 */
	protected void createNewSession() {
		Logger.writeLog("Starting new session...");
		// a session name
		if (tfSessionName.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please, write a session name.");
			return;
		}

		// the number of players between 2 and 10
		String numUser = (String) cbNumPlayer.getSelectedItem();
		if (numUser.isBlank()) {
			JOptionPane.showMessageDialog(null, "Please, select the number of player (2-10).");
			return;
		}

		Session session = new Session(tfSessionName.getText(), user, Integer.parseInt(numUser));
		goGameSessionFrame(session);
	}

	/**
	 * goes to the game
	 * 
	 * @param session
	 */
	private void goGameSessionFrame(Session session) {
		GameSession frame = new GameSession(session);
		frame.setVisible(true);
		setVisible(false);
		dispose();
	}
}
