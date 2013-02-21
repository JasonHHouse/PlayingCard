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
	protected List<Player> players;

	public Poker(int dealer, Deck deck, Stack<Card> discardedDeck,
			List<Player> players) {
		super();
		this.dealer = dealer;
		this.deck = deck;
		this.discardedDeck = discardedDeck;
		this.players = players;
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
	 * Changes the dealer
	 */
	public abstract void changeDealer();

	/**
	 * Gets the minimum from the table for the base pot
	 * 
	 * @param chips
	 */
	public abstract void antee(int chips);

	/**
	 * Changes the position for action
	 */
	protected abstract void changePosition();

	/**
	 * Sorts the hands in ascending order
	 * 
	 * @param hands
	 */
	protected abstract void sortHands();

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
