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
		case 3:
			return compareTo_ThreeCard(hand);
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
		int hand1 = Integer.parseInt(getHandValue_TwoCard(this));
		int hand2 = Integer.parseInt(getHandValue_TwoCard(hand));
		if (hand1 - hand2 > 0)
			return 1;
		else if (hand1 - hand2 < 0)
			return -1;
		else
			return 0;
	}

	/**
	 * 5000000 ----------------------------------------------- Three of a Kind
	 * 4000000 ------------------------------------------------ Straight Flush
	 * 3000000 --------------------------------------------------------- Flush
	 * 2000000 ------------------------------------------------------ Straight
	 * 1000000 ---------------------------------------------------------- Pair
	 * 0000000 ----------------------------------------------------- High Card
	 * 
	 * @param hand
	 * @return
	 * 
	 */
	public static String getHandValue_ThreeCard(Hand hand) {
		Collections.sort(hand.getHand());
		Card first = hand.getHand().get(0);
		Card second = hand.getHand().get(1);
		Card third = hand.getHand().get(2);

		if ((first.getSuit() == second.getSuit())
				&& second.getSuit() == third.getSuit()) {
			if ((first.getRank().ordinal() - 1 == second.getRank().ordinal())
					&& second.getRank().ordinal() - 1 == third.getRank()
							.ordinal()) {
				// Straight Flush
				return "4" + cardValue(hand);
			} else {
				// Flush
				return "3" + cardValue(hand);
			}
		} else {
			if (first.getRank() == second.getRank()
					&& second.getRank() == third.getRank()) {
				// Three of a Kind
				return "5" + cardValue(hand);
			} else if (first.getRank() == second.getRank()
					|| second.getRank() == third.getRank()) {
				// Pair
				return "1" + cardValue(hand);
			} else if ((first.getRank().ordinal() - 1 == second.getRank()
					.ordinal())
					&& second.getRank().ordinal() - 1 == third.getRank()
							.ordinal()) {
				// Straight
				return "2" + cardValue(hand);
			} else {
				// High Card
				return "0" + cardValue(hand);
			}
		}
	}

	/**
	 * 40000 ------------------------------------------------------------ Pair
	 * 30000 -------------------------------------------------- Straight Flush
	 * 20000 ----------------------------------------------------------- Flush
	 * 10000 -------------------------------------------------------- Straight
	 * 00000 ------------------------------------------------------- High Card
	 * 
	 * @param hand
	 * @return
	 */
	public static String getHandValue_TwoCard(Hand hand) {
		Collections.sort(hand.getHand());
		Card first = hand.getHand().get(0);
		Card second = hand.getHand().get(1);
		if (first.getSuit() == second.getSuit()) {
			if (first.getRank().ordinal() - 1 == second.getRank().ordinal()) {
				// Straight Flush
				return "3" + cardValue(hand);
			} else {
				// Flush
				return "2" + cardValue(hand);
			}
		} else {
			if (first.getRank() == second.getRank()) {
				// Pair
				return "4" + cardValue(hand);
			} else if (first.getRank().ordinal() - 1 == second.getRank()
					.ordinal()) {
				// Straight
				return "1" + cardValue(hand);
			} else {
				// High Card
				return "0" + cardValue(hand);
			}
		}
	}

	private int compareTo_ThreeCard(Hand hand) {
		int hand1 = Integer.parseInt(getHandValue_ThreeCard(this));
		int hand2 = Integer.parseInt(getHandValue_ThreeCard(hand));
		if (hand1 - hand2 > 0)
			return 1;
		else if (hand1 - hand2 < 0)
			return -1;
		else
			return 0;
	}

	private static String cardValue(Hand hand) {
		String sum = "";
		for (int i = 0; i < hand.getHand().size(); i++) {
			sum += intToString(hand.getHand().get(i).getRank().ordinal(), 2);
		}
		return sum;
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
