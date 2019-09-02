package gameEngine;

public class Animate {

	
	private double frametime=0;
	private double frameTiming,unp=0;
	private int currentframe=0,finalFrame;
	
	
	
	
	public Animate(double fps) {
		this.frameTiming=1.0/fps;
		
		
	}
	
	
	public void updateTime(double timepassed) {
	
	  
		unp+=timepassed;
		frametime+=timepassed;
	
		
		 if(unp>=frameTiming) {
				unp-=frameTiming;
		
				System.out.println("a: "+frametime);
				changeFrame();
				if(frametime>=1.0) {
           
         	
              frametime=0;
              currentframe=0;
         }
		 }		 }
	
	
	
	private void changeFrame() {
		
	  currentframe++;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
