package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public class SingleSelectedHealComponent extends MoveComponent {
    
	//heals the selected entity
	final static int COMPID=2;
	int heal;
	
	
	public SingleSelectedHealComponent(int heal) {
		super(COMPID);
		this.heal=heal;
		
	}
	
	
	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
		
	      entitySelected.IncreseHp(heal);
		
		

	}

}
