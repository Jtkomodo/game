package input;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import input.GetInput;
public class InputHandler {

	
	
	private static List<Integer> Buttons=new LinkedList<Integer>();
	
	public static byte getStateofButton(int Button) {
		if(Buttons.contains(Button)) {
			return GetInput.getStateofButton(Button);
		
		}else {
			return 0;
		}
		
		
	}
	
	public static void EnableButton(int Button) {
		
	if(!Buttons.contains(Button)) 
		Buttons.add(Button);
	
	}
	
	public static void DisableButton(int Button) {
		if(Buttons.contains(Button)) 
			Buttons.remove((Integer)Button);
			
		
		
		
	}
	
	
    public static void DisableAll() {
		
		Buttons.clear();
	}
	
	
	public static void DisableAllBut(int[] buttons) {
		
		Buttons.clear();
	EnableButtons(buttons);
		
	}
	
	
	
	public static void EnableButtons(int[] Buttons) {
		
		
		for(int i=0;i<Buttons.length;i++) {
		
	      EnableButton(Buttons[i]);
		
	}
	}
}
