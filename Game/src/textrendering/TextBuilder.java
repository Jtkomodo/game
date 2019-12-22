package textrendering;

import org.joml.*;

import Data.Constants;
import gameEngine.BatchedModel;
import gameEngine.MatrixMath;
import gameEngine.Render;
import gameEngine.Start;

public class TextBuilder{

	private String text;
	private BatchedModel textModel;
	private Fontloader loader;
	
	
	public TextBuilder(String Font, float AtlusSize) {
		loader=new Fontloader(Font, AtlusSize);//actually loads the the font file to be used
	    textModel=new BatchedModel();
	    
	}
	
	
	public TextBuilder(Fontloader loader) {
		this.loader=loader;//actually loads the the font file to be used
	    
		
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
		  
		   
			
			Float[] val=loader.Values.get(a);// this gets all the values for the current char
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
			float Xz=x/loader.Texwidth;
			float Yz=y/loader.Texheight;
			float Xo=(x+width)/loader.Texwidth;
			float Yo=(y+height)/loader.Texheight;
			 float height2=height/2;
			 float width2=(width/2);
			 cursor.add(offset);//adds the offset to the cursor
				if((char)a!='\n'){
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
		}
			
				else {
				 yoff-=100;
				cursor=new Vector2f(0,cursor.y);
				 
			}
					
			
	
					 
			 cursor.add(xadv,yoff);//moves the cursor for the next char		 
			
			
		}
	
		}
		
		
		
		
	}

	public void UIdrawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.UIdraw(textModel,new Vector2f(x,y),0,scale,loader.tex,color);
	
	}
	public void UIDebugdrawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.DebugUIdraw(textModel,new Vector2f(x,y),0,scale,loader.tex,color);
		
	}
	public void UIDebugdrawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.DebugUIdraw(textModel,new Vector2f(x,y),0,scale,loader.tex);
		
	}
	
	public void UIdrawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		 Render.UIdraw(textModel,new Vector2f(x,y),0,scale,loader.tex);
		
	}
	
	public void UIdrawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.UIdraw(textModel,new Vector2f(x,y),angle,scale,loader.tex,color);
	
	}
	public void UIDebugdrawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.DebugUIdraw(textModel,new Vector2f(x,y),angle,scale,loader.tex,color);
		
	}
	public void UIDebugdrawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.DebugUIdraw(textModel,new Vector2f(x,y),angle,scale,loader.tex);
		
	}
	
	public void UIdrawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		 Render.UIdraw(textModel,new Vector2f(x,y),angle,scale,loader.tex);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void drawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.draw(textModel,new Vector2f(x,y),0,scale,loader.tex,color);
	
	}
	public void DebugdrawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.Debugdraw(textModel,new Vector2f(x,y),0,scale,loader.tex,color);
		
	}
	public void DebugdrawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.Debugdraw(textModel,new Vector2f(x,y),0,scale,loader.tex);
		
	}
	
	public void drawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		 Render.draw(textModel,new Vector2f(x,y),0,scale,loader.tex);
		
	}
	
	public void drawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.draw(textModel,new Vector2f(x,y),angle,scale,loader.tex,color);
	
	}
	public void DebugdrawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		 Render.draw(textModel,new Vector2f(x,y),angle,scale,loader.tex,color);
		
	}
	public void DebugdrawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 Render.Debugdraw(textModel,new Vector2f(x,y),angle,scale,loader.tex);
		
	}
	
	public void drawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		 Render.Debugdraw(textModel,new Vector2f(x,y),angle,scale,loader.tex);
		
	}

}


