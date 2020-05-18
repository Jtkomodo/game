package gameEngine;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import Data.TilesData;
import rendering.MainRenderHandler;
import rendering.OneTextureBatchedModel;

public class MapLoader {

	
	private float sizeOfEachTile;
	private OneTextureBatchedModel model=new OneTextureBatchedModel(); 
	



	private int[][] map,keychart;


	private int a=0,tilesRenderd=0;

	protected HashMap<Integer,float[]> tilePosData=new HashMap<Integer,float[]>();
	protected HashMap<Integer,float[]> tileUVData=new HashMap<Integer,float[]>();
	protected HashMap<Integer,int[]> test2=new HashMap<Integer,int[]>();

	public MapLoader(int[][] map ,float sizeOfEachTile){
		
		this.sizeOfEachTile=sizeOfEachTile;
	
		loadInData(map);
		
	}
public MapLoader(Texture texture,MapFIle file,float sizeOfEachTile){
		
		this.sizeOfEachTile=sizeOfEachTile;
	
		loadInData(texture,file);
		
	}
	
	public void loadInData(Texture texture, MapFIle file) {
	this.map=file.getMap();
	float[] vert;	
	float[] uv;
	float texWidth=texture.getW();
	float texHeight=texture.getH();
	Vector2f[] uvData=file.getTextures();
	keychart=new int[map[0].length][map.length];	
	int key=0;	
	Start.DebugPrint(map.length+" "+map[0].length);
	
	
	for(int i=0;i<map[0].length;i++) {
		for(int j=0;j<map.length;j++) {
			
			int index=map[j][i];
			float addi=(0.5f*i)*2;	
	        float addj=(0.5f*j)*2;	
	    	keychart[i][j]=key;
				 vert=new float[]{
							addj-0.5f,addi+0.5f,
							addj+0.5f,addi+0.5f,
							addj+0.5f,addi-0.5f,
							addj-0.5f,addi-0.5f
		
				 };
				 

					
if(index>0) {
	      Vector2f uvCoords=uvData[index-1];
		   
		   
			tilePosData.put(key, vert); 
			
			
			uv=new float[]{
				uvCoords.x/texWidth,uvCoords.y/texHeight,
				(uvCoords.x+64)/texWidth,uvCoords.y/texHeight,
				(uvCoords.x+64)/texWidth,(uvCoords.y+64)/texHeight,
				uvCoords.x/texWidth,(uvCoords.y+64)/texHeight,
			};
			
			
			
			
		
			tileUVData.put(key, uv); 	} 
		key++;
		}
		
			
		}
		
		
		
	}
	
	
		
		
	
	
	public void loadInData(int[][] map) {
		this.map=map;
		float[] vert;
		float[] uv;
		keychart=new int[map[0].length][map.length];
		int key=0;
		
		Start.DebugPrint(map.length+" "+map[0].length);
		for(int i=0;i<map[0].length;i++) {
			for(int j=0;j<map.length;j++) {
				String a="";
			
				TilesData tile=TilesData.Dirt;
				int tileIndex=map[j][(map[0].length-1)-i];
			    
				keychart[i][j]=key;
				
				
				
				switch(tileIndex) {
				case 0:
					
					
				    break;
					
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
					 

						
if(tileIndex !=0) {
			   uv=tile.getUVcoords();
			   
			   
				tilePosData.put(key, vert); 
			
				tileUVData.put(key, uv); 	} 
			key++;
			}
			
			
			
		}
		
		return;
		
	
	}
	
	//test method
	public void loadTile(int tilex,int tiley) {
		
	if((tilex<map.length && tilex>=0)&&(tiley<map[0].length && tiley>=0))	{
		int key=keychart[tiley][tilex];
		
		float[] uv=tileUVData.get(key);		
		float[] vert=tilePosData.get(key);	
		
		if(tileUVData.containsKey(key)) {
		model.addvaluestoVBO(vert, uv);
		}
		this.a++;	  
		
		
	}
		
	}
	public void drawtiles(Texture tex) {

		MainRenderHandler.addEntity(new Entity(model,new Vector3f(0,0,-1000),0, sizeOfEachTile,tex));
tilesRenderd=a;
		a=0;

	}
	
	
	
	public void flushModel() {
		model.flushBuffers();
		
		
		
	}
	
	
	public OneTextureBatchedModel getModel() {
		return model;
	}
	
	public int getTilesrenderd() {
		
		return this.tilesRenderd;
	}
	public Vector2f findPositionOnMap(float x,float y) {
		  Vector2f mapPosition=new Vector2f(x/sizeOfEachTile,y/sizeOfEachTile);
		
			
			return mapPosition;
			
			
			
			
			
		}
	
	
}