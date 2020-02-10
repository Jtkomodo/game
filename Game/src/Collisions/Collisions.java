package Collisions;



import org.joml.Vector2f;

import gameEngine.Start;
import gameEngine.VectorMath;




/*this is abstract class that all collision functions will extend it has all the important functions that have to be implemented for
 * the collisions to function properly
 * 
 * 
 * */








public abstract class Collisions extends VectorMath {
 protected boolean DebugColisions=Start.DEBUGCOLISIONS;
 protected boolean isBox=false,isTrigger=false;
	
	public boolean isTrigger() {
	return isTrigger;
}
	public boolean isBox() {
	return isBox;
}
	abstract protected void debug();
	abstract protected Vector2f findVector(Vector2f position, Vector2f movement,Vector2f direction,CircleColision circle); 
	abstract protected Vector2f findVector(Vector2f position, Vector2f movement,Vector2f direction,AABB box); 
	abstract protected boolean vsAABB(AABB box);
	abstract protected boolean vsCircle(CircleColision circle);
	
	 protected  float clamp(float value,float min,float max) {
		   
		   if(value<min) {
			   return min;
			   
		   }
		   
		   else if(value>max) {
			   return max;
		   }else {
			   
			   return value;
		   }
		   }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
