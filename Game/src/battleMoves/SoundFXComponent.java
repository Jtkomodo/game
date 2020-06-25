package battleMoves;

import Scripter.Proccesor;
import ScripterCommands.PlaySoundEffect;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;

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

}
