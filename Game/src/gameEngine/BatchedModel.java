package gameEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import static textrendering.TextBuilder.*;
import java.nio.*;

import  org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;



/*
   This is to allow batch rendering we first just allocate buffers for the vertexes,uvcoords,and indeces then we use the method addvaluestoVBO() to add in a new model(don't forget to to make sure to change the positions so
   they are not rendered on top of each other) then in order to draw all the models in one draw call we call the method draw() after first calling the enable() to change values on the fly we put in the section and the target
   either v_id,tex_id,or ind_id and lastly the data in float array and integer array for v_id,tex_id and ind_id.  
*/

public class BatchedModel {
	 private int drawCount,drawCount2;
		private int v_id,tex_id,ind_id;//made these public so that we can get to them from another method to change values
	private int indBase,pionter,sections,DrawMethod=GL_TRIANGLES;
	private long Texsize=1000000;//size of the amount of space to allocate for the buffers 
	private long Indsize=1000000;
	private long Vertsize=1000000;
	public BatchedModel() {
		
		//setting everything to 0 
		
		drawCount=0;
		drawCount2=0;
		indBase=0;
		pionter=0;
		sections=0;
		//making space for buffers
		 v_id=glGenBuffers();//for vertices		
		   tex_id=glGenBuffers();//for uv Coords
			ind_id=glGenBuffers();//for indices  
			
		
		
		
		//all dynamic draw so we can change them on the fly
		init();
		}
	
	
	public void draw() {
		
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
			
	   glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glVertexAttribPointer(1,2,GL_FLOAT,false,0,0);
		 
		  
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	      glDrawElements(DrawMethod,drawCount,GL_UNSIGNED_INT,0);/* this is a drawcall it takes all the data in the binded buffers and actually draws them to the screen with indices */
	  	
		}
	

	
	
	
public void addvaluestoVBO(float[] v,float[] uv) {//used to add a new model to the buffers
	if((v.length == uv.length) && v.length==8) {//just makes sure they have the correct size of data
		

	
	glBindBuffer(GL_ARRAY_BUFFER, v_id);
	 glBufferSubData(GL_ARRAY_BUFFER,pionter, makeBuffer(v));//adds the vertex values to the vertex buffer
//glBindBuffer(GL_ARRAY_BUFFER,0);
	 
	glBindBuffer(GL_ARRAY_BUFFER, tex_id);
	 glBufferSubData(GL_ARRAY_BUFFER, pionter, makeBuffer(uv));//adds the uv values to the uv buffer
		
int[] indeces= new int[] {
			indBase,indBase+1,indBase+2,
			indBase+2,indBase+3,indBase	
			
			
			
			
	};
	
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	 glBufferSubData(GL_ELEMENT_ARRAY_BUFFER,drawCount2, makeBuffer(indeces));//remember all offsets are in bytes so a int is 4 bytes and a float is as well so we need to multiply be 4 to get the correct offset 
  
	drawCount=drawCount+6;//used for telling the amount of triangles to draw when the drawcall is made
	drawCount2=drawCount*4;//this is for the correct pionter to the next value in the indeces buffer
	
	indBase=indBase+4;//this makes sure that the indeces are loaded in right other wise it will  draw the same model multiple times
	 pionter=pionter+(8*4);//pionter to the next value in the vertex and uv buffers so that it won't write over the old ones
	sections++; //this is for the method change values
	
	
	}
	
	
	
}
	
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
	
	
public static void enable() {
	   glEnableVertexAttribArray(0);
	   glEnableVertexAttribArray(1);
}
public static void disable() {
	   glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}

private static void unbindBuffers() {
	glBindBuffer(GL_ARRAY_BUFFER,0);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);		
	
}	
	
public void changevalues(int section,int target,float[] newValue) {
	if((newValue.length ==8 )&& ((target==v_id)||(target==tex_id))) {
		long tempfloat=(section*8)*4;
		
			glBindBuffer(GL_ARRAY_BUFFER,target);
			glBufferSubData(GL_ARRAY_BUFFER, tempfloat, makeBuffer(newValue));
		    glBindBuffer(GL_ARRAY_BUFFER,0);
		
		}
	
	
	
	
}
public void changevalues(int section,int target,int[] newValue) {
	if((newValue.length ==6 )&& (target==ind_id)) {
		long tempfloat=(section*6)*4;
		
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,target);
			glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, tempfloat, makeBuffer(newValue));
		    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		
		}
	

	
	
}	
public void flushBuffers() {//clears all the buffers and moves the pionter back to one
	
	init();
    drawCount=0;
	drawCount2=0;
	indBase=0;
	pionter=0;
	sections=0;
   
	
	
}	
private void init() {
	
	
	glBindBuffer(GL_ARRAY_BUFFER,v_id);
	  glBufferData(GL_ARRAY_BUFFER,Vertsize,GL_DYNAMIC_DRAW);
	
	 
glBindBuffer(GL_ARRAY_BUFFER,tex_id);
	  glBufferData(GL_ARRAY_BUFFER,Texsize,GL_DYNAMIC_DRAW);
		
	  
glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
glBufferData(GL_ELEMENT_ARRAY_BUFFER,Indsize,GL_DYNAMIC_DRAW);

unbindBuffers();

}




public void setDrawMethod(int drawMethod) {
	if((drawMethod==GL_TRIANGLES) ||(drawMethod==GL_LINES)||(drawMethod==GL_POINTS)) {
       if(drawMethod!=DrawMethod) {
		System.out.println("Switching to Drawmethod "+drawMethod );
		DrawMethod = drawMethod;}}
}
}
