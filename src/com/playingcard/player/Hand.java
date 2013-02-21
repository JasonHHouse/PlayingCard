package com.playingcard.player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.playingcard.deck.Card;

public class Hand implements Comparable<Hand> {

	private List<Card> hand;

	public Hand(Card[] cards) {
		super();
		hand = new ArrayList<Card>();
		for (Card card : cards)
			hand.add(card);
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void removeCard(Card card) {
		for (Card oldCard : hand)
			if (oldCard.equals(card)) {
				hand.remove(oldCard);
				break;
			}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card card : hand)
			sb.append(card + ", ");
		return sb.toString().substring(0, sb.toString().length() - 2);
	}

	@Override
	public int compareTo(Hand hand) {
		switch (this.hand.size()) {
		case 1:
			return compareTo_OneCard(hand);
		case 2:
			return compareTo_TwoCard(hand);
		default:
			return 0;
		}
	}

	private int compareTo_OneCard(Hand hand) {
		return getHandValue_OneCard(this) - getHandValue_OneCard(hand);
	}

	public static int getHandValue_OneCard(Hand hand) {
		return hand.getHand().get(0).getRank().ordinal();
	}

	private int compareTo_TwoCard(Hand hand) {
		int hand1 = getHandValue_TwoCard(this);
		int hand2 = getHandValue_TwoCard(hand);
		if (hand1 - hand2 > 0)
			return 1;
		else if (hand1 - hand2 < 0)
			return -1;
		else
			return 0;
	}

	public static int getHandValue_TwoCard(Hand hand) {
		Collections.sort(hand.getHand());
		Card first = hand.getHand().get(0);
		Card second = hand.getHand().get(1);
		if (first.getSuit() == second.getSuit()) {
			if (first.getRank().ordinal() + 1 == second.getRank().ordinal()
					|| first.getRank().ordinal() - 1 == second.getRank()
							.ordinal()) {
				// Straight Flush
				return 30000 + cardValue(hand);
			} else {
				// Flush
				return 20000 + cardValue(hand);
			}
		} else {
			if (first.getRank() == second.getRank()) {
				// Pair
				return 40000 + pairValue(hand);
			} else if (first.getRank().ordinal() + 1 == second.getRank()
					.ordinal()
					|| first.getRank().ordinal() - 1 == second.getRank()
							.ordinal()) {
				// Straight
				return 10000 + cardValue(hand);
			} else {
				// High Card
				return cardValue(hand);
			}
		}
	}

	private static int pairValue(Hand hand) {
		return hand.getHand().get(0).getRank().ordinal();
	}

	private static int cardValue(Hand hand) {
		String sum = "";
		for (int i = 0; i < hand.getHand().size(); i++) {
			sum += intToString(hand.getHand().get(i).getRank().ordinal(), 2);
		}
		return Integer.parseInt(sum);
	}

	private static String intToString(int num, int digits) {
	    assert digits > 0 : "Invalid number of digits";

	    // create variable length array of zeros
	    char[] zeros = new char[digits];
	    Arrays.fill(zeros, '0');
	    // format number as String
	    DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

	    return df.format(num);
	}
}
