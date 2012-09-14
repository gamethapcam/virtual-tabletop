package com.brokenshotgun.virtualtabletop.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import com.brokenshotgun.virtualtabletop.model.GameMap;
import com.brokenshotgun.virtualtabletop.model.Player;
import com.brokenshotgun.virtualtabletop.model.Position;

/**
 * Tests for game map logic.
 * 
 * @author JP <jp@brokenshotgun.com>
 */
public class GameMapLogicTests {
	Player testPlayer1;
	Player testPlayer2;
	Player testPlayer3;
	String player1Id = "player_1";
	String player2Id = "player_2";
	String player3Id = "player_3";
	GameMap testMap;
	int width = 10, height = 10;
	
	@Before
	public void setupTest() {
		testMap = new GameMap(width, height);
		testPlayer1 = new Player(player1Id);
		testPlayer2 = new Player(player2Id);
		testPlayer3 = new Player(player3Id);
	}
	
	@Test
	public void testValidAddPlaceable() {
		assertThat(testMap.movePlaceable(testPlayer1, new Position(0, 0)), is(true));
	}
	
	@Test
	public void testOutOfBoundsAddPlaceable() {
		assertThat(testMap.movePlaceable(testPlayer1, new Position(-1, 0)), is(false));
		assertThat(testMap.movePlaceable(testPlayer1, new Position(0, -1)), is(false));
		assertThat(testMap.movePlaceable(testPlayer1, new Position(10, 0)), is(false));
		assertThat(testMap.movePlaceable(testPlayer1, new Position(0, 10)), is(false));
	}
	
	@Test
	public void testAddPlaceableToNotPlaceableTile() {
		Position notPlaceablePosition = new Position(1, 1);
		testMap.setTilePlaceable(notPlaceablePosition, false);
		assertThat(testMap.movePlaceable(testPlayer1, notPlaceablePosition), is(false));
	}
	
	@Test
	public void testValidMovePlaceable() {
		assertThat(testMap.movePlaceable(testPlayer1, new Position(0, 0)), is(true));
		assertThat(testMap.movePlaceable(testPlayer2, new Position(0, 0)), is(true));
		assertThat(testMap.movePlaceable(testPlayer3, new Position(0, 0)), is(true));
		
		Position oldPosition = testPlayer1.getPosition();
		Position newPosition = new Position(1, 1);
		assertThat(testMap.movePlaceable(testPlayer1, newPosition), is(true));
		
		assertThat(testMap.getTile(oldPosition).getPlaceableList(), not(hasItem(testPlayer1)));
		assertThat(testMap.getTile(newPosition).getPlaceableList(), hasItem(testPlayer1));
	}
}
