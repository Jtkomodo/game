package enemyAI;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import Data.Moves;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;

public class SelfHealthCheckMoveAI extends EnemyAIMoveComponent {

	
	
	private static final int COMPID=0;
	
	private float percentage;
	
	
	
	
	public SelfHealthCheckMoveAI(int Priorety,float percentage) {
		super(COMPID, Priorety);
		this.percentage=percentage;
	}

	@Override
	public Moves FindMove(BattlePlayerField pcs, BattleEnemyField enemies, Enemy entityUsingMove, Moves[] moves) {
		Moves moveUsed=null; 
		if(entityUsingMove.getHp()<=(entityUsingMove.getMaxHP()*this.percentage)) {
			
			LinkedList<Moves> healingMoves=new LinkedList<Moves>();
	     
			for(int i=0;i<moves.length;i++) {
				Moves move=moves[i];
				if(move.isSingleHeal()) {
					healingMoves.add(move);
				}	 
			}
	     
			Comparator<Moves> c=Comparator.comparing(Moves::getCost);
	        Collections.sort(healingMoves,c);
		    
	        if(!healingMoves.isEmpty()) {
	        	this.EntityToUseMoveON=entityUsingMove;
	        	this.usedMove=healingMoves.get(0);
	        	moveUsed=this.usedMove;
	        }
	        
	        
		
		
		}
		return moveUsed;
	}
	
}
