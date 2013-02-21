package com.playingcard.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.playingcard.deck.Card;
import com.playingcard.deck.Deck;
import com.playingcard.player.Action;
import com.playingcard.player.Hand;
import com.playingcard.player.Player;
import com.playingcard.player.State;

public class Guts extends Poker {

	public Guts(Deck deck, List<Player> players) {
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
			State state = new State(players, dealer);
			position = -2;
			return state;
		} else {
			// normal action
			State state = new State(players, position);
			changePosition();
			if (position == dealer)
				position = -1;
			return state;
		}

	}

	@Override
	public void updateAction(Action action) {
		if (action == Action.Fold)
			players.remove(position--);
	}

	@Override
	protected void sortHands() {
		quickSort(0, players.size() - 1, players);
	}

	@Override
	public List<Player> findWinner() {
		sortHands();

		// Find the top score
		List<Player> winners = new ArrayList<Player>();
		for (int i = players.size() - 1; i >= 0; i--) {
			if (Hand.getHandValue_TwoCard(players.get(players.size() - 1)
					.getHands().get(0)) == Hand.getHandValue_TwoCard(players
					.get(i).getHands().get(0)))
				winners.add(players.get(i));
		}

		// Give out the pot to the players
		for (Player winner : winners)
			winner.collectChips(pot / winners.size());

		// Remove any broke players
		for (Player player : players)
			if (player.getChipCount() < 1)
				players.remove(players);

		gameNumber++;

		return winners;
	}

	@Override
	public void dealCards() {
		for (Player player : players) {
			Card[] cards = deck.getNextCard(2);
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

	@Override
	public void antee(int chips) {
		for (Player player : players)
			player.bet(chips);
		pot = players.size() * chips;
	}

	@Override
	protected void changePosition() {
		position++;
		if (position >= players.size())
			position = 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Guts Poker\nGame Number: " + gameNumber + "\n");
		sb.append("Pot: " + pot + "\n\n");
		for (Player player : players)
			sb.append(player.toString() + "\n");
		return sb.toString();
	}

}
