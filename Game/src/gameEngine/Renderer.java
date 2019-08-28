package gameEngine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class Renderer {

	private static int drawcalls=0,Location=Start.location,Rts=Start.RTS,Color=Start.Color,Projection=Start.Projection;
	private static ShaderProgram s=Start.s;
	private static boolean mirror=false;
	private static Camera cam=Start.cam;
	
	
	public static void draw(BatchedModel model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {
		
		Vector4f newcolor=new Vector4f(0);
		
		color.div(255,newcolor);
		 s.bind();
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),0,scale);
		   if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getProjection());
		       s.loadMat(Rts, rts);
		       s.loadVec4(Color,newcolor);;	  
		  model.draw();	 
			
		    
		
		  Start.s.loadVec4(Color,Data.Constants.DEFAULT_COLOR);;	 
		drawcalls++;
		
		mirror=false;
	
		
	}
	
	
	public static void draw(Model model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {

	Vector4f newcolor=new Vector4f(0);
		
		color.div(255,newcolor);

		
		 s.bind();
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),0,scale);
 if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getProjection());
		       s.loadMat(Rts, rts);
		       s.loadVec4(Color,newcolor);;	
		  model.draw();	 
			
		    
		
		
		
		
		  Start.s.loadVec4(Color,Data.Constants.DEFAULT_COLOR);;	 		
			drawcalls++;
		
			mirror=false;
				
	}
	
	
	
	
	
	
	public static void draw(BatchedModel model,Vector2f position,float angle,float scale,Texture texture) {

		
		
		 s.bind();
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),0,scale);
 if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getProjection());
		       s.loadMat(Rts, rts);
		  model.draw();	 
			
		    
		
		
		
		
		
		
			drawcalls++;
			mirror=false;
			
	}
	
	
	
   public static void draw(Model model,Vector2f position,float angle,float scale,Texture texture) {
		drawcalls++;
		
		 s.bind();
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),0,scale);
 if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getProjection());
		       s.loadMat(Rts, rts);
		  model.draw();	 
			
			mirror=false;
			
		
	}
	
	
	
	public static void changeShader(ShaderProgram shader) {
		
		Renderer.s=shader;
		
	}
	
	
	public static void resetDrawCalls() {
		drawcalls=0;
		
	}


	public static int getDrawcalls() {
		return drawcalls;
	}


	public static void setDrawcalls(int drawcalls) {
		Renderer.drawcalls = drawcalls;
	}


	public static void Mirror() {
		
		Renderer.mirror = true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
