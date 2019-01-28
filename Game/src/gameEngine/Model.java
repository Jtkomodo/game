package gameEngine;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.*;

import  org.lwjgl.BufferUtils;


public class Model {
    private int drawCount;
	private int v_id,tex_id,ind_id;
	
	public Model(float[] vertices,float[] uv_coords,int[] indices) {
		
		      drawCount=indices.length;
		
		            //make buffers
		   v_id=glGenBuffers();//for vertices		
		   tex_id=glGenBuffers();//for uv Coords
			ind_id=glGenBuffers();//for indices  
			
		
		            //bind buffers and store the data in them

		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glBufferData(GL_ARRAY_BUFFER,makeBuffer(vertices),GL_STATIC_DRAW);
		
		 
	    glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferData(GL_ARRAY_BUFFER,makeBuffer(uv_coords),GL_DYNAMIC_DRAW);
			
		  
	    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	      glBufferData(GL_ELEMENT_ARRAY_BUFFER,makeBuffer(indices),GL_STATIC_DRAW);

		    
		   //unbind buffers
	      unbindBuffers();

	}
	
	public void draw() {
  //enable  Attributes
glEnableVertexAttribArray(0);
glEnableVertexAttribArray(1);
		
	               //time to use those	
		
	glBindBuffer(GL_ARRAY_BUFFER,v_id);//bind so we can use 
	  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
		
    glBindBuffer(GL_ARRAY_BUFFER,tex_id);
	  glVertexAttribPointer(1,2,GL_FLOAT,false,0,0);
		
		
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
      glDrawElements(GL_TRIANGLES,drawCount,GL_UNSIGNED_INT,0);
		
    //unbind and disable

glDisableVertexAttribArray(0);
glDisableVertexAttribArray(1);
unbindBuffers();	
}
	
	
	
	
	//make buffers and put data in them
	private static FloatBuffer makeBuffer(float[] array ) {
		FloatBuffer buffer= BufferUtils.createFloatBuffer(array.length); //this just is initializing our buffer with the correct capacity
		buffer.put(array);//puts the data in the newly created buffer
		buffer.flip();//this allows the buffer to be read from very very important 
		return buffer;
	}
	//same as float just integer this time
	private static IntBuffer makeBuffer(int[] array ) {
		IntBuffer buffer= BufferUtils.createIntBuffer(array.length); 
		buffer.put(array);  
		buffer.flip();
		return buffer;
		
	}
	
	private static void unbindBuffers() {
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);		
		
	}
	
	public void  changeUV(float[] data) {
		
		 
	    glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferData(GL_ARRAY_BUFFER,makeBuffer(data),GL_DYNAMIC_DRAW);
		  glBindBuffer(GL_ARRAY_BUFFER,0);	
		  
		
	}
}
