package guis;

import gameEngine.Window;

public class CloseWindow extends GUIfunction {

	
	private Window w;
	
	
	public CloseWindow(Window w) {
		
		this.w=w;
		
		
	}


	@Override
	public boolean invoke() {
		w.CloseWIndow();
		return true;
	}


	public Window getW() {
		return w;
	}


	public void setW(Window w) {
		this.w = w;
	}
	
	
	
	
	

}
