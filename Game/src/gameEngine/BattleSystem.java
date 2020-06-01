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
	
	
	
	public static BattleEntity player=Start.p;
	public static String PickMove="Pickmove",useMOVE="Usemove",exitWINDOW="ExitWindow",saveGAME="SaveGame",UseItem="UseItem",fullheal="FullHeal";
	public static boolean soundPlay=true;
	

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
	private static boolean TurnFinished;
	private static boolean MoveCalled;
	private static Moves currentlyUsedMove;
	
	
	public static void Update(BattleEntity p) {
		 if(TurnFinished) {
		
				currentEntity = StartBattleTurn(playerField,enemyField);
				
		   }
		   boolean selectingEnemy=false;
		   if( MoveCalled && !Start.StartBox.isActive() && !currentlyUsedMove.isHeal()) {
			   selectingEnemy=true;}
			
		   
		   boolean allEnemiesDead=CurrentEnemyFeild.updateField();
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
		 	 
			if(BattleEnded) {   
			battleBox.hide();
			}
			else {  
		 	  
		 	  
		 	if(!PlayersTurn && Proccesor.isUserInputallowed()) {
		 		DebugPrint("hi'");
		 		if(currentEntity.getHp()!=0) {
		 		((Enemy) currentEntity).takeTurn(CurrentEnemyFeild,p);
		 		}
		 		TurnFinished=true;
		 		
		 	}else if(PlayersTurn && Proccesor.isUserInputallowed()){
		 		battleBox.show();
		 	}
		 	  
		 	   if(MoveInprogress && PlayersTurn) {	
		 		   
		 		   battleBox.hide();
		 		
		 	 
		 		
		 		   
		 		  
		 		  Moves move=p.getLastUsedMove();
		 		  
		 		  if(move.isTimedButton()) {
		 			  int State= Button.update();
		 		  if(State!=TimedButton.NOTPUSHED) {
		 			
		 			   TurnFinished=true;
		 				  
		 			   if(!p.getLastUsedMove().isHeal()) {
		 				   
		 				   float Damage=BattleFormulas.CalculateDamage(p, e, State, p.getLastUsedMove().getDamage());
		 				  
		 				   
		 				   
		 				   
		 				   Proccesor.addComandtoItorator(new DrawString(Math.round(Damage)+"!",new Vector2f(100,40),.5f,true,.5f));			
		 				
		 			        e.decreseHp(Damage);
		 			   
		 			   }else {
		 				  source.play(Heal);
		 				   
		 				   float health=BattleFormulas.CalculateHeath(p, State, p.getLastUsedMove().getDamage());
		 				  
		 				  Proccesor.addComandtoItorator(new DrawString(Math.round(health)+"!",new Vector2f(100,40),.5f,true,.5f));			
		 				   
		 				p.IncreseHp(health);   
		 				   
		 			   }
		 			   
		 			   
		 			   
		 			   
		 		   }}else {
		 			
		 			
		 			   TurnFinished=true;
		 				
		 			   if(!move.isHeal()) {
		 				   
		 				  float Damage=BattleFormulas.CalculateDamage(p, e,2, p.getLastUsedMove().getDamage());
		 				   
		 				   
		 				   text1.setString(Math.round(Damage)+"!");
		 				   Proccesor.addComandtoQueue(new DrawModel(text1.getTextModel(),text1.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
		 				
		 			        e.decreseHp(Damage);
		 			   
		 				   
		 				   
		 			   }else {
		 				
		 				
		 						source.play(Heal);
		 					
		 				   float health=BattleFormulas.CalculateHeath(p, 2, p.getLastUsedMove().getDamage());
		 				  text1.setString(Math.round(health)+"!");
		 				  Proccesor.addComandtoQueue(new DrawModel(text1.getTextModel(),text1.getLoader().getTex(),new Vector2f(100,40),.5f,1,true));			
		 				
		 				p.IncreseHp(health);   
		 				 
		 			   }
		 			   
		 			   
		 			   
		 			   
		 			   
		 		   }
		 			   
		 			   
		 		   
		 		   
		 		   
		 		   
		 		   
		 		   
		 	   }else if(MoveCalled==true && PlayersTurn) {
					 
						  if( currentlyUsedMove.isHeal()){
							  GUIMEthods.UseNonAttack(currentlyUsedMove, p);
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
		    PlayersTurn=true;
		    battleBox.reset();
	 		battleBox.show();
	 		
		}else {
			PlayersTurn=false;
			e.setCurrentEnemy((Enemy)returnEntity);
		}
		TurnFinished=false;
		MoveCalled=false;
		MoveInprogress=false;
     
		   		
		return returnEntity;
	}
	
private static void StartBattleRound(BattlePlayerField p,BattleEnemyField e) {
		
		turnOrder=BattleFormulas.calcuateTurnOrder(new BattleEntity[] {p.getCurrentPC1(),p.getCurrentPC2()},e.getEnemies());
	
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
 
	public static void  FullHeal(BattleEntity e) {
		e.setHp(e.getMaxHP());
		e.setSp(e.getMaxsp());
		
	}
		
 




	
}
