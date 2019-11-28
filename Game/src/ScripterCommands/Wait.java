package ScripterCommands;

import Scripter.Proccesor;
import gameEngine.Timer;

public class Wait extends Commands {

	
	private double time,StartTime;
	
	
	public Wait(Double time) {
		this.StopsInput=true;
		this.time=time;
	}


	@Override
	public void Start() {
		this.hasBeenStarted=true;
	
		
		
		this.StartTime=Timer.getTIme();
		gameEngine.Start.DebugPrint("the wait for"+this.time+" seconds command has started at aprox "+(this.StartTime-gameEngine.Start.startTime)+" seconds into the program",this.getClass());
	}


	@Override
	public void Update(double time2) {
		if((time2-StartTime)>=time) {
		this.completed=true;
		gameEngine.Start.DebugPrint("the wait is over waited "+(time2-this.StartTime)+" seconds",this.getClass());
		this.hasBeenStarted=false;
		}
	}
	
	
	
	

	
	

}
