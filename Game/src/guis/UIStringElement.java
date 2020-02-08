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
		super(offset);
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		
	
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color) {//not active color
		super(offset);
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.color=color;
		this.hasColor=true;

	
	}
	

	
	
	public UIStringElement(String string,Vector2f offset,float scale,int state) {//active no color state
		super(offset);
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		
		this.state=state;

		
		
	  
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,int state) {//active color
		super(offset);
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		this.color=color;
		this.hasColor=true;

		this.state=state;

		
	}
	
	


	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,FunctionCaller Function) {//active function color base class
		super(offset);
		this.string=string;
		this.scale=scale;
	
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
	
		this.color=color;
		this.hasColor=true;
		this.HasFunction=true;
	    
	
	    
	    loadFunction(Function);
	    
	    
		
	}
	
	public UIStringElement(String string,Vector2f offset,float scale,FunctionCaller Function) {//active function no color base class 
		super(offset);
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.active=true;
		this.HasFunction=true;
		
		loadFunction(Function);
	    

		
		
	  
	}
	
	
	
	
	
	

	@Override
	protected void drawElement(Vector2f Position) {
	
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
