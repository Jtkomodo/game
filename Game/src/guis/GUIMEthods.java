package guis;

import Data.Moves;
import Items.Item;
import Items.Items;
import Scripter.Proccesor;
import ScripterCommands.DrawString;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;

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
	public static String PickMove="Pickmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame",UseItem="UseItem",fullheal="FullHeal";
	public static boolean soundPlay=true;
	
	public static void UseAttack(Moves move,BattleEntity p,Enemy e) {
		if(move!=null) {
			boolean test= p.useMove(move);

			if(test) { 
			Start.DebugPrint("Used move "+move.getName()+"on"+e.getName()+"--");
			if(move.isTimedButton()) {
				Start.Button.start();
			}
			 Start.MoveInprogress=true;
			 Start.MoveCalled=false;
			}
		}
		  
		
	}
	public static void UseNonAttack(Moves move,BattleEntity p) {
		if(move!=null) {
			boolean test= p.useMove(move);

			if(test) { 
			Start.DebugPrint("Used move "+move.getName());
			if(move.isTimedButton()) {
				Start.Button.start();
			}
		}
		
			 Start.MoveInprogress=true;
			 Start.MoveCalled=false;
			}
		}
		  
		
	public static void ADD(int a,int b) {
		int c=a+b;
		Start.DebugPrint("c="+c);
		
	}
	
	
	
	
	
	
	
	
	
   
	 public static void Pickmove(BattleEntity p,String move) {
		 
		 Moves m=p.getmoveFromString(move);
		 soundPlay=true;
		 if(m!=null) {
			 
		
		 Start.DebugPrint("Picked move "+m.getName()+"--");
		 
		 Start.currentlyUsedMove=m;
		 
		 boolean used=p.testIfMoveCanBeUsed(m);
		 if(used) {
		 if(m.isTimedButton()) {
			Start.Button=m.getCombo();
		 }
		 Start.MoveCalled=true; 
		 Start.MoveInprogress=false;
		
		 }else {
			 soundPlay=false;
			 Start.source.play(Start.NO);
		 }
		 }else {
			 
			 
			 Start.DebugPrint("move "+move+" does not exist in this player's("+ p+") moveset");
			 
		 }
	 }
	 
	 public static void UseItem(BattleEntity p,Items item) {
		 soundPlay=true;
		 
		boolean used= p.useItem(item);
		 if(used) {
			 Start.DebugPrint("used "+item.Item.getName());
			
			 Proccesor.addComandtoQueue(new DrawString("used "+item.Item.getName(),new Vector2f(-100,40),.5f,true,.5f));
			if( item.Item.isHealing())
			 Proccesor.addComandtoQueue(new DrawString("healed "+item.Item.getValue()+"!",new Vector2f(100,40),.5f,true,.5f));
			if( item.Item.isRestorSP())
				 Proccesor.addComandtoQueue(new DrawString("restored "+item.Item.getValue()+"!",new Vector2f(100,40),.5f,true,.5f));
				  
			 
			 Start.MoveInprogress=false;
			  Start.PlayersTurn=false;
			   Start.TurnFinished=true;
	 			 
		 }else {
			 soundPlay=false;
			 Start.source.play(Start.NO);
		 }
		 
	 }
	 
	 
	 
	 
   
	 
	public static  void ExitWindow() {
		 window.CloseWIndow();
		 
	 }
	
	
	
	public static void SaveGame() {
		
		
	}
	public static void  FullHeal(BattleEntity e) {
		e.setHp(e.getMaxHP());
		e.setSp(e.getMaxsp());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
