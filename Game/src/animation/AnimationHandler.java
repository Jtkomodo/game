package animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import gameEngine.Start;

public class AnimationHandler {

	
	private static HashMap<Long,Animate> animations=new HashMap<Long,Animate>();
	
	
	
	protected static void  addAnimation(Animate animation) {
		Start.DebugPrint("added animation "+animation.getKey(),AnimationHandler.class);
		animations.put(animation.getKey(),animation);
		
	}
	
	
	
	
	
	protected static void RemoveAnimatin(long key) {
		Start.DebugPrint("Removed animation "+key,AnimationHandler.class);
		animations.remove(key);
	}
	
	protected static boolean exsits(long key) {
		return animations.containsKey(key);
	}
	
	public static void update() {
		
		for (Entry<Long, Animate> entry : animations.entrySet()) {
		    entry.getValue().updateTime();
		}

	}
	
	
	public static int amountInMap() {
		return animations.size();
	}
	
	
	
	
	
	
	
	
}
