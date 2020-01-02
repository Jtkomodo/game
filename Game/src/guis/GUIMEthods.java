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

	 public static void UseSPmove(BattleEntity p,String move) {
		
		 Moves m=p.getmoveFromString(move);
		if(m.getCost()<=p.getSp()){ 
		 p.decreseSp(m.getCost());
		 if(m!=null && m.isSpecailMove() ) {
			 
			
			 Start.DebugPrint("used move "+m.getName()+"--");
	   if(m.isTimedButton()) {
			m.getCombo().start();
			 Start.Button=m.getCombo();
	   
			 Start.MoveInprogress=true;
	   }	
			 
			 
			 
			  
			 
			 }else {
				 
				 
				 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
				 
			 }
		}
	 }
	 
   
	 public static void Usemove(BattleEntity p,String move) {
		 
		 Moves m=p.getmoveFromString(move);
		 
		 if(m!=null && !m.isSpecailMove()) {
			 
		
		 Start.DebugPrint("used move "+m.getName()+"--");
		 
		 if(m.isTimedButton()) {
				m.getCombo().start();
		   
		 Start.MoveInprogress=true;
		 Start.Button=m.getCombo();
		 }
		 
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 
		 }
	 }
	 
	 public static void UseItem(BattleEntity p,Item item) {
		 
		 Start.DebugPrint("used "+item.getName());
		 p.useItem(item);
		 
		 
	 }
	 
	 
	 
	 
   
	 
	public static  void ExitWindow() {
		 window.CloseWIndow();
		 
	 }
	
	
	
	public static void SaveGame() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
