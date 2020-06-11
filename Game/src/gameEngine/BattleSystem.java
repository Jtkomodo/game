package gameEngine;

import java.util.LinkedList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Data.Constants;
import Data.Moves;
import Items.Inventory;
import Items.Items;
import Scripter.Proccesor;
import ScripterCommands.DrawModel;
import ScripterCommands.DrawString;
import audio.Source;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattleFormulas;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;
import battleClasses.HpBar;
import battleClasses.TimedButton;
import guis.UIBox;
import guis.UIElement;
import guis.UIStringElement;
import guis.UseItem;
import rendering.MainRenderHandler;
import rendering.Model;
import textrendering.TextBuilder;

public class BattleSystem {
	
	
	
	private static BattleEntity player=Start.p;
	private static String PickMove="Pickmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame",UseItem="UseItem",fullheal="FullHeal";
	public static boolean soundPlay=true;
	

	private static BattleEnemyField enemyField;
	//private static BattlePlayerField playerField;
	private static BattleEntity currentEntity;
	private static LinkedList<BattleEntity> turnOrder=new LinkedList<BattleEntity>();
	private static UIBox battleBox;
	private static Model backgroundModel;
	private static Texture backgroundTexture;
	private static Inventory playersInventory=Start.playersInventory, enemyTestInventory=Start.enemyTestInventory;
	private static TextBuilder text1=Start.text1;
	private static HpBar playersSPBAr=Start.playersSPBAr;
	private static boolean PlayersTurn=true;
	private static boolean TurnFinished=false;
	private static boolean MoveCalled=false;
	private static Moves currentlyUsedMove;
	private static boolean MoveInprogress=false;
	private static TimedButton Button;
	
	private static boolean BattleEnded=false;
	private static Source source=Start.source;
	
	
	
	

	public static void INIT(UIBox BattleBox) {
		//this is called before the game loop
		
	     battleBox=BattleBox;
	   
		 
	}
	
	
	
	
	private static void drawBattle(boolean selecting,BattleEntity p) {
		MainRenderHandler.addEntity(new Entity(backgroundModel,new Vector3f(0,0,-10), 0, 64*40, backgroundTexture));
		enemyField.draw(selecting);
		Items[] ITM= playersInventory.getItems();		
		UIElement[] elementlist=new UIElement[ITM.length];	
		
		for(int i=0;i<ITM.length;i++) {
			elementlist[i]=new UIStringElement(ITM[i].Item.getName()+"  "+playersInventory.getAmountOfItem(ITM[i]),new Vector2f(-54,5-(i*14)), .15f,Constants.BLACK,new UseItem(p,ITM[i]));
		}

	
 	
			//MainBatchRender.addTexture(textbox);
		 	    battleBox.getUIState(2).relpaceALLActive(elementlist);
		
		 	   
		battleBox.draw();
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void battleUpdate(BattleEntity p) {
		
		
		//draw the background and sprites
		boolean selecting=true;
		if(!selecting) {
		battleBox.show();
		}else {
			battleBox.hide();
		}
		enemyField.updateField();
		drawBattle(selecting,p);
		if(selecting && !Start.StartBox.isActive()) {
		if(enemyField.selectEnemy()) {
			enemyField.getCurrentEnemy().setHp(0);
			
		}
		}
		//if()
		
		
		
		
		
	}
	
	
	private static BattleEntity StartBattleTurn(BattleEntity p,BattleEnemyField e) {
		return p;
		
	}
	
private static void StartBattleRound(BattleEntity  p,BattleEnemyField e) {
		
		turnOrder=BattleFormulas.calcuateTurnOrder(new BattleEntity[] {p},e.getEnemies());
	
	}
	
private static void EndBattleAsWin() {
	
	
	BattleEnded=true;
	text1.setString("YOU WON!");
	 text1.drawString(100,40,.5f); 
		
	}
private static void EndBattleAsLoss() {
	
	BattleEnded=true;
	
	text1.setString("YOU LOST :(");
	 text1.drawString(100,40,.5f); 
	
}	


public static void UseAttack(Moves move,BattleEntity p,Enemy e) {

	  
	
}
public static void UseNonAttack(Moves move,BattleEntity p) {
	
	}



public static void StartBattle(BattleEnemyField enemies,BattleEntity p,Texture background,Model BackgroundModel) {
	Start.cam.setPosition(new Vector2f(0,0));
	backgroundModel=BackgroundModel;
	backgroundTexture=background;
	Start.overworld=false;
	battleBox.reset();
	Start.a1.removeAnimation();
	Start.StartBox.reset();
	Start.StateOfStartBOx=false;
	enemyField=enemies;
	  
}


public static boolean isMoveInprogress() {
	return MoveInprogress;
}


public static void setMoveInprogress(boolean moveInprogress) {
	MoveInprogress = moveInprogress;
}


public static boolean isMoveCalled() {
	return MoveCalled;
}


public static void setMoveCalled(boolean moveCalled) {
	MoveCalled = moveCalled;
}


public static boolean isTurnFinished() {
	return TurnFinished;
}


public static void setTurnFinished(boolean turnFinished) {
	TurnFinished = turnFinished;
}


public static boolean isPlayersTurn() {
	return PlayersTurn;
}


public static void setPlayersTurn(boolean playersTurn) {
	PlayersTurn = playersTurn;
}


public static TimedButton getButton() {
	return Button;
}


public static void setButton(TimedButton button) {
	Button = button;
}



	
}
