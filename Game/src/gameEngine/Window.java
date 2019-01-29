package gameEngine;
import static org.lwjgl.glfw.GLFW.*;
import gameEngine.Callback;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL13.*;

import java.security.acl.NotOwnerException;

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
		glfwPollEvents();
		int[] width=new int[1], height=new int[1];
glfwGetWindowSize(window, width, height);

glViewport(0, 0, width[0], height[0]);


}
	public void destroy() {
		glfwDestroyWindow(window);
		
	}
	public void render() {
		GLFW.glfwSwapBuffers(window);
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
