package com.playingcard.player;

import java.util.ArrayList;
import java.util.List;

import com.playingcard.deck.Card;

public class Hand {

	List<Card> hand;

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
			if (oldCard.equals(card)){
				hand.remove(oldCard);
				break;
			}
	}
	
	@Override
	public String toString(){
		String strOutput = "";
		for(Card card : hand)
			strOutput += card + ", ";
		return strOutput.substring(0, strOutput.length()-2);
	}
}
