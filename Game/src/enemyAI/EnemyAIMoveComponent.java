package enemyAI;

import java.util.ArrayList;

import Data.Moves;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;
import gameEngine.BattleSystem;

public abstract class EnemyAIMoveComponent extends EnemyAIComponent {

	private static final boolean IS_ITEM_COMPONENT=false;
	private int ID;//every component has a static ID that is unique
	protected BattleEntity EntityToUseMoveON=null;
	protected Moves usedMove=null;
	
	
	public EnemyAIMoveComponent(int ID,int Priorety) {
		super(IS_ITEM_COMPONENT,Priorety);
        this.ID=ID;
		}

	
	
	
	public abstract Moves FindMove(BattlePlayerField pcs,BattleEnemyField enemies,Enemy entityUsingMove,Moves[] moves);//this is called first to find a move and store it	
	public void UsefoundMove(BattlePlayerField pcs, BattleEnemyField enemies, Enemy entityUsingMove) {
		this.usedMove.getMove().useMove(pcs, enemies, entityUsingMove, this.EntityToUseMoveON);
	    BattleSystem.setCurrentlyUsedMove(this.usedMove);
	    BattleSystem.setMoveUsed(true);
	    
	}
	
	//getters
	
    public int getID() {
		return ID;
	}
	
 
	
	
	
	
	
	
	
	
	
	
}
