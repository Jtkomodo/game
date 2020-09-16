package guis;



import java.lang.reflect.Method;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.Entity;
import gameEngine.Start;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class UIStringElement extends UIElement{
	
    

	private String string;
	private float scale;
	private Vector4f color;
	
	public UIStringElement(String string,Vector2f offset,float scale) {//not active no color
		super(offset);
		this.string=string;
		this.scale=scale;
	
		this.offset=offset;
		
	
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color) {//not active color
		super(offset);
		this.string=string;
		this.scale=scale;
	
		this.offset=offset;
		this.color=color;
		this.hasColor=true;

	
	}
	

	
	
	public UIStringElement(String string,Vector2f offset,float scale,int state) {//active no color state
		super(offset);
		this.string=string;
		this.scale=scale;
		
		this.offset=offset;
		this.active=true;
		
		this.state=state;

		
		
	  
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,int state) {//active color
		super(offset);
		this.string=string;
		this.scale=scale;
	
		this.offset=offset;
		this.active=true;
		this.color=color;
		this.hasColor=true;

		this.state=state;

		
	}
	
	


	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,GUIfunction Function) {//active function color base class
		super(offset);
		this.string=string;
		this.scale=scale;
	
	
		this.offset=offset;
		this.active=true;
	
		this.color=color;
		this.hasColor=true;
		this.HasFunction=true;
	    
	
	    
	    loadFunction(Function);
	    
	    
		
	}
	
	public UIStringElement(String string,Vector2f offset,float scale,GUIfunction Function) {//active function no color base class 
		super(offset);
		this.string=string;
		this.scale=scale;
			this.active=true;
		this.HasFunction=true;
		
		loadFunction(Function);
	    

		
		
	  
	}
	
	
	
	
	
	

	@Override
	protected void drawElement(Vector2f Position) {
	
	Vector2f noffset=new Vector2f();offset.add(Position,noffset);
   
	     Start.text1.setString(string);
	     float stringL=(Start.text1.getStringLength())*scale;
	     float stringh=(Start.text1.getStringHieght())*scale;
	     if(!hasColor) {
	     Start.text1.drawString(noffset.x, noffset.y,0, scale);	
		}else {
			
			Start.text1.drawString(noffset.x, noffset.y, scale,color);
			 
		}
		
		MainRenderHandler.addEntity(new Entity(Start.background, new Vector3f(noffset.x+stringL,noffset.y,1000000), 0, 1,Start.COLTEX,Constants.RED));
		MainRenderHandler.addEntity(new Entity(Start.background, new Vector3f(noffset.x,noffset.y-stringh,1000000), 0, 1,Start.COLTEX,Constants.RED));
	}

	

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
		
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	
	

}
