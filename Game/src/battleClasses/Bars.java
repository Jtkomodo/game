package battleClasses;

import org.joml.Vector2f;

import Data.Constants;
import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Texture;

public abstract class Bars {

	
	protected float max;
	protected float value;
	private Model model;
	private Texture background;
	private Vector2f backgroundScale; 
	
	
	public Bars(float max,float value,Vector2f backgroundScale,Texture background) {
		this.max=max;
		this.value=value;
		this.background=background;
		this.backgroundScale=backgroundScale;
		
		float Vert[]=new float[] {
				
				
						-0.5f,+0.5f,
						0.5f,0.5f,
						0.5f,-0.5f,
						-0.5f,-0.5f};
		
		float[] Uv={
				0,0,
				1,0,
				1,1,
				0,1};	
		
		model= new Model(Vert,Uv);
	}

	
	public void decrease(float amount) {
		this.value-=amount;
		if(this.value<0) {
			value=0;
		}
		
	}
	
	public void ecnrease(float amount) {
		this.value+=amount;
		if(this.value>this.max) {
			value=max;
		}
		
	}
	
	
	
	
	
	
	
	abstract public void draw(Vector2f position);//must make draw otherwise we can't draw the bars

	protected void draw(Vector2f position,Texture texture) {
		float scaleX=Math.round((this.value/this.max)*this.backgroundScale.x);
		if(scaleX<0)
			scaleX=0;
		if(scaleX>backgroundScale.x) {
			scaleX=backgroundScale.x;
		}
		
		Render.draw(model, position,0,new Vector2f(backgroundScale.x+2,backgroundScale.y+2),background);
		Render.draw(model, new Vector2f(position.x-((this.backgroundScale.x-(scaleX))/2),position.y), 0 ,new Vector2f(scaleX,backgroundScale.y), texture,Constants.BAR_COLOR_GREEN);
		
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
