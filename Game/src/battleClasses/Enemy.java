package battleClasses;

import Data.Moves;
import Items.Inventory;

public class Enemy extends BattleEntity{

	public Enemy(float atk, float def, float hp,float sp, Moves[] moves,Inventory inventory) {
		super(atk, def, hp,sp, moves,inventory);
		
	
	}

	

	private void useMove(BattleEntity player) {
		
		//this is where the enemy will decide which move to use against the player based on the player's current hp and stats
		
		
	}
	
	private void useItem() {
		//this is where the enemy will decide which item from it's own inventory it will use in times of danger healing items take priority
		
		
	}
	
	public void takeTurn(BattleEntity player) {
		//this is going to be the method that is called when it is this enemy's turn it will do all the calculations to find out whether to attack or use a item
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
