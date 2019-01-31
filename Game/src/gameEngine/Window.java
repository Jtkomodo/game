package gameEngine;

import static org.lwjgl.glfw.GLFW.GLFW_DECORATED;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowMonitor;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {
private long window;
private  Long fullscreen;
private GLFWVidMode vidmode;
private int width,height;


	public Window(int width, int height) {
		this.width=width;
		this.height=height;
		
		
		
		if(!glfwInit()) {
			throw new IllegalStateException("failed to initialize window");
			
		}
		glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
		glfwWindowHint(GLFW_DECORATED,GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
		
		window=glfwCreateWindow(width,height,"Game",0,0);
		if(window==0) {
			throw new IllegalStateException("failed to create window");
		}
		
		vidmode=glfwGetVideoMode(glfwGetPrimaryMonitor());
	
		
		glfwSetWindowPos(window,(vidmode.width()-width)/2,(vidmode.height()-height)/2);
		glfwSetWindowTitle(window,"game");
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glEnable(GL_TEXTURE_2D);
		//glClearColor(.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//allow gfwKeycallbacks for Callback
		


		}

	public boolean isExited() {
		return glfwWindowShouldClose(window);
	}
	public void update() {
		fullscreen=glfwGetWindowMonitor(window);
		glfwPollEvents();
		int[] width=new int[1], height=new int[1];
glfwGetWindowSize(window, width, height);
if(fullscreen==0) {
glViewport(0, 0, width[0], height[0]);
}else{
	glViewport(0, 0, vidmode.width(), vidmode.height()); 
}

}
	public void destroy() {
		glfwDestroyWindow(window);
		
	}
	public void render() {
		glfwSwapBuffers(window);
	}
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT);
	}


    public long geWindow() {
    	return window;
    }
    public void toggleFullscreen() {
    	fullscreen=glfwGetWindowMonitor(window);
    	if(fullscreen==0) {
		
			glfwSetWindowMonitor(window,glfwGetPrimaryMonitor(),0,0,vidmode.width(),vidmode.height(),vidmode.refreshRate());// this allows fullscreen window the thing that actually changes if fullscreen is used is the second argument
				
			glViewport(0, 0, vidmode.width(), vidmode.height()); //this changes the view to the size of the monitor so that it won't be small
				System.out.println(fullscreen);
    	}
    	else {
    		
    		glfwSetWindowMonitor(window,0,(vidmode.width()-width)/2,(vidmode.height()-height)/2,width,height,vidmode.refreshRate());
			glViewport(0,0,width,height);
		}
    		
    	
    }





}
