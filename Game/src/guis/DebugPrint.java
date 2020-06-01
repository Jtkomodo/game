package guis;

import gameEngine.Start;

public class DebugPrint extends GUIfunction {

	
	private String string;
	
	
	public DebugPrint(String string) {
		
		this.string=string;
		
		
	}
	
	
	
	@Override
	public void invoke() {
	   Start.DebugPrint(string);

	}

}
