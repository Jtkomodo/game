package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public class SelfHealComponent extends MoveComponent {

	//heals only self
	
	
    final static int COMPID=4;
		
	private float hp;
	
	
	public SelfHealComponent(float hp) {
		super(COMPID);
		this.hp=hp;
	}


	
	
	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
		entityUsingMove.IncreseHp(hp);
	
	}

}
