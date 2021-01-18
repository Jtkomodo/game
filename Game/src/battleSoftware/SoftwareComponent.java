package battleSoftware;

public abstract class SoftwareComponent {

	private int ID;
	protected boolean Is_Routine=false;
	protected boolean Is_Move=false;
	
	public SoftwareComponent(int ID) {
		this.ID=ID;
	}

	public int getID() {
		return ID;
	}

	public boolean Is_Routine() {
		return Is_Routine;
	}

	public boolean Is_Move() {
		return Is_Move;
	}

	
	
}
