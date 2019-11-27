package ScripterCommands;

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
		
	}


	@Override
	public void Update(double time2) {
		if((time2-StartTime)>=time) {
		this.completed=true;
		gameEngine.Start.DebugPrint("the wait is over");
		this.hasBeenStarted=false;
		}
	}
	
	
	
	

	
	

}
