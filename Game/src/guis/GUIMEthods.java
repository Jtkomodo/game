package guis;

import Data.Moves;
import battleClasses.Enemy;
import battleClasses.Player;
import gameEngine.Start;
import gameEngine.Window;

public abstract class GUIMEthods {

	public static Window window=Start.w;
	public static Player player=Start.p;
	public static String useSpMove=" UseSPmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame";
	
	
	
	 public static void UseSPmove(Integer move) {
		 Start.DebugPrint("used move "+move.intValue()+"--"+player.getspmoves()[move].getName());
		 
		
	 }
	 
   
	 public static void Usemove(Player p,String move) {
		 
		 Moves m=p.getmoveFromString(move);
		 
		 if(m!=null) {
		 Start.DebugPrint("used move "+m.getName()+"--");
		 
		 
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
