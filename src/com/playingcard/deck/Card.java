package com.playingcard.deck;

public class Card implements Comparable<Card> {

	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public String toString() {
		return rank + " " + suit;
	}

	@Override
	public boolean equals(Object obj) {
		Card card = (Card) obj;
		if (card.getSuit().equals(suit) && card.getRank().equals(rank))
			return true;
		return false;
	}

	@Override
	public int compareTo(Card card) {
		return card.getRank().ordinal() - rank.ordinal();
	}
}
