package animation;


import java.util.LinkedList;
import java.util.List;


import gameEngine.Start;

public class AnimationHandler {

	
	private static List<Animate> animations=new LinkedList<Animate>();
	
	
	
	protected static void  addAnimation(Animate animation) {
		
		animations.add(animation);
		Start.DebugPrint("added animation "+animation.getKey(),AnimationHandler.class);
	}
	
	
	
	
	
	protected static void RemoveAnimatin(Animate key) {
		Start.DebugPrint("Removed animation "+key.getKey(),AnimationHandler.class);
		animations.remove(key);
		
	}
	
	protected static boolean exsits(Animate animation) {
		return animations.contains(animation);
	}
	
	public static int getIndex(Animate animation) {
		return animations.indexOf(animation);
	}
	
	
	
	public static void update() {
		
		for (int i=0;i<animations.size();i++) {
		    animations.get(i).updateTime();
		}
   
	}
	
	
	public static int amountInList() {
		return animations.size();
	}
	
	
	
	
	
	
	
	
}
