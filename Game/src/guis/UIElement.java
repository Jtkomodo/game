package guis;

import java.lang.reflect.Method;

import org.joml.Vector2f;

import gameEngine.Start;

public abstract class UIElement {

	protected Class baseCLass=GUIMEthods.class;
	protected Object[] arguments;
	protected boolean HasFunction=false,hasColor=false,active=false,ChangesState=false;
	protected int state=-1;//this is the number to specify which state to switch to when this element is selected
	protected Vector2f offset;
    protected Object objToCallfrom;
    protected Method Function;//function to call
    
    
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
	
	
	
public void invokeMethod() throws Exception {
		
	
	this.Function.invoke(this.objToCallfrom,this.arguments);
	
		
	}
	

	
	
protected void loadFunction(String functionName,Class[] argumentTypes) {
	
	try {
		this.HasFunction=true;
		this.Function=this.baseCLass.getMethod(functionName,argumentTypes);
		
	} catch (NoSuchMethodException e) {
		
		e.printStackTrace();
		System.exit(3007);	
	} catch (SecurityException e) {
		
		e.printStackTrace();
		System.exit(3002);
	}
	
}




public boolean isChangesState() {
	return ChangesState;
}
public void setChangesState(boolean changesState) {
	ChangesState = changesState;
}

public void changeMethod(String functionName,Object[] arguments) {
	Class[] cArg; 
	if(arguments!=null) {
		 cArg= new Class[arguments.length];
		
		 for(int i=0;i<arguments.length;i++) {
			 cArg[i]=arguments[i].getClass();
		 }
	}else {
		cArg=null;
	}
    
    loadFunction(functionName, cArg);
    

}
public void changeParameters(Object[] arguments) {
     this.arguments=arguments;
}
}
