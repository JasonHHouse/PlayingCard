package com.playingcard.test;

import java.util.Collections;

import com.playingcard.deck.StandardDeck;
import com.playingcard.player.Hand;

public class HandCardTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardDeck sd = new StandardDeck();

		/*
		 * for (int i = 0; i < 10; i++) { Hand hand1 = new
		 * Hand(sd.getNextCard(1)); Hand hand2 = new Hand(sd.getNextCard(1));
		 * System.out.println(hand1); System.out.println(hand2);
		 * System.out.println(hand1.compareTo(hand2)); }
		 */

		for (int i = 0; i < 10; i++) {
			Hand hand1 = new Hand(sd.getNextCard(3));
			Collections.sort(hand1.getHand());
			System.out.println(hand1);
			System.out.println(Hand.getHandValue_ThreeCard(hand1));
			// System.out.println(Hand.getHandValue_TwoCard(hand1));
		}

	}

}
