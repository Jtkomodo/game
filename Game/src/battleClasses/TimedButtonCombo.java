package battleClasses;

import java.util.LinkedList;
import java.util.List;

import org.joml.Vector2f;

import Scripter.Proccesor;
import ScripterCommands.DrawModel;
import gameEngine.Start;
import gameEngine.Timer;
import input.InputHandler;

public class TimedButtonCombo extends TimedButton{

  public static final int Excellent=3,good=2,bad=1; 
	
   private int amountForExcellent,amountForGood,amountRight=0;
   private TimedButton[] buttons;
   private int ButtonCurrentlyOn=0;
	
	public TimedButtonCombo(TimedButton[] buttons,int amountForExcellent,int amountForGood) {
		this.buttons=buttons;
		this.amountForExcellent=amountForExcellent;
		this.amountForGood=amountForGood;
		this.iscombo=true;
		
	}

	@Override
	public void start() {
	  
	   this.ButtonCurrentlyOn=0;	
	   this.StartTime=Timer.getTIme();
	   this.Going=true;
	   this.buttons[0].start();
		
	}

	@Override
	public int update() {
		
		
      TimedButton button=buttons[this.ButtonCurrentlyOn];
	  int result=NOTPUSHED;//or combo not done
      
      if(button.isGoing()) {
    	  
    	  
    	  
    	  int status=button.update();
    	  if(status==HIT) {
    		  this.amountRight++;
    	  }
    	  
    	  
    	  
    	  
      }else {
    	 this.ButtonCurrentlyOn++;
    	 if(this.ButtonCurrentlyOn==buttons.length) {
    		 
    		 if(this.amountRight>=this.amountForExcellent) {
    			 result=Excellent;
    			 this.model.setString("Excellent");
    			 Start.DebugPrint("Excellent");
    			 }else if (this.amountRight>=this.amountForGood) {
    				 result=good;
    				 this.model.setString("Good");
    				 Start.DebugPrint("Good");
    			 }else {
    				 result=bad;
    			
    				 this.model.setString("Bad");
    				 Start.DebugPrint("Bad");
    			 }
    		 
    		 
    		 
    		 
    	 }else {
    		 buttons[ButtonCurrentlyOn].start();
    	 }
    	  
      }
      
      if(result!=0) {
    	  this.amountRight=0;
    	  this.ButtonCurrentlyOn=0;
    	  this.Going=false;
    	  Proccesor.addComandtoQueue(new DrawModel(this.model.getTextModel(),this.model.getLoader().getTex(),this.vector.add(100,0,new Vector2f()),.5f,.5,true));
			if(result==bad) {
				 Start.source.play(Start.TimedBad);
			}
    	  
      }
      
      
	 return result;	
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
