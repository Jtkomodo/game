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
	public void invoke() {
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
		}else {
			 Start.soundPlay=false;
			 Start.source.play(Start.NO);
		 }
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 
		 }

	}

}
