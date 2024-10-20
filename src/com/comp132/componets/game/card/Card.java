package com.comp132.componets.game.card;

import javax.swing.ImageIcon;

public abstract class Card {
	/**
	 * The card types
	 */
	public enum CardType {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, REVERSE, SKIP, WILD, WILD4
	}

	/**
	 * The card color and methods that handle string to color conversion and vera-versa.
	 */
	public enum CardColor {
		BLUE, GREEN, YELLOW, RED;

		/**
		 * String to CardColor
		 * 
		 * @param string
		 * @return
		 */
		public static CardColor getCardColorFromString(String string) {
			if (string.equals("blue")) {
				return CardColor.BLUE;
			} else if (string.equals("green")) {
				return CardColor.GREEN;
			} else if (string.equals("yellow")) {
				return CardColor.YELLOW;
			} else if (string.equals("red")) {
				return CardColor.RED;
			}
			return null;
		}

		/**
		 * CardColor to String
		 */
		public static String getStringFromCardColor(CardColor color) {
			if (color == CardColor.BLUE) {
				return "blue";
			} else if (color == CardColor.GREEN) {
				return "green";
			} else if (color == CardColor.YELLOW) {
				return "yellow";
			} else if (color == CardColor.RED) {
				return "red";
			}
			return "";
		}

	}

	/**
	 * The type of card
	 */
	private CardType type;
	
	/*
	 * The color of card
	 */
	private CardColor color;

	public Card(CardType type, CardColor color) {
		super();
		this.type = type;
		this.color = color;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public CardColor getColor() {
		return color;
	}

	public void setColor(CardColor color) {
		this.color = color;
	}

	public abstract int getScore();

	/**
	 * return the image icon of the card
	 * @return
	 */
	public ImageIcon getIcon() {
		String color = "";
		if (getColor() == CardColor.BLUE) {
			color = "blue";
		} else if (getColor() == CardColor.GREEN) {
			color = "green";
		} else if (getColor() == CardColor.YELLOW) {
			color = "yellow";
		} else if (getColor() == CardColor.RED) {
			color = "red";
		}

		String name = "";
		if (type == CardType.ZERO) {
			name = "0";
		} else if (type == CardType.ONE) {
			name = "1";
		} else if (type == CardType.TWO) {
			name = "2";
		} else if (type == CardType.THREE) {
			name = "3";
		} else if (type == CardType.FOUR) {
			name = "4";
		} else if (type == CardType.FIVE) {
			name = "5";
		} else if (type == CardType.SIX) {
			name = "6";
		} else if (type == CardType.SEVEN) {
			name = "7";
		} else if (type == CardType.EIGHT) {
			name = "8";
		} else if (type == CardType.NINE) {
			name = "9";
		} else if (type == CardType.DRAW_TWO) {
			name = "picker";
		} else if (type == CardType.REVERSE) {
			name = "reverse";
		} else if (type == CardType.SKIP) {
			name = "skip";
		}
		name = "src/assets/".concat(color).concat("_").concat(name).concat(".png");

		if (type == CardType.WILD) {
			name = "src/assets/wild_colora_changer.png";
		} else if (type == CardType.WILD4) {
			name = "src/assets/wild_pick_four.png";
		}

		return new ImageIcon(name);
	}

	/**
	 * Returns a card that constructed from string
	 * @param string
	 * @return
	 */
	public static Card getCardFromString(String string) {
		String ss[] = string.split(";");
		CardColor cardColor = CardColor.getCardColorFromString(ss[1]);
		if (null != cardColor) {
			return null;
		}
		
		Card card = null;
		if (ss[1].equals("0")) {
			card = new CardNumber(CardType.ZERO, cardColor);
		} else if (ss[1].equals("1")) {
			card = new CardNumber(CardType.ONE, cardColor);
		} else if (ss[1].equals("2")) {
			card = new CardNumber(CardType.TWO, cardColor);
		} else if (ss[1].equals("3")) {
			card = new CardNumber(CardType.THREE, cardColor);
		} else if (ss[1].equals("4")) {
			card = new CardNumber(CardType.FOUR, cardColor);
		} else if (ss[1].equals("5")) {
			card = new CardNumber(CardType.FIVE, cardColor);
		} else if (ss[1].equals("6")) {
			card = new CardNumber(CardType.SIX, cardColor);
		} else if (ss[1].equals("7")) {
			card = new CardNumber(CardType.SEVEN, cardColor);
		} else if (ss[1].equals("8")) {
			card = new CardNumber(CardType.EIGHT, cardColor);
		} else if (ss[1].equals("9")) {
			card = new CardNumber(CardType.NINE, cardColor);
		} else if (ss[1].equals("picker")) {
			card = new CardAction(CardType.DRAW_TWO, cardColor);
		} else if (ss[1].equals("reverse")) {
			card = new CardAction(CardType.REVERSE, cardColor);
		} else if (ss[1].equals("skip")) {
			card = new CardAction(CardType.SKIP, cardColor);
		} else if (ss[1].equals("wild")) {
			card = new CardWild(CardType.WILD, cardColor);
		} else if (ss[1].equals("wild4")) {
			card = new CardWild(CardType.WILD4, cardColor);
		}

		return card;
	}
}
