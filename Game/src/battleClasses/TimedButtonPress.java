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

public class TimedButtonPress {

  	//trying a actual timed based idea time1 and time two is the range that the button can be hit in once started(in seconds)
	//Button is the actual glfw button we are looking for
	
	
	public static final int HIT=2,MISS=1,NOTPUSHED=0;
	private double time1,time2,StartTime;
	private int Button;
	private boolean Going;
	
	
	public TimedButtonPress(double time1,double time2,int Button) {
		
		this.time1=time1;
		this.time2=time2;
		this.Going=false;
		this.Button=Button;
		
	}
	
	
	
   public void start() {
		 Start.MoveInprogress=true;
		 Start.Button=this;
	   this.StartTime=Timer.getTIme();
	   InputHandler.DisableAllBut(new int[] {Button});
	   
   }
	
	public int update() {
		double TimeNow=Timer.getTIme();
		double TimeSince=Math.max(0,TimeNow-StartTime);
		
		byte StateOfButton=InputHandler.getStateofButton(Button);
		Start.DebugPrint("TimeForButtonPress="+TimeSince+"("+time1+"/"+time2+")");
		
		
		int result;
		
		if(TimeSince>=time2) {
			
			result= MISS;
			}
	   
		else if(StateOfButton==1) {//just pressed
			
			if(TimeSince>=time1 && TimeSince<=time2) {
				
				result= HIT;
			}else {
			
				result= MISS;
			}
			
			
			
		}else {
			result= NOTPUSHED;
		}
		
		if(result!=NOTPUSHED) {
			
			enableButtons();
			this.Going=false;
		}
		
		return result;
		
	}
	public boolean IsGoing() {
		
		return Going;
	}
	
	
	
	private void enableButtons() {
		InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
		
	}
}
