package com.playingcard.deck;

public class Card {

	private final Value value;
	private final Suit suit;

	public Card(Value value, Suit suit) {
		super();
		this.value = value;
		this.suit = suit;
	}

	public Value getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public String toString() {
		return value + " " + suit;
	}

	@Override
	public boolean equals(Object obj) {
		Card card = (Card) obj;
		if (card.getSuit().equals(suit) && card.getValue().equals(value))
			return true;
		return false;
	}
}
