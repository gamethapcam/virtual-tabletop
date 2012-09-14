package com.brokenshotgun.virtualtabletop.model;

public class Player implements Placeable {
	
	private String playerId;
	private Position position;

	public Player(String playerId) {
		this.playerId = playerId;
		position = new Position(0, 0);
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void move(Position p) {
		position = p;
	}
}
