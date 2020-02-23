package Collisions;
import org.joml.Vector2f;

import Data.Constants;
import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Start;
import gameEngine.VectorMath;


public class CircleColision extends Collisions {
private float r;
private Vector2f closest=new Vector2f(0,0),d=new Vector2f(0,0),closest2=new Vector2f(0,0),closest3=new Vector2f(0);
private boolean isCheccked=false,debug=Start.DEBUGCOLISIONS,lastResult=false;
private Model Circle,piont;
	
	
	public CircleColision(Vector2f position,float r,boolean isTrigger) {
		
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
			this.isTrigger=isTrigger;
			
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
	
	    result.sub(direction.mul(0.01f,new Vector2f(0,0)));
  //  result=this.position;
		return result;
	}
	
	
	public void debug() {
		if(debug) {
		
		
		   Render.draw(Circle, position, 0, 1,Start.circleCol1);
		   
		 
		  if(this.isCheccked)
		  Render.draw(piont, closest,0, 3, Start.COLTEX,Constants.YELLOW);
		  
			  
		  
		 
		   Render.draw(piont, closest2,0, 2, Start.COLTEX,Constants.BLACK);
		   Render.draw(piont, closest3,0, 2, Start.COLTEX,Constants.BLACK);
	   
		}
	
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}
	

	

	

	
	
	
	
	
	
	
	
	
}
