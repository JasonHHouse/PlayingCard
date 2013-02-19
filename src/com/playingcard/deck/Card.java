package com.playingcard.deck;

public class Card {

	private String suit;
	private String value;

	public Card(String suit, String value) {
		super();
		this.suit = suit;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
