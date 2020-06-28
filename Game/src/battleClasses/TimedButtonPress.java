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

import org.joml.Vector2f;

import Scripter.Proccesor;
import ScripterCommands.DrawModel;
import gameEngine.Start;
import gameEngine.Timer;
import input.InputHandler;
import textrendering.TextBuilder;

public class TimedButtonPress  {

  	//trying a actual timed based idea time1 and time two is the range that the button can be hit in once started(in seconds)
	//Button is the actual glfw button we are looking for
	
	
//we will need to have a perfect,good,and miss states there will be a short window of time to get perfect a little bigger window to get good and everything else is a miss
//this is all used for timed defense with perfect having the ability to have a counter effect
	
// so we need a range for both states	
// do we want the player to know if the button was pushed to early or to late when it is a miss?	
	
	public static final int HIT_PERFECT=3,HIT_GOOD=2,MISS=1,NOT_PUSHED=0;
	
	private boolean Going;
	private double startTimeOfPerfect,endTimeOfPerfect,startTimeOfGood,endTimeOfGood;
	private int Button;
	private String ButtonName;
	private double StartTime;
	private Vector2f vector=new Vector2f(-90,40);
	private TextBuilder text;
	
	
	
	public TimedButtonPress(double startTimeOfPerfect,double endTImeOfPerfect,double startTimeOfGood,double endTimeOfGood,int Button) {
	    this.startTimeOfPerfect=startTimeOfPerfect;
	    this.endTimeOfPerfect=endTImeOfPerfect;
	    this.startTimeOfGood=startTimeOfGood;
	    this.endTimeOfGood=endTimeOfGood;
	
		this.Going=false;
		this.Button=Button;
		this.ButtonName=Start.buttonNamses.getNameofKey(Button);
	}
	
	
	
   public void start() {
		this.Going=true;
	   this.StartTime=Timer.getTIme();
	   InputHandler.DisableAll();
	   
   }
	
	public int update() {
		 InputHandler.EnableButton(Button);

		double TimeNow=Timer.getTIme();
		double TimeSince=Math.max(0,TimeNow-StartTime);
		
		byte StateOfButton=InputHandler.getStateofButton(Button);
		
		int result=NOT_PUSHED;
		
	
		
		if(TimeSince>=this.endTimeOfGood) {
			
			result= MISS;
			
			}
	   
		else if(StateOfButton==1) {//just pressed
			
			if(TimeSince>=this.startTimeOfPerfect&& TimeSince<=this.endTimeOfPerfect) {
				
				result= HIT_PERFECT;
		
			}else if(TimeSince>=this.startTimeOfGood && TimeSince<=this.endTimeOfGood) {
				result= HIT_GOOD;
			
			}
			
			
			else {
			
				result= MISS;
				
			}
			
			
			
		}else if(TimeSince>=this.endTimeOfPerfect) {//THIS IS ONLY HERE FOR TESTING PURPOSES THERE WILL BE NOTHING TELLING YOU WHEN TO PUSH THE BUTTON BUT THE CONTEXT OF THE ANIMATION
			this.text.setString("PRESS "+this.ButtonName);
			drawEndicator();
			
		}
		
		if(result!=NOT_PUSHED) {
			if(result==MISS) {
				this.text.setString("miss");
				
				Start.DebugPrint("miss");
			}else if(result==HIT_PERFECT) {
				this.text.setString("PERFECT");
				Start.DebugPrint("PERFECT---");
				Start.source.play(Start.Select);//will have different sound for perfect hit and good hit
			}else if(result==HIT_GOOD) {
				this.text.setString("GOOD");
				Start.DebugPrint("GOOD---");
				Start.source.play(Start.Select);
				
			}
			
			Proccesor.addComandtoQueue(new DrawModel(this.text.getTextModel(),this.text.getLoader().getTex(),this.vector.add(100,0,new Vector2f()),.5f,.5,false));
			enableButtons();
			this.Going=false;
			
		}else {
			 InputHandler.DisableAll();
		}
		
		
		
		
		return result;
		
	}
	private void enableButtons() {
		InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
		
		
	}



	private void drawEndicator() {
	   text.drawString(vector.x, vector.y,.5f);
		
	}



	public boolean IsGoing() {
		
		return Going;
	}
	
	
	

}
