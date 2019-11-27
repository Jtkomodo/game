package gameEngine;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.*;

import  org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;


public class Model extends ModelFramwork{
    private int drawCount;
	private int vao_id,v_id,tex_id,ind_id;
	private static  boolean draw=true;
	private float[] vertices,uv_coords;
	private int[] indeces= {
			0,1,2,
			2,3,0	
				
		};;
		

		

		
		
public Model(float width,float height,float u,float v,float texWidth,float texHeight) {
		  float  Texwidth=texWidth;
			float Texheight=texHeight;		
			float  wi=width;
			float  h=height;
			float  Texx=u;
			float Texy=v;		
			
			float[]  uv=new float[]{
					Texx/Texwidth,Texy/Texheight,
					(Texx+wi)/Texwidth,Texy/Texheight,
					(Texx+wi)/Texwidth,(Texy+h)/Texheight,
					Texx/Texwidth,(Texy+h)/Texheight
					};
			
			float[] vert={
					   -wi,+h,
						wi,h,
						wi,-h,
						-wi,-h};
			
			MakeModel(vert,uv,indeces);
			
			
		
	}
		
		
		
		
		
	public Model(float[] vertices,float[] uv_coords) {
		
	 MakeModel(vertices,uv_coords,indeces);
		
	}
		
		
	public Model(float[] vertices,float[] uv_coords,int[] indeces) {
		
		 MakeModel(vertices,uv_coords,indeces);
			
		}
				
		
		
		
		
		
		
		
	private void MakeModel(float[] vertices,float[] uv_coords,int[] indices) {
		this.vertices=vertices;
		this.uv_coords=uv_coords;
		this.indeces=indices;
		
		      drawCount=indices.length;
		vao_id=glGenVertexArrays();
		glBindVertexArray(vao_id);
		            //make buffer
		   v_id=glGenBuffers();//for vertices		
		   tex_id=glGenBuffers();//for uv Coords
			ind_id=glGenBuffers();//for indices  
			
		
		            //bind buffers and store the data in them

		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glBufferData(GL_ARRAY_BUFFER,makeBuffer(vertices),GL_DYNAMIC_DRAW);
		
		 
	    glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferData(GL_ARRAY_BUFFER,makeBuffer(uv_coords),GL_DYNAMIC_DRAW);
			
		  
	    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	      glBufferData(GL_ELEMENT_ARRAY_BUFFER,makeBuffer(indices),GL_STATIC_DRAW);

	 
	      enableAtributes();
	      
	      glBindBuffer(GL_ARRAY_BUFFER,v_id);//bind so we can use     
		  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
		  glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glVertexAttribPointer(1,2,GL_FLOAT,false,0,0);
				
	     	    
		   //unbind buffers
	//		glBindBuffer(GL_ARRAY_BUFFER,0);	     
		glBindVertexArray(0);

  //  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);		
			
	
		

	}
	
	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public float[] getUv_coords() {
		return uv_coords;
	}

	public void setUv_coords(float[] uv_coords) {
		this.uv_coords = uv_coords;
	}

	public int[] getIndeces() {
		return indeces;
	}

	public void setIndeces(int[] indeces) {
		this.indeces = indeces;
	}

	public void draw() {
	if(draw) {	
		
		
		glBindVertexArray(vao_id);
	
	//	enableAtributes();
	
	
		   glDrawElements(DrawMethod,drawCount,GL_UNSIGNED_INT,0);			
    //  disableAtributes();
  	glBindVertexArray(0);  
	}
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
	
		public void  changeUV(float[] data) {
		 glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferSubData(GL_ARRAY_BUFFER, 0, makeBuffer(data));
		  glBindBuffer(GL_ARRAY_BUFFER,0);
			}
	public void  changeVert(float[] data) {
		  glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glBufferSubData(GL_ARRAY_BUFFER, 0, makeBuffer(data));
		  glBindBuffer(GL_ARRAY_BUFFER,0);
			}
	

  
private void enableAtributes() {
	glEnableVertexAttribArray(0);
	   glEnableVertexAttribArray(1);
}
private void disableAtributes() {
	glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}




}

