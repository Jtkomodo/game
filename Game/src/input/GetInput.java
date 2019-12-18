package input;


import java.util.HashMap;

import gameEngine.Callback;


public class GetInput {
   
	
	private static HashMap<Integer,Boolean> lastKeyState=new HashMap<Integer,Boolean>();
	
		
	private static boolean Find(int key) {
		try {
			Boolean newKey=Callback.keys[key];

			return newKey;	
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("sorry there is no"+key+"key");
		}}
 
	
	
	public static byte getStateofButton(int button) {
		
		if(!lastKeyState.containsKey(button)) {
			lastKeyState.put(button,false);
		}
		boolean now;	
	    now=Find(button);
	
		byte state;
		
		if(lastKeyState.get(button)) {
			if(now) {
				state=3;// 11(still pressed)
				
			}
			else {
				state=2;//10(just released)
				
			}
			
		}
		else {
				if(now) {
					state=1;// 01(just pressed)
					
				}
				else {
					state=0;// 00(Still released)
				
				}
				
			}
		lastKeyState.put(button,now);
		return state;
	}
	
	
}
