package ScripterCommands;

import org.joml.Vector2f;

import animation.Animate;
import animation.AnimationHandler;
import gameEngine.Renderer;
import gameEngine.Timer;
import jdk.nashorn.internal.runtime.Debug;

public class animate extends Commands {

	private Animate animation;
	private double StartTime;
	private double time;
	private Vector2f position;
	private boolean walk=false,Draw=false;
	private float angle,scale;
	
	public animate(Animate animation,double time,boolean StopsInput) {
		this.animation=animation;
		this.time=time;
		this.StopsInput=StopsInput;
		
	}
	public animate(Animate animation,Vector2f position,float angle,float scale,double time,boolean StopsInput) {
		this.animation=animation;
		this.Draw=true;
		this.angle=angle;
		this.scale=scale;
		this.position=position;
		this.time=time;
		this.StopsInput=true;
		this.StopsInput=StopsInput;
		
	}
	
	
	
	
	
	
	@Override
	public void Start() {
		animation.Play();
		animation.addAnimation();
         this.StartTime=Timer.getTIme();
         this.hasBeenStarted=true;
	}

	@Override
	public void Update(double time2) {
	   
		if(((time2-StartTime)>=time)) {
			this.completed=true;
			this.hasBeenStarted=false;
		
			
		}
		
	    
		if(Draw) {
			
			animation.drawAnimatedModel(position,angle, scale,false);
		}
	
		
	}

}
