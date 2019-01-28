package gameEngine;
import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;

import gameEngine.Window;
/*
	So far the first 4 bits in the zero index of the Keys Vector are for right,left,down up
	the last two in the second index is for whether escape is pushed and for whether full screen  
    short cut is pushed or not(ctrl+f) so for instance Keys[0]=0000 1001(9) stands for up-right



*/
public class Input {

	private byte[] Keys=new byte[3];
	private boolean check,last=false;
	
	private HashMap<Integer,Boolean> lastKeyState=new HashMap<Integer,Boolean>();
	public Input(Window window ) {
	glfwSetKeyCallback(window.geWindow(),new Callback());
	}
	 public void findKeys() {
		     //Directional keys
		

		if(Find(GLFW_KEY_UP)) {
			Keys[0] |= 0x01;
			Keys[0] &= 0xfd;
			
		}
		else {
			Keys[0] &=0xfe;
		}
		
		if(Find(GLFW_KEY_DOWN)) {
			Keys[0] |= 0x02;
			Keys[0] &= 0xfe;
			
		}
		else {
			Keys[0] &= 0xfd;
		}
		if(Find(GLFW_KEY_RIGHT)) {
			Keys[0] |=0x04;
			Keys[0] &=0xf7;
			
		}
		
		else {
			Keys[0]&=0xfb;
			
		}
		if(Find(GLFW_KEY_LEFT)) {
			Keys[0] |=0x08;
			Keys[0]&=0xfb;
			
		}
		
		else {
			Keys[0] &=0xf7;
		}if(Find(GLFW_KEY_C)) {
			Keys[0] |=0x10;
			
		}
		else {
			Keys[0] &=0xef;
		}if(Find(GLFW_KEY_T)) {
			Keys[0] |=0x20;
			
		}
		else {
			Keys[0] &=0xdf;
		}
		//Different window keys;
		if(Find(GLFW_KEY_ESCAPE)) {
			Keys[1] |= 0x80;
			
			
		}
		if((Find(GLFW_KEY_LEFT_CONTROL) || Find(GLFW_KEY_RIGHT_CONTROL))&& Find(GLFW_KEY_F)) {
		    Keys[1] |= 0x40;
		}
		else {
			Keys[1] &=0xbf;
		}
	}
	
	
	public byte getDirectionalInput() {
		return Keys[0];
	}
	
	public boolean IsEscapePushed() {
		if(Keys[1] < 0){
			check=true;
		}
		else {
			check=false;
		}
		return check;
	}
	private boolean Find(int key) {
		try {
			Boolean newKey=Callback.keys[key];

			return newKey;	
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("sorry there is no"+key+"key");
		}}
 
	
	public byte stateOfFullscreen() {
		if((Keys[1] &0x40)!=0){
			check=true;
		}
		else {
			check=false;
		}
		byte state;
		
		if(last) {
			if(check) {
				state=3;// 11(still pressed)
				
			}
			else {
				state=2;//10(just released)
				
			}
			
		}
		else {
				if(check) {
					state=1;// 01(just pressed)
					
				}
				else {
					state=0;// 00(Still released)
				
				}
				
			}
		
		last=check;
		return state;
		
	}
	
	public byte getStateofButton(int button) {
		
		if(!lastKeyState.containsKey(button)) {
			lastKeyState.put(button,false);
		}
		boolean now;	
	    now=Find(button);
	
		byte state;
		if(lastKeyState.get(button)) {
			if(now) {
				state=3;// 11(still pressed)
				
			}
			else {
				state=2;//10(just released)
				
			}
			
		}
		else {
				if(now) {
					state=1;// 01(just pressed)
					
				}
				else {
					state=0;// 00(Still released)
				
				}
				
			}
		lastKeyState.put(button,now);
		return state;
	}
	
	
}
