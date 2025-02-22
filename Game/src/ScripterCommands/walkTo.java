package ScripterCommands;

import org.joml.Vector2f;
import org.joml.Vector3f;

import gameEngine.RenderEntity;
import gameEngine.Start;
import gameEngine.Timer;
import gameEngine.VectorMath;

public class walkTo extends Commands {

	
	private Vector2f begin;
	private Vector2f end;
	private RenderEntity e;
	private double time,StartTime;
	
	
	
	public walkTo(RenderEntity e,Vector2f begin,Vector2f end,double time) {
		this.begin=begin;
		this.end=end;
		this.time=time;
		this.e=e;
	}
	
	
	
	
	
	
	
	
	@Override
	public void Start() {
	  this.hasBeenReset=false;
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
			
			}else {
				Vector2f v=new Vector2f();
				
			v=VectorMath.Lerp(begin,end, (float) (timeTaken/this.time));
						
			
				e.setPosition(new Vector3f(v,1000000));
			
			}
	}

}
