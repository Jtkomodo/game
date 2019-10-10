package gameEngine;


import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

public class UIBox {

	
	private int currentState=0;
	private boolean left=false,right=false,up=false,down=false,select=false,back=false,isActive=true;
	private Vector2f position;
	
	private List<UIBoxState> statelist=new ArrayList<UIBoxState>();	
	private List<UIBoxState> alwaysShownStateList=new ArrayList<UIBoxState>();	
	
	public UIBox(Vector2f position,UIBoxState[] states) {
		
		this.position=position;
		
		for(int i=0;i<states.length;i++) {
			statelist.add(states[i]);
			if(states[i].isAlwaysShown()) {
				alwaysShownStateList.add(states[i]);
			}
			
			
			}
			
		statelist.get(0).setHasarrow(true);
		
	}
	
	
	
	public void setPosition(Vector2f position) {
		this.position=position;
	}
	
	
	
	public void draw() {
		
		if(isActive) {
		UIBoxState UIstate=statelist.get(currentState);
		
		for(int i=0;i<alwaysShownStateList.size();i++) {
			UIBoxState	UIstates=alwaysShownStateList.get(i);
			UIstates.drawBox(this.position);	
			}
	    	UIstate.drawBox(this.position);
		
		}
		
		
	}
	
	
    public void draw(int state) {
		
    	if(isActive) {
    	UIBoxState UIstate=statelist.get(state);
    	for(int i=0;i<alwaysShownStateList.size();i++) {
    		UIBoxState	UIstates=alwaysShownStateList.get(i);
    		UIstates.drawBox(this.position);	
    		}
        	UIstate.drawBox(this.position);
    	}
    	
    	
		
	}
 public void drawAndSetState(int state) {
		
    	setCurrentState(state);
    	UIBoxState UIstate=statelist.get(state);
    	
    	
    	for(int i=0;i<alwaysShownStateList.size();i++) {
		UIBoxState	UIstates=alwaysShownStateList.get(i);
		UIstates.drawBox(this.position);	
		}
    	UIstate.drawBox(this.position);
	}
	
	public void GoLeft() {
		this.left=true;
		
	}
	
	public boolean checkStateOfleft() {
		
		boolean result= this.left;
		this.left=false;
		return result;
		
		
	}
	
	
	public void GoRight() {
		this.right=true;
		
	}
	
	public boolean checkStateOfRight() {
		
		boolean result= this.right;
		this.right=false;
		return result;
		
		
	}
	
	public void GoUp() {
		this.up=true;
		
	}
	
	public boolean checkStateOfUp() {
		
		boolean result= this.up;
		this.up=false;
		return result;
		
		
	}
	
	public void GoDown() {
		this.down=true;
		
	}
	
	public boolean checkStateOfDown() {
		
		boolean result= this.down;
		this.down=false;
		return result;
		
		
	}
	
	
	public void Select() {
		this.select=true;
		
	}
	
	public boolean checkStateOfSelect() {
		
		boolean result= this.select;
		this.select=false;
		return select;
		
		
	}
	
	
	public void GoBack() {
		this.back=true;
		
	}
	
	public boolean checkStateOfBack() {
		
		boolean result= this.back;
		this.back=false;
		return result;
		
		
	}



  public void show() {
	isActive=true;  
  }
  
  public void hide() {
	  isActive=false;
  }
 public boolean isActive() {
	 
	 return isActive;
 }



	public int getCurrentState() {
		return currentState;
	}

    public void setCurrentState(int currentState) {
    	statelist.get(this.currentState).setHasarrow(false);
    	this.currentState = currentState;
		statelist.get(currentState).setHasarrow(true);
	}
    
    public UIBoxState getUIState() {
    	return this.statelist.get(currentState);
    	
    }
    public UIBoxState getUIState(int index) {
    	return this.statelist.get(index);
    	
    }
}
