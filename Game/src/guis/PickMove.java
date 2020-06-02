package guis;

import Data.Moves;
import battleClasses.BattleEntity;
import gameEngine.BattleSystem;
import gameEngine.Start;

public class PickMove extends GUIfunction {

	BattleEntity p;
	String move;
	
	public PickMove(BattleEntity p,String move) {
		
		this.p=p;
		this.move=move;
		
	}
	
	
	
	@Override
	public void invoke() {
		 Moves m=p.getmoveFromString(move);
		 Start.soundPlay=true;
		 if(m!=null) {
			 
		
		 Start.DebugPrint("Picked move "+m.getName()+"--");
		 
		 Start.currentlyUsedMove=m;
		 
		 boolean used=p.testIfMoveCanBeUsed(m);
		 if(used) {
		 if(m.isTimedButton()) {
			BattleSystem.setButton(m.getCombo());
		 }
		 BattleSystem.setMoveCalled(true); 
		 BattleSystem.setMoveInprogress(false);
		
		 }else {
			 Start.soundPlay=false;
			 Start.source.play(Start.NO);
		 }
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 
		 }

	}

}
