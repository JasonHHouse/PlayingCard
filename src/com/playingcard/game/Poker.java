package com.playingcard.game;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.playingcard.deck.Card;
import com.playingcard.deck.Deck;
import com.playingcard.player.Action;
import com.playingcard.player.Hand;
import com.playingcard.player.Player;
import com.playingcard.player.State;

public abstract class Poker {

	protected int dealer;
	protected int position;
	protected int winner;
	protected int pot;
	protected int gameNumber;
	protected Deck deck;
	protected Stack<Card> discardedDeck;
	protected List<Player> allPlayers;
	protected List<Player> currentPlayers;

	public Poker(int dealer, Deck deck, Stack<Card> discardedDeck,
			List<Player> allPlayers) {
		super();
		this.dealer = dealer;
		this.deck = deck;
		this.discardedDeck = discardedDeck;
		this.allPlayers = allPlayers;
		this.currentPlayers = allPlayers;
	}

	/**
	 * Find out if there is a winner
	 */
	public abstract State nextAction();

	/**
	 * Ends the action passing the players action
	 */
	public abstract void updateAction(Action action);

	/**
	 * Find out if there is a winner
	 * 
	 * @return the winner or null
	 */
	public abstract List<Player> findWinner();

	/**
	 * Clear old hands and deal new cards
	 */
	public abstract void dealCards();

	/**
	 * All games should print out the game as it progresses
	 */
	public abstract String toString();

	/**
	 * Changes the dealer
	 */
	public void changeDealer() {
		dealer++;
		if (dealer >= currentPlayers.size())
			dealer = 0;
	}

	/**
	 * Gets the minimum from the table for the base pot
	 * 
	 * @param chips
	 */
	public void antee(int chips) {
		for (Player player : allPlayers)
			player.bet(chips);
		pot = allPlayers.size() * chips;
	}

	/**
	 * Changes the position for action
	 */
	protected void changePosition() {
		position++;
		if (position >= currentPlayers.size())
			position = 0;
	}

	/**
	 * Sorts the hands in ascending order
	 * 
	 * @param hands
	 */
	protected void sortHands() {
		quickSort(0, currentPlayers.size() - 1, currentPlayers);
	}

	/**
	 * Sorts the Players based on the hand
	 * 
	 * @param low
	 * @param high
	 * @param players
	 */
	protected void quickSort(int low, int high, List<Player> players) {
		int i = low;
		int j = high;
		Hand pivot = players.get(low + (high - low) / 2).getHands().get(0);

		while (i <= j) {
			while (players.get(i).getHands().get(0).compareTo(pivot) < 0)
				i++;

			while (players.get(j).getHands().get(0).compareTo(pivot) > 0)
				j--;

			if (i <= j) {
				Collections.swap(players, i, j);
				i++;
				j--;
			}
		}

		if (low < j)
			quickSort(low, j, players);
		if (i < high)
			quickSort(i, high, players);
	}

}
