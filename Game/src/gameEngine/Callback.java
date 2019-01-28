package gameEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

//this class is needed to use glfwKeyCallback in Window class 

public class Callback extends  GLFWKeyCallback {

	
	public static boolean[] keys= new boolean[2560];
	
	
	@Override
	public void invoke(long window, int key, int scancode, int action , int mods ) {
		
		keys[key]=action !=GLFW.GLFW_RELEASE;
		
	
	}

	
	
	
	

	
	
	
	
	
	
}
