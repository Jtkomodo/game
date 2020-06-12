package guis;


import java.util.LinkedList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import static org.lwjgl.glfw.GLFW.*;
import org.joml.Vector2f;

import audio.Sound;
import audio.Source;
import battleClasses.BattleEntity;
import gameEngine.Start;
import input.GetInput;
import input.InputHandler;


public class UIBox {

	private int currentState=0;
	
	private boolean isActive=false;

	private Vector2f position;
	private static boolean isAnyOpened;
	private Sound selectSound=Start.Select,MovementSound=Start.Move,BackSound=Start.Back;
	private Source source=new Source(new Vector2f(0), 1, 1, 0, 0,0);
	private static List<UIBox> opended=new LinkedList<UIBox>();
	private List<UIBoxState> statelist=new LinkedList<UIBoxState>();	
	private List<UIBoxState> alwaysShownStateList=new LinkedList<UIBoxState>();	
	private Stack<Integer> backStack=new Stack<Integer>();//this is a stack that just sores the previous states
	
	public UIBox(Vector2f position,UIBoxState[] states) {
		source.setSourceRelitive(true);
		this.position=position;
		
		for(int i=0;i<states.length;i++) {
			statelist.add(states[i]);
			if(states[i].isAlwaysShown()) {
				alwaysShownStateList.add(states[i]);
			}
			
			
			}
			
		statelist.get(0).setActive(true);
		
	}
	
   
	
	public int/*returns the id for the state to use later*/ addUIState(UIBoxState state) {

		statelist.add(state);
		if(state.isAlwaysShown()) {
			alwaysShownStateList.add(state);
		}
		
		return this.statelist.size()-1;
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
    
    
    
public void Update() {
	if(isActive) {
		
	int up=InputHandler.getStateofButton(GLFW_KEY_UP),down=InputHandler.getStateofButton(GLFW_KEY_DOWN)
	,left=InputHandler.getStateofButton(GLFW_KEY_LEFT),right=InputHandler.getStateofButton(GLFW_KEY_RIGHT),
    Enter=InputHandler.getStateofButton(GLFW_KEY_ENTER),backspace=InputHandler.getStateofButton(GLFW_KEY_BACKSPACE);
	boolean movement=false;
	boolean entered=false;
	if(up==1) 
	  movement=GoUp();
	if(down==1)	
	   movement=GoDown();
	if(left==1)
		movement=GoLeft();
	if(right==1)
		movement= GoRight();
	if(Enter==1) {
	   entered= Select();
	
	if(Start.soundPlay && entered) {source.play(selectSound);
	}else if(entered==false && Start.soundPlay==true) {
		source.play(Start.NO);
	}
	
	
	}
	if(backspace==1) {
     if(GoBack()) {
	   source.play(BackSound);	    	 
		     }
		     
	}
    if(movement) {
	  source.play(MovementSound);	
	}
	
	
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
	
	public boolean GoLeft() {
		return statelist.get(currentState).DecreasePositionIndeX();		
		
	}
	
	
	public boolean GoRight() {
		return statelist.get(currentState).IncreasePositionIndexX();		
		
	}
	
	
	
	public boolean GoUp() {
		return statelist.get(currentState).IncreasePositionIndexY();		
	}
	
	
	public boolean GoDown() {
		return statelist.get(currentState).DecreasePositionIndexY();		
		
	}
	
	
	
	
	public boolean Select() {//will return -1 if this changes state otherwise returns a value for us to tell what function is used
	
		boolean returnB=false;
		UIBoxState state=this.statelist.get(currentState);
	
	    UIElement e=state.getActiveEllement();
	  
	if(e!=null && state.isAnyActive()) {	
		int stateToChangeTo=e.getState();//this is just telling us what happens if this string element is clicked on
		if(stateToChangeTo!=-1) {//-1 means this is not a element that goes to another state
			backStack.push(this.currentState);
			setCurrentState(stateToChangeTo);
			returnB=true;
		}else if(e.isHasFunction()) {
		
				e.invokeMethod();
		returnB=true;
			
		}
		
		}
		
		
		return returnB;
		
	
	}
	

	
	
	public boolean GoBack() {
	
		try{
		    int lastState=backStack.pop();
		
		    setCurrentState(lastState);
			return true;}
	catch(EmptyStackException e) {
	return false;
	
 
	}
	
	}



  public void show() {
	
		
	  isActive=true;
	isAnyOpened=true;
	opended.add(this);
	
	InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ENTER,GLFW_KEY_BACKSPACE});
	
  }
  
  public void hide() {
	  if(isActive) {
		  
	  isActive=false;
	  opended.remove(this);
	  isAnyOpened=!(opended.isEmpty());}
  }
 public boolean isActive() {
	 
	 return isActive;
 }

  public  void reset() {
       
       getUIState().reset();
       setCurrentState(0);
       getUIState().reset();
	  this.backStack.clear();
	  
	  
  }

  
	public UIBoxState getCurrentState() {
		return this.statelist.get(currentState);
	}

    public void setCurrentState(int newState) {
   
    	getUIState().setActive(false);
    	
    	this.currentState=newState;
    	getUIState().setActive(true);
    	
    	
      
    }
    
    public UIBoxState getUIState() {
    	return this.statelist.get(currentState);
    	
    }
    public UIBoxState getUIState(int index) {
    	if(index<this.statelist.size()) {
    	return this.statelist.get(index);
    	}else {
    		return this.statelist.get(this.statelist.size()-1);
    	}
    }


	public static  boolean isOpened() {
		return isAnyOpened;
	}
    


	
}
	
