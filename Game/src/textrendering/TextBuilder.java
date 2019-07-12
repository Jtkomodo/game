package textrendering;

import org.joml.*;

import gameEngine.BatchedModel;
import gameEngine.MatrixMath;
import gameEngine.Start;

public class TextBuilder extends Fontloader{

	private String text;
	private BatchedModel textModel;
	
	
	
	
	
	public TextBuilder(String Font, float AtlusSize) {
		super(Font, AtlusSize);//actually loads the the font file to be used
	    textModel=new BatchedModel();
	    
	}
	
	
	
	public void setString(String text) {//this makes our string model from a string to be drawn with drawstring()
		if(this.text!=text) {//this just checks to see if it is the same string as the last rendered 
		
		//------------init values-------------------	
	   textModel.flushBuffers();//important this makes sure that we start with a clean state so that nothing will go wrong
		
		this.text=text;
		 Vector2f offset=new Vector2f(0,0);//offset from our x coord of the current cursor position 
		float[] l;//uv coords (don't know why I called it l) 
		float[]  v;//vertex
	    Vector2f cursor=new Vector2f(0,0);// the curent cursor position 
		
	//actually make the model from values in font file	
		for(int i=0;i<text.length();i++) {//simple for loop
			int a=text.charAt(i);//this gets each char in order and uses it as the key to the hexmap that has all the info we need to draw in the correct place  
			Float[] val=super.Values.get(a);// this gets all the values for the current char
			float x=val[0];// x value of the topleft hand corner
			float y=val[1];//y value of the topleft hand corner
			float width=val[2];//this is the width of the char used for both find the correct uv values and vert
			float height=val[3];
			float xoff=val[4];//offset from the begging of the cursor in x
			float yoff=val[5];//in y
	         //loading into vertex for easy addition
			 offset.x=xoff;
			 offset.y=-yoff;
			float xadv=val[6];//how much the cursor needs to advance for the next char
			//makes easy reading 
			float Xz=x/Texwidth;
			float Yz=y/Texheight;
			float Xo=(x+width)/Texwidth;
			float Yo=(y+height)/Texheight;
			 float height2=height/2;
			 float width2=(width/2);
			 cursor.add(offset);//adds the offset to the cursor
			 l=new float[]{// loads in the correct uv coords
					Xz,Yz,
					Xo,Yz,
					Xo,Yo,
					Xz,Yo
					};
			
			v=new float[]{// finds the positons of all the corners of the char
				   cursor.x+ -width2,cursor.y+height2,
				   cursor.x+width2, cursor.y+height2,
				   cursor.x+width2,cursor.y-height2,
				   cursor.x+-width2,cursor.y-height2
				};
			textModel.addvaluestoVBO(v, l);//this is what actually adds the char into the batched model with the correct uv and vertex pionts
			
			 cursor.add(xadv,yoff);//moves the cursor for the next char
			
		}
		}
		
		
		
		
	}
	public void drawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		Start.s.bind();// binds our shader program
		super.tex.bind(2);//binds our texture to texture2d 
		//color.mul(0.01f);
		Matrix4f target= MatrixMath.getMatrix(new Vector2f(x/scale,y/scale), 0,scale);//this creates our matrix to multiply with the projection matrix to place in the correct coords
		 Start.s.loadInt(Start.location, 2);//this loads the texture binded to the second location into the fragment shader program so it can be used
	  	 Start.s.loadMat(Start.Projection,Start.cam.getProjection());// loads our projection matrix to the vertex shader
	     Start.s.loadMat(Start.RTS, target);//loads our position matrix into the vertex shader
	     Start.s.loadVec4(Start.Color,color);
		textModel.draw();// calls our draw call which actually does all the gpu commands 
		
		Start.s.loadVec4(Start.Color,new Vector4f(0,0,0,0));
	}
	
	public void drawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		Start.s.bind();// binds our shader program
		super.tex.bind(2);//binds our texture to texture2d 
		
		Matrix4f target= MatrixMath.getMatrix(new Vector2f(x/scale,y/scale), 0,scale);//this creates our matrix to multiply with the projection matrix to place in the correct coords
		 Start.s.loadInt(Start.location, 2);//this loads the texture binded to the second location into the fragment shader program so it can be used
	  	 Start.s.loadMat(Start.Projection,Start.cam.getProjection());// loads our projection matrix to the vertex shader
	     Start.s.loadMat(Start.RTS, target);//loads our position matrix into the vertex shader
	     
		textModel.draw();// calls our draw call which actually does all the gpu commands 
		
		
	}
	
	

}
