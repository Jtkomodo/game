package ScripterCommands;

public abstract class Commands {

	
	
	protected boolean completed;
	protected boolean hasBeenStarted=false;
	protected boolean StopsInput=false;
	protected boolean hasBeenReset=false;
    public abstract void Start();
    public abstract void Update(double time2);
	
	
	
	
	public boolean iscompleted() {
		return this.completed;
	}
	public boolean HasBeenStarted() {
		return hasBeenStarted;
	}

	 public boolean isStopsInput() {
			return StopsInput;
		}
    public void Reset() {
    	this.hasBeenStarted=false;
       this.hasBeenReset=true;
    }
    public boolean hasBeeenReset() {
    	return this.hasBeenReset;
    }
    
}
