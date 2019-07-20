package Collisions;
import org.joml.*;

import gameEngine.MatrixMath;
import gameEngine.Model;
import gameEngine.Start;
import gameEngine.VectorMath;


public class CircleColision extends CollisionFunctions {
private float r;
private Vector2f position,closest=new Vector2f(0,0),d=new Vector2f(0,0);
private boolean isCheccked=false;
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
	protected boolean vsAABB(AABB box) {
		// TODO Auto-generated method stub
		return false;
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
	    
	    currentmovement.sub(movement.mul(0.0001f,new Vector2f(0,0)));
		   
		
		return currentmovement;
	}

	@Override
	public Vector2f findVector(Vector2f position, Vector2f movement, Vector2f direction, AABB box) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void debug() {
		
		
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
