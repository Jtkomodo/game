package battleSoftware;

import events.Condition;
import events.Events;

public abstract class Routines extends SoftwareComponent{

	public Events event;
	
	public Routines(int ID,Events event) {
		super(ID);
		this.event=event;
	}

	public void activate() {
		this.event.ActivateFlags();
	}
	
	public void deactivate() {
		this.event.deactivateFlags();
	}
	
	
	

}
