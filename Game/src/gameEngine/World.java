package gameEngine;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.EOFException;
import Data.Tiles;
import org.joml.Math;

public class World {

	private static File file=new File(System.getProperty("user.dir"));
	private final byte[] Tile1rgba={(byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0xff};
	private HashMap<Integer,Byte> Data= new HashMap<Integer,Byte>();  	
	private HashMap<Integer,Tiles> tiles=new HashMap<Integer,Tiles>();
	private int i=0,width,height;
	private int[][] map;
	private Camera cam;
	
	
	
	public World(String name, int width, int height,Camera cam) {
				this.width=width;
				this.height=height;
				this.cam=cam;
		try {
		
		  File file2= new File(file.getAbsolutePath()+"/src/"+name+"Data");
		  DataInputStream map=new DataInputStream(new FileInputStream(file2));
		  byte c;
			int k=0;
			while(true) {
				try{
				c=map.readByte();	
				}catch(EOFException e){
                   break;

				}
				Data.put(k,  c);
				
				
				k++;
			}
			map.close();
		
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
				   
				
				   	  if(r==Tiles.Dirt.getColorR() &&  g==Tiles.Dirt.getColorG() && b==Tiles.Dirt.getColorB()) {
							
						map[j/4][i]=1;
				   		  
				   		
						 }
						
				   	  else if(r==Tiles.Grassw.getColorR() && g==Tiles.Grassw.getColorG() && b==Tiles.Grassw.getColorB()){
				   		  map[j/4][i]=2;
				   	  }
				   	  else if(r==Tiles.Water.getColorR() && g==Tiles.Water.getColorG() && b==Tiles.Water.getColorB()){
						map[j/4][i]=3;
					}
					else if(r==Tiles.Grass.getColorR() &&  g==Tiles.Grass.getColorG() && b==Tiles.Grass.getColorB()) {
							
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
