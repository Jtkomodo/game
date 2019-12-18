package guis;

import Data.Moves;

import static org.lwjgl.glfw.GLFW.*;

import battleClasses.Enemy;
import battleClasses.Player;
import battleClasses.TimedButtonPress;
import gameEngine.Start;
import gameEngine.Window;

public abstract class GUIMEthods {

	public static Window window=Start.w;
	public static Player player=Start.p;
	public static String useSpMove="UseSPmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame";
	private static TimedButtonPress button=new TimedButtonPress(2,4,GLFW_KEY_SPACE);
	
	
	
	 public static void UseSPmove(Player p,String move) {
		
		 Moves m=p.getmoveFromString(move);
		 
		 
		 if(m!=null && m.isSpecailMove() ) {
			 
			
			 Start.DebugPrint("used move "+m.getName()+"--");
		
			 button.start();
			 
			 
			 
			 
			  
			 
			 }else {
				 
				 
				 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
				 
			 }
		
	 }
	 
   
	 public static void Usemove(Player p,String move) {
		 
		 Moves m=p.getmoveFromString(move);
		 
		 if(m!=null && !m.isSpecailMove()) {
			 
		
		 Start.DebugPrint("used move "+m.getName()+"--");
		 
		 button.start();
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 
		 }
	 }
	 
   
	 
	public static  void ExitWindow() {
		 window.CloseWIndow();
		 
	 }
	
	
	
	public static void SaveGame() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
