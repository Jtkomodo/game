package gameEngine;


import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import static textrendering.TextBuilder.*;
import java.nio.*;

import  org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

public class MultipleTextureBatchedModel {
	
	
	
	
	


	/*
	   This is to allow batch rendering we first just allocate buffers for the vertexes,uvcoords,and indeces then we use the method addvaluestoVBO() to add in a new model(don't forget to to make sure to change the positions so
	   they are not rendered on top of each other) then in order to draw all the models in one draw call we call the method draw() after first calling the enable() to change values on the fly we put in the section and the target
	   either v_id,tex_id,or ind_id and lastly the data in float array and integer array for v_id,tex_id and ind_id.  
	*/


		 private int drawCount,drawCount2;
			private int v_id,tex_id,ind_id,color_id,Trans_id;//made these public so that we can get to them from another method to change values
		private int indBase,pionter,sections;
	   private final int maxSections=1000000;
		private int pionter2=0,pionter3=0;
		public MultipleTextureBatchedModel() {
			
			//setting everything to 0 
			
			  drawCount=0;
				drawCount2=0;
				indBase=0;
				pionter=0;
				pionter2=0;
				pionter3=0;
				sections=0;
			//making space for buffers
			 v_id=glGenBuffers();//for vertices		
			   tex_id=glGenBuffers();//for uv Coords
				ind_id=glGenBuffers();//for indices  
				color_id=glGenBuffers();
			    Trans_id=glGenBuffers();
			
			
			//all dynamic draw so we can change them on the fly
			init();
			}
		
		
		public void draw() {
			
			glBindBuffer(GL_ARRAY_BUFFER,v_id);
			  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
				
		   
		   glBindBuffer(GL_ARRAY_BUFFER,tex_id);
			  glVertexAttribPointer(1,3,GL_FLOAT,false,0,0);
		   glBindBuffer(GL_ARRAY_BUFFER,color_id);
			  glVertexAttribPointer(2,4,GL_FLOAT,false,0,0);
		   glBindBuffer(GL_ARRAY_BUFFER,Trans_id);
			  glVertexAttribPointer(3,3,GL_FLOAT,false,0,0);
					  
			  
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
		      glDrawElements(GL_TRIANGLES,drawCount,GL_UNSIGNED_INT,0);/* this is a drawcall it takes all the data in the binded buffers and actually draws them to the screen with indices */
		  	unbindBuffers();
		      
		      
			}
		

		
		
		
	public boolean addvaluestoVBO(float[] v,float[] uv,float[] colors,float[] translation) {//used to add a new model to the buffers
		if((uv.length%12==0) && (v.length%8==0) && (colors.length%16==0 )&& (translation.length%12==0)) {//just makes sure they have the correct size of data
			
        int quads=v.length/8;		
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		 glBufferSubData(GL_ARRAY_BUFFER,pionter, makeBuffer(v));//adds the vertex values to the vertex buffer
	//glBindBuffer(GL_ARRAY_BUFFER,0);
		 glBindBuffer(GL_ARRAY_BUFFER, Trans_id);
		 glBufferSubData(GL_ARRAY_BUFFER,pionter2, makeBuffer(translation));//adds the vertex values to the vertex buffer
		 glBindBuffer(GL_ARRAY_BUFFER, tex_id);
		 glBufferSubData(GL_ARRAY_BUFFER, pionter2, makeBuffer(uv));//adds the uv values to the uv buffer
		 
		 
		 glBindBuffer(GL_ARRAY_BUFFER, color_id);
		 glBufferSubData(GL_ARRAY_BUFFER, pionter3, makeBuffer(colors));//adds the uv values to the uv buffer	
	int[] indeces=new int[6*quads];
		for(int i=0;i<quads;i++) {
			int i2=i*6;
			indeces[i2]=indBase;indeces[i2+1]=indBase+1;indeces[i2+2]=indBase+2;
			indeces[i2+3]=indBase+2;indeces[i2+4]=indBase+3;indeces[i2+5]=indBase;	
			indBase+=4;
		}
		 
		
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
		 glBufferSubData(GL_ELEMENT_ARRAY_BUFFER,drawCount2, makeBuffer(indeces));//remember all offsets are in bytes so a int is 4 bytes and a float is as well so we need to multiply be 4 to get the correct offset 
	  
		drawCount=drawCount+(6*quads);//used for telling the amount of triangles to draw when the drawcall is made
		drawCount2=drawCount*4;//this is for the correct pionter to the next value in the indeces buffer
		
		
		 pionter=pionter+(v.length*4);//pionter to the next value in the vertex buffer so that it won't write over the old one
		 pionter2=pionter2+(uv.length*4);//pionter to the next value in the uv buffer
		 pionter3=pionter3+(colors.length*4);//pionter to the next value in the color buffer
		 sections+=quads; //this is for the method change values
		 	unbindBuffers();
		 return (sections>=maxSections);
		 
		}else{
			Start.DebugPrint("sorry but that is not the correct format for the data. sizes of each is"+ v.length+","+uv.length+","+colors.length+","+translation.length+"/nShould be devisable by 8,12,16,12");
		return false;
		}
		
		
		
	}
		
	private  FloatBuffer makeBuffer(float[] array ) {
		FloatBuffer buffer= BufferUtils.createFloatBuffer(array.length); //this just is initializing our buffer with the correct capacity
		buffer.put(array);//puts the data in the newly created buffer
		buffer.flip();//this allows the buffer to be read from very very important
		return buffer;
	}
	//same as float just integer this time

	private  IntBuffer makeBuffer(int[] array ) {
		IntBuffer buffer= BufferUtils.createIntBuffer(array.length); 
		buffer.put(array);  
		buffer.flip();
		return buffer;
		
	}	
		
		
	public void enable() {
		   glEnableVertexAttribArray(0);
		   glEnableVertexAttribArray(1);
		   glEnableVertexAttribArray(2);
		   glEnableVertexAttribArray(3);
	}
	public void disable() {
		   glDisableVertexAttribArray(0);
	    glDisableVertexAttribArray(1);
	    glDisableVertexAttribArray(2);
	    glDisableVertexAttribArray(3);
	}

	private static void unbindBuffers() {
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);		
		
	}	
		
	
		
		
		
	public void flushBuffers() {//clears all the buffers and moves the pionter back to one
		
		init();
	    drawCount=0;
		drawCount2=0;
		indBase=0;
		pionter=0;
		pionter2=0;
		pionter3=0;
		sections=0;
	   
		
		
	}	
	private void init() {
		enable();
		
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glBufferData(GL_ARRAY_BUFFER,this.maxSections*(8*4),GL_DYNAMIC_DRAW);
		
		 
	glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferData(GL_ARRAY_BUFFER,this.maxSections*(12*4),GL_DYNAMIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER,color_id);
		  glBufferData(GL_ARRAY_BUFFER,this.maxSections*(16*4),GL_DYNAMIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER,Trans_id);
			  glBufferData(GL_ARRAY_BUFFER,this.maxSections*(12*4),GL_DYNAMIC_DRAW);
											
		  
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER,this.maxSections*(6*4),GL_DYNAMIC_DRAW);

	unbindBuffers();

	}








	

}
