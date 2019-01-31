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
import static org.lwjgl.stb.STBImage.stbi_load;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;


public class Texture {
private  int TEXid;
private ByteBuffer data;
private  File file=new File(System.getProperty("user.dir"));
public  int h,w;
private IntBuffer width,height,comp;
private HashMap<Integer,Byte> imageData= new HashMap<Integer,Byte>();  
public Texture(String path) {
		
		
	
		
		//create buffers for stbi to use
		 width=BufferUtils.createIntBuffer(1);
         height=BufferUtils.createIntBuffer(1);
	     comp=BufferUtils.createIntBuffer(1);
		
		
	    
	                                   //load our texture 
		try {
		data=stbi_load(file.getAbsolutePath().toString()+"/src/res/"+path+".png",width,height,comp,4);
		if(data==null) {
			throw new IOException(STBImage.stbi_failure_reason());
		}
	    System.out.println(data.capacity()/4);
	    
	    loadMapWithBuffer(data);
	  
	  data.flip();
	  
	    
		
		//get the width and height of the image
		 w = width.get();
		 System.out.println(w);
		 h= height.get();
	
	    this.TEXid = glGenTextures();
	    
	  loadTexture(data);
	  } catch (IOException e) {
			System.out.println(e.getMessage());
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
private void loadMapWithBuffer(ByteBuffer data) {
	 
    for(int i=0;i<data.capacity();i++) {
    	imageData.put(i,data.get(i));
    	
    	
    }
	
	
}





public void PrintImageData() {
    for(int i=0;i<h;i++) {
    	
   	 
	for(int j=0;j<w*4;j+=4) {  
	  byte r=imageData.get((i*w*4)+j);
	  byte g=imageData.get((i*w*4)+(j+1));
   	  byte b=imageData.get((i*w*4)+(j+2));
   	  byte a=imageData.get((i*w*4)+(j+3)); 
   	System.out.print(" pixel["+String.format("%2d",j/4)+"]"+"["+String.format("%2d",i)+"]"+String.format(" "+"%2x",r)+String.format(" "+"%2x",g)+String.format(" "+"%2x",b)+String.format(" "+"%2x",a));
	if(j/4==w-1) {
		System.out.print("\n");
	}
	
	
	}
	
		
		
	}
   	  
}
private void loadTexture(ByteBuffer data) {
	
	  glBindTexture(GL_TEXTURE_2D,TEXid);//binds
      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
      glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,w,h,0,GL_RGBA,GL_UNSIGNED_BYTE,data);
   
   glBindTexture(GL_TEXTURE_2D,0);//unbinds
	
	
}
public void changeImageData(int x,int y,byte red,byte green,byte blue,byte Alpha) {
	int collum=(y*w*4),row=x*4;
	data.limit(data.capacity());
	data.flip();
	data.limit(data.capacity());
    System.out.println(row + collum);
	data.put(row+collum, red);
	data.put(row+collum+1, green);
	data.put(row+collum+2, blue);
	data.put(row+collum+3, Alpha);
	data.flip();
	data.limit(data.capacity());
	  
	loadMapWithBuffer(data);
	data.flip();
	loadTexture(data);
	
	
}

public void changeColor(byte red,byte green,byte blue,byte Alpha,byte nr,byte ng,byte nb,byte na ) {
	for(int i=0; i<data.capacity();i+=4) {
		byte R=imageData.get(i);
		byte G=imageData.get(i+1);
		byte B=imageData.get(i+2);
		byte A=imageData.get(i+3);
		
		if(R==red && G==green && B==blue && A==Alpha ) {
			data.limit(data.capacity());
			
			data.put(i, nr);
			data.put(i+1, ng);
			data.put(i+2, nb);
			data.put(i+3,na);
			}
		
	}
 data.flip();
  data.limit(data.capacity());
  
   loadMapWithBuffer(data);
   data.flip();
   loadTexture(data);
}

public void createFile(boolean createReadable,String path) throws IOException {

	
	if(createReadable) {
	File f=new File(file.getAbsolutePath()+"/src/"+path+"Readable");

DataOutputStream d=new DataOutputStream(new FileOutputStream(f));

for(int i=0;i<h;i++) {
	for(int j=0;j<w*4;j+=4) {  
		  byte r=imageData.get((i*w*4)+j);
		  byte g=imageData.get((i*w*4)+(j+1));
	   	  byte b=imageData.get((i*w*4)+(j+2));
	   	  byte a=imageData.get((i*w*4)+(j+3)); 
	   	d.writeChars(" pixel["+String.format("%2d",j/4)+"]"+"["+String.format("%2d",i)+"]"+String.format(" "+"%2x",r)+String.format(" "+"%2x",g)+String.format(" "+"%2x",b)+String.format(" "+"%2x",a));
		if(j/4==w-1) {
			d.writeChars("\n");
		}	
	
}

	
	
}
d.flush();
d.close();
	}
File f2=new File(file.getAbsolutePath()+"/src/"+path+"Data");	
DataOutputStream data=new DataOutputStream(new FileOutputStream(f2));
for(int i=0;i<imageData.size();i++ ) {
	data.writeByte(imageData.get(i).byteValue());
	
	
}

  data.flush();
  data.close();
    
	
	
}
}
