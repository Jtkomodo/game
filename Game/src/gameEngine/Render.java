package gameEngine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import Data.Constants;

public class Render {

	private static int drawcalls=0,Location=Start.location,Rts=Start.RTS,Color=Start.Color,Projection=Start.Projection;
	private static ShaderProgram s=Start.s;
	private static boolean mirror=false;
	private static boolean  draw=true;
	private static Camera cam=Start.cam;
	
	
	
	
	
	
	
	public static void Debugdraw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {
		
		if(Start.Debugdraw)
			draw(model,position,angle,scale,texture,color);
	}
	
	
	
   public static void Debugdraw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture) {
		
		if(Start.Debugdraw)
			draw(model,position,angle,scale,texture);
	}
	

	public static void DebugUIdraw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {
		
		if(Start.Debugdraw)
			UIdraw(model,position,angle,scale,texture,color);
	}
	
	
	
  public static void DebugUIdraw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture) {
		
		if(Start.Debugdraw)
			UIdraw(model,position,angle,scale,texture);
	}
	
	
	
	
	
	
	public static void draw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {
if(draw) {
	Vector4f newcolor=new Vector4f(0);
		
		color.div(255,newcolor);

		
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),angle,scale);
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
		
			mirror=false;}
				
	}
	
	
	
	
	
	
	
	
	
	
   public static void draw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture) {
	   if(draw) {	
	   drawcalls++;
		
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),angle,scale);
 if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getProjection());
		       s.loadMat(Rts, rts);
		  model.draw();	 
			
			mirror=false;
	   }
		
	}
   public static void UIdraw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {
	   if(draw) {
		Vector4f newcolor=new Vector4f(0);
		
		color.div(255,newcolor);
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),angle,scale);
		   if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getUIProjection());
		       s.loadMat(Rts, rts);
		       s.loadVec4(Color,newcolor);;	  
		  model.draw();	 
			
		    
		
		  Start.s.loadVec4(Color,Data.Constants.DEFAULT_COLOR);	 
		drawcalls++;
		
		mirror=false;
	   }
		
	}
	public static void UIdraw(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture) {
		if(draw) {
		
		
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),angle,scale);
if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getUIProjection());
		       s.loadMat(Rts, rts);
		  model.draw();	 
			
		    
		
		
		
		
		
		
			drawcalls++;
			mirror=false;}
			
	}
    
	
	
	public static void draw(MultipleTextureBatchedModel model,Vector2f position,float angle,float scale,Texture[] textures) {
		
		changeShader(Start.Batcheds);
		int[] samplers=new int[textures.length];
		for(int i=0;i<textures.length;i++) {
			textures[i].bind(i);
			samplers[i]=i;
		}
		 
		   
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),angle,scale);
if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
          
		   s.loadMat(Start.UIProjection, cam.getUIProjection());
		   s.loadIntegers(Start.location2, samplers); 
		   s.loadMat(Start.Projection2,cam.getProjection());
		   s.loadMat(Start.RTS2, rts);
		     
		  model.draw();	 
			
		    
		
		
		
		
			changeShader(Start.s);
		
			drawcalls++;
			mirror=false;}
			
	
	
	public static void Debugdraw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture,Vector4f color) {
		
		if(Start.Debugdraw)
			draw(model,position,angle,scale,texture,color);
	}
	
	
	
   public static void Debugdraw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture) {
		
		if(Start.Debugdraw)
			draw(model,position,angle,scale,texture);
	}
	

	public static void DebugUIdraw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture,Vector4f color) {
		
		if(Start.Debugdraw)
			UIdraw(model,position,angle,scale,texture,color);
	}
	
	
	
  public static void DebugUIdraw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture) {
		
		if(Start.Debugdraw)
			UIdraw(model,position,angle,scale,texture);
	}
	
	
	
	
	
	
	public static void draw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture,Vector4f color) {
if(draw) {
	Vector4f newcolor=new Vector4f(0);
		
		color.div(255,newcolor);

		
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale.x,position.y/scale.y),angle,scale);
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
		
			mirror=false;}
				
	}
	
	
	
	
	
	
	
	
	
	
   public static void draw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture) {
	   if(draw) {	
	   drawcalls++;
		
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale.x,position.y/scale.y),angle,scale);
 if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getProjection());
		       s.loadMat(Rts, rts);
		  model.draw();	 
			
			mirror=false;
	   }
		
	}
   public static void UIdraw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture,Vector4f color) {
	   if(draw) {
		Vector4f newcolor=new Vector4f(0);
		
		color.div(255,newcolor);
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale.x,position.y/scale.y),angle,scale);
		   if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getUIProjection());
		       s.loadMat(Rts, rts);
		       s.loadVec4(Color,newcolor);;	  
		  model.draw();	 
			
		    
		
		  Start.s.loadVec4(Color,Data.Constants.DEFAULT_COLOR);;	 
		drawcalls++;
		
		mirror=false;
	   }
		
	}
	public static void UIdraw(ModelFramwork model,Vector2f position,float angle,Vector2f scale,Texture texture) {
		if(draw) {
		
		
		 
		   texture.bind(0);
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale.x,position.y/scale.y),angle,scale);
if(mirror) {
			   
			   MatrixMath.mirror(rts);
		   }
		   
		   s.loadInt(Location, 0); 
		  	   s.loadMat(Projection,cam.getUIProjection());
		       s.loadMat(Rts, rts);
		  model.draw();	 
			
		    
		
		
		
		
		
		
			drawcalls++;
			mirror=false;}
			
	}
	
	
	
	
	
	
	
	
	
	private static void changeShader(ShaderProgram shader) {
		
		s=shader;
		shader.bind();
		
	}
	
	
	public static void resetDrawCalls() {
		drawcalls=0;
		
	}


	public static int getDrawcalls() {
		return drawcalls;
	}


	public static void setDrawcalls(int drawcalls) {
		Render.drawcalls = drawcalls;
	}


	public static void Mirror() {
		
		Render.mirror = true;
	}
	
	
	
	
	public static Camera getCam() {
		return cam;
	}




	public static void setCam(Camera cam) {
		Render.cam = cam;
	}


	 public static void enable() {
		   
		   draw=true;
	   }
	   public static void disable() {
		   
	       draw=false;
	   }
	


	
	
	
	
	
	
	
	
}
