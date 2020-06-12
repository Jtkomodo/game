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
	
	
	
	
	private static String PickMove="Pickmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame",UseItem="UseItem",fullheal="FullHeal";
	public static boolean soundPlay=true;
	

	private static BattleEnemyField enemyField;
	private static BattlePlayerField playerField;
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
	private static boolean EnemySelected=false;
	private static boolean PCSelected=false;
	private static boolean endedRound=false;
	private static Moves currentlyUsedMove;
	private static TimedButton Button;
	
	private static boolean BattleEnded=false;
	private static Source source=Start.source;
	
	
	
	

	public static void INIT(UIBox BattleBox) {
		//this is called before the game loop
		
	     battleBox=BattleBox;
	   
		 
	}
	
	
	
	
	private static void drawBattle() {
		MainRenderHandler.addEntity(new Entity(backgroundModel,new Vector3f(0,0,-10), 0, 64*40, backgroundTexture));
		enemyField.draw(EnemySelected);
		playerField.draw(PCSelected);
		battleBox.draw();
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void battleUpdate() {
		
	
		if(EnemySelected || PCSelected) {
			battleBox.hide();
			
		}else {
			battleBox.show();
		}
		
		
		boolean enemiesdead=enemyField.updateField();
		boolean PCsdead=playerField.updateField();
		if(PCsdead) {
			EndBattleAsLoss();
		}
		
		
		if(enemiesdead) {
			EndBattleAsWin();
		}
		
		
		drawBattle();
		
		if(PCSelected) {
			if(playerField.selectPC()) {
			    playerField.getCurrentPC().setHp(0);
			}
			
		}
		
		if(EnemySelected && !Start.StartBox.isActive()) {
		if(enemyField.selectEnemy()) {
			enemyField.getCurrentEnemy().setHp(0);
			
		}
		}
	
		
		
		
		
		
	}
	
	
	private static void StartBattleTurn(BattleEntity  currentPC) {
	  if(currentPC.isEnemy()) {
		  Enemy e=(Enemy)currentPC;
		  
	  }else{
		
		  
		  
	  }
		
	}
	
private static void StartBattleRound(BattlePlayerField  p,BattleEnemyField e) {
		
		turnOrder=BattleFormulas.calcuateTurnOrder(p.getPCs(),e.getEnemies());
	
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



public static void StartBattle(BattleEnemyField enemies,BattlePlayerField p,Texture background,Model BackgroundModel) {
	Start.cam.setPosition(new Vector2f(0,0));
	backgroundModel=BackgroundModel;
	backgroundTexture=background;
	Start.overworld=false;
	battleBox.reset();
	Start.a1.removeAnimation();
	Start.StartBox.reset();
	Start.StateOfStartBOx=false;
	enemyField=enemies;
	playerField=p;
	  
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




public static void setSelectingEnemy(boolean b) {
	EnemySelected=b;
	 if(b==true) {
    	 PCSelected=false;
    	 
     }
}




public static void setSelectingPC(boolean b) {
     PCSelected=b;
     if(b==true) {
    	 EnemySelected=false;
    	 
     }
	
}



	
}
