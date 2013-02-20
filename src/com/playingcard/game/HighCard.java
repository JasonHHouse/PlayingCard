package com.playingcard.game;

import java.util.ArrayList;
import java.util.List;

import com.playingcard.deck.Card;
import com.playingcard.deck.Deck;
import com.playingcard.player.Hand;
import com.playingcard.player.Player;

public class HighCard extends Poker {

	public HighCard(Deck deck, List<Player> players) {
		super(0, deck, null, players);
		gameNumber = 0;
	}

	@Override
	public String findWinner() {
		// Find the top score
		int topValue = -1;
		for (Player player : players)
			if (topValue < player.getHands().get(0).getHand().get(0).getValue()
					.ordinal())
				topValue = player.getHands().get(0).getHand().get(0).getValue()
						.ordinal();

		// Add the winers to a list
		List<Player> winners = new ArrayList<Player>();
		StringBuilder sb = new StringBuilder();
		for (Player player : players)
			if (topValue == player.getHands().get(0).getHand().get(0)
					.getValue().ordinal()) {
				winners.add(player);
			}

		if(winners.size() > 1)
			sb.append("Winners are...\n");
		else
			sb.append("Winner is...\n");
		// Give out the pot to the players
		for (Player winner : winners) {
			winner.collectChips(pot / winners.size());
			sb.append(winner.toString());
		}

		// Remove any broke players
		for (Player player : players)
			if (player.getChipCount() < 1)
				players.remove(players);

		gameNumber++;

		return sb.toString();
	}

	@Override
	public void dealCards() {
		for (Player player : players) {
			Card[] cards = deck.getNextCard(1);
			Hand hand = new Hand(cards);
			ArrayList<Hand> hands = new ArrayList<Hand>();
			hands.add(hand);
			player.setHands(hands);
		}
	}

	@Override
	public void changeDealer() {
		dealer++;
		if (dealer >= players.size())
			dealer = 0;
	}

	public void antee(int chips) {
		for (Player player : players)
			player.bet(chips);
		pot = players.size() * chips;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("High Card Poker\nGame Number: " + gameNumber + "\n");
		sb.append("Pot: " + pot + "\n\n");
		for (Player player : players)
			sb.append(player.toString() + "\n");
		return sb.toString();
	}

}
