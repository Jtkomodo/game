package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattleFormulas;
import battleClasses.BattlePlayerField;
import gameEngine.Start;

public class SingleDamageComponent extends MoveComponent {

	
	
	final static int COMPID=1;
	
	private int damage;
	
	
	public SingleDamageComponent(int damage) {
		super(COMPID);
	   	this.damage=damage;
	   	this.timedButtonDependent=true;
		
	}
	
	
	
	
	public int getDamage() {
		return damage;
	}




	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
		float d=BattleFormulas.CalculateDamage(entityUsingMove, entitySelected,damage);
		entitySelected.decreseHp(d);
	   Start.DebugPrint("Damage="+d);
		
		
	}
	
	@Override
    public void useMoveAfterPress(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected,int gradeOfPress) {
		
		entitySelected.decreseHp(BattleFormulas.CalculateDamage(entityUsingMove, entitySelected,damage,gradeOfPress));

	}


}
