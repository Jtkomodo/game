package input;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import gameEngine.Start;


public class MousePositionCallback extends GLFWCursorPosCallback {

	protected static float X,Y;
	protected static float Width=Start.width,Height=Start.height;
	
	@Override
	public void invoke(long window, double x, double y) {
		
		  X=(float)x;
	      Y=(float)y;
		
	}

}
