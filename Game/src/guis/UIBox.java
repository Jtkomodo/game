package guis;


import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import org.joml.Vector2f;

import battleClasses.Player;
import gameEngine.Start;

public class UIBox {

	private int currentState=0;
	
	private boolean isActive=true;
	private Player p=Start.p;

	private Vector2f position;
	
	private List<UIBoxState> statelist=new ArrayList<UIBoxState>();	
	private List<UIBoxState> alwaysShownStateList=new ArrayList<UIBoxState>();	
	private Stack<Integer> backStack=new Stack<Integer>();//this is a stack that just sores the previous states
	
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
		statelist.get(currentState).DecreasePositionIndeX();		
		
	}
	
	
	public void GoRight() {
		statelist.get(currentState).IncreasePositionIndexX();		
		
	}
	
	
	
	public void GoUp() {
		statelist.get(currentState).IncreasePositionIndexY();		
	}
	
	
	public void GoDown() {
		statelist.get(currentState).DecreasePositionIndexY();		
		
	}
	
	
	
	
	public void Select() {//will return -1 if this changes state otherwise returns a value for us to tell what function is used
	
		UIBoxState state=this.statelist.get(currentState);
		int liststate=state.getOffsetPositionOnlist();
	    UIStringElement e=state.getStringActiveElement(liststate);
			
	if(e!=null) {	
		int stateToChangeTo=e.getState();//this is just telling us what happens if this string element is clicked on
		if(stateToChangeTo!=-1) {//-1 means this is not a element that goes to another state
			backStack.push(this.currentState);
			setCurrentState(stateToChangeTo);
			
		}else if(e.isHasFunction()) {
			try {
				e.invokeMethod();
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}
		
		
		}
		}
		
		
		
		
	
	}
	

	
	
	public void GoBack() {
		try{
		    int lastState=backStack.pop();
			
		    setCurrentState(lastState);}
	catch(EmptyStackException e) {
	
	
 
	}
	
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

  public  void reset() {
	setCurrentState(getUIState(currentState).getBeginElement());
	backStack.clear();
  }

	public int getCurrentState() {
		return currentState;
	}

    public void setCurrentState(int currentState) {
   
        statelist.get(this.currentState).setHasarrow(false);
    	this.currentState = currentState;
    	statelist.get(currentState).setOffsetPositionOnlist(getUIState(currentState).getBeginElement());
        statelist.get(currentState).setHasarrow(true);
    
    }
    
    public UIBoxState getUIState() {
    	return this.statelist.get(currentState);
    	
    }
    public UIBoxState getUIState(int index) {
    	return this.statelist.get(index);
    	
    }
}
	
