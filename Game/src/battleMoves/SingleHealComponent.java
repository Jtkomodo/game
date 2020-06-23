package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public class SingleHealComponent extends MoveComponent {
    
	final static int COMPID=2;
	int heal;
	
	
	public SingleHealComponent(int heal) {
		super(COMPID);
		this.heal=heal;
		
	}
	
	
	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
		
	      entitySelected.IncreseHp(heal);
		
		

	}

}
