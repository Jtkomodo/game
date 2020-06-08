package rendering;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.*;

import  org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import gameEngine.Start;


public class Model extends ModelFramwork{
    private int drawCount;
	private int vao_id,v_id,tex_id,ind_id;
	private static  boolean draw=true;
	
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
			
			  UV_coords=new float[]{
					Texx/Texwidth,Texy/Texheight,
					(Texx+wi)/Texwidth,Texy/Texheight,
					(Texx+wi)/Texwidth,(Texy+h)/Texheight,
					Texx/Texwidth,(Texy+h)/Texheight
					};
			
			 Vertices=new float[]{
					   -wi,+h,
						wi,h,
						wi,-h,
						-wi,-h};
			
			MakeModel(Vertices,UV_coords,indeces);
			
			
		
	}
		
		
		
		
		
	public Model(float[] vertices,float[] uv_coords) {
		
	 MakeModel(vertices,uv_coords,indeces);
		
	}
		
		
	public Model(float[] vertices,float[] uv_coords,int[] indeces) {
		
		 MakeModel(vertices,uv_coords,indeces);
			
		}
				
		

	
	
public void changeValues(float[] vertices,float[] uv_coords) {
	
	changeVert(vertices);
	changeUV(uv_coords);
}
		
		
		
		
		
	private void MakeModel(float[] vertices,float[] uv_coords,int[] indices) {
		this.Vertices=vertices;
		this.UV_coords=uv_coords;
		this.indeces=indices;
		
		      drawCount=indices.length;
		vao_id=glGenVertexArrays();
		glBindVertexArray(vao_id);
		            //make buffer
		   v_id=glGenBuffers();//for vertices		
		   tex_id=glGenBuffers();//for uv Coords
			ind_id=glGenBuffers();//for indices  
			
			FloatBuffer Vertsbuffer=MemoryUtil.memAllocFloat(vertices.length);	
			FloatBuffer Uvsbuffer=MemoryUtil.memAllocFloat(uv_coords.length);	
			IntBuffer Indbuffer=MemoryUtil.memAllocInt(indeces.length);
			Vertsbuffer.put(vertices);
			Uvsbuffer.put(uv_coords);
		    Indbuffer.put(indeces);
		    Vertsbuffer.flip();
		    Uvsbuffer.flip();
		    Indbuffer.flip();
				
			
			
		
		            //bind buffers and store the data in them

			
	
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glBufferData(GL_ARRAY_BUFFER,Vertsbuffer,GL_DYNAMIC_DRAW);
		
		
	    glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferData(GL_ARRAY_BUFFER,Uvsbuffer,GL_DYNAMIC_DRAW);
			
	
	    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	      glBufferData(GL_ELEMENT_ARRAY_BUFFER,Indbuffer,GL_STATIC_DRAW);

	
	      enableAtributes();
	      
	      glBindBuffer(GL_ARRAY_BUFFER,v_id);//bind so we can use     
		  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
		  glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glVertexAttribPointer(1,2,GL_FLOAT,false,0,0);
				
	     	    
		   //unbind buffers
//		glBindBuffer(GL_ARRAY_BUFFER,0);	     
		glBindVertexArray(0);

 //  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		
	
			listOfModels.add(this);
			 MemoryUtil.memFree(Vertsbuffer);
			 MemoryUtil.memFree(Uvsbuffer);
			 MemoryUtil.memFree(Indbuffer);
		
Start.DebugPrint("made new model");
	}
	
	public float[] getVertices() {
		return Vertices;
	}

	public void setVertices(float[] vertices) {
		this.Vertices = vertices;
	}

	public float[] getUv_coords() {
		return UV_coords;
	}

	public void setUv_coords(float[] uv_coords) {
		this.UV_coords = uv_coords;
	}

	public int[] getIndeces() {
		return indeces;
	}

	public void setIndeces(int[] indeces) {
		this.indeces = indeces;
	}

	protected void draw() {
	if(draw) {	
		
		
		glBindVertexArray(vao_id);
	
	//	enableAtributes();
	
	
		   glDrawElements(DrawMethod,drawCount,GL_UNSIGNED_INT,0);			
    //  disableAtributes();
  	glBindVertexArray(0);  
	}
	}
	
	
	//make buffers and put data in them
		
		public void  changeUV(float[] data) {
		 glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		FloatBuffer buffer= MemoryUtil.memAllocFloat(data.length);
		buffer.put(data);
		buffer.flip();
		
		  glBufferSubData(GL_ARRAY_BUFFER, 0,buffer);
		  glBindBuffer(GL_ARRAY_BUFFER,0);
		 MemoryUtil.memFree(buffer);
		  this.UV_coords=data;
			}
	public void  changeVert(float[] data) {
		  glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  FloatBuffer buffer= MemoryUtil.memAllocFloat(data.length);
			buffer.put(data);
			buffer.flip();
		  glBufferSubData(GL_ARRAY_BUFFER, 0,buffer);
		  glBindBuffer(GL_ARRAY_BUFFER,0);
		  MemoryUtil.memFree(buffer);
		  this.Vertices=data;
			}
	

  
private void enableAtributes() {
	glEnableVertexAttribArray(0);
	   glEnableVertexAttribArray(1);
}
private void disableAtributes() {
	glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}





@Override
protected void delete() {
	glDeleteVertexArrays(vao_id);
	glDeleteBuffers(new int[] {this.v_id,this.tex_id,this.ind_id});
	
}




}

