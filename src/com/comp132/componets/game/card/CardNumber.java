package com.comp132.componets.game.card;

/**
 * The number card
 */
public class CardNumber extends Card {

	public CardNumber(CardType type, CardColor color) {
		super(type, color);
	}

	/*
	 * returns the the score of the card
	 */
	public int getScore() {
		CardType type = getType();
		if (type == CardType.ZERO) {
			return 0;
		} else if (type == CardType.ONE) {
			return 1;
		} else if (type == CardType.TWO) {
			return 2;
		} else if (type == CardType.THREE) {
			return 3;
		} else if (type == CardType.FOUR) {
			return 4;
		} else if (type == CardType.FIVE) {
			return 5;
		} else if (type == CardType.SIX) {
			return 6;
		} else if (type == CardType.SEVEN) {
			return 7;
		} else if (type == CardType.EIGHT) {
			return 8;
		} else if (type == CardType.NINE) {
			return 9;
		} else {
			return -1;
		}
	}

	/**
	 * Card type in string
	 */
	public String toString() {
		CardType type = getType();

		if (type == CardType.ZERO) {
			return "0".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.ONE) {
			return "1".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.TWO) {
			return "2".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.THREE) {
			return "3".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.FOUR) {
			return "4".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.FIVE) {
			return "5".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.SIX) {
			return "6".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.SEVEN) {
			return "7".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.EIGHT) {
			return "8".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.NINE) {
			return "9".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else {
			return "";
		}
	}
}
