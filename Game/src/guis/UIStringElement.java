package guis;

import org.joml.Vector2f;
import org.joml.Vector4f;

import gameEngine.Start;
import textrendering.TextBuilder;

public class UIStringElement {

	private Vector2f offset;
	private TextBuilder text=new TextBuilder(Start.aakar); 
	private String string;
	private float scale;
    private int state=-1,function=-1;//this is the number to specify which state to switch to when this element is selected
	private boolean hasColor=false,active=false;//active means this element can be clicked on
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
	

	
	
	public UIStringElement(String string,Vector2f offset,float scale,boolean changeState,int function_OR_State) {//active no color
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		if(changeState==false){
			this.function=function_OR_State;
		}else {
			this.state=function_OR_State;
		}
	  
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,boolean changeState,int function_OR_State) {//active color
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=true;
		this.color=color;
		this.hasColor=true;
		if(changeState==false){
			this.function=function_OR_State;
		}else {
			this.state=function_OR_State;
		}
	  
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
	
	public int getFunction() {
		return this.function;
	}
	
	
	
	
	
	
	
	
	
	
}
