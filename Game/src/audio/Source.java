package audio;

import org.joml.Vector2f;
import static org.lwjgl.openal.AL10.*;
public class Source {

	
	private Vector2f position;
	private int sourceID;
	private float gain,pitch,rollOff,refrenceDistance,MaxDistance;
	
	/*MaxDistance only works in a linear Distance attenuation model*/
	
	
	
	
	public Source(Vector2f position,float gain,float pitch,float rollOff,float referenceDistance,float MaxDistance) {
		   this.position=position;
		   this.gain=gain;
		   this.pitch=pitch;
		   this.rollOff=rollOff;
		   this.refrenceDistance=referenceDistance;
		   this.MaxDistance=MaxDistance;
		   
		  sourceID=alGenSources();
		   alSourcef(sourceID, AL_GAIN, gain);
		   alSourcef(sourceID, AL_PITCH, pitch);
		   alSource3f(sourceID,AL_POSITION,-position.x,-position.y,1);
		   alSourcef(sourceID,AL_ROLLOFF_FACTOR,rollOff);
		   alSourcef(sourceID,AL_REFERENCE_DISTANCE,referenceDistance);
		   alSourcef(sourceID,AL_MAX_DISTANCE,MaxDistance);
			  
		   
	   }
	
	
	  public float getRollOff() {
		return rollOff;
	}


	public void setRollOff(float rollOff) {
		this.rollOff = rollOff;
		alSourcef(sourceID,AL_ROLLOFF_FACTOR,rollOff);
	}


	public float getRefrenceDistance() {
		return refrenceDistance;
	}


	public void setRefrenceDistance(float referenceDistance) {
		this.refrenceDistance = referenceDistance;
	alSourcef(sourceID,AL_REFERENCE_DISTANCE,referenceDistance);
	}


	public float getMaxDistance() {
		return MaxDistance;
	}


	public void setMaxDistance(float MaxDistance) {
		this.MaxDistance = MaxDistance;
	    alSourcef(sourceID,AL_MAX_DISTANCE,MaxDistance);
	}


	public void play(Sound sound){
		if(alGetSourcef(sourceID,AL_SOURCE_STATE)!=AL_PLAYING) {
		  alSourcei(sourceID, AL_BUFFER, sound.getSoundId());
		  alSourcePlay(sourceID);
		}
	  }
	
	  public void SetPosition(Vector2f position) {
		  this.position=position;
		   alSource3f(sourceID,AL_POSITION,-position.x,-position.y,1);
			 
		  
		  
	  }
	
	  public void setGain(float gain) {
		  this.gain=gain;
		  alSourcef(sourceID, AL_GAIN, gain);
		  
		  
	  }
	  
	  public void setPitch(float pitch) {
		  this.pitch=pitch;
		   alSourcef(sourceID, AL_PITCH, pitch);
	  }
	  
	   public Vector2f getPosition() {
			return position;
		}


		


		public float getGain() {
			return gain;
		}


		public float getPitch() {
			return pitch;
		}


	
}
