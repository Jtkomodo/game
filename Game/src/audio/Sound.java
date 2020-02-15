package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;

import static org.lwjgl.openal.AL10.*;

public class Sound {

private int soundid;
	
           public Sound(String fileName) {
        	 
        	   this.soundid=alGenBuffers();
        	 
        		InputStream stream=getClass().getResourceAsStream("/res/AudioFiles/"+fileName+".wav");
        	  
               WaveData sound=WaveData.create(stream);	   
        	  if(sound==null) {
        		  System.err.print("Somthing Went wrong when loading sound file "+fileName);
        	  }
               
               AL10.alBufferData(soundid,sound.format, sound.data,sound.samplerate);
        	   sound.dispose();
           }

          public int getSoundId() {
        	  
        	  return this.soundid;
        	  
          }












}

