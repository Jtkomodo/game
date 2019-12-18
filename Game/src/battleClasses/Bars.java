package battleClasses;

import org.joml.Vector2f;

import gameEngine.Model;
import gameEngine.Renderer;
import gameEngine.Texture;

public abstract class Bars {

	
	protected float max;
	protected float value;
	private Model model;
	
	
	
	public Bars(float max,float value) {
		this.max=max;
		
	}

	
	public void decrease(float amount) {
		this.value-=amount;
		if(this.value<0) {
			value=0;
		}
		
	}
	
	public void enrease(float amount) {
		this.value+=amount;
		if(this.value>this.max) {
			value=max;
		}
		
	}
	
	
	
	
	
	
	
	

	public void draw(Vector2f position,float scale,Texture texture) {
		
		Renderer.draw(model, position, 0 ,scale, texture);
		
	}
	
	
	
	public float getMax() {
		return max;
	}


	public void setMax(float max) {
		this.max = max;
	}


	public float getValue() {
		return value;
	}


	public void setValue(float value) {
		this.value = value;
	}

	
	
	
	
	
}
