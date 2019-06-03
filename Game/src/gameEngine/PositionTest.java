package gameEngine;

import org.joml.Vector2f;

public class PositionTest {

	private int[][] map;
	private float scaleOfEachTiles;
	
	public PositionTest(int[][] map,float scaleOfEachTiles) {
		this.map=map;
		this.scaleOfEachTiles=scaleOfEachTiles;
		
		
		
		
		
	}
	
	
	
	
	public Vector2f findPositionOnMap(float x,float y) {
	  Vector2f mapPosition=new Vector2f(x/scaleOfEachTiles,y/scaleOfEachTiles);
	
		
		return mapPosition;
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
