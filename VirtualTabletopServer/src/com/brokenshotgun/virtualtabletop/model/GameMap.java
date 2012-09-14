package com.brokenshotgun.virtualtabletop.model;

public class GameMap {

	private int width;
	private int height;
	private MapTile[][] tileArray;
	
	/**
	 * Create a new game map.
	 * 
	 * By default, all positions are placeable.
	 * 
	 * @param width
	 * @param height
	 */
	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;
		tileArray = new MapTile[width][height];
		for(int x=0; x<width; ++x) {
			for(int y=0; y<height; ++y) {
				tileArray[x][y] = new MapTile(new Position(x, y), true);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Get the tile at the specified position.
	 * 
	 * @param position
	 * @return the tile at the position if valid, otherwise null
	 */
	public MapTile getTile(Position position) {
		if(isValid(position)) {
			return tileArray[position.getX()][position.getY()];
		}
		return null;
	}
	
	/**
	 * Move placeable object to a new position on the map.
	 * 
	 * This will update the placeable position, remove it from the tile at the 
	 * old position (if it exists), and will add it to the tile at the new position.
	 * 
	 * @param placeable
	 * @param newPosition
	 * @return true if placeable object was moved, false if new position is not placeable
	 */
	public boolean movePlaceable(Placeable placeable, Position newPosition) {
		if(isPlaceablePosition(newPosition)) {
			Position oldPosition = placeable.getPosition();
			placeable.move(newPosition);
			if(isValid(oldPosition)) {
				tileArray[oldPosition.getX()][oldPosition.getY()].removePlaceable(placeable);
			}
			tileArray[newPosition.getX()][newPosition.getY()].addPlaceable(placeable);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if position is not out of bounds.
	 * 
	 * @param position
	 * @return
	 */
	public boolean isValid(Position position) {
		return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
	}
	
	/**
	 * Checks if position is on a placeable tile.
	 * 
	 * @param position
	 * @return true if the tile at the position is placeable, false if the position is not placeable
	 * or is out of bounds
	 */
	public boolean isPlaceablePosition(Position position) {
		if(isValid(position)) {
			return tileArray[position.getX()][position.getY()].isPlaceable();
		}
		return false;
	}
	
	/**
	 * Set if the game tile at the given position should be placeable.
	 * 
	 * @param position
	 * @param isPlaceable
	 */
	public void setTilePlaceable(Position position, boolean isPlaceable) {
		if(isValid(position)) {
			tileArray[position.getX()][position.getY()].setPlaceable(isPlaceable);
		}
	}
}
