package battleClasses;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.Entity;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;
import textrendering.TextBuilder;

public abstract class Bars {

	
	protected float max;
	protected float value;
	protected Vector4f Barcolor=Constants.BAR_COLOR_GREEN,lowValuecolor=Constants.BAR_COLOR_RED;
	private float z=1000;
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
	
	
	
	
	
	
	
	abstract public void draw(Vector2f position,TextBuilder text);//must make draw otherwise we can't draw the bars

	protected void draw(Vector2f position,Texture texture,TextBuilder text) {
		
		
		Vector4f color=new Vector4f(0);
		float percentage=(this.value/this.max);
		if(percentage<.20f) {
	       color=this.lowValuecolor;
		}else {
			color=this.Barcolor;
		}
		
		
		float scaleX=Math.round(percentage*this.backgroundScale.x);
		if(scaleX<0)
			scaleX=0;
		if(scaleX>backgroundScale.x) {
			scaleX=backgroundScale.x;
		}
		
		
		
		
		MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z),0,new Vector2f(backgroundScale.x+2,backgroundScale.y+2),background));
		MainRenderHandler.addEntity(new Entity(model, new Vector3f(position.x-((this.backgroundScale.x-(scaleX))/2),position.y,z+1), 0 ,new Vector2f(scaleX,backgroundScale.y), texture,color));
		text.drawString(position.x-(this.backgroundScale.x/2),position.y+(this.backgroundScale.y/2)+6,0f,.12f);
		
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


	public float getZ() {
		return z;
	}


	public void setZ(float z) {
		this.z = z;
	}


	public Vector2f getBackgroundScale() {
		return backgroundScale;
	}

	
	
	
	
	
}
