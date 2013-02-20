package com.playingcard.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StandardDeck extends Deck {

	private static final int NUMBER_OF_SHUFFLES = 500;
	private static final int DECK_SIZE = 52;

	public StandardDeck() {
		super(new ArrayList<Card>());
		shuffleDeck();
	}

	@Override
	public Card[] getNextCard(int cardCount) {
		Card[] nextCards = new Card[cardCount];
		if (cardCount <= cards.size()) {
			for (int i = 0; i < cardCount; i++) {
				nextCards[i] = cards.get(0);
				cards.remove(0);
			}
			return nextCards;
		}
		return null;
	}

	@Override
	public void shuffleDeck() {
		// Load the deck
		for (Suit suit : Suit.values())
			for (Value value : Value.values())
				cards.add(new Card(value, suit));

		// shuffle the cards
		Random r = new Random();
		for (int i = 0; i < NUMBER_OF_SHUFFLES; i++) {
			swap(r.nextInt(DECK_SIZE), r.nextInt(DECK_SIZE));
		}

	}

	@Override
	public void cutDeck(int location) {
		List<Card> front = cards.subList(0, location);
		List<Card> back = cards.subList(location, cards.size());
		back.addAll(front);
		cards = back;
	}

	@Override
	public boolean isDeckEmpty() {
		if (cards == null || cards.size() == 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : cards)
			sb.append(card.toString() + "\n");
		return sb.toString();
	}

	@Override
	public void resetDeck() {
		// Clear the deck
		cards = new ArrayList<Card>();
	}
	
	private void swap(int a, int b) {
		Collections.swap(cards, a, b);
	}	
}
