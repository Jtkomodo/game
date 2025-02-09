package ScripterCommands;

import org.joml.Vector2f;

import gameEngine.Timer;
import rendering.Render;
import textrendering.TextBuilder;

public class DrawString extends Commands {

	private TextBuilder textModel=gameEngine.Start.text1;
    private Vector2f position;
    private String string;
	private float size;
	private double Time,StartTime;
	
	public DrawString(String text,Vector2f position,float size,boolean stopsInput,double time) {
		textModel.setString(text);
		this.string=text;
		this.StopsInput=stopsInput;
		this.position=position;
		this.size=size;
		this.Time=time;
	}
	
	
	public DrawString(String text,Vector2f position,float size,String FontFileName,float AtlusSize,boolean stopsInput,double time) {
		textModel.getLoader().setLoader(FontFileName,AtlusSize);
	    textModel.setString(text);
		this.StopsInput=stopsInput;
		this.position=position;
		this.size=size;
		this.Time=time;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void Start() {
		this.StartTime=Timer.getTIme();
		this.completed=false;
		this.hasBeenReset=false;
		this.hasBeenStarted=true;
	}

	@Override
	public void Update(double time2) {
		if(((time2-StartTime)>=Time)) {
			this.completed=true;
		
		
			
		}
	    this.textModel.setString(string);
		this.textModel.drawString(position.x, position.y, size);
		
		
	}

}
