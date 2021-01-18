package events;

public class Events {


	private Condition[] conditions;
	private EventAction action;

	public Events(Condition[] conditions,EventAction action) {
		this.conditions=conditions;
		this.action=action;
	}

	public void ActivateFlags() {
		for(int i=0;i<this.conditions.length;i++) {
			FlagHandler.addFlag(conditions[i].getFlag());
			Condition condition=this.conditions[i];
			condition.getFlag().addEvent(this);
		}
	}
	public void deactivateFlags() {
		for(int i=0;i<this.conditions.length;i++) {
			Condition condition=this.conditions[i];
			condition.getFlag().removeEvent(this);
		}
	}
	
	
	
	

	public void Invoke() {

		this.action.invoke();


	}

	public boolean Condition() {
		boolean return_true=true;
		for(int i=0;i<this.conditions.length;i++) {
			Condition condition=this.conditions[i];
			if(!condition.isTrue()) {
				return_true=false;
				break;
			}
		}


		return return_true;

	}




}
