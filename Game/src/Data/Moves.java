package Data;


import static org.lwjgl.glfw.GLFW.*;

import battleClasses.TimedButton;
import battleClasses.TimedButtonCombo;
import battleClasses.TimedButtonPress;
import battleClasses.TimedButtonHold;

public enum Moves {

	punch("punch",33,0,false,new TimedButtonPress(2,2.5,GLFW_KEY_SPACE)),//damage,special,cost,healing move
	heal("heal",25,25,true,new TimedButtonCombo(new TimedButton[]{new TimedButtonPress(2,2.5,GLFW_KEY_SPACE),new TimedButtonHold(1,1.4,2,GLFW_KEY_RIGHT)},2,1))
	,Doublekick("double kick",50,33,false,new TimedButtonCombo(new TimedButton[] {new TimedButtonPress(3,3.39,GLFW_KEY_SPACE),new TimedButtonPress(3,3.39,GLFW_KEY_SPACE)},2,1)),
	superHeal("SuperHeal",50,100,true)
	
	;

	
	
	
	
	
	
	private int cost;
	private float damage;
	private boolean specailMove,health,TimedButton;
	private String name;
	private TimedButton combo;

	
	
	Moves(String name,float damage,int cost,boolean health,TimedButton combo){
		this.name=name;
		this.damage=damage;
		this.cost=cost;
		this.health=health;
		this.specailMove=(cost>0);
		this.combo=combo;
		this.TimedButton=true;
		
		
	}	
	
	Moves(String name,float damage,int cost,boolean health){
		this.name=name;
		this.damage=damage;
		this.cost=cost;
		this.health=health;
		this.specailMove=(cost>0);
		this.TimedButton=false;
		
		
	}	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setTimedButton(boolean timedButton) {
		TimedButton = timedButton;
	}

	public TimedButton getCombo() {
		return combo;
	}

	public void setCombo(TimedButton combo) {
		this.combo = combo;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
}
