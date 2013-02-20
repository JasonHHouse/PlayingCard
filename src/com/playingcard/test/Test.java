package com.playingcard.test;

import java.util.ArrayList;

import com.playingcard.deck.StandardDeck;
import com.playingcard.game.HighCard;
import com.playingcard.player.Player;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardDeck sd = new StandardDeck();

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
			System.out.println(hc.findWinner());
			hc.changeDealer();
		}
	}

}
