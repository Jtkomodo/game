package gameEngine;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.io.EOFException;
import Data.TilesData;
import org.joml.Math;
import org.newdawn.slick.opengl.PNGDecoder;

public class WorldLoader {

	private static File file=new File(System.getProperty("user.dir"));
	private final byte[] Tile1rgba={(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0xff};
	private HashMap<Integer,Byte> Data= new HashMap<Integer,Byte>();  	
	private HashMap<Integer,TilesData> tilesData=new HashMap<Integer,TilesData>();
	private int i=0,width,height;
	private int[][] map;
	private Camera cam;
	
	
	
	public WorldLoader(String name, int width, int height,Camera cam) {
				this.width=width;
				this.height=height;
				this.cam=cam;
		
				String location=new String("/res/Maps/"+name+".png");
				
				
		try {
			PNGDecoder decoder = new PNGDecoder(getClass().getResourceAsStream(location));
			 

		    //create a byte buffer big enough to store RGBA values
		   ByteBuffer data = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

		    //decode
		    decoder.decode(data, decoder.getWidth() * 4, PNGDecoder.RGBA);

	 
		 data.flip();
		  byte c;
			int k=0;
			while(data.position()<data.capacity()) {
				try{
				c=data.get();	
				}catch(Exception e){
                   
					System.out.println(e);
					break;

				}
				Data.put(k,  c);
				
				
				k++;
			}
			data.clear();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	map=GetMapData();
	
	}
	
	
	private int[][] GetMapData() {
		int[][] map= new int[width][height];
		int y=0;
			for(int i=0;i<height;i++) {
				for(int j=0;j<width*4;j+=4) {  
				
					  byte r=Data.get((i*width*4)+j);
					  byte g=Data.get((i*width*4)+(j+1));
				   	  byte b=Data.get((i*width*4)+(j+2));
				   	  byte a=Data.get((i*width*4)+(j+3)); 
				   
				
				   	  if(r==TilesData.Dirt.getColorR() &&  g==TilesData.Dirt.getColorG() && b==TilesData.Dirt.getColorB()) {
							
						map[j/4][i]=1;
				   		  
				   		
						 }
						
				   	  else if(r==TilesData.Grassw.getColorR() && g==TilesData.Grassw.getColorG() && b==TilesData.Grassw.getColorB()){
				   		  map[j/4][i]=2;
				   	  }
				   	  else if(r==TilesData.Water.getColorR() && g==TilesData.Water.getColorG() && b==TilesData.Water.getColorB()){
						map[j/4][i]=3;
					}
					else if(r==TilesData.Grass.getColorR() &&  g==TilesData.Grass.getColorG() && b==TilesData.Grass.getColorB()) {
							
						map[j/4][i]=4;
							 
						   
						 }
				   	  
				   	 
				   	  
				   	  
		}
	}
			return map;
			}
	
	
	public int[][] Getmap(){
	   return map;
		
	}
	
}
