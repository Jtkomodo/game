package guis;



import java.lang.reflect.Method;
import org.joml.Vector2f;
import org.joml.Vector4f;


import gameEngine.Start;
import textrendering.TextBuilder;

public class UIStringElement {
	
    private Class baseCLass=GUIMEthods.class;
    private Object objToCallfrom;
	private Vector2f offset;
	private TextBuilder text=new TextBuilder(Start.aakar); 
	private String string;
	private Object[] arguments;
	private float scale;
    private int state=-1;//this is the number to specify which state to switch to when this element is selected
	private Vector4f color;
	private boolean HasFunction=false,hasColor=false,active=false,ChangesState=false;
	



	private Method Function;
	
	
	public UIStringElement(String string,Vector2f offset,float scale) {//not active no color
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		
	
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color) {//not active color
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.color=color;
		this.hasColor=true;

	
	}
	

	
	
	public UIStringElement(String string,Vector2f offset,float scale,int state) {//active no color state
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		
		this.state=state;

		
		
	  
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,int state) {//active color
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		this.color=color;
		this.hasColor=true;

		this.state=state;

		
	}
	
	
	public UIStringElement(String string,Vector2f offset,float scale,String functionName,Object[] arguments) {//active function no color 
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.arguments=arguments;
		this.offset=offset;
		this.active=true;
		this.HasFunction=true;
		
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
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,String functionName,Object[] arguments) {//active function color
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		this.arguments=arguments;
		this.color=color;
		this.hasColor=true;
		this.HasFunction=true;
	    
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
	
	public UIStringElement(String string,Vector2f offset,float scale,String functionName,Object[] arguments,Class[] argumentTypes,Class baseCLass) {//active function no color base class 
		this.string=string;
		this.scale=scale;
		this.baseCLass=baseCLass;
		this.text.setString(string);
		this.arguments=arguments;
		this.offset=offset;
		this.active=true;
		this.HasFunction=true;
		
		
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
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,String functionName,Object[] arguments,Class[] argumentTypes,Class baseclass) {//active function color base class
		this.string=string;
		this.scale=scale;
		this.baseCLass=baseclass;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		this.arguments=arguments;
		this.color=color;
		this.hasColor=true;
		this.HasFunction=true;
	    
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
	
	public UIStringElement(String string,Vector2f offset,float scale,String functionName,Object[] arguments,Class[] argumentTypes,Object objtocallfrom) {//active function no color base class 
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.baseCLass=objtocallfrom.getClass();
		this.objToCallfrom=objtocallfrom;
		this.arguments=arguments;
		this.offset=offset;
		this.active=true;
		this.HasFunction=true;
		
		loadFunction(functionName,argumentTypes);
	    

		
		
	  
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,String functionName,Object[] values,Class[] argumentTypes,Object objtocallfrom) {//active function color base class
		this.string=string;
		this.scale=scale;
		this.baseCLass=objtocallfrom.getClass();
		this.objToCallfrom=objtocallfrom;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		this.arguments=values;
		this.color=color;
		this.hasColor=true;
		this.HasFunction=true;
		
	    
	    loadFunction(functionName,argumentTypes);
	    
		
	}
	
	
	
	
	
	
	
	public boolean isHasFunction() {
		return HasFunction;
	}
	public boolean isActive() {
		return active;
	}
	public void drawElement(Vector2f Position) {
	
	Vector2f noffset=new Vector2f();offset.add(Position,noffset);
		
		
		if(!hasColor) {
	     text.drawString(noffset.x, noffset.y,0, scale);	
		}else {
			text.drawString(noffset.x, noffset.y, scale,color);
		}
		
	}

	public Vector2f getoffset() {
		return offset;
	}

	public void setoffset(Vector2f offset) {
		this.offset = offset;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
		this.text.setString(string);	
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	public int getState() {
		return state;
	}
	
	
	
public void invokeMethod() throws Exception {
		
	Start.DebugPrint(this.string);
	this.Function.invoke(this.objToCallfrom,this.arguments);
	
		
	}
	

	
	
private void loadFunction(String functionName,Class[] argumentTypes) {
	
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
