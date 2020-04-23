package Collisions;



import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.Entity;
import gameEngine.Start;
import guis.FunctionCaller;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;
//06d7ea
public class AABB extends Collisions{
	private float widthR,heightR,resistance,amount;//resistance is the amount of push away the box will have on the player
	private Vector2f lc=new Vector2f(0,0),rc=new Vector2f(0,0),r,ClosestPosition=new Vector2f(0,0),edgep=new Vector2f(0,0),edgen=new Vector2f(0,0),ActualPosition=new Vector2f(0);
	private Vector2f PosBeforeCol=new Vector2f();
	private Model aabb,piont;
	private float z=50;
	private boolean colide=false,COLIDECHECK=false;
	private boolean pushable;
	private boolean hasFunction=false;
	private FunctionCaller function;
	
	
	public AABB(Vector2f position ,float widthR,float heightR,float resistance,FunctionCaller function,boolean Pushable) {
		this.function=function;
		this.hasFunction=true;
		construct(position,widthR,heightR,resistance);
       this.pushable=Pushable;
		}
	
	
	
	
	
	public AABB(Vector2f position ,float widthR,float heightR,float resistance,boolean Pushable) {
	
	construct(position,widthR,heightR,resistance);
	   this.pushable=Pushable;
	}
	
	
	private void construct(Vector2f position,float widthR,float heightR,float resistance) {
		this.isBox=true;
		 this.widthR=widthR;
		 this.heightR=heightR;
		 
	     ActualPosition=position;

	     position.add(widthR,-heightR,this.position);
		
	     if(resistance==1) {
	    	 this.isTrigger=true;
	     }
	     
		 this.resistance=resistance;	
		 this.r=new Vector2f(this.widthR,this.heightR);
		
		 this.position.sub(this.r,this.lc);
		// System.out.println("lc= "+lc);
		 this.position.add(this.r,this.rc); 
		// System.out.println("lc= "+rc);
		 
		 if(DebugColisions) {
			 float[] Vert= {
					 -widthR,+heightR,
						widthR,heightR,
						widthR,-heightR,
						-widthR,-heightR
					 };
				float[] uvBg={
						0,0,
						1,0,
						1,1,
						0,1
						
						};
				int[] ind= {
						0,1,2,
						2,3,0	
							
					};float[] vert={
							-0.5f,+0.5f,
							0.5f,0.5f,
							0.5f,-0.5f,
							-0.5f,-0.5f
						};
					
					
					
					
				aabb= new Model(Vert,uvBg,ind);
				
				
				piont= new Model(vert,uvBg,ind);
				//piont.setDrawMethod(GL_POINTS);
			
			 
		 }
		
		
		
		
	}
	
	@Override
	protected boolean vsCircle(CircleColision circle) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean vsAABB(AABB box) {
		Vector2f lcB=new Vector2f(0,0);
		Vector2f rcB=new Vector2f(0,0);
		Vector2f lcA=new Vector2f(0,0);
		Vector2f rcA=new Vector2f(0,0);
	
		this.COLIDECHECK=true;
		this.colide=false;
		lcA=this.lc;
		rcA=this.rc;
		lcB=box.getLc();
		rcB=box.getRc();
		
		
	
	
//		
//		 MainRenderHandler.addEntity(new Entity(piont, new Vector3f(lc,200), 0, 3,Start.COLTEX,Constants.RED));
//		   MainRenderHandler.addEntity(new Entity(piont, new Vector3f(rc,200), 0, 3,Start.COLTEX,Constants.RED));
//			 MainRenderHandler.addEntity(new Entity(piont, new Vector3f(lcB,200), 0, 3,Start.COLTEX,Constants.RED));
//			   MainRenderHandler.addEntity(new Entity(piont, new Vector3f(rcB,200), 0, 3,Start.COLTEX,Constants.RED));
//		
//		
	
		
	
		/*
		       _____________rc
		       |           |        
		       |     A     |     
		       |___________|
              lc            		       
		       _____________rc
		       |           | 
		       |     B     |     
		       |___________|
              lc             		
		
		
		*/
		if((rcA.x<lcB.x) || (rcB.x<lcA.x)) {// if the right side of A comes before the left side of B or vice versa return false(they can not be colliding)
		
			
			return false;
		}
		else if((rcA.y<lcB.y) || (rcB.y<lcA.y)) {//if the Bottom side of A comes before the top side of B or vice versa return false
		
			
			
			
			return false;
		}
		
		else {//if both all sides have been checked and not resulted in no collision then there must be a collision
			this.colide=true;
			
			if(box.hasFunction) {
		
				box.getFunction().invoke();}
			return true;
		}
	  
		
		
		
		
		
	
	}
	public FunctionCaller getFunction() {
		return function;
	}





