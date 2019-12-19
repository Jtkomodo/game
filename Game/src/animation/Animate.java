package animation;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Start;
import gameEngine.Texture;
import gameEngine.Timer;


public class Animate {

 

	private double time,time2,timepassed,frametime=0;
	private double frameTiming,unp=0,fps;
	private int frameAnmount;
	private int currentframe=0,finalFrame,start,end;
	private int[] HowlongEachFrameLasts;
    private Model model;
    private SpriteSheetLoader animation;
	private boolean going=true,eachTimed=false;
	private Texture texture;
	
	
	public Animate(double fps,Model model,SpriteSheetLoader animation,int start,int end) {
		addAnimation();
		this.fps=fps;
		
		this.frameTiming=1.0/fps;
		this.time=Timer.getTIme();
		this.model=new Model(model.getVertices(),model.getUv_coords());
		
		this.finalFrame=animation.getLastFrame();
		
		SetStartAndEnd(start,end);
		texture=animation.getTexture();
	
		this.animation=animation;
	}
	
	
	
	
	
	
	
	public Animate(double fps,Model model,SpriteSheetLoader animation,int[] HowlongEachFramelasts/*in frames one integer value for each frame*/,int start) {
		
		addAnimation();
		
		this.fps=fps;
		this.frameTiming=1.0/fps;
		this.time=Timer.getTIme();
	    this.HowlongEachFrameLasts=HowlongEachFramelasts;
		this.eachTimed=true;
		
		
		this.model=new Model(model.getVertices(),model.getUv_coords());
		
		this.finalFrame=animation.getLastFrame();
		
		SetStartAndEnd(start,(start+HowlongEachFramelasts.length)-1);
		texture=animation.getTexture();
	
		this.animation=animation;
	}
	
	
	
	public void updateTime() {
	
	    time2=Timer.getTIme();
	if(going) {//if the sprite is supposed to be animated at this time start the animation timing
	    timepassed=time2-time;//this is the just the difference form the last time through the game loop to now
		unp+=timepassed;//this is just the time taken since a animation frame was changed
		frametime+=timepassed;//same thing but this is to actually tell when a second has passed only for debugging purposes 
	  
		
		 if(unp>=frameTiming) {//if the time since the last frame time has reached our target time change frame
				unp-=frameTiming;//resets the time in a way it doesn't lose frames
				
				
				
				if(this.eachTimed) {
				
				if(frameAnmount>=this.HowlongEachFrameLasts[this.currentframe]) {
				changeFrame();//change the sprite
				this.frameAnmount=0;
				}
				this.frameAnmount++;
				
				}else {
					changeFrame();//change the sprite
				}
				if(frametime>=1.0) {
                  
					frametime=0;
         }
		 }  }time=time2;//allows us to know next frame what the time was last		 
		 }
	
	
	
	private void changeFrame() {
	  
		float  Texwidth=animation.getSize();//gets the size of the sheet itself
		float    Texheight=Texwidth;//always is one to one
		float[] data=animation.getValueInMap(currentframe);//gets the data of the next sprite so that we can change the u v coords
		if(data!=null) {//just makes sure no null pionter exception happens 
	    	 
	    	 
	     
			
	
		float wi=data[2];//gets the width of the sprite
		float  h=data[3];//gets the height
		float Texx=data[0];//u
		
		float    Texy=data[1];///v	
		   float height2=h/64;
		   float width2=wi/64;     
		 
		  
	       float[] uv={
			Texx/Texwidth,Texy/Texheight,
			(Texx+wi)/Texwidth,Texy/Texheight,
			(Texx+wi)/Texwidth,(Texy+h)/Texheight,
			Texx/Texwidth,(Texy+h)/Texheight  };
		
		
		model.changeUV(uv);//change the uv of the model 
	     }	
		
	if(currentframe!=end) {
	 
			currentframe++;//sets the next frame
		
		}else {
		
		currentframe=start;//resets the animation
	}
	
	}
	
	
	private void ResetFrame() {
		  
		float  Texwidth=animation.getSize();
		float    Texheight=Texwidth;
		float[] data=animation.getValueInMap(start);
		if(data!=null) {
	    	 
	    	 
	     
			
	
		float wi=data[2];
		float  h=data[3];
		float Texx=data[0];
		
		float    Texy=data[1];	
		   float height2=h/64;
		   float width2=wi/64;     
		 
		  
	       float[] uv={
			Texx/Texwidth,Texy/Texheight,
			(Texx+wi)/Texwidth,Texy/Texheight,
			(Texx+wi)/Texwidth,(Texy+h)/Texheight,
			Texx/Texwidth,(Texy+h)/Texheight  };
		
		
		model.changeUV(uv);
	     }	
		
	

	}
	
	
	public void Stop() {//stops the animation
	    going=false;
	   ResetFrame();
	    
	}
	public void Pause() {//pauses the animation
	    going=false;
	   
	    
	}	
	public void Play() {//plays the animation 
	    going=true;
	   
	}
	public void changefps(double fps) {//changes speed of the animation
		if(this.fps!=fps) {
		this.fps=fps;
		this.frameTiming=1.0/fps;
		}
	}
	
