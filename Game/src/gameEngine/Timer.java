package gameEngine;

public class Timer {

	
	public static double getTIme() {
		
		return (double)System.nanoTime()/(double)1000000000;
		
	}
	
	
	private double TimeStarted,time;
	private boolean isGoing=false;
	
	
	
	public void seTimer(double time) {
		TimeStarted=getTIme();
		isGoing=true;
		this.time=time;
	}
	
	public boolean update() {
		if(isGoing) {
		boolean finished=false;
		double Time2=getTIme();
		if((Time2-TimeStarted)>=time) {
			isGoing=false;
			return true;
			
		}else return false;
		
		
		
		
		}else return false;
	}
	
}
