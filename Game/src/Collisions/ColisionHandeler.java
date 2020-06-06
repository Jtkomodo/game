package Collisions;



import java.util.LinkedList;

import org.joml.Vector2f;

import gameEngine.Start;
import textrendering.TextBuilder;

public class ColisionHandeler {
	private static LinkedList<Collisions> ColsPushed=new LinkedList<Collisions>();
	private static LinkedList<Collisions> Cols=new LinkedList<Collisions>();
	private static LinkedList<Collisions> Triggers=new LinkedList<Collisions>(); 
	private static LinkedList<Collisions> allCollisions=new LinkedList<Collisions>();
	private static TextBuilder text=new TextBuilder(Start.aakar);
	private static boolean colided;
	public  static int amountThrough=0;
	
	public static Vector2f updateVector(Collisions ToTestWith,Vector2f position,Vector2f oldPosition,Vector2f movement,Vector2f direction) {
		Vector2f returnVector=position;
		amountThrough++;
		for(int i=0;i<Cols.size();i++) {
		 Collisions col=Cols.get(i);
		 if(col!=ToTestWith) {
			if(ToTestWith.isBox && col.isBox) {
			  returnVector =CheckAndGetResponse((AABB)ToTestWith,(AABB)col,returnVector,oldPosition,movement, direction);
		 }else if(!ToTestWith.isBox && !col.isBox) {
			 returnVector =CheckAndGetResponse((CircleColision)ToTestWith,(CircleColision)col,returnVector,oldPosition,movement, direction);
			}
		 else if(!ToTestWith.isBox && col.isBox) {
			 returnVector =CheckAndGetResponse((CircleColision)ToTestWith,(AABB)col,returnVector,oldPosition,movement, direction);
			}
		 }
		}
		
		
		return returnVector;
		
	}
	

	public static Vector2f updateVector(Collisions ToTestWith,Vector2f position,Vector2f oldPosition,Vector2f movement,Vector2f direction,Collisions collsionTOSKIP) {
		Vector2f returnVector=position;
		amountThrough++;
		ColsPushed.add(collsionTOSKIP);
		for(int i=0;i<Cols.size();i++) {
		 Collisions col=Cols.get(i);
		 if(!col.equals(ToTestWith) && !ColsPushed.contains(col)) {
	
			if(ToTestWith.isBox && col.isBox) {
			  returnVector =CheckAndGetResponse((AABB)ToTestWith,(AABB)col,returnVector,oldPosition,movement, direction);
		 }else if(!ToTestWith.isBox && !col.isBox) {
			 returnVector =CheckAndGetResponse((CircleColision)ToTestWith,(CircleColision)col,returnVector,oldPosition,movement, direction);
			}
		 else if(!ToTestWith.isBox && col.isBox) {
			 returnVector =CheckAndGetResponse((CircleColision)ToTestWith,(AABB)col,returnVector,oldPosition,movement, direction);
			}
		 }
		}
		
		
		return returnVector;
		
	}
//	
//	public static boolean updateCollsionCheck(Collisions ToTestWith,Vector2f position,Vector2f oldPosition,Vector2f movement,Vector2f direction,Collisions collsionTOSKIP) {
//	
//		boolean check=false;
//		for(int i=0;i<Cols.size();i++) {
//		 Collisions trigger=Cols.get(i);
//		 if(!trigger.equals(ToTestWith) && !trigger.equals(collsionTOSKIP)) {
//	
//			 if(trigger!=ToTestWith) {
//					if(trigger.isBox) {
//						if(checkTriger(ToTestWith,(AABB)trigger)) {
//					check=true;	
//					break;
//					}
//					}
//					}else {
//						if(checkTriger(ToTestWith,(CircleColision)trigger)) {
//							check=true;
//							break;
//					}
//					
//					 }
//		 }
//		}
//	
//		
//		return check;
//		
//	}
	
	public static void updateTriggers(Collisions ToTestWith) {
		
		for(int i=0;i<Triggers.size();i++) {
			Collisions trigger=Triggers.get(i);
			 if(trigger!=ToTestWith) {
			if(trigger.isBox) {
				checkTriger(ToTestWith,(AABB)trigger);
			}else {
				checkTriger(ToTestWith,(CircleColision)trigger);
			}
			
			 }
			
		}
		
		
		
		
	}
	
	
	public static void addCollision(Collisions colision) {
		if(!allCollisions.contains(colision)) {
		if(colision.isTrigger) {
			Triggers.add(colision);
			Start.DebugPrint("Trigger added");
		}else {
			Cols.add(colision);
			Start.DebugPrint("Colision Added");
		}
		allCollisions.add(colision);
		}
		
	}
	
public static void addCollisions(Collisions[] colisions) {
		
		for(int i=0;i<colisions.length;i++) {
			addCollision(colisions[i]);
		}
		
		
	}
	
	
	
	public static void  Debug() {
		
		for(int i=0;i<allCollisions.size();i++) {
			Collisions col=allCollisions.get(i);
			if(col.DebugColisions) {  
			col.debug();
		    text.setString(""+i);
		    Vector2f vector=col.getPosition();
		    text.DebugdrawString(vector.x,vector.y,.5f);
		    
		    }
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static boolean checkTriger(Collisions a,AABB b) {
		
		return a.vsAABB(b);
		
		
	}
	
	

private static boolean checkTriger(Collisions a,CircleColision b) {
		
		return a.vsCircle(b);
		
		
	}
	
  
	
	
   private static Vector2f CheckAndGetResponse(AABB a,AABB b,Vector2f position,Vector2f oldposition, Vector2f movement, Vector2f direction) {
	   Vector2f vec=new Vector2f(0,0);
	  
		  boolean check=a.vsAABB(b);
		
		  if(check){
			  colided=true;
		  Vector2f new2= a.findVector(oldposition,movement,direction,b);
		  
	
	
			return new2;
		  }else {
				//a.setCenterPosition(position);	
			return position;
		  }
		
	}
	
	
  private static Vector2f  CheckAndGetResponse(CircleColision a,AABB b,Vector2f position,Vector2f oldposition, Vector2f movement, Vector2f direction) {
	  Vector2f vec=new Vector2f(0,0);
	  a.setPosition(position);
	  boolean check=a.vsAABB(b);
	  
	  if(check){
			
	  Vector2f new2= a.findVector(oldposition,movement,direction,b);
	  
	
		//a.setPosition(new2);			
		
		return new2;
	  }else {
			//a.setPosition(position);	
			return position;
	  }
		
		
	}
public static void remove(int index) {
	
	if(index<allCollisions.size()) {
	Collisions col=allCollisions.get(index);
	allCollisions.remove(col);
	if(Triggers.contains(col)) {
		Triggers.remove(col);
		
	}
	if(Cols.contains(col)) {
		Cols.remove(col);
		
	}
	Start.DebugPrint("REMOVED");
	}
	
}
  
  
  
  
  
  private static Vector2f CheckAndGetResponse(CircleColision a,CircleColision b,Vector2f position,Vector2f oldposition, Vector2f movement, Vector2f direction) {
		
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
			//a.setPosition(position);	
			return position;
		
	  }
		
	}

public static void setColided(boolean colided) {
	ColisionHandeler.colided = colided;
}
public static boolean getColided() {
	return colided;
}	
	
public static void FlushPushList() {
   ColsPushed.clear();
}


}	
	
	
	
	
	
	
	
	
	
	
	

