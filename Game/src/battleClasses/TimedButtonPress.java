package battleClasses;



import org.joml.Vector2f;

import Scripter.Proccesor;
import ScripterCommands.DrawModel;
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
		this.ButtonName=Start.buttonNamses.getNameofKey(Button);
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
			this.model.setString("PRESS "+this.ButtonName);
			drawEndicator();
			
		}
		
		if(result!=NOTPUSHED) {
			if(result==MISS) {
				this.model.setString("miss");
				Proccesor.addComandtoQueue(new DrawModel(this.model.getTextModel(),this.model.getLoader().getTex(),this.vector.add(100,0,new Vector2f()),.5f,1));
				Start.DebugPrint("miss");
			}else if(result==HIT) {
				this.model.setString("HIT");
				Start.DebugPrint("hit");
				Proccesor.addComandtoQueue(new DrawModel(this.model.getTextModel(),this.model.getLoader().getTex(),this.vector.add(100,0,new Vector2f()),.5f,1));			
			}
		
			enableButtons();
			this.Going=false;
		}else {
			 InputHandler.DisableAll();
		}
		
		
		
		
		return result;
		
	}
	public boolean IsGoing() {
		
		return Going;
	}
	
	
	

}
