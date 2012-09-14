package com.brokenshotgun.virtualtabletop.model;

import java.util.ArrayList;
import java.util.List;

public class MapTile {
	
	private Position position;
	private boolean isPlaceable;
	private List<Placeable> placeableList;
	
	public MapTile(Position position, boolean isPlaceable) {
		this.position = position;
		this.isPlaceable = isPlaceable;
		placeableList = new ArrayList<Placeable>();
	}
	
	/**
	 * Add a placeable object to this game tile.
	 * @param p
	 */
	public void addPlaceable(Placeable p) {
		placeableList.add(p);
	}
	
	/**
	 * Remove placeable object from this game tile.
	 * @param p
	 */
	public void removePlaceable(Placeable p) {
		placeableList.remove(p);
	}
	
	/**
	 * Get a list of placeable objects currently on this tile.
	 * @return
	 */
	public List<Placeable> getPlaceableList() {
		return placeableList;
	}

	public Position getPosition() {
		return position;
	}

	public boolean isPlaceable() {
		return isPlaceable;
	}

	public void setPlaceable(boolean isPlaceable) {
		this.isPlaceable = isPlaceable;
	}

}
