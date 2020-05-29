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
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattleFormulas;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;
import battleClasses.HpBar;
import battleClasses.TimedButton;
import guis.FunctionCaller;
import guis.GUIMEthods;
import guis.UIBox;
import guis.UIElement;
import guis.UIStringElement;
import rendering.MainRenderHandler;
import rendering.Model;
import textrendering.TextBuilder;

public class BattleSystem {

	private static BattleEnemyField enemyField=Start.CurrentEnemyFeild;
	private static BattlePlayerField playerField;
	private static BattleEntity currentEntity=Start.currentEntity; 
	private static ArrayList<BattleEntity> turnOrder=new ArrayList<BattleEntity>();
	private static UIBox battleBox=Start.battleBox;
	private static Model backgroundModel=Start.background;
	private static Texture backgroundTexture=Start.bg;
	private static Inventory playersInventory=Start.playersInventory, enemyTestInventory=Start.enemyTestInventory;
	private static TextBuilder text1=Start.text1;
	private static HpBar playersSPBAr=Start.playersSPBAr;
	private static boolean PlayersTurn;
	
	
	public static void Update(BattleEntity p) {
		 if(Start.TurnFinished) {
		
				currentEntity = StartBattleTurn(playerField,enemyField);
				
		   }
		   boolean selectingEnemy=false;
		   if( Start.MoveCalled && !Start.StartBox.isActive() && !Start.currentlyUsedMove.isHeal()) {
			   selectingEnemy=true;}
			
		   
		   boolean allEnemiesDead=Start.CurrentEnemyFeild.updateField();
		   if(selectingEnemy) {
			   enemyField.selectEnemy();
			   
		   }else {
			   enemyField.setSelected(false);
		   }
		  
		   
		   Enemy e=enemyField.getCurrentEnemy();
		   
		   
		 
		   
		   
		 
		  
			 
		   
		   Vector2f position1=new Vector2f(-90,40);
		
		   
		 		Entity backg=new Entity(backgroundModel,new Vector3f(0,0,-10),0,64*40,backgroundTexture); 
		 	   
		      MainRenderHandler.addEntity(backg);
		 		
		 		
		 	    
		 	    //MainRenderHandler.addEntity(new Entity(player,new Vector3f(-192,-20,10),0,64*1.5f,playerTex,true));
		 	    enemyField.draw(selectingEnemy);//draws all the enemies to the screen
		 	    
		 	    
		 		Items[] ITM= playersInventory.getItems();		
				UIElement[] elementlist=new UIElement[ITM.length];	
				
				for(int i=0;i<ITM.length;i++) {
				
					elementlist[i]=new UIStringElement(ITM[i].Item.getName()+"  "+playersInventory.getAmountOfItem(ITM[i]),new Vector2f(-54,5-(i*14)), .15f,Constants.BLACK,new FunctionCaller(GUIMEthods.UseItem,new Object[] {p,ITM[i]},new Class[] {p.getClass(),Items.class}));
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
		 	 
			if(Start.BattleEnded) {   
			Start.battleBox.hide();
			}
			else {  
		 	  
		 	  
		 	if(!Start.PlayersTurn && Proccesor.isUserInputallowed()) {
		 		Start.DebugPrint("hi'");
		 		if(Start.currentEntity.getHp()!=0) {
		 		((Enemy) Start.currentEntity).takeTurn(Start.CurrentEnemyFeild,p);
		 		}
		 		Start.TurnFinished=true;
		 		
		 	}else if(PlayersTurn && Proccesor.isUserInputallowed()){
		 		battleBox.show();
		 	}
		 	  
		 	   if(Start.MoveInprogress && PlayersTurn) {	
		 		   
		 		   battleBox.hide();
		 		
		 	 
		 		
		 		   
		 		  
		 		  Moves move=p.getLastUsedMove();
		 		  
		 		  if(move.isTimedButton()) {
		 			  int State= Start.Button.update();
		 		  if(State!=TimedButton.NOTPUSHED) {
		 			
		 			   Start.TurnFinished=true;
		 				  
		 			   if(!p.getLastUsedMove().isHeal()) {
		 				   
		 				   float Damage=BattleFormulas.CalculateDamage(p, e, State, p.getLastUsedMove().getDamage());
		 				  
		 				   
		 				   
		 				   
		 				   Proccesor.addComandtoItorator(new DrawString(Math.round(Damage)+"!",new Vector2f(100,40),.5f,true,.5f));			
		 				
		 			        e.decreseHp(Damage);
		 			   
		 			   }else {
		 				  Start.source.play(Start.Heal);
		 				   
		 				   float health=BattleFormulas.CalculateHeath(p, State, p.getLastUsedMove().getDamage());
		 				  
		 				  Proccesor.addComandtoItorator(new DrawString(Math.round(health)+"!",new Vector2f(100,40),.5f,true,.5f));			
		 				   
		 				p.IncreseHp(health);   
		 				   
		 			   }
		 			   
		 			   
		 			   
		 			   
		 		   }}else {
		 			
		 			
		 			   Start.TurnFinished=true;
		 				
		 			   if(!move.isHeal()) {
		 				   
		 				  float Damage=BattleFormulas.CalculateDamage(p, e,2, p.getLastUsedMove().getDamage());
		 				   
		 				   
		 				   text1.setString(Math.round(Damage)+"!");
		 				   Proccesor.addComandtoQueue(new DrawModel(text1.getTextModel(),text1.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
		 				
		 			        e.decreseHp(Damage);
		 			   
		 				   
		 				   
		 			   }else {
		 				
		 				
		 						Start.source.play(Start.Heal);
		 					
		 				   float health=BattleFormulas.CalculateHeath(p, 2, p.getLastUsedMove().getDamage());
		 				  text1.setString(Math.round(health)+"!");
		 				  Proccesor.addComandtoQueue(new DrawModel(text1.getTextModel(),text1.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
		 				
		 				p.IncreseHp(health);   
		 				 
		 			   }
		 			   
		 			   
		 			   
		 			   
		 			   
		 		   }
		 			   
		 			   
		 		   
		 		   
		 		   
		 		   
		 		   
		 		   
		 	   }else if(Start.MoveCalled==true && Start.PlayersTurn) {
					 
						  if( Start.currentlyUsedMove.isHeal()){
							  GUIMEthods.UseNonAttack(Start.currentlyUsedMove, p);
						  }
						  battleBox.hide();
					   }
		 	   
		 	   
		 	   
		 	}     
		
		 	  
		   
		   
	   
		
		
		
	}
	
	
	private static BattleEntity StartBattleTurn(BattlePlayerField p,BattleEnemyField e) {
		if(turnOrder.isEmpty())  {
			StartBattleRound(p,e);
			
		}
		BattleEntity returnEntity=turnOrder.remove(0);
		if(!returnEntity.isEnemy()) {
		    Start.PlayersTurn=true;
		    battleBox.reset();
	 		battleBox.show();
	 		
		}else {
			Start.PlayersTurn=false;
			e.setCurrentEnemy((Enemy)returnEntity);
		}
		Start.TurnFinished=false;
		Start.MoveCalled=false;
		Start.MoveInprogress=false;
     
		   		
		return returnEntity;
	}
	
private static void StartBattleRound(BattlePlayerField p,BattleEnemyField e) {
		
		turnOrder=BattleFormulas.calcuateTurnOrder(new BattleEntity[] {p.getCurrentPC1(),p.getCurrentPC2()},e.getEnemies());
	
	}
	
private static void EndBattleAsWin() {
	
	
	Start.BattleEnded=true;
	text1.setString("YOU WON!");
	 text1.drawString(100,40,.5f); 
		
	}
private static void EndBattleAsLoss() {
	
	Start.BattleEnded=true;
	
	text1.setString("YOU LOST :(");
	 text1.drawString(100,40,.5f); 
	
}	
	
}
