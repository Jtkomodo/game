package Collisions;



import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import events.Flag;
import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.VectorMath;
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
	private boolean COLIDECHECK=false;
	public Flag colide_flag=new Flag(false);
    public boolean collide= false;
	
	
    public AABB(Vector2f position, float widthR, float heightR, float resistance,Flag flag) {
		this.colide_flag=flag;
    	construct(position,widthR,heightR,resistance);
		
	}
	

	
	public AABB(Vector2f position, float widthR, float heightR, float resistance) {
		construct(position,widthR,heightR,resistance);
		
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
		
		lcA=this.lc;
		rcA=this.rc;
		lcB=box.getLc();
		rcB=box.getRc();
		
		
	   this.collide=false;
	
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
		
			box.colide_flag.setState(false);
			return false;
			
		}
		else if((rcA.y<lcB.y) || (rcB.y<lcA.y)) {//if the Bottom side of A comes before the top side of B or vice versa return false
		
		   box.colide_flag.setState(false);
			
			return false;
		}
		
		else {//if both all sides have been checked and not resulted in no collision then there must be a collision
			box.colide_flag.setState(true);
			this.collide=true;
			
			return true;
		}
	  
		
		
		
		
		
	
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
	   
	  
	 //  Start.DebugPrint("("+ColisionHandeler.amountThrough+")");

	int amountThrough=ColisionHandeler.amountThrough;
	  
	   Vector2f currentmovement=new Vector2f(0,0);
	   movement.add(new Vector2f(position.x,position.y),currentmovement);//this is the current position after addition of the movement
//	 Vector2f magangle=VectorMath.getMagAndAngleComponet(movement);
//	
//	 
//	//   MainRenderHandler.addEntity(new Entity(piont, new Vector3f(position,z+10),-magangle.y,magangle.x*10,Start.VectorTex,Constants.RED));
//	   
//	 Start.DebugPrint(""+magangle.y);
//	   
	   
	   
	   
	   Vector2f newMOvement=currentmovement; //this is the return value   
	   
	
	
	   Vector2f penetration=new Vector2f(0,0);//this is the vector representing how much into the box we are in
	  
	
	   
	   
	   this.amount=box.resistance;

	   
	
	   
	  
	   if(amount!=0 && amount!=1) {     
		 
			
		   newMOvement=position.lerp(currentmovement, amount);
	
	   }else if(amount==0) {
				   
		
	if(this.collide) {
	
	    
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
	     

			 
          
			 currentmovement.sub(penetration.add(direction.mul(.001f,new Vector2f()),new Vector2f()), newMOvement); 
		 

		 
		 
		 
	   }else {
		   
		   newMOvement=currentmovement;
		   
		  
		   
	   }
	   
	   }else if(amount==1) {
		   return currentmovement;
	   }
	   
	   
	//   Start.DebugPrint("("+amountThrough+")"+"nm="+newMOvement);
	   
	   return newMOvement;
	   
	   
	   
	
   }
   
  
	   
	  
 




public void debug() {
	   if(Start.DEBUGCOLISIONS) {
		

		   MainRenderHandler.addEntity(new Entity(aabb, new Vector3f(position,z), 0, 1,Start.COLTEX,Constants.COL_COLOR_BLUE));
		    
	//	  MainRenderHandler.addEntity( new Entity(piont,new Vector3f( ClosestPosition,200), 0,3,Start.COLTEX,Constants.BAR_COLOR_ORANGE));
			
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



 
	public void setPosBeforeCol(Vector2f posBeforeCol) {
		PosBeforeCol = posBeforeCol;
	}

	

   

	
	
	
	
}