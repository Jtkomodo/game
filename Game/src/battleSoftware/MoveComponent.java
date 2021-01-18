package battleSoftware;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public abstract class MoveComponent extends SoftwareComponent {


	
	protected boolean Started=false;
	protected boolean Finished=false;
	protected BattlePlayerField pcs;
	protected BattleEnemyField enemeis;
	protected BattleEntity entitySelected,EntityUsingMove;
	
	
	public MoveComponent(int ID) {
		super(ID);
		this.Is_Move=true;
	}
    
	public void SartMove(BattlePlayerField pcs,BattleEnemyField enemeis,BattleEntity entityUsingMove,BattleEntity entitySelected) {
		this.Started=true;
		this.Finished=false;
		this.pcs=pcs;
		this.enemeis=enemeis;
		this.EntityUsingMove=entityUsingMove;
		this.entitySelected=entitySelected;
	}
	public void updateMove() {
		
	}
	public abstract void endMove(int TimeButtonResult);
	public boolean canMoveBeUsed(BattlePlayerField pcs,BattleEnemyField enemeis,BattleEntity entityUsingMove,BattleEntity entitySelected) {
		return true;
	}
	public boolean isComponentDone(BattlePlayerField pcs,BattleEnemyField enemeis,BattleEntity entityUsingMove,BattleEntity entitySelected) {
		return true;
	}
	
	
}
