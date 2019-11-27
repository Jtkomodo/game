package input;
import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;

import gameEngine.Callback;
import gameEngine.Window;
/*
	So far the first 4 bits in the zero index of the Keys Vector are for right,left,down up
	the last two in the second index is for whether escape is pushed and for whether full screen  
    short cut is pushed or not(ctrl+f) so for instance Keys[0]=0000 1001(9) stands for up-right



*/
public class GetInput {

	private byte[] Keys=new byte[3];
	private boolean check,last=false;
	
	private HashMap<Integer,Boolean> lastKeyState=new HashMap<Integer,Boolean>();
	public GetInput(Window window ) {
	glfwSetKeyCallback(window.geWindow(),new Callback());
	}
	
	private boolean Find(int key) {
		try {
			Boolean newKey=Callback.keys[key];

			return newKey;	
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("sorry there is no"+key+"key");
		}}
 
	
	
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
