package gameEngine;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import Data.TilesData;

public class MapLoader {

	
	private float sizeOfEachTile;
	private BatchedModel model=new BatchedModel(); 
	private int[][] map,keychart;
	protected HashMap<Integer,float[]> tilePosData=new HashMap<Integer,float[]>();
	protected HashMap<Integer,float[]> tileUVData=new HashMap<Integer,float[]>();
	protected HashMap<Integer,int[]> test2=new HashMap<Integer,int[]>();
	protected HashMap<Integer,String> test=new HashMap<Integer,String>();
	public MapLoader(int[][] map ,float sizeOfEachTile){
		
		this.sizeOfEachTile=sizeOfEachTile;
		this.map=map;
		loadInData();
		
	}
	
	
	public void loadInData() {
		float[] vert;
		float[] uv;
		keychart=new int[map.length][map[0].length];
		int key=0;
		for(int i=0;i<map[0].length;i++) {
			for(int j=0;j<map.length;j++) {
				String a;    
				TilesData tile;
				int tileIndex=map[j][63-i];
			    
				keychart[i][j]=key;
				
				
				
				switch(tileIndex) {
				
				
				case 1:
					tile=TilesData.Dirt;
					a="dirt";
					break;
				
				case 2:
					tile=TilesData.Grassw;
					a="grassw";
					break;
				
				case 3:
					tile=TilesData.Water;
					a="water";
					break;
					
				case 4:
					tile=TilesData.Grass;
					a="grass";
					break;
				
				default:
					tile=TilesData.Dirt;
					a="default";
					break;
				
				}
				
				float addi=(0.5f*i)*2;	
		        float addj=(0.5f*j)*2;		
					 vert=new float[]{
								addj-0.5f,addi+0.5f,
								addj+0.5f,addi+0.5f,
								addj+0.5f,addi-0.5f,
								addj-0.5f,addi-0.5f
			
					 };
					 

						

			   uv=tile.getUVcoords();
			   
			   
				tilePosData.put(key, vert); 
				test.put(key, a);
				tileUVData.put(key, uv); 	 
			key++;
			}
			
			
			
		}
		
		return;
		
	
	}
	
	//test method
	public void loadTile(int tilex,int tiley) {
		//model.flushBuffers();
	if((tilex<map.length && tilex>=0)&&(tiley<map[0].length && tiley>=0))	{
		int key=keychart[tiley][tilex];
		
		float[] uv=tileUVData.get(key);		
		float[] vert=tilePosData.get(key);	
		
		
		model.addvaluestoVBO(vert, uv);
		
		
	}
		
	}
	public void drawtiles(Texture tex) {
		Start.s.bind();// binds our shader program
		tex.bind(2);//binds our texture to texture2d 
		
		Matrix4f target= MatrixMath.getMatrix(new Vector2f(0/this.sizeOfEachTile,0/this.sizeOfEachTile), 0,this.sizeOfEachTile);//this creates our matrix to multiply with the projection matrix to place in the correct coords
		 Start.s.loadInt(Start.location, 2);//this loads the texture binded to the second location into the fragment shader program so it can be used
	  	 Start.s.loadMat(Start.Projection,Start.cam.getProjection());// loads our projection matrix to the vertex shader
	     Start.s.loadMat(Start.RTS, target);//loads our position matrix into the vertex shader
		model.draw();// calls our draw call which actually does all the gpu commands 
		
		
	}
	
	
	
	public void flushModel() {
		model.flushBuffers();
		
		
		
	}
	
	
	
	
	
	
	
}
