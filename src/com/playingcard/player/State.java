package com.playingcard.player;

import java.util.List;

public class State {

	private List<Player> players;
	private int currentPlayersAction;

	public State(List<Player> players, int currentPlayersAction) {
		super();
		this.players = players;
		this.currentPlayersAction = currentPlayersAction;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getCurrentPlayersAction() {
		return currentPlayersAction;
	}

	public void setCurrentPlayersAction(int currentPlayersAction) {
		this.currentPlayersAction = currentPlayersAction;
	}

}
