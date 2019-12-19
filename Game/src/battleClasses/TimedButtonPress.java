package battleClasses;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_T;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import gameEngine.Start;
import gameEngine.Timer;
import input.InputHandler;

public class TimedButtonPress extends TimedButton {

  	//trying a actual timed based idea time1 and time two is the range that the button can be hit in once started(in seconds)
	//Button is the actual glfw button we are looking for
	
	


	
	public TimedButtonPress(double time1,double time2,int Button) {
		
		this.time1=time1;
		this.time2=time2;
		this.Going=false;
		this.Button=Button;
		
	}
	
	
	@Override
   public void start() {
		this.Going=true;
	   this.StartTime=Timer.getTIme();
	   InputHandler.DisableAllBut(new int[] {Button});
	   
   }
	@Override
	public int update() {
		double TimeNow=Timer.getTIme();
		double TimeSince=Math.max(0,TimeNow-StartTime);
		
		byte StateOfButton=InputHandler.getStateofButton(Button);
		Start.DebugPrint("TimeForButtonPress="+TimeSince+"("+time1+"/"+time2+")");
		int result=NOTPUSHED;
		
	
		
		if(TimeSince>=time2) {
			
			result= MISS;
			
			}
	   
		else if(StateOfButton==1) {//just pressed
			
			if(TimeSince>=time1 && TimeSince<=time2) {
				
				result= HIT;
		
			}else {
			
				result= MISS;
				
			}
			
			
			
		}else if(TimeSince>=time1) {
			this.model.setString("PRESS");
			drawEndicator();
			
		}
		
		if(result!=NOTPUSHED) {
			if(result==MISS) {
				this.model.setString("miss");
				drawEndicator();
				Start.DebugPrint("miss");
			}else if(result==HIT) {
	
				Start.DebugPrint("hit");
				
			}
		
			enableButtons();
			this.Going=false;
		}
		
		
		
		
		return result;
		
	}
	public boolean IsGoing() {
		
		return Going;
	}
	
	
	

}
