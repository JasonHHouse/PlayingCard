package com.playingcard.test;

import java.util.ArrayList;
import java.util.List;

import com.playingcard.deck.StandardDeck;
import com.playingcard.game.HighCard;
import com.playingcard.player.Player;

public class TestHighCard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardDeck sd = new StandardDeck();

	/*	for (int i = 0; i < 10; i++) {
			Hand hand1 = new Hand(sd.getNextCard(2));
			Hand hand2 = new Hand(sd.getNextCard(2));
			System.out.println(hand1);
			System.out.println(hand2);
			System.out.println(hand1.compareTo(hand2));
		}*/

		Player player1 = new Player("Jason", 100);
		Player player2 = new Player("Greg", 50);
		Player player3 = new Player("John", 175);
		Player player4 = new Player("Bill", 100);
		Player player5 = new Player("Uri", 50);
		Player player6 = new Player("Mike", 175);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);

		HighCard hc = new HighCard(sd, players);
		for (int i = 0; i < 5; i++) {
			hc.antee(5);
			hc.dealCards();
			System.out.print(hc.toString());
			List<Player> winners = hc.findWinner();
			System.out.println("Winner...");
			for (Player winner : winners)
				System.out.println(winner);
			hc.changeDealer();
		}
	}

}
