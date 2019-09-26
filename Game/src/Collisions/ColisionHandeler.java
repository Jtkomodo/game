package Collisions;

import org.joml.Vector2f;

public class ColisionHandeler {

	
	public static boolean checkTriger(AABB a,AABB b) {
		
		return a.vsAABB(b);
		
		
	}
	
	

public static boolean checkTriger(CircleColision a,AABB b) {
		
		return a.vsAABB(b);
		
		
	}
	
   public static boolean checkTriger(CircleColision a,CircleColision b) {
		
		return a.vsCircle(b);
		
		
	}
	
	
	
   public static Vector2f CheckAndGetResponse(AABB a,AABB b,Vector2f position,Vector2f oldposition, Vector2f movement, Vector2f direction) {
	   Vector2f vec=new Vector2f(0,0);
	   a.setPosition(position);
		  boolean check=a.vsAABB(b);
		  if(check){
		  Vector2f new2= a.findVector(oldposition,movement,direction,b);
		  
			new2.sub(position,vec);
			Vector2f vector=new Vector2f(0);
			vec.add(position,vector);
			
			a.setPosition(vector);			
		
			return vector;
		  }else {
				a.setPosition(position);	
			return position;
		  }
		
	}
	
	
  public static Vector2f  CheckAndGetResponse(CircleColision a,AABB b,Vector2f position,Vector2f oldposition, Vector2f movement, Vector2f direction) {
	  Vector2f vec=new Vector2f(0,0);
	  a.setPosition(position);
	  boolean check=a.vsAABB(b);
	  if(check){
	  Vector2f new2= a.findVector(oldposition,movement,direction,b);
	  
		new2.sub(position,vec);

		Vector2f vector=new Vector2f(0);
		vec.add(position,vector);
		
		a.setPosition(vector);			
		
		return vector;
	  }else {
			a.setPosition(position);	
			return position;
	  }
		
		
	}
	
  public static Vector2f CheckAndGetResponse(CircleColision a,CircleColision b,Vector2f position,Vector2f oldposition, Vector2f movement, Vector2f direction) {
		
	  Vector2f vec=new Vector2f(0,0);
	  a.setPosition(position);
	  boolean check=a.vsCircle(b);
	  if(check){
	  Vector2f new2= a.findVector(oldposition,movement,direction,b);
	  
		new2.sub(position,vec);
	   
		Vector2f vector=new Vector2f(0);
		position.add(vec,vector);
		
		
		
		
				
	
		
		return vector;
	  }else {
			a.setPosition(position);	
			return position;
		
	  }
		
	}
	
	
	


}	
	
	
	
	
	
	
	
	
	
	
	

