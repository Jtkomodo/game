package input;

import org.lwjgl.glfw.GLFWCharCallback;

import gameEngine.Start;
import static  org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;

public class CharCallback extends GLFWCharCallback {

	public static String string="";
	public static boolean takeInput=false;
	
	
	@Override
	public void invoke(long  window, int codepoint) {
		if(takeInput) {
				
			string =(string+String.valueOf(Character.toChars(codepoint)));
			
			
			
		}
			

	}
	
	
	
	public static void clearString() {
		string="";
	}
	
}
