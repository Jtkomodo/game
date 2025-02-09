package ScripterCommands;

import org.joml.Vector2f;
import org.joml.Vector3f;

import animation.Animate;
import animation.AnimationHandler;
import gameEngine.RenderEntity;
import gameEngine.Timer;
import rendering.Render;


public class animate extends Commands {

	private Animate animation;
	private double StartTime;
	private double time;
	private Vector3f position;
	private boolean walk=false,Draw=false;
	private float angle,scale;
	
	public animate(Animate animation,double time,boolean StopsInput) {
		this.animation=animation;
		this.time=time;
		this.StopsInput=StopsInput;
		
	}
	public animate(Animate animation,Vector3f position,float angle,float scale,double time,boolean StopsInput) {
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
         this.hasBeenReset=false;
	}

	@Override
	public void Update(double time2) {
	   
		if(((time2-StartTime)>=time)) {
			this.completed=true;
			animation.removeAnimation();
			this.hasBeenStarted=false;
		
			
		}
		
	    
		
		
		if(Draw) {
		    RenderEntity e=animation.getE();
		    e.setPosition(position);
		    e.setAngle(angle);
		    e.setSize(scale);
		    e.setMirror(false);
		}
	
		
	}

}
