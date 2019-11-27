package animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gameEngine.Start;

public class AnimationHandler {

	
	private static HashMap<Integer,Animate> animations=new HashMap<Integer,Animate>();
	
	
	
	protected static void  addAnimation(Animate animation) {
		Start.DebugPrint("added animation "+animation.getKey(),AnimationHandler.class);
		animations.put(animation.getKey(),animation);
		
	}
	
	
	
	
	
	protected static void RemoveAnimatin(int key) {
		Start.DebugPrint("Removed animation "+key,AnimationHandler.class);
		animations.remove(key);
	}
	
	protected static boolean exsits(int key) {
		return animations.containsKey(key);
	}
	
	public static void update() {
		
		for (HashMap.Entry<Integer, Animate> entry : animations.entrySet()) {
		    entry.getValue().updateTime();
		}

	}
	
	
	public static int amountInMap() {
		return animations.size();
	}
	
	
	
	
	
	
	
	
}
