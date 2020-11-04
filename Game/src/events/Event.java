package events;

import org.joml.Vector2f;

public abstract class Event {

	protected boolean ended=false;
	protected boolean Started=false;
	
    protected abstract void UpdateInput();
	protected abstract void Update(Vector2f Position);//this is where we put the code that will happen every frame
	protected abstract void Start();//this is where we put the init code
	protected abstract void Finsh();//this is where we close down the event
	
	public void call_To_Finish_Early() {
		this.ended=true;
	}
	
	
	public boolean HasEnded() {
		return this.ended;
		
	}
	public boolean HasStarted() {
		return this.Started;
	}
}