	public void SetStartAndEnd(int start,int end) {//changes the animation in the same sprite sheet
		
		if(this.finalFrame>=start) {
		
		this.start=start;}else {
			start=0;
		}
		if(this.finalFrame>=end) {
			this.end=end;
		}else {
			this.end=finalFrame;
		}
		this.currentframe=start;
		
	}
	
	public void ChangeAnimation(double fps,Model model,SpriteSheetLoader animation,int start,int end) {//completely changes the animation with a new sprite sheet
		this.fps=fps;
		this.frameTiming=1.0/fps;
		this.time=Timer.getTIme();
		this.model=new Model(model.getVertices(),model.getUv_coords());
		this.eachTimed=false;
		this.finalFrame=animation.getLastFrame();
		
		SetStartAndEnd(start,end);
		texture=animation.getTexture();
		
	
		this.animation=animation;
	}
	
	public void ChangeAnimation(double fps,Model model,SpriteSheetLoader animation,int[] HowlongEachFramelasts/*in frames one integer value for each frame*/,int start) {
		this.fps=fps;
		this.frameTiming=1.0/fps;
		this.time=Timer.getTIme();
	    this.HowlongEachFrameLasts=HowlongEachFramelasts;
		this.eachTimed=true;
		
		
		this.model=new Model(model.getVertices(),model.getUv_coords());
		
		this.finalFrame=animation.getLastFrame();
		
		SetStartAndEnd(start,(start+HowlongEachFramelasts.length)-1);
		texture=animation.getTexture();
	
		this.animation=animation;
	}
	
	public void drawAnimatedModel(Vector2f position,float angle,float scale) {
		
		if(AnimationHandler.exsits(this)){
		Render.draw(model, position, angle, scale,texture);
	
		}
		
		
	}
	
	public void drawAnimatedModel(Vector2f position,float angle,float scale,boolean mirror) {
		
		
		if(AnimationHandler.exsits(this)){
		if(mirror)	
		Render.Mirror();

		Render.draw(model, position, angle, scale,texture);
		}
		
		
	}
	
	
public void drawAnimatedModel(Vector2f position,float angle,Vector2f scale) {
		
		if(AnimationHandler.exsits(this)){
		Render.draw(model, position, angle, scale,texture);
	
		}
		
		
	}
	
	public void drawAnimatedModel(Vector2f position,float angle,Vector2f scale,boolean mirror) {
		
		
		if(AnimationHandler.exsits(this)){
		if(mirror)	
		Render.Mirror();

		Render.draw(model, position, angle, scale,texture);
		}
		
		
	}
	
	
	public int getCurrentframe() {
		return this.currentframe;
		
	}

    public void removeAnimation() {
    	if(AnimationHandler.exsits(this)) {
    	AnimationHandler.RemoveAnimatin(this);
        this.Stop();
    	}
    }


    public void addAnimation() {
    	if(!(AnimationHandler.exsits(this)))
    	AnimationHandler.addAnimation(this);
    }
    public boolean exists() {
    	return AnimationHandler.exsits(this);
    }


	public long getKey() {
		return AnimationHandler.getIndex(this);
	}

    





	
}
