package com.comp132.componets.game.card;

/**
 * The Action Card
 */
public class CardAction extends Card {

	public CardAction(CardType type, CardColor color) {
		super(type, color);
	}

	//
	/*
	 * returns the the score of the card
	 */
	public int getScore() {
		CardType type = getType();

		if (type == CardType.DRAW_TWO) {
			return 20;
		} else if (type == CardType.REVERSE) {
			return 20;
		} else if (type == CardType.SKIP) {
			return 20;
		} else {
			return -1;
		}
	}

	/**
	 * Card type in string
	 */
	public String toString() {
		CardType type = getType();

		if (type == CardType.DRAW_TWO) {
			return "DRAW_TWO".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.REVERSE) {
			return "REVERSE".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.SKIP) {
			return "SKIP".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else {
			return "";
		}
	}
}
