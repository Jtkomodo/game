package rendering;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import org.lwjgl.opengl.GL15;

import java.util.LinkedList;

public abstract class ModelFramwork {

	protected static int DrawMethod=GL_TRIANGLES;
	protected float[] Vertices,UV_coords; 
	protected boolean BatchedModel=false;
	protected static LinkedList<ModelFramwork> listOfModels=new LinkedList<ModelFramwork>();
	
	protected abstract void draw();
	
	
	
	
	public static void setDrawMethod(int drawMethod) {
		if((drawMethod==GL_TRIANGLES) ||(drawMethod==GL_LINES)||(drawMethod==GL_POINTS)) {
			if(drawMethod!=DrawMethod) {  
			System.out.println("Switching Draw Method to "+drawMethod );
			DrawMethod = drawMethod;}}
	}


	public float[] getVertices() {
	
		return this.Vertices;
	}

   public static void deleteALL() {
	   for(int i=0;i<listOfModels.size();i++) {
		   listOfModels.get(i).delete();
		   
	   }
	   
   }


	protected abstract void delete();
	
	





	public float[] getUv_coords() {
		
		return UV_coords;
	}




	public boolean isBatchedModel() {
		return BatchedModel;
	}




	
	
	
}