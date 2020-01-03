package battleClasses;

import org.joml.Vector2f;

import Scripter.Proccesor;
import ScripterCommands.DrawModel;
import gameEngine.Start;
import gameEngine.Timer;
import input.InputHandler;

public class TimedButtonHold extends TimedButton {

	
	
	private double timeToHold,timePressed;
	private boolean hasBeenPushed=false;
	
	public TimedButtonHold(double time1,double time2,double timeToHold,int button) {
		this.timeToHold=timeToHold;
		this.Button=button;
		this.time1=time1;
		this.time2=time2;
		this.Going=false;
		this.ButtonName=Start.buttonNamses.getNameofKey(button);
		this.model.setString("Hold "+this.ButtonName);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void start() {
		this.Going=true;
	   this.StartTime=Timer.getTIme();
	   InputHandler.DisableAll();

	}

	@Override
	public int update() {
		 InputHandler.EnableButton(Button);

		double TimeNow=Timer.getTIme();
		double TimeSince=Math.max(0,TimeNow-StartTime);
		
		byte StateOfButton=InputHandler.getStateofButton(Button);
		
	
		int result=NOTPUSHED;
		
		 if(TimeSince>=time1) {
				this.model.setString("HOLD "+this.ButtonName);
			
				
				drawEndicator();
				
			}
		
		if(this.hasBeenPushed) {
		
			TimeSince=Math.max(0,TimeNow-this.timePressed);
			
			
			Start.DebugPrint("TimeForButtonHold="+TimeSince+"("+TimeSince+")");
			
			if(StateOfButton==3) {//held 
		
				
			if(TimeSince>=this.timeToHold) {
				result=HIT;
				
				
			}
			
		}else {
		
			
		result=MISS;	
		}
		
		
		} else {
			Start.DebugPrint("TimeForButtonPress="+TimeSince+"("+time1+"/"+time2+")");
		///same code as before to check if it is first pressed in the right range
			
		if(TimeSince>=time2) {
				
				result= MISS;
			
		}else if(StateOfButton==1) {//just pressed
				
				if(TimeSince>=time1 && TimeSince<=time2) {
					
					this.hasBeenPushed=true;
					result=NOTPUSHED;//this is actually going to be used as the complete action isn't finished
					this.timePressed=Timer.getTIme();
				}else {
					
					
					
					
					
				
					result= MISS;
					
				}
				
				
				
			}
		
		
		
		
		}
		
		
		 
		
		if(result!=NOTPUSHED) {
			
		if(result==MISS) {
			this.model.setString("miss");
			drawEndicator();
			 Start.DebugPrint("miss");
		}else if(result==HIT) {
			
			this.model.setString("hit");
			Start.DebugPrint("hit");
			
		}
			
			
			
			enableButtons();
			this.hasBeenPushed=false;
		this.timePressed=0;
			this.Going=false;
			Proccesor.addComandtoQueue(new DrawModel(this.model.getTextModel(),this.model.getLoader().getTex(),this.vector.add(100,0,new Vector2f()),.5f,.5,false));
			
		}else {
			 InputHandler.DisableAll();

		}
		
		return result;
	}

}
