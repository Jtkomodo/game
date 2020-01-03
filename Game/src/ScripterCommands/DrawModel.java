package ScripterCommands;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.ModelFramwork;
import gameEngine.Render;
import gameEngine.Texture;
import gameEngine.Timer;

public class DrawModel extends Commands {


	private Vector2f position;
	private Vector4f color=Constants.WHITE;
	private ModelFramwork model;
	private double time,StartTime;
	private float size;
	private Texture texture;
	
	
	public DrawModel(ModelFramwork model,Texture texture,Vector2f position,float size,double time,boolean stopsInput){
		this.position=position;
		this.model=model;
		this.size=size;
		this.texture=texture;
		this.time=time;
		this.StopsInput=stopsInput;
		
	}
	
	
 public DrawModel(ModelFramwork model,Texture texture,Vector2f position,float size,Vector4f color,double time,boolean stopsInput){
		this.model=model;
		this.position=position;
		this.color=color;
		this.size=size;
		this.texture=texture;
		this.time=time;
		this.StopsInput=stopsInput;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void Start() {
		this.StartTime=Timer.getTIme();
		this.completed=false;
		this.hasBeenStarted=true;
	}

	@Override
	public void Update(double time2) {
		if(((time2-StartTime)>=time)) {
			this.completed=true;
			this.hasBeenStarted=false;
		
			
		}
		
		Render.draw(model,position,0f,size,texture,color);
		
		
	}

}
