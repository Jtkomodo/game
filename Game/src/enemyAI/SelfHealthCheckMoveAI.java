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
		float hp=entityUsingMove.getHp();
		float maxHP=entityUsingMove.getMaxHP();
		
		if(hp<=(maxHP*this.percentage)) {
			
			LinkedList<Moves> healingMoves=new LinkedList<Moves>();
		
			for(int i=0;i<moves.length;i++) {
				Moves move=moves[i];
				if(move.isSinglSelectedeHeal()) {
					healingMoves.add(move);
				}	 
			}
			
			
			
	        Comparator<Moves> c=Comparator.comparing(Moves::getSingleSelectedHealingAmount);
	        Collections.sort(healingMoves,c);
		    
	        float amountNeededToHeal=maxHP-hp;
	        Moves foundMove=null;//this is the move that is the best one to use
	        if(!healingMoves.isEmpty()) {
	        for(int i=healingMoves.size()-1;i>=0;i--) {	
	            
	        	Moves move=healingMoves.get(i);
	        	float cost=move.getCost();
	        	
	        	
	        	if(cost<=entityUsingMove.getSp()) {
	        		if(foundMove==null) {
	        		foundMove=move;
	        		}else {
	        			float costF=foundMove.getCost();
	        			if((move.getCost()<costF) && (move.getSingleSelectedHealingAmount()>=(amountNeededToHeal-(amountNeededToHeal*.05)))){//if the cost is less than the last move and heals at least 95%
	        				//of the amount needed to heal then replace the last move with this one
	        				
	        				foundMove=move;
	        			}
	        			
	        			
	        		}
	        		
	        	}
	        	
	        	
	        	
	        	
	        }
	        
	        }
	        
	        this.EntityToUseMoveON=entityUsingMove;
        	this.usedMove=foundMove;
        	moveUsed=this.usedMove;
		
		
		}
		return moveUsed;
	}
	
}
