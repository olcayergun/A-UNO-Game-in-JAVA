package com.comp132.gui;

import com.comp132.componets.Session;
import com.comp132.componets.SessionOperator;
import com.comp132.componets.User;
import com.comp132.componets.UserOperator;
import com.comp132.componets.game.Deck;
import com.comp132.componets.game.card.Card;
import com.comp132.componets.game.card.Card.CardColor;
import com.comp132.componets.game.card.Card.CardType;
import com.comp132.helper.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class GameSession extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private final static Random sourceRandom = new Random();

    /**
     * Saves the current session name, user
     */
    private final Session session;

    /**
     * the other player’s names with their card count on their hands which is keep
     * in a user object
     */
    private final HashMap<Integer, User> computerUsers = new HashMap<>();
    private final JLabel lLeftCard;
    private final JLabel lOpenedCard;
    private final JLabel lPlayer1;
    private final JLabel lPlayer2;
    private final JLabel lPlayer3;
    private final JLabel lPlayer4;
    private final JLabel lPlayer5;
    private final JLabel lPlayer6;
    private final JLabel lPlayer7;
    private final JLabel lPlayer8;
    private final JLabel lPlayer9;
    private final JLabel lCount1;
    private final JLabel lCount2;
    private final JLabel lCount3;
    private final JLabel lCount4;
    private final JLabel lCount5;
    private final JLabel lCount6;
    private final JLabel lCount7;
    private final JLabel lCount8;
    private final JLabel lCount9;
    private final JLabel lPointer1;
    private final JLabel lPointer2;
    private final JLabel lPointer3;
    private final JLabel lPointer4;
    private final JLabel lPointer5;
    private final JLabel lPointer6;
    private final JLabel lPointer7;
    private final JLabel lPointer8;
    private final JLabel lPointer9;
    private final JLabel lUno1;
    private final JLabel lUno2;
    private final JLabel lUno3;
    private final JLabel lUno4;
    private final JLabel lUno5;
    private final JLabel lUno6;
    private final JLabel lUno7;
    private final JLabel lUno8;
    private final JLabel lUno9;
    private final JLabel lMyCard1;
    private final JLabel lMyCard2;
    private final JLabel lMyCard3;
    private final JLabel lMyCard4;
    private final JLabel lMyCard5;
    private final JLabel lMyCard6;
    private final JLabel lMyCard7;
    private final JLabel lMyCard8;
    private final JLabel lGameDirection;
    private final JLabel lGameColor;
    private final JButton bPass;
    private final JButton bNextPlayer;
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
     * start postion of the user's hand to show
     */
    private int userCardPosition = 0;
    /**
     * The opened card on the deck
     */
    private Card openedCard;
    /*
     * Interact to declare “Uno” if the player will have one card in their hand
     * after their play
     */
    private boolean gotOne = false;
    /**
     * Check the user player can pass
     */
    private boolean canPass = false;

    /**
     * Create the frame.
     */
    public GameSession(Session session) {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Logger.writeLog("Saving the game...");
                saveTheSession();
            }
        });

        this.session = session;

        Logger.writeLog("Starting a new game...");

        setResizable(false);
        setTitle("R UNO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setBounds(100, 100, 800, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("ToolTip.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lSessionName = new JLabel(this.session.getName());
        lSessionName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lSessionName.setBounds(10, 11, 157, 26);
        contentPane.add(lSessionName);

        JLabel lblNewLabel = new JLabel("Players");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setBounds(318, 48, 138, 26);
        contentPane.add(lblNewLabel);

        JLabel lblPlayer = new JLabel("Player #1");
        lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer.setBounds(28, 85, 65, 14);
        contentPane.add(lblPlayer);

        JLabel lblPlayer_7 = new JLabel("Player #2");
        lblPlayer_7.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_7.setBounds(111, 85, 65, 14);
        contentPane.add(lblPlayer_7);

        JLabel lblPlayer_1 = new JLabel("Player #3");
        lblPlayer_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_1.setBounds(194, 85, 65, 14);
        contentPane.add(lblPlayer_1);

        JLabel lblPlayer_2 = new JLabel("Player #4");
        lblPlayer_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_2.setBounds(277, 85, 65, 14);
        contentPane.add(lblPlayer_2);

        JLabel lblPlayer_3 = new JLabel("Player #5");
        lblPlayer_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_3.setBounds(360, 85, 65, 14);
        contentPane.add(lblPlayer_3);

        JLabel lblPlayer_4 = new JLabel("Player #6");
        lblPlayer_4.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_4.setBounds(443, 85, 65, 14);
        contentPane.add(lblPlayer_4);

        JLabel lblPlayer_5 = new JLabel("Player #7");
        lblPlayer_5.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_5.setBounds(526, 85, 65, 14);
        contentPane.add(lblPlayer_5);

        JLabel lblPlayer_5_1 = new JLabel("Player #8");
        lblPlayer_5_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_5_1.setBounds(609, 85, 65, 14);
        contentPane.add(lblPlayer_5_1);

        JLabel lblPlayer_5_2 = new JLabel("Player #9");
        lblPlayer_5_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPlayer_5_2.setBounds(692, 85, 65, 14);
        contentPane.add(lblPlayer_5_2);

        lPlayer9 = new JLabel("Name #9");
        lPlayer9.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer9.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer9.setBounds(692, 116, 65, 14);
        contentPane.add(lPlayer9);

        lPlayer8 = new JLabel("Name #8");
        lPlayer8.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer8.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer8.setBounds(609, 116, 65, 14);
        contentPane.add(lPlayer8);

        lPlayer7 = new JLabel("Name #7");
        lPlayer7.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer7.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer7.setBounds(526, 116, 65, 14);
        contentPane.add(lPlayer7);

        lPlayer6 = new JLabel("Name #6");
        lPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer6.setBounds(443, 116, 65, 14);
        contentPane.add(lPlayer6);

        lPlayer5 = new JLabel("Name #5");
        lPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer5.setBounds(360, 116, 65, 14);
        contentPane.add(lPlayer5);

        lPlayer4 = new JLabel("Name #4");
        lPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer4.setBounds(277, 116, 65, 14);
        contentPane.add(lPlayer4);

        lPlayer3 = new JLabel("Name #3");
        lPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer3.setBounds(194, 116, 65, 14);
        contentPane.add(lPlayer3);

        lPlayer2 = new JLabel("Name #2");
        lPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer2.setBounds(111, 116, 65, 14);
        contentPane.add(lPlayer2);

        lPlayer1 = new JLabel(session.getUser().getName());
        lPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        lPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lPlayer1.setBounds(28, 116, 65, 14);
        contentPane.add(lPlayer1);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel.setBounds(27, 103, 65, 2);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_1.setBounds(110, 103, 65, 2);
        contentPane.add(panel_1);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_1_1.setBounds(277, 103, 65, 2);
        contentPane.add(panel_1_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2.setBounds(194, 103, 65, 2);
        contentPane.add(panel_2);

        JPanel panel_1_2 = new JPanel();
        panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_1_2.setBounds(443, 103, 65, 2);
        contentPane.add(panel_1_2);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_3.setBounds(360, 103, 65, 2);
        contentPane.add(panel_3);

        JPanel panel_1_3 = new JPanel();
        panel_1_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_1_3.setBounds(609, 103, 65, 2);
        contentPane.add(panel_1_3);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_4.setBounds(526, 103, 65, 2);
        contentPane.add(panel_4);

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_5.setBounds(692, 103, 65, 2);
        contentPane.add(panel_5);

        lCount1 = new JLabel("CCount  #1");
        lCount1.setHorizontalAlignment(SwingConstants.CENTER);
        lCount1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount1.setBounds(28, 141, 65, 14);
        contentPane.add(lCount1);

        lCount2 = new JLabel("CCount #2");
        lCount2.setHorizontalAlignment(SwingConstants.CENTER);
        lCount2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount2.setBounds(111, 141, 65, 14);
        contentPane.add(lCount2);

        lCount3 = new JLabel("CCount #3");
        lCount3.setHorizontalAlignment(SwingConstants.CENTER);
        lCount3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount3.setBounds(194, 141, 65, 14);
        contentPane.add(lCount3);

        lCount4 = new JLabel("CCount #4");
        lCount4.setHorizontalAlignment(SwingConstants.CENTER);
        lCount4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount4.setBounds(277, 141, 65, 14);
        contentPane.add(lCount4);

        lCount5 = new JLabel("CCount #5");
        lCount5.setHorizontalAlignment(SwingConstants.CENTER);
        lCount5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount5.setBounds(360, 141, 65, 14);
        contentPane.add(lCount5);

        lCount6 = new JLabel("CCount #6");
        lCount6.setHorizontalAlignment(SwingConstants.CENTER);
        lCount6.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount6.setBounds(443, 141, 65, 14);
        contentPane.add(lCount6);

        lCount7 = new JLabel("CCount #7");
        lCount7.setHorizontalAlignment(SwingConstants.CENTER);
        lCount7.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount7.setBounds(526, 141, 65, 14);
        contentPane.add(lCount7);

        lCount8 = new JLabel("CCount #8");
        lCount8.setHorizontalAlignment(SwingConstants.CENTER);
        lCount8.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount8.setBounds(609, 141, 65, 14);
        contentPane.add(lCount8);

        lCount9 = new JLabel("CCount #9");
        lCount9.setHorizontalAlignment(SwingConstants.CENTER);
        lCount9.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lCount9.setBounds(692, 141, 65, 14);
        contentPane.add(lCount9);

        JLabel lblGameDiretion = new JLabel("Game Direction :");
        lblGameDiretion.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGameDiretion.setBounds(28, 223, 99, 14);
        contentPane.add(lblGameDiretion);

        lGameDirection = new JLabel("-->");
        lGameDirection.setHorizontalAlignment(SwingConstants.CENTER);
        lGameDirection.setFont(new Font("Tahoma", Font.BOLD, 18));
        lGameDirection.setBounds(137, 224, 65, 14);
        contentPane.add(lGameDirection);

        JLabel lblCurrentColor = new JLabel("Current Color :");
        lblCurrentColor.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCurrentColor.setBounds(28, 248, 99, 14);
        contentPane.add(lblCurrentColor);

        lGameColor = new JLabel();
        lGameColor.setForeground(Color.RED);
        lGameColor.setBackground(Color.RED);
        lGameColor.setHorizontalAlignment(SwingConstants.CENTER);
        lGameColor.setFont(new Font("Tahoma", Font.BOLD, 13));
        lGameColor.setBounds(137, 249, 65, 14);
        contentPane.add(lGameColor);

        JLabel lDeck = new JLabel("");
        // Handles when the user clicks on the deck to get a card
        lDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Logger.writeLog("Let the user gets a card...");
                getCardForUserPlayer();
            }
        });
        lDeck.setIcon(new ImageIcon(Objects.requireNonNull(GameSession.class.getResource("/assets/card_back_alt.png"))));
        lDeck.setBounds(296, 223, 78, 109);
        contentPane.add(lDeck);

        lOpenedCard = new JLabel("");
        lOpenedCard.setIcon(null);
        lOpenedCard.setBounds(409, 223, 78, 109);
        contentPane.add(lOpenedCard);

        // The user hand
        // Show cards and
        // Handles mouse entered, exited and clicked events
        lMyCard1 = new JLabel();
        lMyCard1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard1, 1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard1, 1);
            }
        });
        lMyCard1.setBounds(61, 391, 78, 109);
        contentPane.add(lMyCard1);

        lMyCard2 = new JLabel("");
        lMyCard2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard2, 2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard2);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard2, 2);
            }
        });
        lMyCard2.setBounds(143, 391, 78, 109);
        contentPane.add(lMyCard2);

        lMyCard3 = new JLabel("");
        lMyCard3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard3, 3);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard3);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard3, 3);
            }
        });
        lMyCard3.setBounds(225, 391, 78, 109);
        contentPane.add(lMyCard3);

        lMyCard4 = new JLabel("");
        lMyCard4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard4, 4);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard4);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard4, 4);
            }
        });
        lMyCard4.setBounds(307, 391, 78, 109);
        contentPane.add(lMyCard4);

        lMyCard5 = new JLabel("");
        lMyCard5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard5, 5);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard5);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard5, 5);
            }
        });
        lMyCard5.setBounds(389, 391, 78, 109);
        contentPane.add(lMyCard5);

        lMyCard8 = new JLabel("");
        lMyCard8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard8, 8);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard8);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard8, 8);
            }
        });
        lMyCard8.setBounds(635, 391, 78, 109);
        contentPane.add(lMyCard8);

        lMyCard7 = new JLabel("");
        lMyCard7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard7, 7);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard7);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard7, 7);
            }
        });
        lMyCard7.setBounds(553, 391, 78, 109);
        contentPane.add(lMyCard7);

        lMyCard6 = new JLabel("");
        lMyCard6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upCard(lMyCard6, 6);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downCard(lMyCard6);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                playCard(lMyCard6, 6);
            }
        });
        lMyCard6.setBounds(471, 391, 78, 109);
        contentPane.add(lMyCard6);

        // Show to the user's cards which are hidden
        // Left sise and right side move
        JButton bLeft = new JButton("<<");
        bLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userCardPosition <= 0) {
                    return;
                }
                userCardPosition--;
                showUserPlayerCards();
            }
        });
        bLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
        bLeft.setBounds(4, 391, 53, 109);
        contentPane.add(bLeft);

        JButton bRight = new JButton(">>");
        bRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userCardPosition >= session.getUser().getHand().size() - 8) {
                    return;
                }
                userCardPosition++;
                showUserPlayerCards();
            }
        });
        bRight.setFont(new Font("Tahoma", Font.BOLD, 11));
        bRight.setBounds(717, 391, 53, 109);
        contentPane.add(bRight);

        // Shows who is playing
        lPointer1 = new JLabel("");
        lPointer1.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer1.setBounds(28, 166, 65, 14);
        contentPane.add(lPointer1);

        lPointer2 = new JLabel("");
        lPointer2.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer2.setBounds(111, 166, 65, 14);
        contentPane.add(lPointer2);

        lPointer3 = new JLabel("");
        lPointer3.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer3.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer3.setBounds(194, 166, 65, 14);
        contentPane.add(lPointer3);

        lPointer4 = new JLabel("");
        lPointer4.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer4.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer4.setBounds(277, 166, 65, 14);
        contentPane.add(lPointer4);

        lPointer5 = new JLabel("");
        lPointer5.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer5.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer5.setBounds(360, 166, 65, 14);
        contentPane.add(lPointer5);

        lPointer6 = new JLabel("");
        lPointer6.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer6.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer6.setBounds(443, 166, 65, 14);
        contentPane.add(lPointer6);

        lPointer7 = new JLabel("");
        lPointer7.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer7.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer7.setBounds(526, 166, 65, 14);
        contentPane.add(lPointer7);

        lPointer8 = new JLabel("");
        lPointer8.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer8.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer8.setBounds(609, 166, 65, 14);
        contentPane.add(lPointer8);

        lPointer9 = new JLabel("");
        lPointer9.setHorizontalAlignment(SwingConstants.CENTER);
        lPointer9.setFont(new Font("Tahoma", Font.BOLD, 18));
        lPointer9.setBounds(692, 166, 65, 14);
        contentPane.add(lPointer9);

        // who says "UNO"
        lUno1 = new JLabel("");
        lUno1.setForeground(Color.RED);
        lUno1.setHorizontalAlignment(SwingConstants.CENTER);
        lUno1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno1.setBounds(28, 191, 65, 14);
        contentPane.add(lUno1);

        lUno2 = new JLabel("");
        lUno2.setForeground(Color.RED);
        lUno2.setHorizontalAlignment(SwingConstants.CENTER);
        lUno2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno2.setBounds(111, 191, 65, 14);
        contentPane.add(lUno2);

        lUno3 = new JLabel("");
        lUno3.setForeground(Color.RED);
        lUno3.setHorizontalAlignment(SwingConstants.CENTER);
        lUno3.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno3.setBounds(194, 191, 65, 14);
        contentPane.add(lUno3);

        lUno4 = new JLabel("");
        lUno4.setForeground(Color.RED);
        lUno4.setHorizontalAlignment(SwingConstants.CENTER);
        lUno4.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno4.setBounds(277, 191, 65, 14);
        contentPane.add(lUno4);

        lUno5 = new JLabel("");
        lUno5.setForeground(Color.RED);
        lUno5.setHorizontalAlignment(SwingConstants.CENTER);
        lUno5.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno5.setBounds(360, 191, 65, 14);
        contentPane.add(lUno5);

        lUno6 = new JLabel("");
        lUno6.setForeground(Color.MAGENTA);
        lUno6.setHorizontalAlignment(SwingConstants.CENTER);
        lUno6.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno6.setBounds(443, 191, 65, 14);
        contentPane.add(lUno6);

        lUno7 = new JLabel("");
        lUno7.setForeground(Color.RED);
        lUno7.setHorizontalAlignment(SwingConstants.CENTER);
        lUno7.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno7.setBounds(526, 191, 65, 14);
        contentPane.add(lUno7);

        lUno8 = new JLabel("");
        lUno8.setForeground(Color.RED);
        lUno8.setHorizontalAlignment(SwingConstants.CENTER);
        lUno8.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno8.setBounds(609, 191, 65, 14);
        contentPane.add(lUno8);

        lUno9 = new JLabel("");
        lUno9.setForeground(Color.RED);
        lUno9.setHorizontalAlignment(SwingConstants.CENTER);
        lUno9.setFont(new Font("Tahoma", Font.BOLD, 18));
        lUno9.setBounds(692, 191, 65, 14);
        contentPane.add(lUno9);

        // the card that lastly left
        lLeftCard = new JLabel();
        lLeftCard.setHorizontalAlignment(SwingConstants.CENTER);
        lLeftCard.setFont(new Font("Tahoma", Font.BOLD, 18));
        lLeftCard.setBounds(306, 343, 65, 14);
        contentPane.add(lLeftCard);

        // User can pass
        bPass = new JButton("PASS");
        bPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (canPass) {
                    nextPlayer(0);
                }
            }
        });
        bPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bPass.setBounds(579, 264, 144, 55);
        contentPane.add(bPass);

        // User can say "UNO"
        JButton bOne = new JButton("ONE");
        bOne.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gotOne = true;
            }
        });
        bOne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bOne.setBounds(579, 204, 144, 55);
        contentPane.add(bOne);

        bNextPlayer = new JButton("NEXT PLAYER");
        bNextPlayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                letPlayerPlay();
            }
        });
        bNextPlayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bNextPlayer.setBounds(579, 324, 144, 55);
        contentPane.add(bNextPlayer);

        // prepare the table
        setupTable();
    }

    /**
     * Setups the table, creates deck and shuffles, deals for the each player
     */
    private void setupTable() {
        Logger.writeLog("Setting up the table...");

        // set visible the component according to the number of the player
        for (int i = 9; i > session.getNumofcomputerUser(); i--) {
            JLabel lPlayer = getLabelByName("lPlayer".concat(Integer.toString(i)));
            lPlayer.setVisible(false);

            JLabel lCount = getLabelByName("lCount".concat(Integer.toString(i)));
            lCount.setVisible(false);

            JLabel lPointer = getLabelByName("lPointer".concat(Integer.toString(i)));
            lPointer.setVisible(false);

            JLabel lUno = getLabelByName("lUno".concat(Integer.toString(i)));
            lUno.setVisible(false);
        }

        // create computer player
        for (int i = 1; i < session.getNumofcomputerUser(); i++) {
            User computerUser = new User("Computer Player#".concat(Integer.toString(i)), "", 0, 0, 0, 0);
            computerUser.setName("Computer".concat(Integer.toString(i)));
            computerUsers.put(i, computerUser);
        }

        // create the deck
        deck = new Deck();
        session.setDeck(deck);

        session.getUser().getHand().clear();
        // deal seven cards for each player
        // deal the cards
        for (int i = 0; i < 7; i++) {
            session.getUser().getHand().add(deck.getCard());

            for (int j = 1; j < session.getNumofcomputerUser(); j++) {
                computerUsers.get(j).getHand().add(deck.getCard());
            }
        }
        session.setComputerUsers(computerUsers);

        // Set card counts
        JLabel label = getLabelByName("lCount1");
        label.setText(Integer.toString(session.getUser().getHand().size()));

        for (int j = 1; j < session.getNumofcomputerUser(); j++) {
            label = getLabelByName("lCount".concat(Integer.toString((j + 1))));
            label.setText(Integer.toString(computerUsers.get(j).getHand().size()));
        }

        // Turn over the top card of the draw pile to start the discard pile
        // open the 1st card from the deck
        openedCard = deck.getCard();
        lLeftCard.setText(Integer.toString(deck.getCards().size()));
        lOpenedCard.setIcon(openedCard.getIcon());
        changeGameColor(openedCard.getColor());

        session.setOpenedCard(openedCard);
        session.setGameColor(openedCard.getColor());

        // show user's hand
        userCardPosition = 0;
        showUserPlayerCards();

        session.setUserCardPosition(userCardPosition);

        // Start the game with the user’s turn
        gamePointer = 1;
        label = getLabelByName("lPointer".concat(Integer.toString(gamePointer)));
        label.setText("*");

        session.setGamePointer(gamePointer);

        // The user may not have a playable card.
        checkTheUserHasAPlayableCard();

        session.setGameDirection(gameDirection);
        bNextPlayer.setVisible(false);
    }

    /**
     * play the card which is selected by the user
     *
     * @param slot the slot number
     */
    protected void playCard(JLabel jlabel, int slot) {
        //Not user player turn
        if (gamePointer != 1) {
            return;
        }

        Point point = jlabel.getLocation();
        point.y = 391;
        jlabel.setLocation(point);

        Logger.writeLog("The user play the card that is selected.");
        if (checkTheCardCanPlay(slot)) {
            Card card = session.getUser().getHand().get(userCardPosition + slot - 1);
            playCard(card, session.getUser());
        }
    }

    /**
     * Handle computer play
     *
     * @param gamePointer the num of the computer player
     */
    private void playComputer(int gamePointer) {
        Logger.writeLog("The computer plays...".concat(Integer.toString(gamePointer)));
        User computerUser = computerUsers.get(gamePointer - 1);
        ArrayList<Card> cards = computerUser.getHand();
        ArrayList<Card> playableCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (checkTheCardCanPlay(card)) {
                playableCards.add(card);
            }
        }

        if (!playableCards.isEmpty()) {
            // a simple implementation with some randomness for a game session to function
            // and continue normally
            int index = sourceRandom.nextInt(playableCards.size());
            Card cardToPlay = playableCards.get(index);

            playCard(cardToPlay, computerUser);
        } else {
            // No playable card so that draw one from the deck
            Card newCard = deck.getCard();
            if (checkTheCardCanPlay(newCard)) {
                playCard(newCard, computerUser);
            } else {
                nextPlayer(0);
            }
        }
    }

    /**
     * play the card
     *
     * @param card        the card to play
     * @param playingUser the user who is playing
     */
    protected void playCard(Card card, User playingUser) {
        Logger.writeLog("Check the card how to be played.".concat(card.toString()));
        int drawCard = 0;
        boolean skipNextPlayer = false;
        if (card.getColor() == openedCard.getColor()) {
            // Same Color
            if (card.getType() == CardType.DRAW_TWO) {
                drawCard = 2;
            } else if (card.getType() == CardType.REVERSE) {
                changeGameDirection();
            } else if (card.getType() == CardType.SKIP) {
                skipNextPlayer = true;
            }
        } else if (card.getType() == openedCard.getType()) {
            // Same Type
            changeGameColor(card.getColor());
        } else if (card.getType() == CardType.WILD) {
            CardColor cardColor = letPlayerChooseColor();
            changeGameColor(cardColor);
        } else if (card.getType() == CardType.WILD4) {
            CardColor cardColor = letPlayerChooseColor();
            changeGameColor(cardColor);
            drawCard = 2;
        }
        openedCard = card;
        lOpenedCard.setIcon(openedCard.getIcon());
        //
        playingUser.getHand().remove(card);

        // Check if there is no card on the user's hand
        if (gamePointer == 1) {
            if (session.getUser().getHand().isEmpty()) {
                // The user won.
                session.getUser().setWins(session.getUser().getWins() + 1);
                session.getUser().setGames(session.getUser().getGames() + 1);

                // Calculate score
                int score = 0;
                for (Card usercard : session.getUser().getHand()) {
                    score += usercard.getScore();
                }
                session.getUser().setScore(session.getUser().getScore() + score);

                // Show Win Dialog and exit
                showWinDialog(session.getUser());
            }
        } else {
            User computerUser = computerUsers.get(gamePointer - 1);
            if (computerUser.getHand().isEmpty()) {
                // The computer won
                session.getUser().setLoses(session.getUser().getLoses() + 1);
                session.getUser().setGames(session.getUser().getGames() + 1);

                showWinDialog(session.getUser());
            } else if (1 == computerUser.getHand().size()) {
                JOptionPane.showMessageDialog(this, computerUser.getName() + ": I got UNO card!!!", "COMPUTER UNO", JOptionPane.WARNING_MESSAGE);
            }
        }

        // show the user's card
        showUserPlayerCards();

        //check the nextplayer will be skipped
        if (skipNextPlayer) {
            updateGamePointerForNextPlayer();
        }
        // Let the next player play
        nextPlayer(drawCard);
    }

    /**
     * switch the turn to the next player
     *
     * @param drawCard player may draw some card
     */
    protected void nextPlayer(int drawCard) {
        Logger.writeLog("Move to next player...");
        // wait for 3 sn for the user hit UNO button
        if (gamePointer == 1 && 1 == session.getUser().getHand().size()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }

            // The user forgot to hit the UNO button
            if (!gotOne) {
                // No one point out that
                int probability = sourceRandom.nextInt(100);
                if (probability < 20) {
                    JOptionPane.showMessageDialog(this, "THe user forget to say UNO, so that must be draw two card..",
                            "USER UNO", JOptionPane.WARNING_MESSAGE);
                    // The penalty is taking two new cards from the Draw Pile
                    session.getUser().getHand().add(deck.getCard());
                    session.getUser().getHand().add(deck.getCard());
                }
            }
            gotOne = false;
        }

        for (int i = 1; i < 10; i++) {
            JLabel label = getLabelByName("lPointer".concat(Integer.toString(i)));
            label.setText("");
        }

        updateGamePointerForNextPlayer();

        JLabel label = getLabelByName("lPointer".concat(Integer.toString(gamePointer)));
        label.setText("*");

        // if there is a draw card, draw it.
        if (drawCard != 0) {
            if (gamePointer == 1) {
                for (int i = 0; i < drawCard; i++) {
                    session.getUser().getHand().add(deck.getCard());
                    // show the user's card
                    showUserPlayerCards();
                }
            } else {
                for (int i = 0; i < drawCard; i++) {
                    computerUsers.get(gamePointer - 1).getHand().add(deck.getCard());
                }
            }
        }
        bNextPlayer.setVisible(true);
    }

    private void letPlayerPlay() {
        // let computer player play
        if (1 < gamePointer) {
            playComputer(gamePointer);
            bNextPlayer.setVisible(true);
        } else {
            // Check the user has at least one playable card
            // otherwise draw a card until play
            canPass = false;
            checkTheUserHasAPlayableCard();
            bNextPlayer.setVisible(false);
        }
    }

    /**
     * the player must draw a card from the draw pile until a drawn card meets the
     * criteria.
     *
     * @return true if user has a playable card
     */
    private boolean checkTheUserHasAPlayableCard() {
        Logger.writeLog("Checking the user has a playable card.");
        bPass.setEnabled(false);
        boolean canPlay = false;

        for (Card card : session.getUser().getHand()) {
            if (checkTheCardCanPlay(card)) {
                canPlay = true;
                canPass = true;
                break;
            }
        }
        if (!canPlay) {
            session.getUser().getHand().add(deck.getCard());
        }

        bPass.setEnabled(true);
        return canPlay;
    }


    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    /**
     * Shows the hand of user
     */
    private void showUserPlayerCards() {
        Logger.writeLog("Showing the user hand on the table.");
        // delete all card firstly
        for (int i = 1; i < 9; i++) {
            JLabel label = getLabelByName("lMyCard".concat(Integer.toString(i)));
            label.setIcon(null);
        }

        ArrayList<Card> cards = session.getUser().getHand();
        int i = 1;
        for (int j = 0; j < cards.size(); j++) {
            if (userCardPosition + j < cards.size()) {
                JLabel label = getLabelByName("lMyCard".concat(Integer.toString(i++)));
                ImageIcon icon = cards.get(userCardPosition + j).getIcon();
                label.setIcon(icon);
                if (i == 9) {
                    break;
                }
            }
        }

        //Check there is a playable card
        //ifnot disable PASS button
        if (!checkTheUserHasAPlayableCard()) {
            canPass = false;
        }
    }

    /**
     * check this card in a slot can be played.
     *
     * @param slot which slot the card stand on.
     * @return true can be played, otherwise false
     */
    private boolean checkTheCardCanPlay(int slot) {
        Logger.writeLog("Check this card in a slot can be played.");
        ArrayList<Card> hand = session.getUser().getHand();
        if (userCardPosition + slot - 1 < hand.size()) {
            Card card = hand.get(userCardPosition + slot - 1);
            if (null != card) {
                return checkTheCardCanPlay(card);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * check this card can be played.
     *
     * @param card which is going to check.
     * @return true can be played, otherwise false
     */
    private boolean checkTheCardCanPlay(Card card) {
        Logger.writeLog("Check this card can be played.");
        try {
            // check the card has the same color with game.
            if (card.getColor() == gameColor) {
                return true;
            }

            // check the card has the same type with game.
            if (card.getType() == openedCard.getType()) {
                return true;
            }

            // check the card is a wild card.
            if (card.getType() == CardType.WILD || card.getType() == CardType.WILD4) {
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        return false;
    }

    /**
     * get a card from the deck put it the user player hand, then show it
     */
    protected void getCardForUserPlayer() {
        Logger.writeLog("Get a card from the deck put it the user hand, then show it.");
        Card card = deck.getCard();
        lLeftCard.setText(Integer.toString(deck.getCards().size()));
        session.getUser().getHand().add(card);
        if (session.getUser().getHand().size() - 8 <= 0) {
            userCardPosition = 0;
        } else {
            userCardPosition = session.getUser().getHand().size() - 8;
        }

        showUserPlayerCards();
    }

    /**
     * change the direction of the game
     */
    private void changeGameDirection() {
        Logger.writeLog("Change the direction of the game.");
        gameDirection = !gameDirection;
        if (gameDirection) {
            lGameDirection.setText("-->");
        } else {
            lGameDirection.setText("<--");
        }
    }

    /**
     * change the color of the game
     */
    private void changeGameColor(CardColor color) {
        Logger.writeLog("Change the color of the game.");
        if (color == null) {
            return;
        }

        if (color == CardColor.BLUE) {
            lGameColor.setForeground(Color.BLUE);
            lGameColor.setText("BLUE");
        } else if (color == CardColor.GREEN) {
            lGameColor.setForeground(Color.GREEN);
            lGameColor.setText("GREEN");
        } else if (color == CardColor.YELLOW) {
            lGameColor.setForeground(Color.YELLOW);
            lGameColor.setText("YELLOW");
        } else if (color == CardColor.RED) {
            lGameColor.setForeground(Color.RED);
            lGameColor.setText("RED");
        }
        gameColor = color;
    }

    /**
     * Shows dialog box for selecting a color
     *
     * @return the card color that is chosen
     */

    private CardColor letPlayerChooseColor() {
        int selection;
        if (gamePointer == 1) {
            Logger.writeLog("Shows dialog box for selecting a color.");
            String[] options = {"BLUE", "GREEN", "YELLOW", "RED"};
            selection = JOptionPane.showOptionDialog(null, "Select a color!!!", "New Game Color",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        } else {
            selection = sourceRandom.nextInt(3);
        }

        if (selection == 0) {
            return CardColor.BLUE;
        } else if (selection == 1) {
            return CardColor.GREEN;
        } else if (selection == 2) {
            return CardColor.YELLOW;
        } else {
            return CardColor.RED;
        }

    }

    /**
     * move up the user card up
     *
     * @param jlabel the card
     */
    private void upCard(JLabel jlabel, int slot) {
        //Not user player
        if (gamePointer != 1) {
            return;
        }
        Logger.writeLog("Move up the user card up.");
        if (checkTheCardCanPlay(slot)) {
            Point point = jlabel.getLocation();
            point.y = 370;
            jlabel.setLocation(point);
        }
    }

    /**
     * move down the user card up
     *
     * @param jlabel the card
     */
    private void downCard(JLabel jlabel) {
        //Not user player
        if (gamePointer != 1) {
            return;
        }

        Logger.writeLog("Move down the user card up.");
        Point point = jlabel.getLocation();
        point.y = 391;
        jlabel.setLocation(point);
    }

    /**
     * Attempts to retrieve a component from a JFrame or JDialog using the name of
     * the private variable that NetBeans (or other IDE) created to refer to it in
     * code.
     *
     * @param <T>  Generic allow easier casting from the calling side.
     * @param name name of the private field variable, case sensitive
     * @return null if no match, otherwise a component.
     */
    @SuppressWarnings("unchecked")
    private <T extends Component> T getLabelByName(String name) {
        Object object = null;
        for (Field label : this.getClass().getDeclaredFields()) {
            try {
                label.setAccessible(true);
                if (label.getName().equals(name)) {
                    object = label.get(this);
                    break;
                }
            } catch (Exception ignored) {
            }
        }
        return (T) object;
    }

    /**
     * save the seesion
     */
    protected void saveTheSession() {
        Logger.writeLog("Closing without the game over");
        session.setDeck(deck);
        session.setComputerUsers(computerUsers);
        session.setGameColor(gameColor);
        session.setGameDirection(gameDirection);
        session.setGamePointer(gamePointer);
        session.setOpenedCard(openedCard);
        session.setUserCardPosition(userCardPosition);

        new SessionOperator().saveSessionData(session);
    }

    /**
     * Update the game pointer for next player
     */
    private void updateGamePointerForNextPlayer() {
        if (gameDirection) {
            gamePointer++;
        } else {
            gamePointer--;
        }

        if (gamePointer == 0) {
            gamePointer = session.getNumofcomputerUser();
        }

        if (gamePointer == session.getNumofcomputerUser() + 1) {
            gamePointer = 1;
        }
    }

    /**
     * Shows the winner
     *
     * @param user the winner
     */
    protected void showWinDialog(User user) {
        Logger.writeLog("We have a winner...");
        // Show a dialog
        JOptionPane.showMessageDialog(this, user.getName() + " won the game", "WINNER", JOptionPane.PLAIN_MESSAGE);

        UserOperator userOperator = new UserOperator();
        userOperator.updateUserData(session.getUser());

        // The game is over so that goto the main
        Main frameMain = new Main(session.getUser());
        frameMain.setVisible(true);
        setVisible(false);
        dispose();
    }
}
