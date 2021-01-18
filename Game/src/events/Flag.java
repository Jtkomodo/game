package events;

import java.util.LinkedList;

import gameEngine.Start;

public class Flag {

	
	private boolean state=false;
	private LinkedList<Events> events=new LinkedList<Events>();
	private boolean stateChanged=false;
	
	
	public Flag(boolean state) {
		this.state=state;
	}
	
	public void addEvent(Events event) {
		if(!this.events.contains(event)) {
			this.events.add(event);
		}	
	}
	public void removeEvent(Events event) {
		this.events.remove(event);
	}

	public void TriggerEvents() {
		Start.DebugPrint("triggerEvents");
		this.stateChanged=false;
		for(int i=0;i<this.events.size();i++) {
			Events event=events.get(i);
			if(event.Condition()) {
				event.Invoke();
			}
		}
	}
	
	
	
	public boolean setState(boolean state) {
	
		
		
		if(this.state!=state) {
			this.state=state;
			this.stateChanged=true;
			FlagHandler.SetFlag_Changed(true);
		}
		return this.stateChanged;
	}

	public boolean State() {
		return state;
	}

	public boolean StateChanged() {
		return stateChanged;
	}
	
}
