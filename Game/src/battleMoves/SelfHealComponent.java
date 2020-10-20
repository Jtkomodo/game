package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public class SelfHealComponent extends MoveComponent {

	//heals only self
	
	
    final static int COMPID=4;
		
	private int hp;
	
	
	public SelfHealComponent(int hp) {
		super(COMPID);
		this.hp=hp;
	}


	
	
	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
		entityUsingMove.IncreseHp(hp);
	
	}
	
	public int getHp() {
		return this.getHp();
	}
	

}
