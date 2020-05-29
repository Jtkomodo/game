package Data;


import static org.lwjgl.glfw.GLFW.*;

import battleClasses.TimedButton;
import battleClasses.TimedButtonCombo;
import battleClasses.TimedButtonPress;
import battleClasses.TimedButtonHold;

public enum Moves {

	punch("punch",33,0,false,false,new TimedButtonPress(.5,1,GLFW_KEY_A)),//damage,special,cost,healing move
	heal("heal",25,25,true,false,new TimedButtonCombo(new TimedButton[]{new TimedButtonPress(.5,1,GLFW_KEY_A),new TimedButtonHold(.5,1,2,GLFW_KEY_D),new TimedButtonPress(.5
			,.9,GLFW_KEY_A)},2,1))
	,Doublekick("double kick",50,33,false,false,new TimedButtonCombo(new TimedButton[] {new TimedButtonPress(.5,.9,GLFW_KEY_S),new TimedButtonPress(.5,.9,GLFW_KEY_A)
			,new TimedButtonPress(.5,.9,GLFW_KEY_D)},3,2)),
	superHeal("SuperHeal",100,40,true,false);
	
	;

	
	
	
	
	
	
	private int cost;
	private float damage;
	private boolean specailMove,health,TimedButton,AffectsField;
	private String name;
	private TimedButton combo;

	
	
	Moves(String name,float damage,int cost,boolean health,boolean AffectsField,TimedButton combo){
		this.name=name;
		this.damage=damage;
		this.cost=cost;
		this.health=health;
		this.specailMove=(cost>0);
		this.combo=combo;
		this.TimedButton=true;
		this.AffectsField=AffectsField;
		
	}	
	
	Moves(String name,float damage,int cost,boolean health,boolean AffectsField){
		this.name=name;
		this.damage=damage;
		this.cost=cost;
		this.health=health;
		this.specailMove=(cost>0);
		this.TimedButton=false;
		this.AffectsField=AffectsField;
		
	}	
	
	
	public boolean isAffectsField() {
		return AffectsField;
	}

	public String getName() {
		return name;
	}

	
	public float getDamage() {
		return damage;
	}


	public int getCost() {
		return cost;
	}


	public boolean isSpecailMove() {
		return specailMove;
	}


	public boolean isHeal() {
		return health;
	}

	public boolean isTimedButton() {
		return TimedButton;
	}


	public TimedButton getCombo() {
		return combo;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
}
