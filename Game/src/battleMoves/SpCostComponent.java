package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public class SpCostComponent extends MoveComponent {

	final static int COMPID=3;
	
	
	private int cost;
	
	public SpCostComponent(int cost) {
		super(COMPID);
		this.cost=cost;
	
	}
	
	
	
	
	public int getCost() {
		return cost;
	}




	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
	
           entityUsingMove.decreseSp(cost);
		
		
		
		
	}

	@Override
	boolean canMoveBeUsed(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected) {
		
		float sp=entityUsingMove.getSp();
		return (sp>this.cost);
		
		
	}
	
	
	
}
