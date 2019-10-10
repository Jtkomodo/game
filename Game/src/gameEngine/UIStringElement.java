package gameEngine;

import org.joml.Vector2f;
import org.joml.Vector4f;

import textrendering.TextBuilder;

public class UIStringElement {

	private Vector2f offset;
	private TextBuilder text=new TextBuilder(Start.aakar); 
	private String string;
	private float scale;
	private boolean hasColor=false,active=true;//active means this element can be clicked on
	private Vector4f color;
	
	public UIStringElement(String string,Vector2f offset,float scal,boolean active) {
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=active;
	
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color,boolean active) {
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.color=color;
		this.hasColor=true;
		this.active=active;
	
	}
	
	public UIStringElement(String string,Vector2f offset,float scale) {
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.active=active;
	
	}
	public UIStringElement(String string,Vector2f offset,float scale,Vector4f color) {
		this.string=string;
		this.scale=scale;
		this.text.setString(string);
		this.offset=offset;
		this.color=color;
		this.hasColor=true;
		this.active=active;
	
	}
	
	public boolean isActive() {
		return active;
	}
	public void drawElement(Vector2f Position) {
	
	Vector2f noffset=new Vector2f();offset.add(Position,noffset);
		
		
		if(!hasColor) {
	  text.drawString(noffset.x, noffset.y, scale);	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
