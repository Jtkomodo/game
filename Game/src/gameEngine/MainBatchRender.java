package gameEngine;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;

public class MainBatchRender {

	private static ArrayList<Texture> textures=new ArrayList<Texture>();
	private static ArrayList<MultipleTextureBatchedModel> models=new ArrayList<MultipleTextureBatchedModel>();
	private static int currentbatch=0;
	private static boolean Mirror=false; 
	
	
	
	
	
	public static void draw() {
		Texture array[]=new Texture[textures.size()];
		for(int i=0;i<models.size();i++) {
		Render.draw(models.get(i),new Vector2f(0),0,1, textures.toArray(array));
		}
		
		
		
	}
	
	
	
	
	
	public static void addModel(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture,Vector4f color) {
		
		
		float LocalVerts[]=model.getVertices();
		
		if(LocalVerts.length!=0 && LocalVerts.length%8==0) { 
				
		int TextureSampler=0;
		if(!textures.contains(texture)) {
			 textures.add(texture); 
		   
	   }
	   TextureSampler=textures.indexOf(texture);
	   int sections=LocalVerts.length/8;
	 
	   float[] verts=new float[sections*8];
	   float[] uvs=new float[sections*12];
	   float[] colors=new float[sections*16]; 
	   float[] translations=new float[sections*8];
		float cuvs[]= model.getUv_coords();
	   Matrix4f trs=new Matrix4f();
	   trs.identity();
	    trs.scale(scale);
	    
	    trs.rotateZ((float) java.lang.Math.toRadians(angle));
	  
	for(int i=0;i<sections;i++) {
		Matrix4f mat=new Matrix4f();
	      
		int i2=i*8;
	    int i3=i*12;
	    int i4=i*16;		
		mat.set(LocalVerts[i2+0],LocalVerts[i2+1],0,0,
	    		LocalVerts[i2+2],LocalVerts[i2+3],0,0,
	    		LocalVerts[i2+4],LocalVerts[i2+5],0,0,
	    		LocalVerts[i2+6],LocalVerts[i2+7],0,0
	    		
	    		);
	    

	  mat.transpose();
	    mat.mul(trs);
	    if(Mirror) {
	    	MatrixMath.mirror(mat);
	    	Mirror=false;
	    }
		  mat.transpose();  
	 
	   
	  //  trs.transpose();
	    Vector4f vert1=new Vector4f();
	    Vector4f vert2=new Vector4f();
	    Vector4f vert3=new Vector4f();
	    Vector4f vert4=new Vector4f();
	    mat.getColumn(0,vert1);
	    mat.getColumn(1,vert2);
	    mat.getColumn(2,vert3);
	    mat.getColumn(3,vert4);
	  
	    
	  verts[i2+0]=vert1.x;verts[i2+1]=vert1.y;   
	  verts[i2+2]=vert2.x;verts[i2+3]=vert2.y;
	  verts[i2+4]=vert3.x;verts[i2+5]=vert3.y; 
	  verts[i2+6]=vert4.x;verts[i2+7]=vert4.y;
	    
	   
	   
	  translations[i2+0]=position.x;translations[i2+1]=position.y;   
	  translations[i2+2]=position.x;translations[i2+3]=position.y;
	  translations[i2+4]=position.x;translations[i2+5]=position.y; 
	  translations[i2+6]=position.x;translations[i2+7]=position.y;
	    
	   
		
		
		

	  uvs[i3+0]=(cuvs[i2+0]);uvs[i3+1]=(cuvs[i2+1]);    uvs[i3+2]=(TextureSampler);
	  uvs[i3+3]=(cuvs[i2+2]);uvs[i3+4]=(cuvs[i2+3]);    uvs[i3+5]=(TextureSampler);
	  uvs[i3+6]=(cuvs[i2+4]);uvs[i3+7]=(cuvs[i2+5]);    uvs[i3+8]=(TextureSampler);
	  uvs[i3+9]=(cuvs[i2+6]);uvs[i3+10]=(cuvs[i2+7]);    uvs[i3+11]=(TextureSampler);
	
	  Vector4f newcolor=new Vector4f();
	color.div(255,newcolor);		
		
		
	 colors[i4+0]=newcolor.x;colors[i4+1]=newcolor.y;colors[i4+2]=newcolor.z;colors[i4+3]=newcolor.w;
	 colors[i4+4]=newcolor.x;colors[i4+5]=newcolor.y;colors[i4+6]=newcolor.z;colors[i4+7]=newcolor.w;
	 colors[i4+8]=newcolor.x;colors[i4+9]=newcolor.y;colors[i4+10]=newcolor.z;colors[i4+11]=newcolor.w;
	 colors[i4+12]=newcolor.x;colors[i4+13]=newcolor.y;colors[i4+14]=newcolor.z;colors[i4+15]=newcolor.w;
	}

	 
	
	addDataToBatch(verts,uvs,colors,translations);
	}
		
		   	
		
	}
	
	
public static void addModel(ModelFramwork model,Vector2f position,float angle,float scale,Texture texture) {
		
	float LocalVerts[]=model.getVertices();
	
	if(LocalVerts.length!=0 && LocalVerts.length%8==0) { 
			
	int TextureSampler=0;
	if(!textures.contains(texture)) {
		 textures.add(texture); 
	   
   }
   TextureSampler=textures.indexOf(texture);
   int sections=LocalVerts.length/8;
 
   float[] verts=new float[sections*8];
   float[] uvs=new float[sections*12];
   float[] colors=new float[sections*16]; 
   float[] translations=new float[sections*8];
	float cuvs[]= model.getUv_coords();
   Matrix4f trs=new Matrix4f();
   trs.identity();
    trs.scale(scale);
   
    trs.rotateZ((float) java.lang.Math.toRadians(angle));
  
for(int i=0;i<sections;i++) {
	Matrix4f mat=new Matrix4f();
      
	int i2=i*8;
    int i3=i*12;
    int i4=i*16;		
	mat.set(LocalVerts[i2+0],LocalVerts[i2+1],0,0,
    		LocalVerts[i2+2],LocalVerts[i2+3],0,0,
    		LocalVerts[i2+4],LocalVerts[i2+5],0,0,
    		LocalVerts[i2+6],LocalVerts[i2+7],0,0
    		
    		);
    

  mat.transpose();
    mat.mul(trs);
    if(Mirror) {
    	MatrixMath.mirror(mat);
    	Mirror=false;
    }
	  mat.transpose();  
 
   
  //  trs.transpose();
    Vector4f vert1=new Vector4f();
    Vector4f vert2=new Vector4f();
    Vector4f vert3=new Vector4f();
    Vector4f vert4=new Vector4f();
    mat.getColumn(0,vert1);
    mat.getColumn(1,vert2);
    mat.getColumn(2,vert3);
    mat.getColumn(3,vert4);
  
    
  verts[i2+0]=vert1.x;verts[i2+1]=vert1.y;   
  verts[i2+2]=vert2.x;verts[i2+3]=vert2.y;
  verts[i2+4]=vert3.x;verts[i2+5]=vert3.y; 
  verts[i2+6]=vert4.x;verts[i2+7]=vert4.y;
   
  
  translations[i2+0]=position.x;translations[i2+1]=position.y;   
  translations[i2+2]=position.x;translations[i2+3]=position.y;
  translations[i2+4]=position.x;translations[i2+5]=position.y; 
  translations[i2+6]=position.x;translations[i2+7]=position.y;
	    
	   
		
   

	
	
	

  uvs[i3+0]=(cuvs[i2+0]);uvs[i3+1]=(cuvs[i2+1]);    uvs[i3+2]=(TextureSampler);
  uvs[i3+3]=(cuvs[i2+2]);uvs[i3+4]=(cuvs[i2+3]);    uvs[i3+5]=(TextureSampler);
  uvs[i3+6]=(cuvs[i2+4]);uvs[i3+7]=(cuvs[i2+5]);    uvs[i3+8]=(TextureSampler);
  uvs[i3+9]=(cuvs[i2+6]);uvs[i3+10]=(cuvs[i2+7]);    uvs[i3+11]=(TextureSampler);
Vector4f newcolor=Constants.DEFAULT_COLOR;
		
	
	
 colors[i4+0]=newcolor.x;colors[i4+1]=newcolor.y;colors[i4+2]=newcolor.z;colors[i4+3]=newcolor.w;
 colors[i4+4]=newcolor.x;colors[i4+5]=newcolor.y;colors[i4+6]=newcolor.z;colors[i4+7]=newcolor.w;
 colors[i4+8]=newcolor.x;colors[i4+9]=newcolor.y;colors[i4+10]=newcolor.z;colors[i4+11]=newcolor.w;
 colors[i4+12]=newcolor.x;colors[i4+13]=newcolor.y;colors[i4+14]=newcolor.z;colors[i4+15]=newcolor.w;
}
addDataToBatch(verts,uvs,colors,translations);
	}

	   	
	}
	
	
	
	
	
	
	public static  void flushModel() {
		for(int i=0;i<models.size();i++) {
		models.get(i).flushBuffers();
		}
		
	}
	private static void addDataToBatch(float[] verts,float[] uvs,float[] colors,float[] translations) {
		if(models.isEmpty()) {
			models.add(new MultipleTextureBatchedModel());
			currentbatch=0;
		}



		 boolean batchedHasReachedMax=MainBatchRender.models.get(currentbatch).addvaluestoVBO(verts,uvs,colors,translations);

		 if(batchedHasReachedMax) {
		   models.add(new MultipleTextureBatchedModel());	
		   currentbatch++;	
		 }
		
	}
	public static void UiDraw() {
		
		
	}
	
	public static void Mirror() {
		
		Mirror=true;
		
		
	}
	
	
	
	
	
	
}
