package com.playingcard.player;

import java.util.List;

public class Player {

	private List<Hand> hands;
	private String name;
	private int chips;

	public Player(String name, int chips) {
		super();
		this.name = name;
		this.chips = chips;
	}

	public void bet(int chips){
		this.chips -= chips;
	}

	public void collectChips(int chips) {
		this.chips += chips;
	}

	public int getChipCount() {
		return chips;
	}
	
	public List<Hand> getHands() {
		return hands;
	}

	public void setHands(List<Hand> hands) {
		this.hands = hands;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player: " + name + "\n");
		sb.append("Chip count: " + chips + "\n");
		for (int i = 0; i < hands.size(); i++)
			sb.append("Hand " + (i + 1) + ": " + hands.get(i).toString() + "\n");
		return sb.toString();
	}
}
