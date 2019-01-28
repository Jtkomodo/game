package gameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

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
			BufferedReader map= new BufferedReader(new FileReader(file.getAbsolutePath()+"/src/"+name+"Data/"));
			int c,k=0;
			while((c= map.read())!= -1) {
				Data.put(k, (byte) c);
				
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
				   	
				   	  
				   	  if(r==Tile1rgba[0] && g==Tile1rgba[1] && b==Tile1rgba[2] &&  a==Tile1rgba[3]) {
				   		  map[j/4][i]=1;
				   		  
				   		
				   	  }
				   	  else if(r==(byte)0xff && g==(byte)0xff && b==(byte)0xff &&  a==(byte)0xff){
				   		  map[j/4][i]=2;
				   	  }
				   	  
				   	  
				   	  else {
				   		  map[j/4][i]=0;
				   	  }
				   	  
				   	  }
		}
			return map;
			}
	
	
	public int[][] Getmap(){
	   return map;
		
	}
	
}
