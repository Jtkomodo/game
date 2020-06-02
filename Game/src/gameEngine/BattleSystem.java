package gameEngine;

import java.util.ArrayList;

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
	
	
	
	public static BattleEntity player=Start.p;
	public static String PickMove="Pickmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame",UseItem="UseItem",fullheal="FullHeal";
	public static boolean soundPlay=true;
	

	private static BattleEnemyField enemyField;
	//private static BattlePlayerField playerField;
	private static BattleEntity currentEntity;
	private static ArrayList<BattleEntity> turnOrder=new ArrayList<BattleEntity>();
	private static UIBox battleBox=Start.battleBox;
	private static Model backgroundModel=Start.background;
	private static Texture backgroundTexture=Start.bg;
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
	
	
	
	

	
	
	
	
	public static void battleUpdate(BattleEntity p) {
		 if(isTurnFinished()) {
		
				currentEntity = StartBattleTurn(p,enemyField);
				
		   }
		   boolean selectingEnemy=false;
		   if( isMoveCalled() && !Start.StartBox.isActive() 
				   && !currentlyUsedMove.isHeal()) {
			   selectingEnemy=true;}
			
		   
		   boolean allEnemiesDead=enemyField.updateField();
		   if(selectingEnemy) {
			   enemyField.selectEnemy();
			   
		   }else {
			   enemyField.setSelected(false);
		   }
		  
		   
		   Enemy e=enemyField.getCurrentEnemy();
		   
		   
		 
		   
		   
		 
		  
			 
		   
		   Vector2f position1=new Vector2f(-90,40);
		
		   
		 		Entity backg=new Entity(backgroundModel,new Vector3f(0,0,-10),0,64*40,backgroundTexture); 
		 	   
		      MainRenderHandler.addEntity(backg);
		 		
		 		
		 	    
		 	    MainRenderHandler.addEntity(new Entity(Start.player,new Vector3f(-192,-20,10),0,64*1.5f,Start.playerTex,true));
		 	    enemyField.draw(selectingEnemy);//draws all the enemies to the screen
		 	    
		 	    
		 		Items[] ITM= playersInventory.getItems();		
				UIElement[] elementlist=new UIElement[ITM.length];	
				
				for(int i=0;i<ITM.length;i++) {
					elementlist[i]=new UIStringElement(ITM[i].Item.getName()+"  "+playersInventory.getAmountOfItem(ITM[i]),new Vector2f(-54,5-(i*14)), .15f,Constants.BLACK,new UseItem(p,ITM[i]));
				}

			
		 	
				//----------------------GAME--LOOP------------------------------
			
			
				
				
			battleBox.getUIState(2).relpaceALLActive(elementlist);
			battleBox.draw();
		 	if(selectingEnemy) { 
		 		  text1.setString("Selecting enemy");
		 		  text1.drawString(position1.x-100,position1.y+150, .64f);
		 	}
		 	 
		 	     //battleBox.getUIState().setOffsetPositionOnlist(arrowPosition);   	   
		 	   
		 	    
		 	    
		 	
		 	    
		 	  battleBox.setPosition(position1);
		 	
		 	
		 	 text1.setString("HP: "+Math.round(p.getHp())+"/"+Math.round(p.getMaxDEF()));
		 	  p.getHpbar().draw(new Vector2f(position1.x-100,position1.y+50),text1);

		 	  text1.setString("SP: "+Math.round(playersSPBAr.getValue())+"/"+Math.round(playersSPBAr.getMax()));
		 	  playersSPBAr.draw(new Vector2f(position1.x-110,position1.y+70), text1);
		 	 
		 	
		 	
		 	  
		 	  
		 	 if(p.getHp()==0) {
				   EndBattleAsLoss();
				   
				   
			   }
			   

		 	 if(allEnemiesDead) {
				   EndBattleAsWin();
				   
				   
		 	 }
		 	 
			if(BattleEnded) {   
			battleBox.hide();
			}
			else {  
		 	  
		 	  
		 	if(!isPlayersTurn() && Proccesor.isUserInputallowed()) {
		 	//	DebugPrint("hi'");
		 		if(currentEntity.getHp()!=0) {
		 		((Enemy) currentEntity).takeTurn(enemyField,p);
		 		}
		 		setTurnFinished(true);
		 		
		 	}else if(isPlayersTurn() && Proccesor.isUserInputallowed()){
		 		battleBox.show();
		 	}
		 	  
		 	   if(isMoveInprogress() && isPlayersTurn()) {	
		 		   
		 		   battleBox.hide();
		 		
		 	 
		 		
		 		   
		 		  
		 		  Moves move=p.getLastUsedMove();
		 		  
		 		  if(move.isTimedButton()) {
		 			  int State= getButton().update();
		 		  if(State!=TimedButton.NOTPUSHED) {
		 			
		 			   setTurnFinished(true);
		 				  
		 			   if(!p.getLastUsedMove().isHeal()) {
		 				   
		 				   float Damage=BattleFormulas.CalculateDamage(p, e, State, p.getLastUsedMove().getDamage());
		 				  
		 				   
		 				   
		 				   
		 				   Proccesor.addComandtoItorator(new DrawString(Math.round(Damage)+"!",new Vector2f(100,40),.5f,true,.5f));			
		 				
		 			        e.decreseHp(Damage);
		 			   
		 			   }else {
		 				  source.play(Start.Heal);
		 				   
		 				   float health=BattleFormulas.CalculateHeath(p, State, p.getLastUsedMove().getDamage());
		 				  
		 				  Proccesor.addComandtoItorator(new DrawString(Math.round(health)+"!",new Vector2f(100,40),.5f,true,.5f));			
		 				   
		 				p.IncreseHp(health);   
		 				   
		 			   }
		 			   
		 			   
		 			   
		 			   
		 		   }}else {
		 			
		 			
		 			   setTurnFinished(true);
		 				
		 			   if(!move.isHeal()) {
		 				   
		 				  float Damage=BattleFormulas.CalculateDamage(p, e,2, p.getLastUsedMove().getDamage());
		 				   
		 				   
		 				   text1.setString(Math.round(Damage)+"!");
		 				   Proccesor.addComandtoQueue(new DrawModel(text1.getTextModel(),text1.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
		 				
		 			        e.decreseHp(Damage);
		 			   
		 				   
		 				   
		 			   }else {
		 				
		 				
		 						source.play(Start.Heal);
		 					
		 				   float health=BattleFormulas.CalculateHeath(p, 2, p.getLastUsedMove().getDamage());
		 				  text1.setString(Math.round(health)+"!");
		 				  Proccesor.addComandtoQueue(new DrawModel(text1.getTextModel(),text1.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
		 				
		 				p.IncreseHp(health);   
		 				 
		 			   }
		 			   
		 			   
		 			   
		 			   
		 			   
		 		   }
		 			   
		 			   
		 		   
		 		   
		 		   
		 		   
		 		   
		 		   
		 	   }else if(isMoveCalled()==true && isPlayersTurn()) {
					 
						  if( currentlyUsedMove.isHeal()){
							  UseNonAttack(currentlyUsedMove, p);
						  }
						  battleBox.hide();
					   }
		 	   
		 	   
		 	   
		 	}     
		
		 	  
		   
		   
	   
		
		
		
	}
	
	
	private static BattleEntity StartBattleTurn(BattleEntity p,BattleEnemyField e) {
		if(turnOrder.isEmpty())  {
			StartBattleRound(p,e);
			
		}
		BattleEntity returnEntity=turnOrder.remove(0);
		if(!returnEntity.isEnemy()) {
		    setPlayersTurn(true);
		    battleBox.reset();
	 		battleBox.show();
	 		
		}else {
			setPlayersTurn(false);
			e.setCurrentEnemy((Enemy)returnEntity);
		}
		setTurnFinished(false);
		setMoveCalled(false);
		setMoveInprogress(false);
     
		   		
		return returnEntity;
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
	if(move!=null) {
		boolean test= p.useMove(move);

		if(test) { 
		Start.DebugPrint("Used move "+move.getName()+"on"+e.getName()+"--");
		if(move.isTimedButton()) {
			getButton().start();
		}
		 setMoveInprogress(true);
		 setMoveCalled(false);
		}
	}
	  
	
}
public static void UseNonAttack(Moves move,BattleEntity p) {
	if(move!=null) {
		boolean test= p.useMove(move);

		if(test) { 
		Start.DebugPrint("Used move "+move.getName());
		if(move.isTimedButton()) {
			getButton().start();
		}
	}
	
		 setMoveInprogress(true);
		 setMoveCalled(false);
		}
	}



public static void StartBattle(BattleEnemyField enemies,BattleEntity p) {
	Start.cam.setPosition(new Vector2f());
	
	for(int i=0;i<enemies.getAmountOfEnemies();i++) {
	Enemy e=enemies.getEnemy(i);
	
	e.setInventory(enemyTestInventory);
	
	e.setHp(e.getMaxHP());
	e.setSp(e.getMaxsp());
	}
	
	enemyField=enemies;
	battleBox.show();
	setTurnFinished(false);
	BattleEnded=false;
	enemyField=enemies;
	currentEntity=p; 
	currentlyUsedMove=p.getmoves()[0];
	setTurnFinished(false);

	  
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
