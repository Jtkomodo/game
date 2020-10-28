package guis;

import Data.Moves;
import battleClasses.BattleEntity;
import gameEngine.BattleSystem;
import gameEngine.Start;

public class PickMove extends GUIfunction {

	
	String move;
	
	public PickMove(String move) {
		
	
		this.move=move;
		
	}
	
	
	
	@Override
	public boolean invoke() {
		 BattleEntity p=BattleSystem.getCurrentEntity();
		 Moves m=p.getmoveFromString(move);
		 Start.soundPlay=true;
		 if(m!=null) {
			 
		
		 Start.DebugPrint("Picked move "+m.getName()+"--");
		 
		 Start.currentlyUsedMove=m;
		 
		 boolean used=p.testIfMoveCanBeUsed(m);
		 if(used) {
			 BattleSystem.setCurrentlyUsedMove(m);
		if(!m.isSinglSelectedeHeal()) {
		 BattleSystem.setSelectingEnemy(true);
		 BattleSystem.setMoveCalled(true); 
		
		}else {
			
			 BattleSystem.setSelectingPC(true);
			 BattleSystem.setMoveCalled(true); 
			
			
			
		}
		return true;
		}else {
			return false;
		 }
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 return false;
		 }

	}

}
