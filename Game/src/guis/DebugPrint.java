package guis;

import gameEngine.Start;

public class DebugPrint extends GUIfunction {

	
	private String string;
	
	
	public DebugPrint(String string) {
		
		this.string=string;
		
		
	}
	
	
	
	@Override
	public boolean invoke() {
	   Start.DebugPrint(string);
       return true;
	}

}
