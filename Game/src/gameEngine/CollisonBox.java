package gameEngine;

import org.joml.Vector2f;

public class CollisonBox {
	private float x,y,width,height,resistance;//resistance is the amount of push away the box will have on the player
	
	
	
	public CollisonBox(float x,float y ,float width,float height,float resistance) {
	 this.width=width;
	 this.height=height;
	 this.x=x;
	 this.y=y;
	 this.resistance=resistance;	
		
		
		
	}
	
	private boolean HitBox(float positionx,float positioiny) {
		
	//need to emplement this but this will tell if we have hit the box or not
		
		
		return false;
	}
	
	
   public Vector2f findVector(float x,float y) {
	
	   
	   
	   //this will calculate the position vector of  object after change after change 
	   
	   
	   
	   
	   
	   
	   return null;
	   
	   
	   
   }
	
	
	
//--------------getters--and--setters---------------------------------------------------	
	
	
	



	public float getX() {
		return x;
	}



	public void setX(float x) {
		this.x = x;
	}



	public float getY() {
		return y;
	}



	public void setY(float y) {
		this.y = y;
	}



	public float getWidth() {
		return width;
	}



	public void setWidth(float width) {
		this.width = width;
	}



	public float getHeight() {
		return height;
	}



	public void setHeight(float height) {
		this.height = height;
	}



	public float getResistance() {
		return resistance;
	}



	public void setResistance(float resistance) {
		this.resistance = resistance;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
