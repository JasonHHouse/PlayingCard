package com.playingcard.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.net.ssl.HandshakeCompletedEvent;

import com.playingcard.deck.Card;
import com.playingcard.deck.Deck;
import com.playingcard.player.Action;
import com.playingcard.player.Hand;
import com.playingcard.player.Player;
import com.playingcard.player.State;

public class ThreeCardMolly extends Poker {

	public ThreeCardMolly(Deck deck, List<Player> players) {
		super(0, deck, new Stack<Card>(), players);
		gameNumber = 0;
		position = dealer + 1;
	}

	@Override
	public State nextAction() {
		if (position == -2) {
			return null;
		} else if (position == -1) {
			// last action on dealer
			State state = new State(currentPlayers, dealer);
			position = -2;
			return state;
		} else {
			// normal action
			State state = new State(currentPlayers, position);
			changePosition();
			if (position == dealer)
				position = -1;
			return state;
		}
	}

	@Override
	public void updateAction(Action action) {
		if (action == Action.Fold)
			currentPlayers.remove(position--);
	}

	@Override
	public List<Player> findWinner() {
		sortHands();

		// Find the top score
		List<Player> winners = new ArrayList<Player>();
		for (int i = currentPlayers.size() - 1; i >= 0; i--) {
			if (Hand.getHandValue_TwoCard(currentPlayers
					.get(currentPlayers.size() - 1).getHands().get(0)) == Hand
					.getHandValue_TwoCard(currentPlayers.get(i).getHands()
							.get(0)))
				winners.add(currentPlayers.get(i));
		}

		// Check if the winner is a dealer and if they have trips
		if (winners.size() == 1)
			if (winners.get(0).getHands().get(0)
					.equals(allPlayers.get(dealer).getHands().get(0)))
				if (Integer.parseInt(Hand.getHandValue_ThreeCard(winners.get(0)
						.getHands().get(0))) < 5000000) {
					return null;
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
			Card[] cards = deck.getNextCard(3);
			Hand hand = new Hand(cards);
			ArrayList<Hand> hands = new ArrayList<Hand>();
			hands.add(hand);
			player.setHands(hands);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Three Card Molly\nGame Number: " + gameNumber + "\n");
		sb.append("Pot: " + pot + "\n\n");
		for (Player player : currentPlayers)
			sb.append(player.toString() + "\n");
		return sb.toString();
	}

}
