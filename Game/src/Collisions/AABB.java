package Collisions;


import org.joml.Math;
import org.joml.Vector2f;

import Data.Constants;
import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Start;
import guis.FunctionCaller;
//06d7ea
public class AABB extends Collisions{
	private float widthR,heightR,resistance,amount;//resistance is the amount of push away the box will have on the player
	private Vector2f position=new Vector2f(0),lc=new Vector2f(0,0),rc=new Vector2f(0,0),r,Pposition=new Vector2f(0,0),beforeCol=new Vector2f(0,0),edgep=new Vector2f(0,0),edgen=new Vector2f(0,0),ActualPosition=new Vector2f(0);
	private Model aabb,piont;
	private boolean colide=false,COLIDECHECK=false;
	private boolean hasFunction=false;
	private FunctionCaller function;
	
	
	public AABB(Vector2f position ,float widthR,float heightR,float resistance,FunctionCaller function) {
		this.function=function;
		this.hasFunction=true;
		construct(position,widthR,heightR,resistance);

		}
	
	
	
	
	
	public AABB(Vector2f position ,float widthR,float heightR,float resistance) {
	
	construct(position,widthR,heightR,resistance);

	}
	
	
	private void construct(Vector2f position,float widthR,float heightR,float resistance) {
		 this.widthR=widthR;
		 this.heightR=heightR;
		 
	     ActualPosition=position;

	     position.add(widthR,-heightR,this.position);
		
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
		
		
		
		
	
		
	
		/*
		     lc_____________
		       |           |        
		       |     A     |     
		       |___________|
                            rc		       
		     lc_____________
		       |           | 
		       |     B     |     
		       |___________|
                            rc		
		
		
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
	 
	   Vector2f lc=new Vector2f(0,0);
	   Vector2f rc=new Vector2f(0,0);
	   Vector2f newMOvement=new Vector2f(0,0); //this is the rerturn value
	  
	   Vector2f currentmovement=new Vector2f(0,0);movement.add(new Vector2f(position.x,position.y),currentmovement);//this is the current position after addition of the movement
	 
	   currentmovement.sub(this.r,lc);//this is the bottom left corner of the cuurent box
	   currentmovement.add(this.r,rc); //this is the top right corner
	   if(this.colide) {
		
		  this.beforeCol=currentmovement;//this is just a position value so i can know exactly where the box is before change if a colision has happend
	 }
	
	   Vector2f penetration=new Vector2f(0,0);
	   
	   
	   this.amount=box.resistance;

	   
	
	   
	  
	   if(amount!=0 && amount!=1) {     
		 
			
		   newMOvement=lerp(position,currentmovement, amount);
	
	   }else if(amount==0) {
		Vector2f d= new Vector2f(0,0);position.sub(box.position,d);
	
	    Vector2f closest;
		closest=new Vector2f(clamp(box.position.x+d.x,box.lc.x,box.rc.x),clamp(box.position.y+d.y,box.lc.y,box.rc.y));
	//	System.out.println(d"before "+d+"after "+closest);
		//currentmovement.sub(pen,newMOvement);
		
		this.Pposition=closest;
				   
		
	if(this.colide) {
			Vector2f pen=new Vector2f(0,0);closest.sub(position,pen);
			
		   Vector2f norm=new Vector2f(0,0);pen.normalize(norm);
		   float dot=dotProduct(norm, new Vector2f(0,1));
		  
		if(Math.abs(direction.x)!=Math.abs(direction.y)){
		   
			
			
			
			
			
			
			
			
			
		
		 if(closest.x==box.lc.x) {
			
			 closest.sub(r.x,0,edgep);
			 
		   }else if(closest.x==box.rc.x) {
		
				 closest.add(r.x,0,edgep);
				 
			   } if(closest.y==box.lc.y) {
					
					 closest.sub(0,r.y,edgep);
					 
				   }else if(closest.y==box.rc.y) {
						
						 closest.add(0,r.y,edgep);
						 
					   }
		}else {
			
			   if(closest.x==box.lc.x) {
				

					 closest.sub(r.x,0,edgep);
					 
				   }else if(closest.x==box.rc.x) {
						
						 closest.add(r.x,-0,edgep);
						 
					   } if(closest.y==box.lc.y) {
							 
							 closest.sub(0,r.y,edgep);
							 
						   }else if(closest.y==box.rc.y) {
								
								 closest.add(0,r.y,edgep);
			
			

						   
						   
						   
	   }

			
					   
					   
					   
					   
					   
					   
					   
					   
					   
					   
					   
					   
					   
		}
		   
		   
		   edgep.sub(movement.mul(0.0001f,new Vector2f(0,0)));
		   
				newMOvement=edgep;
	   }else {
		   newMOvement=currentmovement;
		   
		  
		   
	   }
	   
	   }else if(amount==1) {
		   return currentmovement;
	   }
	   
	    return newMOvement;
	   
	   
	   
	
   }
   
  
	   
	  
   public void debug() {
	   if(Start.DEBUGCOLISIONS) {
		

		   Render.draw(aabb, position, 0, 1,Start.COLTEX,Constants.COL_COLOR_BLUE);
		  // Start.s.bind();
		   Render.draw(piont, ActualPosition, 0, 6,Start.COLTEX,Constants.RED);
		 
			  
			  Render.draw(piont, Pposition, 0, 1,Start.COLTEX,Constants.YELLOW);
			
			  Render.draw(piont, edgen, 0, 3,Start.COLTEX,Constants.YELLOW);
			  

		  // }
			
	   }
	   
	   
	   
	   
   }
   
   
   
	
	
	
//--------------getters--and--setters---------------------------------------------------	
	
	
	




	public float getWidthR() {
		return widthR;
	}



	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
		 
		position.sub(this.r,this.lc);
		position.add(this.r,this.rc); 
		 
		
	}

	public void setWidthR(float widthR) {
		this.widthR = widthR;
	}



	public float getHeighR() {
		return heightR;
	}



	public void setHeightR(float height) {
		this.heightR = height;
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

	public void setLc(Vector2f lc) {
		this.lc = lc;
	}

	public Vector2f getRc() {
		return rc;
	}

	public void setRc(Vector2f rc) {
		this.rc = rc;
	}






	
	
	
	
}