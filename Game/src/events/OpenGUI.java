package events;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Data.Constants;
import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.Timer;
import guis.GUIManeger;
import input.GetInput;
import rendering.MainRenderHandler;

public class OpenGUI extends Event {

	
	private GUIManeger GUI;
	private GetInput input=new GetInput();
	private Vector2f padding;
	private float sizeOfStrings;
    private int ButtonToOpenAndClose;
    private Vector2f offset;
	
	public OpenGUI(Vector2f offset,GUIManeger GUI,Vector2f padding,float sizeOfStrings,int ButtonToOpenAndClose) {
		this.offset=offset;
        this.GUI=GUI;
		this.padding=padding;
		this.sizeOfStrings=sizeOfStrings;
		this.ButtonToOpenAndClose=ButtonToOpenAndClose;
			
	}
	
	
	
	
	
	
	
	@Override
	protected void Update(Vector2f Position) {
		
		if(!GUI.isOpen()) {
			Start.text1.setString("press "+Start.buttonNamses.getNameofKey(ButtonToOpenAndClose));
			Start.text1.drawString(Position.x, Position.y,sizeOfStrings);
			
		}
	     GUI.draw(Position.add(offset,new Vector2f()), padding, sizeOfStrings);         
        
	}
	
    @Override
	protected void UpdateInput() {
    	 
		 if(input.getStateofButtonInstanced(ButtonToOpenAndClose)==1) {
			 if(GUI.isOpen()) {
				 GUI.close();
			 }else {
				 GUI.open();
			 }
			 
			 
		 }
		 if(GUI.isOpen()) {
			 Start.STOP_PLAYER_MOVEMENT=true;
		 }
   	     GUI.InputUpdate();	
	}

	@Override
	protected void Start() {
        GUI.reset();
        this.Started=true;
        this.ended=false;
	}

	@Override
	protected void Finsh() {
		GUI.close();
		this.Started=false;
		this.ended=false;
	}







	







  

}
