package com.comp132.componets.game;

import com.comp132.componets.game.card.Card;
import com.comp132.componets.game.card.Card.CardColor;
import com.comp132.componets.game.card.Card.CardType;
import com.comp132.componets.game.card.CardAction;
import com.comp132.componets.game.card.CardNumber;
import com.comp132.componets.game.card.CardWild;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final static Random sourceRandom = new Random();
    /**
     * Holds unopened cards
     */
    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * Holds opened cards
     */
    private final ArrayList<Card> opennedCards = new ArrayList<Card>();

    public Deck() {
        super();

        prepareDeck();
    }

    /**
     * Create an deck object from string
     *
     * @param s string that holds deck's info.
     */
    public static Deck getDeckFromString(String s) {
        String[] ss = s.split(",");
        Deck deck = new Deck();

        for (int i = 0; i < ss.length; i++) {
            String strDeck = ss[i];
            Card card = Card.getCardFromString(strDeck);
            if (card != null) {
                deck.getCards().add(card);
            }
        }

        return deck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private void prepareDeck() {
        // creation of a deck
        cards.clear();
        opennedCards.clear();

        // The Uno cards have four color "suits", which are red, yellow, blue, and
        // green.
        CardColor[] colors = new CardColor[4];
        colors[0] = CardColor.BLUE;
        colors[1] = CardColor.YELLOW;
        colors[2] = CardColor.GREEN;
        colors[3] = CardColor.RED;

        for (CardColor color : colors) {
            // one number 0 card
            cards.add(new CardNumber(CardType.ZERO, color));

            // two sets of cards numbered 1-9
            cards.add(new CardNumber(CardType.ONE, color));
            cards.add(new CardNumber(CardType.ONE, color));
            cards.add(new CardNumber(CardType.TWO, color));
            cards.add(new CardNumber(CardType.TWO, color));
            cards.add(new CardNumber(CardType.THREE, color));
            cards.add(new CardNumber(CardType.THREE, color));
            cards.add(new CardNumber(CardType.FOUR, color));
            cards.add(new CardNumber(CardType.FOUR, color));
            cards.add(new CardNumber(CardType.FIVE, color));
            cards.add(new CardNumber(CardType.FIVE, color));
            cards.add(new CardNumber(CardType.SIX, color));
            cards.add(new CardNumber(CardType.SIX, color));
            cards.add(new CardNumber(CardType.SEVEN, color));
            cards.add(new CardNumber(CardType.SEVEN, color));
            cards.add(new CardNumber(CardType.EIGHT, color));
            cards.add(new CardNumber(CardType.EIGHT, color));
            cards.add(new CardNumber(CardType.NINE, color));
            cards.add(new CardNumber(CardType.NINE, color));

            // Action card: two of each action card in each color
            // Draw two, Reverse, Skip
            cards.add(new CardAction(CardType.DRAW_TWO, color));
            cards.add(new CardAction(CardType.DRAW_TWO, color));
            cards.add(new CardAction(CardType.REVERSE, color));
            cards.add(new CardAction(CardType.REVERSE, color));
            cards.add(new CardAction(CardType.SKIP, color));
            cards.add(new CardAction(CardType.SKIP, color));
        }

        // There are two types of wild cards, four of each type
        // Wild, Wild draw four
        for (int i = 0; i < 4; i++) {
            cards.add(new CardWild(CardType.WILD, null));
            cards.add(new CardWild(CardType.WILD4, null));
        }
        shuffle();
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; --i) {
            int index = sourceRandom.nextInt(i);
            Card c = cards.get(index);
            cards.set(index, cards.get(i));
            cards.set(i, c);
        }
    }

    /**
     * Returns the last card on the deck.
     *
     * @return the card
     */
    public Card getCard() {
        Card card;
        if (cards.size() > 0) {
            card = cards.getLast();
            cards.remove(card);
            opennedCards.add(card);
        } else {
            prepareDeck();
            card = cards.getLast();
        }

        return card;
    }

    /**
     * Returns a string that includes the deck info
     *
     * @return
     */
    public String getCardsInString() {
        String s = "";
        for (Card card : getCards()) {
            s = s.concat(card.toString()).concat(",");
        }
        return s;
    }
}
