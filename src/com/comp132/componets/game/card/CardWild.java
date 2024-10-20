package com.comp132.componets.game.card;

/**
 * The wild card
 */
public class CardWild extends Card {

	public CardWild(CardType type, CardColor color) {
		super(type, color);
	}

	//
	/*
	 * returns the the score of the card
	 */
	public int getScore() {
		CardType type = getType();
		if (type == CardType.WILD) {
			return 50;
		} else if (type == CardType.WILD4) {
			return 50;
		} else {
			return -1;
		}
	}

	/**
	 * Card type in string
	 */
	public String toString() {
		CardType type = getType();

		if (type == CardType.WILD) {
			return "wild".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else if (type == CardType.WILD4) {
			return "wild4".concat(";").concat(CardColor.getStringFromCardColor(getColor()));
		} else {
			return "";
		}
	}
}
