package gameEngine;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public abstract class ModelFramwork {

	protected static int DrawMethod=GL_TRIANGLES;
	
	
	public abstract void draw();
	
	
	public static void setDrawMethod(int drawMethod) {
		if((drawMethod==GL_TRIANGLES) ||(drawMethod==GL_LINES)||(drawMethod==GL_POINTS)) {
			if(drawMethod!=DrawMethod) {  
			System.out.println("Switching Draw Method to "+drawMethod );
			DrawMethod = drawMethod;}}
	}
	
	
}