package Collisions;
import org.joml.*;
import org.joml.Math;

import gameEngine.MatrixMath;
import gameEngine.Model;
import gameEngine.Start;
import gameEngine.VectorMath;


public class CircleColision extends CollisionFunctions {
private float r;
private Vector2f position,closest=new Vector2f(0,0),d=new Vector2f(0,0),closest2=new Vector2f(0,0),closest3=new Vector2f(0);
private boolean isCheccked=false,debug=Start.DEBUGCOLISIONS,lastResult=false;
private Model Circle,piont;
	
	
	public CircleColision(Vector2f position,float r) {
		
		this.r=r;
		this.position=position;
		 float[] Vert= {
				 -r,+r,
					r,r,
					r,-r,
					-r,-r
				 };
		int[] ind= {
				0,1,2,
				2,3,0	
					
			};float[] vert={
					-0.5f,+0.5f,
					0.5f,0.5f,
					0.5f,-0.5f,
					-0.5f,-0.5f
				};	float[] uv={
						0,0,
						1,0,
						1,1,
						0,1
						
						};
			
			 this.Circle=new Model(Vert,uv,ind);
			 this.piont=new Model(vert,uv,ind); 
			
			
	}
	@Override
	public boolean vsAABB(AABB box) {
		Vector2f boxPosition=box.getPosition();
		Vector2f boxlc=box.getLc();
		Vector2f boxrc=box.getRc();
		Vector2f vector=new Vector2f(0);
	    Vector2f closest;
	    Vector2f d= new Vector2f(0,0);position.sub(boxPosition,d);
			closest=new Vector2f(clamp(boxPosition.x+d.x,boxlc.x,boxrc.x),clamp(boxPosition.y+d.y,boxlc.y,boxrc.y));
		
			
		
		 closest.sub(this.position,vector);
		float mag=VectorMath.getMagnitude(vector);
	
		if(lastResult==false) {
			this.closest2=closest;		
			
			
		}
		if(mag<this.r) {
			
			
			
			lastResult=true;
			return true;
		}else {
		lastResult=false;
		return false;
		
		}
	}
	
	public boolean vsCircle(CircleColision b) {
	
		position.sub(b.position,d);
	    Vector2f normal=VectorMath.normalize(d);
	    normal.mul(b.r,closest);
	    closest.add(b.position);
	    this.isCheccked=true;
		
		
		float collisionDistance = this.r+b.r;
		float mag=VectorMath.getMagnitude(d);
		if(mag>collisionDistance) {
			return false;
		}
		else {
			
			return true;
			
		}
	}
	
	
	
	@Override
	public Vector2f findVector(Vector2f oldposition, Vector2f movement, Vector2f direction, CircleColision circle) {
		Vector2f currentmovement=new Vector2f(0,0);
		
		Vector2f c=new Vector2f(0,0);
	    Vector2f normal=VectorMath.normalize(d);
	    
	    normal.mul(-r,c);
	    closest.sub(c,currentmovement);
	    
	 
		   
		
		return currentmovement;
	}

	@Override
	public Vector2f findVector(Vector2f position, Vector2f movement, Vector2f direction, AABB box) {
		Vector2f result=new Vector2f(0);
		Vector2f v=new Vector2f(0);
	
	
	result.add(closest2,v);  
		

	  //Vector2f r=new Vector2f(0);
	  //closest2.sub(this.position,r);
	  //r.mul(2);
	//boolean lcx=false,rcx=false;
	Vector2f m=new Vector2f(0);
	Vector2f mN=new Vector2f(0);
	
		  if(v.x==box.getLc().x) {
				m.x=-1;
				 //v.sub(r,0,v);
				 
			   }else if(v.x==box.getRc().x) {
					m.x=1;
					//v.add(r,0,v);
					 
				   } if(v.y==box.getLc().y) {
						m.y=-1;
						// v.sub(0,r,v);
						 
					   }else if(v.y==box.getRc().y) {
							m.y=1;
							 //v.add(0,r,v);
							}
		
			mN=VectorMath.normalize(m);		   		
			mN.mul(r,m);	   
			v.add(m);	   
				   
		result=v;  
	  this.lastResult=false;//very important otherwise sliding will not work
	
	    result.sub(movement.mul(0.01f,new Vector2f(0,0)));
  //  result=this.position;
		return result;
	}
	
	
	public void debug() {
		if(debug) {
		
		  Start.s.bind();
		   Start.circleCol1.bind(5);
		   Matrix4f target=MatrixMath.getMatrix(new Vector2f(this.position.x,this.position.y),0,1);
		   
		   Start.s.loadInt(Start.location, 5); 
		   Start.s.loadMat(Start.Projection,Start.cam.getProjection());
		   Start.s.loadMat(Start.RTS, target);
		   Circle.draw();
		 
		   if(this.isCheccked){
		   Start.s.loadVec4(Start.Color,Data.Constants.YELLOW);;	  
		   Start.COLTEX.bind(5);
		   target=MatrixMath.getMatrix(new Vector2f(this.closest.x/3,this.closest.y/3),0,3);
		   Start.s.loadMat(Start.RTS, target);
		   piont.draw();
		  Start.s.loadVec4(Start.Color,Data.Constants.DEFAULT_COLOR);;	 
		 }
			  
		  
		   Start.s.loadVec4(Start.Color,Data.Constants.BLACK);;	  
		   Start.COLTEX.bind(5);
		   target=MatrixMath.getMatrix(new Vector2f(this.closest2.x/2,this.closest2.y/2),0,2);
		   Start.s.loadMat(Start.RTS, target);
		   piont.draw();
		   
		   Start.s.loadVec4(Start.Color,Data.Constants.BLACK);;	  
		   Start.COLTEX.bind(5);
		   target=MatrixMath.getMatrix(new Vector2f(this.closest3.x/2,this.closest3.y/2),0,2);
		   Start.s.loadMat(Start.RTS, target);
		   piont.draw();
		   
		  Start.s.loadVec4(Start.Color,Data.Constants.DEFAULT_COLOR);;	    
		   
		   
		}
	
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	

	

	
	
	
	
	
	
	
	
	
}
