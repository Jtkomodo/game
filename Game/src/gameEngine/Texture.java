package gameEngine;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.PNGDecoder;


public class Texture {
private  int TEXid;

private static ArrayList<Texture> textures=new ArrayList<Texture>();
private int h,w;
private IntBuffer width,height,comp;

private String Path;
















public Texture(String path) {
		
		Path=path;
	
		
		//create buffers for stbi to use
		 width=BufferUtils.createIntBuffer(1);
         height=BufferUtils.createIntBuffer(1);
	     comp=BufferUtils.createIntBuffer(1);
		ByteBuffer data;
			    
	                                   //load our texture 
		try {
			
			String location=new String("/res/"+path+".png");
			
		//data=stbi_load(location,width,height,comp,4);
			InputStream stream=getClass().getResourceAsStream(location);
			 PNGDecoder decoder = new PNGDecoder(stream);
			

			    //create a byte buffer big enough to store RGBA values
			    data = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

			    //decode
			    
			    
			    decoder.decode(data, decoder.getWidth() * 4, PNGDecoder.RGBA);

			    
			    
		if(data==null) {
			throw new IOException();
		}
	 
		 stream.close();
	 //   loadMapWithBuffer(data);
		
		
		
		
		
		
		
		
	  
	     data.flip();
	  
	    
		
		//get the width and height of the image
		 w =decoder.getWidth();
		 h= decoder.getHeight();
	
	    this.TEXid = glGenTextures();
	    textures.add(this);
	  loadTexture(data);
	  } catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("didn't work");
		}
		
		
	
	}
	
	
	
	public void bind(int sampler) {
		  
		if(sampler>=0 && sampler<=31) {
		glActiveTexture(GL_TEXTURE0+sampler);
		glBindTexture(GL_TEXTURE_2D,TEXid);
		   
		} 
	

		
		
	}
	public void  unbind() {
		glBindTexture(GL_TEXTURE_2D,0);
	}

  public void delete() {
      GL11.glDeleteTextures(this.TEXid);
  }




private void loadTexture(ByteBuffer data) {
	
	  glBindTexture(GL_TEXTURE_2D,TEXid);//binds
	  GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
      glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,w,h,0,GL_RGBA,GL_UNSIGNED_BYTE,data);
   
   glBindTexture(GL_TEXTURE_2D,0);//unbinds
 
   
  
}
public static void deleteAllTextures() {
	for(int i=0;i<textures.size();i++) {
	textures.get(i).delete();
	}
	}





public int getH() {
	return h;
}



public int getW() {
	return w;
}






}
