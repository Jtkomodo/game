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
	private static BattleEntity currentEntity=Start.p;
	private static BattleEntity currentSelectedEntity=Start.p;
	private static LinkedList<BattleEntity> turnOrder=new LinkedList<BattleEntity>();
	private static UIBox battleBox;
	private static Model backgroundModel;
	private static Texture backgroundTexture;
	private static Inventory playersInventory=Start.playersInventory, enemyTestInventory=Start.enemyTestInventory;
	private static TextBuilder text1=Start.text1;
	private static HpBar playersSPBAr=Start.playersSPBAr;
	
	
	private static boolean MoveUsed=false;
	private static boolean turnFinshed=true;
	private static boolean PlayersTurn=true;
	private static boolean TurnFinished=false;
	private static boolean MoveCalled=false;
	private static boolean EnemySelected=false;
	private static boolean PCSelected=false;
	
	
	
	private static Moves currentlyUsedMove;

	
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
	
	//hide gui box if selecting a enemy or 
	
	
		boolean enemiesdead=enemyField.updateField();
		boolean PCsdead=playerField.updateField();
		if(PCsdead) {
			EndBattleAsLoss();
		}
		
		
		if(enemiesdead) {
			EndBattleAsWin();
		}
		if(TurnFinished) {
		currentEntity=StartBattleTurn();	
		}
		
		if(EnemySelected || PCSelected || !PlayersTurn) {
			battleBox.hide();
			
		}else {
			battleBox.show();
		}
		
		
		drawBattle();
		
		if(MoveUsed) {
			TurnFinished=currentlyUsedMove.getMove().isMoveDone(playerField, enemyField, currentEntity,currentEntity);
			
		}
		
		
		if(TurnFinished) {
			MoveUsed=false;
			MoveCalled=false;
		    PCSelected=false;
		    EnemySelected=false;
		    currentSelectedEntity=null;
		    battleBox.reset();
		    playerField.ResetSelected();
		    enemyField.ResetSelected();
		}
		
		//If players turn then check if we are in the selecting stage and if we have already selected a Entity to use the move or item on
		if(PlayersTurn) {
			if(PCSelected) {
				if(playerField.selectPC()) {
					if(MoveCalled) {
						currentlyUsedMove.getMove().useMove(playerField, enemyField, currentEntity,playerField.getCurrentPC());
						currentSelectedEntity=playerField.getCurrentPC();
						MoveUsed=true;
						}
					}
			
		}
		
			if(EnemySelected && !Start.StartBox.isActive()) {
				if(enemyField.selectEnemy()) {
					if(MoveCalled) {
						currentlyUsedMove.getMove().useMove(playerField, enemyField, currentEntity,enemyField.getCurrentEnemy());
						currentSelectedEntity=enemyField.getCurrentEnemy();
						MoveUsed=true;
					}
				}
		}
	
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	public static void setCurrentlyUsedMove(Moves currentlyUsedMove) {
		BattleSystem.currentlyUsedMove = currentlyUsedMove;
	}




	private static BattleEntity StartBattleTurn() {//this goes down the turn order list making sure to
		//set any values needed to be set before the game turn begins and returns the next entity that is taking it's turn now 
    TurnFinished=false; 
	
    Start.DebugPrint("NEW_TURN");
    BattleEntity newCurrentEntity;
     //if we have not reached the end of the turnOrder list then the new current entity is the next one on the list
     if(!turnOrder.isEmpty()) {
    	 newCurrentEntity=turnOrder.poll();
     }else {//else we neeed to make a new list
    	 StartBattleRound(playerField,enemyField);
    	 newCurrentEntity=turnOrder.poll();
     }
     
     //now that we have the current Entity we need to check if is a pc or a enemy
     
     if(newCurrentEntity.isEnemy()) {
    	 Start.DebugPrint("ENEMY'S TURN");
    	 PlayersTurn=false;
    	 
     }else {
    	 PlayersTurn=true;
    	 
     }
     
     
     
     
     
     return newCurrentEntity;
		
		
	}
	
private static void StartBattleRound(BattlePlayerField  p,BattleEnemyField e) {
	   Start.DebugPrint("NEW_ROUND");
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
	TurnFinished=true;
	
	  
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




public static BattleEntity getCurrentEntity() {
	return currentEntity;
}




public static BattleEnemyField getEnemyField() {
	return enemyField;
}




public static BattlePlayerField getPlayerField() {
	return playerField;
}




public static BattleEntity getSelectedEntity() {
	
	return currentSelectedEntity;
}



	
}
