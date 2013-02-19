package com.playingcard.test;

import com.playingcard.deck.StandardDeck;
import com.playingcard.player.Hand;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardDeck sd = new StandardDeck();
		System.out.println(sd.toString());
		sd.cutDeck(10);
		System.out.println(sd.toString());
		sd.shuffleDeck();
		System.out.println(sd.toString());
		while(!sd.isDeckEmpty())
			System.out.println(sd.getNextCard(1));
		System.out.println(sd.toString());
		
		sd.shuffleDeck();
		Hand hand1 = new Hand(sd.getNextCard(5));
		Hand hand2 = new Hand(sd.getNextCard(5));
		System.out.println(hand1.toString());
		System.out.println(hand2.toString());
		
		hand1.removeCard(hand1.getHand().get(0));
		hand2.removeCard(hand2.getHand().get(0));
		System.out.println(hand1.toString());
		System.out.println(hand2.toString());
	}

}
