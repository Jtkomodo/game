package input;



import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import gameEngine.Camera;
import gameEngine.MatrixMath;
import gameEngine.Start;
import gameEngine.Window;

import static org.lwjgl.glfw.GLFW.*;

public class GetInput {
   
	//this can be a object or just a static class the object is needed to have unique input for things that will require it the static type will be used by input handler class
   	
	private static HashMap<Integer,Boolean> lastKeyState=new HashMap<Integer,Boolean>();
	private HashMap<Integer,Boolean> lastKeyStateInstance=new HashMap<Integer,Boolean>();
	
	
	
	private static boolean Find(int key) {
		try {
			Boolean newKey;
			
				newKey=KeyCallback.keys[key];
			return newKey;	
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("sorry there is no"+key+"key");
		}}
 

	
	public static byte getStateofButton(int button) {
		
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
public byte getStateofButtonInstanced(int button) {
		
		if(!lastKeyStateInstance.containsKey(button)) {
			lastKeyStateInstance.put(button,false);
		}
		boolean now;	
	    now=Find(button);
	
		byte state;
		
		if(lastKeyStateInstance.get(button)) {
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
		lastKeyStateInstance.put(button,now);
		return state;
}



}
