package Scripter;

public abstract class Commands {

	
	
	protected boolean completed;
	protected boolean hasBeenStarted=false;
	protected boolean StopsInput=false;
	
    abstract void Start();
    abstract void Update(double time2);
	
	
	
	
	public boolean iscompleted() {
		return this.completed;
	}
	public boolean HasBeenStarted() {
		return hasBeenStarted;
	}

	 public boolean isStopsInput() {
			return StopsInput;
		}
	
}
