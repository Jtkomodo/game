package ScripterCommands;

import audio.Sound;
import audio.Source;
import gameEngine.Timer;

public class PlaySoundEffect extends Commands {

	private Sound sound;
	private Source source;
	private double StartTime;
	private float timeToTakeBeforePlaying;
	
	
    public PlaySoundEffect(Source source,Sound sound,float timeToTakeBeforePlaying) {
       this.sound=sound;	
       this.timeToTakeBeforePlaying=timeToTakeBeforePlaying;
       this.source=source;
    }
	
	
	
	
	
	
	@Override
	public void Start() {
    gameEngine.Start.DebugPrint("STARTED");
    this.completed=false;
	this.hasBeenStarted=true;
	this.hasBeenReset=false;
	this.StartTime=Timer.getTIme();

	}

	@Override
	public void Update(double time2) {
		if((time2-StartTime)>=timeToTakeBeforePlaying) {
	     this.source.play(this.sound);
	     this.completed=true;
		}
	}

   public boolean isPlayingSound() {
	  return this.source.isPlaying();
   }


}
