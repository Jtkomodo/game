package battleClasses;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Vector2f;

import Data.Moves;
import Items.Inventory;
import Items.Item;
import Items.Items;
import Scripter.Proccesor;
import ScripterCommands.DrawModel;
import gameEngine.Start;

public class Enemy extends BattleEntity{

	public Enemy(float atk, float def, float hp,float sp, Moves[] moves,Inventory inventory) {
		super(atk, def, hp,sp, moves,inventory);
		
	
	}

	

	private ArrayList<Moves> makeListOfUseableMoves() {
		
		ArrayList<Moves> returnList=new ArrayList<Moves>();
		
		for(int i=0;i<this.movelist.size();i++) {
			
			
			Moves move=this.movelist.get(i);
			if(move.getCost()>=this.sp) {
				returnList.add(move);
				
			}
			
		}
		
		
		
		
		
		return returnList;
		
		
		
	}
	

	
	//this is the generic ai for enemies later we will make different kinds of enemies that will extend this class and we will override the take turn method
	
	public void takeTurn(BattleEntity player) {
		//this is going to be the method that is called when it is this enemy's turn it will do all the calculations to find out whether to attack or use a item
		
	
		
		///first we make a list of usable moves
	
		
		
	ArrayList<Moves> moves=makeListOfUseableMoves();		
		
	
	boolean actionTaken=false;
	
	
	//next we check enemy's hp
	
	//if HP is less than 25%----------------------------------------------------------------------------------------------------------
	if(this.hp<(this.maxHP*.25f)) {
		//heal up
		
		//we first check if we have a item that heals
		boolean healingItemFound=false;
		
		Items itemWithHighestHeal=null;
		ArrayList<Items> healingItems=new ArrayList<Items>();
		if(!this.inventory.isEmpty()) {
		for(int i=0;i<this.inventory.getItems().length;i++) {
			
			Items item=this.inventory.getItems()[i];
		  	if(item.Item.isHealing()) {
		  		
		  		healingItems.add(item);
		  		
		  		
		  		//we find the highest healing item
		  		
		  		if(itemWithHighestHeal!=null) {
		  			
		  		  if(item.Item.getValue()>itemWithHighestHeal.Item.getValue()) {
		  			  
		  			  itemWithHighestHeal=item;
		  		  }
		  			
		  			
		  		}else {
		  			itemWithHighestHeal=item;
		  		}
		  		
		  		
		  		
		  		
		  		
		  	}
		
		
		
		}
		healingItemFound=!healingItems.isEmpty();
		}
		
		
		//now we check if there is a healing move
	    boolean healingMoveFound=false;
	    Moves HighestHealingMove=null;
	    ArrayList<Moves> healingMoves=new ArrayList<Moves>();
	    
		for(int i=0;i<moves.size();i++) {
			
			Moves move=moves.get(i);
			if(move.isHeal()) {
				
				healingMoves.add(move);
				
			if(HighestHealingMove!=null) {
				
				if(move.getDamage()>HighestHealingMove.getDamage()) {
					HighestHealingMove=move;
					
				}
				
			}else {
				HighestHealingMove=move;
				
			}
			
			
			
			}
			
			
			
		}
		healingMoveFound=!healingMoves.isEmpty();
		
		if(healingMoveFound && healingItemFound) {
			float value1=itemWithHighestHeal.Item.getValue();
			float value2=HighestHealingMove.getDamage();
			
			if(value1>value2) {
				
				actionTaken=useItem(itemWithHighestHeal);
				
			}else if(value1<value2) {
				
			     
				actionTaken=useMove(HighestHealingMove,player);
			}else {
				Random r=new Random();
				float random=r.nextFloat();
				
				
				if(random<=.5) {
					
					actionTaken=useItem(itemWithHighestHeal);
				}else {
					actionTaken=useMove(HighestHealingMove,player);
				}
				
				
			}
			
			
		}else if(healingMoveFound){
			
			actionTaken=useMove(HighestHealingMove,player);
			
			
		}else if(healingItemFound){
			actionTaken=useItem(itemWithHighestHeal);
			
			
		}
		
		Start.DebugPrint("actiontaken= "+actionTaken+"("+healingMoveFound+","+healingItemFound+")");		
	}
	//-------------------------------------------------------------------------------------------------------------------------
	

	
	
		
		
		
	}
	
	
	

	public boolean useMove(Moves move,BattleEntity player) {
		
		boolean used=this.movelist.contains(move);
		
		if(used) {
			
			 if(!move.isHeal()) {
				   
				  float Damage=BattleFormulas.CalculateDamage(this, player,move.getDamage());
				   
						
				
			        player.decreseHp(Damage);
			   
				   
				   
			   }else {
				
				   
				   float health=BattleFormulas.CalcaulateHealth(this, move.getDamage());
				
				IncreseHp(health);   
				   
			   }
			
			
			
			
			
			
			
			
			
		Start.DebugPrint("enemy used move "+move.getName());	
			
			
		}
		
		
		
		
		
		
		return used;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
