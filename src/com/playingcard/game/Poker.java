package com.playingcard.game;

import java.util.List;
import java.util.Stack;

import com.playingcard.deck.Card;
import com.playingcard.deck.Deck;
import com.playingcard.player.Player;

public abstract class Poker {

	protected int dealer;
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
	public abstract String findWinner();

	/**
	 * Clear old hands and deal new cards
	 */
	public abstract void dealCards();

	/**
	 * Changes the dealer
	 */
	public abstract void changeDealer();

}
