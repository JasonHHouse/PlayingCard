package com.playingcard.test;

import java.util.ArrayList;
import java.util.List;

import com.playingcard.deck.StandardDeck;
import com.playingcard.game.Guts;
import com.playingcard.player.Action;
import com.playingcard.player.Player;
import com.playingcard.player.State;

public class TestGuts {

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
		
		Guts g = new Guts(sd, players);
		g.antee(5);
		g.dealCards();
		System.out.print(g.toString());
		State state;
		do {
			state = g.nextAction();
			g.updateAction(Action.Call);
		} while(state != null);
		List<Player> winners = g.findWinner();
		System.out.println("Winner...");
		for (Player winner : winners)
			System.out.println(winner);
	}

}
