package Data;


import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;


import battleClasses.TimedButtonPress;
import battleMoves.*;
import ScripterCommands.PlaySoundEffect;
import audio.Sound;
import audio.Source;
public enum Moves {
//OLD WAY OF DOING IT KEEPING IT HEAR AS A REFRENCE
//	punch("punch",33,0,false,false,new TimedButtonPress(.5,1,GLFW_KEY_A)),//damage,special,cost,healing move
//	heal("heal",25,25,true,false,new TimedButtonCombo(new TimedButton[]{new TimedButtonPress(.5,1,GLFW_KEY_A),new TimedButtonHold(.5,1,2,GLFW_KEY_D),new TimedButtonPress(.5
//			,.9,GLFW_KEY_A)},2,1))
//	,Doublekick("double kick",50,33,false,false,new TimedButtonCombo(new TimedButton[] {new TimedButtonPress(.5,.9,GLFW_KEY_S),new TimedButtonPress(.5,.9,GLFW_KEY_A)
//			,new TimedButtonPress(.5,.9,GLFW_KEY_D)},3,2)),
//	superHeal("SuperHeal",100,40,true,false);
//	
//	;
	
	
	punch("Lazer",new Move(new MoveComponent[] {
			new SingleDamageComponent(50),
			//new SelfHealComponent(20),
			new SpCostComponent(30), 
			new SoundFXComponent(new PlaySoundEffect[] {
					new PlaySoundEffect(new Source(new Vector2f(0),1,1, 1,200, 0), new Sound("Lazer"),0.5f),
					new PlaySoundEffect(new Source(new Vector2f(0),1,1, 1,200, 0), new Sound("FIreOrDamage"),2.5f)
			})
	})),
	heal("heal",new Move(new MoveComponent[] {
	       new SingleSelectedHealComponent(25),		
		   new SpCostComponent(25),
			new SoundFXComponent(new PlaySoundEffect[] {
					new PlaySoundEffect(new Source(new Vector2f(0),1,1, 1,200, 0), new Sound("healing sound"),0.0f),
				
			}),
	})),
	Doublekick("double kick",new Move(new MoveComponent[] {
			new SingleDamageComponent(50),
			new SpCostComponent(33),
		
	})),
	SupprHeal("SuperHeal",new Move(new MoveComponent[] {
	        new SingleSelectedHealComponent(100),
	        new SpCostComponent(40)		
	        
	}))
	
	;
	
	
	
	
	
	
	
	
	
	
	private String name;
	private Move move;

	Moves(String name,Move move){
		this.name=name;
		this.move=move;
	}

	public String getName() {
		return name;
	}

	public Move getMove() {
		return move;
	}
	
	
	public float getDamage() {
		
		if(this.move.HaSingleDamageComponent()) {
		     SingleDamageComponent c=this.move.getSingleDamageComponent();
			return c.getDamage();
		}else {
			return 0;
		}
		
		
		
	}
	


	public int getCost() {
		if(this.move.HasSpCostComponent()) {
			SpCostComponent c=this.move.getSpCostComponent();
			return c.getCost();
		}else {
			return 0;
		}
	}


	public boolean isSpecailMove() {
		return this.move.HasSpCostComponent();
	}


	public boolean isSingleHeal() {
		return this.move.HasSingleHealComponent();
	}

		
}
