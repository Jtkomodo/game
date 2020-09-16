package enemyAI;

import java.util.LinkedList;

import Data.Moves;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;

public class EnemyUseMostPowerfulMoveOnMostHPAI extends EnemyAIMoveComponent {

	
	final static int COMPID=3;
	
	
	
	
	
	public EnemyUseMostPowerfulMoveOnMostHPAI(int Priorety) {
		super(COMPID, Priorety);
		
	}

	@Override
	public Moves FindMove(BattlePlayerField pcs, BattleEnemyField enemies, Enemy entityUsingMove, Moves[] moves) {
		Moves foundMove=null;
		
		BattleEntity[] pcArray=pcs.getAlivePCs();
		LinkedList<Moves> moveList=new LinkedList<Moves>();
	
		
		
				
		
		return foundMove;
	}

}
