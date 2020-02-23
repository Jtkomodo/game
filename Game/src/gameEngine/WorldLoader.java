package gameEngine;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.HashMap;

import org.newdawn.slick.opengl.PNGDecoder;

import Data.TilesData;
import customExceptions.MapSizeNotCorrectlyDefined;

public class WorldLoader {

	

	private HashMap<Integer,Byte> Data= new HashMap<Integer,Byte>();  	
    private int width,height;
	private int[][] map;
	
	
	
	
	public WorldLoader(String name,boolean png) {
     if(png) {	
		
		loadFromPng(name);
     }else {
    	 loadFromFile(name);
    	 
     }
	}
	
	
	private void loadFromFile(String name) {

		String location=new String("/res/Maps/"+name+".mp");
	    
	
	
		InputStream stream=getClass().getResourceAsStream(location);
		
		
	
		InputStreamReader isr = new InputStreamReader(stream);//creates a input stream so that we can read the file
		BufferedReader br=new BufferedReader(isr);//makes the bufferd reader that takes in the input stream from the file
		String line="";//just intializing the line
		try {
			line=br.readLine();
			String[] size=line.split(",");
			if(size.length==2) {
				width=Integer.parseInt(size[0]);
				height=Integer.parseInt(size[1]);
			}else {
				br.close();
				throw new MapSizeNotCorrectlyDefined(name);
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MapSizeNotCorrectlyDefined e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			try {
				line=br.readLine();
				String[] indexs=line.split(",");
				//TODO finish the file loading by file
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
	
	
	
	}


	private void loadFromPng(String name) {
		
		
		
		String location=new String("/res/Maps/"+name+".png");
		
		
try {
	InputStream stream=getClass().getResourceAsStream(location);
	PNGDecoder decoder = new PNGDecoder(stream);
	 

    //create a byte buffer big enough to store RGBA values
   ByteBuffer data = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

    //decode
    decoder.decode(data, decoder.getWidth() * 4, PNGDecoder.RGBA);


 data.flip();
width=decoder.getWidth();
height=decoder.getHeight();
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
	stream.close();

} catch (Exception e) {
	e.printStackTrace();
}

map=GetMapFromColors();
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	private int[][] GetMapFromColors() {
		int[][] map= new int[width][height];
		
			for(int i=0;i<height;i++) {
				for(int j=0;j<width*4;j+=4) {  
				
					  byte r=Data.get((i*width*4)+j);
					  byte g=Data.get((i*width*4)+(j+1));
				   	  byte b=Data.get((i*width*4)+(j+2));
				   	  byte a=Data.get((i*width*4)+(j+3)); 
				   
				if(a!=0) {
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
				   	  
				   	 
				}else {
					
					
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
