package gameEngine;

import static java.lang.Math.*;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class VectorMath {

	
	
	
	
	
	
	public static Vector2f Lerp(Vector2f start,Vector2f end,float amount) {
		float x=start.x+((end.x-start.x)*amount);
		float y=start.y+((end.y-start.y)*amount);
		return new Vector2f(x,y);
	}
	
	
	
	
	
	
	
	public static Vector2f normalize(Vector2f vector) {
		Vector2f normal;
	if((vector.x !=0) || (vector.y!=0)){//this is so we don't have a divided by zero problem
		float mag=getMagnitude(vector);
		float newx=vector.x/mag;
		float newy=vector.y/mag;
		
		normal= new Vector2f(newx,newy);
	}
	else {
		normal=vector;
	}
	

		return normal;
		
	}
	
	public static float getMagnitude(Vector2f vector) {
		float magnitude;
		magnitude=(float) sqrt(pow(vector.x, 2)+pow(vector.y,2));
		return magnitude;
		
	}
	
	
	public static boolean  MagIsGreater(Vector2f a,Vector2f b) {
		float lA=(float)((float)pow(a.x,2)+pow(a.y,2));
		float lB=(float)((float)pow(b.x,2)+pow(b.y,2));
	     boolean check;
	     if(lA>lB) {
	    	 check=true;
	    	 
	     }
	     else {
	    	 check=false;
	     }
		return check;
		
	}
	
	public static boolean  MagIsLessThan(Vector2f a,Vector2f b) {
		float lA=(float)((float)pow(a.x,2)+pow(a.y,2));
		float lB=(float)((float)pow(b.x,2)+pow(b.y,2));
	     boolean check;
	     if(lA<lB) {
	    	 check=true;
	    	 
	     }
	     else {
	    	 check=false;
	     }
		return check;
		
	}
	
	
	
	public static boolean  MagIsEqual(Vector2f a,Vector2f b) {
		float lA=(float)((float)pow(a.x,2)+pow(a.y,2));
		float lB=(float)((float)pow(b.x,2)+pow(b.y,2));
	     boolean check;
	     if(lA==lB) {
	    	 check=true;
	    	 
	     }
	     else {
	    	 check=false;
	     }
		return check;
		
	}
	
	
	public static float dotProduct(Vector2f a,Vector2f b) {
		float dot=(a.x*b.x)+(a.y*b.y);
		return dot;
		
	}
   
	public static float AngleBeteen(Vector2f a,Vector2f b){
		float dot=dotProduct(a,b);
		float Maga=getMagnitude(a);
		float Magb=getMagnitude(b);
		float newmag=Maga*Magb;
		float n=dot/newmag;
		
		float angle=(float) acos(n);
		angle=(float)toDegrees(angle);
		return angle;
		
		
		
		
	}
	
	public static float AngleBeteenNormalized(Vector2f a,Vector2f b){
		float dot=dotProduct(a,b); 
		float angle=(float) acos(dot);
		angle=(float)toDegrees(angle);
		return angle;
		
		
		
		
	}
	
	//x->y->z->x
	//xyzzy
	//ab-ab
	
	public static Vector3f crossProduct(Vector2f a,Vector2f b) {
		Vector3f newA=new Vector3f(a,0);
		Vector3f newB=new Vector3f(b,0);
		Vector3f cross;
		
		float crossx=newA.y*newB.z-newA.z*newB.y;
		float crossy=newA.z*newB.x-newA.x*newB.z;
		float crossz=newA.x*newB.y-newA.y*newB.x;
		
		
		cross=new Vector3f(crossx,crossy,crossz);
		
		return cross;
		
	}
	
	
	public static Vector2f ProjectionV(Vector2f u,Vector2f v) {//projecting u onto v
       Vector2f d=new Vector2f();
		float dot=dotProduct(u,v); 
		float mag= getMagnitude(v);
		mag=(float) Math.pow(mag, 2);
		if(mag==0) {
			mag=.0000001f;
		}
		v.mul(dot/mag,d);
         
        return d;
		
	}

	public static Vector3f crossProduct(Vector3f u, Vector3f v) {
		Vector3f newA=u;
		Vector3f newB=v;
		Vector3f cross;
		float crossx=newA.y*newB.z-newA.z*newB.y;
		float crossy=newA.z*newB.x-newA.x*newB.z;
		float crossz=newA.x*newB.y-newA.y*newB.x;
		
		
		cross=new Vector3f(crossx,crossy,crossz);
		
		return cross;
		
	}
	
	
	
	
}
