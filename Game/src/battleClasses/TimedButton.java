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

import gameEngine.Model;
import gameEngine.Start;
import gameEngine.Texture;
import input.InputHandler;
import textrendering.TextBuilder;

public abstract class TimedButton {

	
	
	public static final int HIT=2,MISS=1,NOTPUSHED=0;
	
	
	protected TextBuilder model=new TextBuilder(Start.aakar);
	protected Texture texture;
	protected Vector2f vector=new Vector2f(-90,40);
	protected double time1,time2,StartTime;
	protected int Button;
	protected boolean Going=false,iscombo=false;
	
	
	public abstract void start();
	public abstract int update(); 
	
	
	
	
	
	
	
	
	
	protected void drawEndicator() {
		
		model.drawString(vector.x, vector.y,.5f);
	}
	
	
	protected void enableButtons() {
		InputHandler.EnableButtons(new int[] {GLFW_KEY_UP,GLFW_KEY_DOWN,GLFW_KEY_RIGHT,GLFW_KEY_LEFT,GLFW_KEY_ESCAPE,GLFW_KEY_W,GLFW_KEY_T,GLFW_KEY_RIGHT_CONTROL,GLFW_KEY_LEFT_CONTROL,GLFW_KEY_F,GLFW_KEY_H});
		
	}
	public boolean isGoing() {
		return Going;
	}
	public boolean isCombo() {
		return this.iscombo;
	}
	
	
}
