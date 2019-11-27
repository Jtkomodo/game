package guis;



import java.lang.reflect.Method;
import org.joml.Vector2f;
import org.joml.Vector4f;


import gameEngine.Start;
import textrendering.TextBuilder;

public class UIStringElement extends UIElement{
	
    
	private TextBuilder text=new TextBuilder(Start.aakar); 
	private String string;
	private float scale;
	private Vector4f color;
	
	
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
	
	
	
	
	
	
	

	@Override
	public void drawElement(Vector2f Position) {
	
	Vector2f noffset=new Vector2f();offset.add(Position,noffset);
		
		
		if(!hasColor) {
	     text.drawString(noffset.x, noffset.y,0, scale);	
		}else {
			text.drawString(noffset.x, noffset.y, scale,color);
		}
		
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
	
	
	
	
	

}
