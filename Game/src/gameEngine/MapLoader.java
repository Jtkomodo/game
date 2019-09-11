package gameEngine;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import Data.TilesData;
import textrendering.TextBuilder;

public class MapLoader {

	
	private float sizeOfEachTile;
	private BatchedModel model=new BatchedModel(); 
	private TextBuilder text=new TextBuilder("aakar",512);



	private int[][] map,keychart;
	private int a=0;
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
		a++;	  
		
    }
	
		
	
		
	}
	public void drawtiles(Texture tex) {
	
		Renderer.draw(model,new Vector2f(0),0, sizeOfEachTile,tex);
text.setString("Tiles: "+a);
text.UIDebugdrawString(Start.screencoordx,(480/2)+Start.screencoordy-20,.24f);
		
		a=0;
	}
	
	
	
	public void flushModel() {
		model.flushBuffers();
		
		
		
	}
	
	
	public BatchedModel getModel() {
		return model;
	}
	
	
	
	
}
