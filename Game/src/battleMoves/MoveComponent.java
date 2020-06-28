package battleMoves;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

public abstract class MoveComponent {

	
  
	protected boolean timedButtonDependent=false;
    private int ID;

	protected MoveComponent (int ID){
		this.ID=ID;
	}
	
	

	//this will be what happens when the move is used
	abstract void useMove(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected);

    	
	
	//override this to give the move Component a condition like it the entity needs a certain amount of sp or something ,but we can go as crazy as we want like maybe
	//there needs to be a specific hardware equipped or somthing
	boolean canMoveBeUsed(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected) {
		
		return true;
		
	}
	public void useMoveAfterPress(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected,int gradeOfPress) {
		
		
	}
	public boolean isComponentDone(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected) {
		
		return true;
	}
	
	
    
	
	public int getID() {
		return this.ID;
	}



	public boolean isTimedButtonDependent() {
		return timedButtonDependent;
	}



	


	
}
