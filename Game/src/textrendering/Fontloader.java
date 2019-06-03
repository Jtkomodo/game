package textrendering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import gameEngine.*;
import gameEngine.MatrixMath;
public class Fontloader {
private String Font="aakar";

protected Texture tex;
protected float Texwidth,Texheight;
private static File file=new File(System.getProperty("user.dir"));
protected HashMap<Integer,Float[]> Values=new HashMap<Integer,Float[]>();



 public Fontloader(String Font,float size) {//has to be 1 to 1
	 this.Font=Font;
	 this.tex= new Texture(Font);
	 loadFile();
	 this.Texwidth=size;
	 this.Texheight=size;

	 
 }
 
  
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 private void loadFile() {
	
		//StringBuilder string=new StringBuilder();
		BufferedReader br;
		
		try{
			br=new BufferedReader(new FileReader(new File(file.getAbsolutePath()+"/src/res/fonts/"+Font+".fnt")));
			String line="";
			int i=0;
			int collums=0;
			int rows=0;
			int temp=0;
			while(true) {
			
			
			char c=(char)br.read();
			
			if(c=='*') {
			
		        line=line+" *";
				
				break;
			}
			
			
			if(c!='/') {
			line =line+c;
			
			
			}else {
				temp++;
				line=line+" ";
			}
			if(c=='\\') {
				collums++;
				rows=temp;
				temp=0;
			}
			
					
			
			
			
			i++;
			
			}
			
			br.close();
		
			i=0;
			int index=0;
			Float[] val=new Float[7]; 
			Integer ascii=0;
			String line2="";
			while(true){
				
			char c=line.charAt(i);
			if(c=='*') {
				
				break;
			}
			if((c==' ' || c=='\\')) {	
				if(index==1) {
					
					ascii=Integer.parseInt(line2);
				}
				if(index>1) {
				    val[index-2]=Float.parseFloat(line2);
				    }
			
				index++;
				if(c=='\\') {
				
					
					Values.put(ascii, val);
					index=0;
					val= new Float[7];
					
					
				}
				line2="";
				
			}else {
				line2=line2+c;}	
				
	
				
			
		i++;
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	
	     
   }
	 
  
	
	
	
}