	public void setFunction(FunctionCaller function) {
		this.function = function;
	}





	@Override
	public Vector2f findVector(Vector2f position, Vector2f movement, Vector2f direction, CircleColision circle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
   public Vector2f findVector(Vector2f position, Vector2f movement,Vector2f direction,AABB box) {
	   //position is the current position before movement
	   //movement is the current step we are taking
	   //direction is a normalized version of the current velocity
	   //box is the box we are checking collision with
	   
	  
	  

	   Vector2f newMOvement=new Vector2f(0,0); //this is the return value
	  
	   Vector2f currentmovement=new Vector2f(0,0);
	   movement.add(new Vector2f(position.x,position.y),currentmovement);//this is the current position after addition of the movement
	 
	 //  MainRenderHandler.addEntity(new Entity(aabb, new Vector3f(currentmovement,z), 0, 1,Start.COLTEX,Constants.COL_COLOR_BLUE));
	   
	   
	   
	
	
	   Vector2f penetration=new Vector2f(0,0);//this is the vector representing how much into the box we are in
	  
	
	   
	   
	   this.amount=box.resistance;

	   
	
	   
	  
	   if(amount!=0 && amount!=1) {     
		 
			
		   newMOvement=position.lerp(currentmovement, amount);
	
	   }else if(amount==0) {
				   
		
	if(this.colide) {
		Vector2f d3= new Vector2f(0,0);
		//Vector2f d32= new Vector2f(0,0);
		this.getPosBeforeCol().sub(box.position,d3);
	
	
	    
	 
		box.ClosestPosition=new Vector2f(clamp(box.position.x+d3.x,box.lc.x,box.rc.x),clamp(box.position.y+d3.y,box.lc.y,box.rc.y));
	  
		   Vector2f closesta=box.ClosestPosition;
		   Vector2f closestb;
		   Vector2f d2=new Vector2f();
			closesta.sub(this.PosBeforeCol,d2);
			closestb=new Vector2f(clamp(currentmovement.x+d2.x,lc.x,rc.x),clamp(currentmovement.y+d2.y,lc.y,rc.y));
			MainRenderHandler.addEntity( new Entity(piont,new Vector3f(closestb,200), 0,3,Start.COLTEX,Constants.BAR_COLOR_ORANGE));
	     closestb.sub(closesta, penetration);
	     
		 if(!box.isPushable()) {
			 

			 currentmovement.sub(penetration.add(direction.mul(.001f,new Vector2f()),new Vector2f()), newMOvement); 
		 }else {
             
			 boolean save=ColisionHandeler.getColided();//saving the old  value
			 ColisionHandeler.setColided(false);//setting to false so that we can use it to check for collision

			 Vector2f saveVector=new Vector2f(box.getPosition());
		     box.setPosBeforeCol(saveVector);//posbeforecol set to current position
		     Vector2f boxPosition=push(currentmovement, closestb, penetration, direction, box);//this is the movement after push
		  
		     box.setCenterPosition(boxPosition);//setting position to the bocx position after push so we can check collisons
		     Vector2f d=new Vector2f();boxPosition.sub(saveVector,d);//this is the movement vector
		     if(d.equals(new Vector2f(0))) {
		    	 d.set(new Vector2f(0.001f,0.001f));
		    	 
		     }
		     Start.DebugPrint("P"+Math.round(boxPosition.x)+","+Math.round(boxPosition.y)); 
		     Start.DebugPrint(""+Math.round(saveVector.x)+","+Math.round(saveVector.y)); 
         
		     
		     Vector2f newBoxPosition=ColisionHandeler.updateVector(box, boxPosition, saveVector, d, d.normalize(new Vector2f()),this);//this is doing the collssion check
            
		     boolean colision=ColisionHandeler.getColided();//checking to see if any collision happened
		   
		     if(!colision) {
		     box.setCenterPosition(boxPosition);
		     newMOvement=currentmovement; 
		     }else {
		    	  box.setCenterPosition(newBoxPosition);
		    	 currentmovement.sub(penetration.add(direction.mul(.001f,new Vector2f()),new Vector2f()), newMOvement); 
		    
		     }
		     ColisionHandeler.setColided(save);//restoring the old value
		 
		 }
		 
		 
		 
//		  MainRenderHandler.addEntity(new Entity(aabb, new Vector3f(newMOvement,199), 0, 1,Start.COLTEX,Constants.COL_COLOR_RED));
//		   MainRenderHandler.addEntity(new Entity(aabb, new Vector3f(this.PosBeforeCol,198), 0, 1,Start.COLTEX,new Vector4f(0,255,0,Constants.COL_COLOR_RED.w)));
 
//		   MainRenderHandler.addEntity(new Entity(aabb, new Vector3f(this.PosBeforeCol,198), 0, 1,Start.COLTEX,new Vector4f(0,255,0,Constants.COL_COLOR_RED.w)));
//		   MainRenderHandler.addEntity(new Entity(piont, new Vector3f(lc,200), 0, 3,Start.COLTEX,Constants.RED));
//		   MainRenderHandler.addEntity(new Entity(piont, new Vector3f(rc,200), 0, 3,Start.COLTEX,Constants.RED));
//		
//		 
		 
		 
		 
		 
		 
	   }else {
		   newMOvement=currentmovement;
		   
		  
		   
	   }
	   
	   }else if(amount==1) {
		   return currentmovement;
	   }
	   
	    return newMOvement;
	   
	   
	   
	
   }
   
  
	   
	  
   public boolean isPushable() {
	return pushable;
}





public void debug() {
	   if(Start.DEBUGCOLISIONS) {
		

		   MainRenderHandler.addEntity(new Entity(aabb, new Vector3f(position,z), 0, 1,Start.COLTEX,Constants.COL_COLOR_BLUE));
		    
		  MainRenderHandler.addEntity( new Entity(piont,new Vector3f( ClosestPosition,200), 0,3,Start.COLTEX,Constants.BAR_COLOR_ORANGE));
			
		 //  MainRenderHandler.addEntity( new Entity(aabb, new Vector3f(edgen,0), 0, 1,Start.COLTEX,Constants.YELLOW));
		   
		   
		   
		

		  // }
			
	   }
	   
	   
	   
	   
   }
   
   
   
	
	
	
//--------------getters--and--setters---------------------------------------------------	
	
	
	




	public float getWidthR() {
		return widthR;
	}



	@Override
	public void setPosition(Vector2f position) {
		
		 position.add(widthR,-heightR,this.position);
		this.position.sub(this.r,this.lc);
		this.position.add(this.r,this.rc); 
		 
		
	}

	public void setCenterPosition(Vector2f position) {
		this.position.set(position);
		 
		position.sub(this.r,this.lc);
		position.add(this.r,this.rc); 
		 
		
	}


	public float getHeighR() {
		return heightR;
	}




	public float getResistance() {
		return resistance;
	}



	public void setResistance(float resistance) {
		this.resistance = resistance;
	}
	
	
	public Vector2f getLc() {
		return lc;
	}

	public Vector2f getRc() {
		return rc;
	}





	public float getZ() {
		return z;
	}





	public void setZ(float z) {
		this.z = z;
	}





	public Vector2f getPosBeforeCol() {
		return PosBeforeCol;
	}



   public Vector2f push(Vector2f currentmovement, Vector2f closestb,Vector2f penetration,Vector2f direction,AABB box) {
	   
	   Vector2f lc=new Vector2f();
		Vector2f rc=new Vector2f();
	   currentmovement.add(this.r,lc);
	   currentmovement.sub(this.r,rc);  
	
		Vector2f newMOvement=new Vector2f();
		if((closestb.x==lc.x || closestb.x== rc.x )) {//if on the left or right side
       	  box.getPosition().add(penetration.sub(direction.mul(.001f,new Vector2f()),new Vector2f()).x,0, newMOvement);
        }
		  
		else if((closestb.y==lc.y || closestb.y==rc.y)) {//if on top or bottom
      	  box.getPosition().add(0,penetration.sub(direction.mul(.001f,new Vector2f()),new Vector2f()).y, newMOvement);
       }
    
	   return newMOvement;
   }

	public void setPosBeforeCol(Vector2f posBeforeCol) {
		PosBeforeCol = posBeforeCol;
	}

	




	
	
	
	
}