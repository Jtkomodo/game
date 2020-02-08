package guis;

import java.lang.reflect.Method;

import org.joml.Vector2f;

import gameEngine.Start;

public abstract class UIElement {

	
	protected boolean HasFunction=false,hasColor=false,active=false,ChangesState=false;
	protected int state=-1;//this is the number to specify which state to switch to when this element is selected
	protected Vector2f offset;
    protected FunctionCaller Function;
    
    
      public UIElement(Vector2f offset) {
    	  this.offset=offset;
      }
    
    
    
    
	
	protected abstract void drawElement(Vector2f Position);
	

	public boolean isHasFunction() {
		return HasFunction;
	}
	public boolean isActive() {
		return active;
	}
	
	public Vector2f getoffset() {
		return offset;
	}

	public void setoffset(Vector2f offset) {
		this.offset = offset;
	}
	
	
	public int getState() {
		return state;
	}
	
	
	
public void invokeMethod(){
		
	
	this.Function.invoke();
	
		
	}
	

	
	
protected void loadFunction(FunctionCaller Function) {
	
	
		this.HasFunction=true;
		this.Function=Function;
		
	
}




public boolean isChangesState() {
	return ChangesState;
}
public void setChangesState(boolean changesState) {
	ChangesState = changesState;
}

public void changeMethod(FunctionCaller Function) {

    loadFunction(Function);
    

}
}
