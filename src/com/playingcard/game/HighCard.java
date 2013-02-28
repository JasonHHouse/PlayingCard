package com.playingcard.game;

import java.util.ArrayList;
import java.util.List;

import com.playingcard.deck.Card;
import com.playingcard.deck.Deck;
import com.playingcard.player.Action;
import com.playingcard.player.Hand;
import com.playingcard.player.Player;
import com.playingcard.player.State;

public class HighCard extends Poker {

	public HighCard(Deck deck, List<Player> allPlayers) {
		super(0, deck, null, allPlayers);
		gameNumber = 0;
		position = dealer + 1;
	}

	@Override
	public List<Player> findWinner() {
		sortHands();

		// Find the top score
		List<Player> winners = new ArrayList<Player>();
		for (int i = currentPlayers.size() - 1; i >= 0; i--) {
			if (Hand.getHandValue_OneCard(currentPlayers
					.get(currentPlayers.size() - 1).getHands().get(0)) == Hand
					.getHandValue_OneCard(currentPlayers.get(i).getHands()
							.get(0)))
				winners.add(currentPlayers.get(i));
		}

		// Give out the pot to the players
		for (Player winner : winners)
			winner.collectChips(pot / winners.size());

		// Remove any broke players
		for (Player player : currentPlayers)
			if (player.getChipCount() < 1)
				currentPlayers.remove(currentPlayers);

		gameNumber++;

		return winners;
	}

	@Override
	public void dealCards() {
		for (Player player : currentPlayers) {
			Card[] cards = deck.getNextCard(1);
			Hand hand = new Hand(cards);
			ArrayList<Hand> hands = new ArrayList<Hand>();
			hands.add(hand);
			player.setHands(hands);
		}
	}

	@Override
	public State nextAction() {
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("High Card Poker\nGame Number: " + gameNumber + "\n");
		sb.append("Pot: " + pot + "\n\n");
		for (Player player : currentPlayers)
			sb.append(player.toString() + "\n");
		return sb.toString();
	}

	@Override
	public void updateAction(Action action) {
	}

}
