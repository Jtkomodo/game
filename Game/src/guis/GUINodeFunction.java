package guis;

import org.joml.Vector2f;

public abstract class GUINodeFunction {
	
	
	//this is for things that happen when a node has the cursor on it
	public abstract void invoke(Vector2f position,Vector2f padding,float sizeOfStrings);
	
	

}
