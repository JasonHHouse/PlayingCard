package com.playingcard.deck;

import java.util.List;

public abstract class Deck {

	protected List<Card> cards;

	public Deck(List<Card> cards) {
		this.cards = cards;
	}
	/**
	 * Used to get the next card(s) in the deck, returns null if empty
	 * 
	 * @param cardCount
	 * @return array of cards or null if empty
	 */
	public abstract Card[] getNextCard(int cardCount);

	/**
	 * Shuffles the deck, putting all cards back in
	 * 
	 */
	public abstract void shuffleDeck();

	/**
	 * Cuts the deck moving the bottom half to the top
	 * 
	 */
	public abstract void cutDeck(int location);

	/**
	 * Finds out if any cards left in the deck
	 * 
	 * @return true if empty, false if not empty
	 */
	public abstract boolean isDeckEmpty();
	
	/**
	 * To print the deck
	 */
	public abstract String toString();

	/**
	 * Resets the deck to the original filled amount
	 */
	public abstract void resetDeck();
}
