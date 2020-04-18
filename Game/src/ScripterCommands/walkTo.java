package ScripterCommands;

import org.joml.Vector2f;
import org.joml.Vector3f;

import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.Timer;
import gameEngine.VectorMath;

public class walkTo extends Commands {

	
	private Vector2f begin;
	private Vector2f end;
	private Entity e;
	private double time,StartTime;
	
	
	
	public walkTo(Entity e,Vector2f begin,Vector2f end,double time) {
		this.begin=begin;
		this.end=end;
		this.time=time;
		
		this.e=e;
	}
	
	
	
	
	
	
	
	
	@Override
	public void Start() {
		this.hasBeenStarted=true;
		this.StartTime=Timer.getTIme();
		gameEngine.Start.DebugPrint("the walkto ("+this.begin+","+this.end+","+time+") command has started at aprox "+(this.StartTime-gameEngine.Start.startTime)+" seconds into the program",this.getClass());
		
	}

	@Override
	public void Update(double time2) {
		double timeTaken=(time2-StartTime);
		if(timeTaken>=time) {
			this.completed=true;
			gameEngine.Start.DebugPrint("the walkTo is over ",this.getClass());
			this.hasBeenStarted=false;
			}else {
				Vector2f v=new Vector2f();
				
				begin.lerp(end, (float) (timeTaken/this.time), v);
						
			
				e.setPosition(new Vector3f(v,e.getPosition().z));
			
			}
	}

}
