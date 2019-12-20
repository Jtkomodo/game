package guis;

import Data.Moves;
import Items.Item;

import static org.lwjgl.glfw.GLFW.*;

import battleClasses.Enemy;
import battleClasses.BattleEntity;
import battleClasses.TimedButton;
import battleClasses.TimedButtonCombo;
import battleClasses.TimedButtonHold;
import battleClasses.TimedButtonPress;
import gameEngine.Start;
import gameEngine.Window;

public abstract class GUIMEthods {

	public static Window window=Start.w;
	public static BattleEntity player=Start.p;
	public static String useSpMove="UseSPmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame",UseItem="UseItem";
	private static TimedButtonPress button=new TimedButtonPress(2,4,GLFW_KEY_RIGHT);
	private static TimedButtonHold buttonHold=new TimedButtonHold(2,4,2,GLFW_KEY_RIGHT);
	private static TimedButtonCombo buttonCombo=new TimedButtonCombo(new TimedButton[]{button,buttonHold,button,buttonHold},4,2);
	
	 public static void UseSPmove(BattleEntity p,String move) {
		
		 Moves m=p.getmoveFromString(move);
		 
		 
		 if(m!=null && m.isSpecailMove() ) {
			 
			
			 Start.DebugPrint("used move "+m.getName()+"--");
		
			 buttonCombo.start();
			 Start.MoveInprogress=true;
			 Start.Button=buttonCombo;
			 
			 
			 
			  
			 
			 }else {
				 
				 
				 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
				 
			 }
		
	 }
	 
   
	 public static void Usemove(BattleEntity p,String move) {
		 
		 Moves m=p.getmoveFromString(move);
		 
		 if(m!=null && !m.isSpecailMove()) {
			 
		
		 Start.DebugPrint("used move "+m.getName()+"--");
		 
		 button.start();
		 Start.MoveInprogress=true;
		 Start.Button=button;
		 
		 
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 
		 }
	 }
	 
	 public static void UseItem(BattleEntity p,Item item) {
		 
		 Start.DebugPrint("used "+item.getName());
		 item.useItem(p);
		 
		 
	 }
	 
	 
	 
	 
   
	 
	public static  void ExitWindow() {
		 window.CloseWIndow();
		 
	 }
	
	
	
	public static void SaveGame() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
