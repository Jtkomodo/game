package battleMoves;

import Scripter.Proccesor;
import ScripterCommands.PlaySoundEffect;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import gameEngine.Start;

public class SoundFXComponent extends MoveComponent {

	static final int COMPID=5;
	private PlaySoundEffect[] sounds;
	
	public SoundFXComponent(PlaySoundEffect[] sounds) {
		super(COMPID);
		this.sounds=sounds;
	}
	
	
	
	
	
	@Override
	void useMove(BattlePlayerField pcs, BattleEnemyField enemies, BattleEntity entityUsingMove,BattleEntity entitySelected) {
	        for(int i=0;i<this.sounds.length;i++) {
	        	  PlaySoundEffect sounfx=sounds[i];
	        	  Proccesor.addComandtoItorator(sounfx);
	        }

	}
	@Override
    public boolean isComponentDone(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected) {
	     boolean moveDone=true;
		for(int i=0;i<this.sounds.length;i++) {
			  PlaySoundEffect sounfx=sounds[i];
			if(!sounfx.iscompleted() || !sounfx.hasBeeenReset() || sounfx.isPlayingSound()) {//if any of these have not been completed break and set return value to false
				moveDone=false;
				//Start.DebugPrint("SOUNDFX_COMPONENT_NOT_FINISHED");
				break;
				
			}
			
		}
		
		if(moveDone) {
			Start.DebugPrint("SOUNDFX_COMPONENT_FINISHED");
		    
		}
		
		
		return moveDone;
	
	}
	

}
