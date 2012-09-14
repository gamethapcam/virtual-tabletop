package com.brokenshotgun.virtualtabletop.model;

public interface Placeable {
	
	public Position getPosition();
	public void move(Position p);

}
