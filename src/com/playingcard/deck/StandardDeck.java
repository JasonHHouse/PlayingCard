package com.playingcard.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StandardDeck extends Deck {

	private static final int NUMBER_OF_SHUFFLES = 500;
	private static final int DECK_SIZE = 52;
	
	private List<Card> cards;
	private String[] suits = { "Diamond", "Heart", "Spade", "Club" };
	private String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"Jack", "Queen", "King", "Ace" };

	public StandardDeck() {
		shuffleDeck();
	}

	@Override
	public Card[] getNextCard(int cardCount) {
		Card[] nextCards = new Card[cardCount];
		if(cardCount <= cards.size()) {
			for(int i = 0; i < cardCount; i++) {
				nextCards[i] = cards.get(0);
				cards.remove(0);
			}
			return nextCards;
		}
		return null;
	}

	@Override
	public void shuffleDeck() {
		//Clear the deck
		cards = new ArrayList<Card>();
		
		//Load the deck
		for(String suit : suits)
			for(String value : values)
				cards.add(new Card(suit,value));

		//shuffle the cards
		Random r = new Random();
		for(int i = 0; i < NUMBER_OF_SHUFFLES; i++){
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
		String strOutput = "";
		for(Card card : cards)
			strOutput += card.toString() + "\n";
		return strOutput;
	}

	private void swap(int a, int b) {
		Collections.swap(cards,a,b);
	}
}
