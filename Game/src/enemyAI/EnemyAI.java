package enemyAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import org.joml.Vector2f;

import Data.Moves;
import Items.Items;
import battleClasses.BattleEnemyField;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;
import gameEngine.BattleSystem;
import gameEngine.Start;

public class EnemyAI {

	
	LinkedList<EnemyAIComponent> components=new LinkedList<EnemyAIComponent>();
    LinkedList<EnemyAIComponent> PossibleActions=new LinkedList<EnemyAIComponent>();

	
	private int p;
	
	
	public EnemyAI(EnemyAIComponent[] components) {
		SetAIAndSort(components);
		
	}
	
	
	
	private void SetAIAndSort(EnemyAIComponent[] enemyAI) {
	  if(enemyAI.length!=0) {
	     for(int i=0;i<enemyAI.length;i++) {
	    	 this.components.add(enemyAI[i]);
	    	 
	     }
		     
		
		
			if(!this.components.isEmpty()) {
				 Comparator<EnemyAIComponent> byPriorety = Comparator.comparing(EnemyAIComponent::getPriorety);
				 Collections.sort(this.components, byPriorety);    
			}
		
			
		
			
		
		
		}
	}
	public boolean TakeAction(Enemy EnemyUsingAction,BattleEnemyField enemies,BattlePlayerField pcs,Moves[] affourdableMoves) {
		//deleting lists just incase
		Start.DebugPrint("action Being Selected");
	boolean ActionTaken=false;
    this.PossibleActions.clear();
    if(!this.components.isEmpty()) {
		
	
	
		int lastPriorety=this.components.get(0).getPriorety();
	   
		//set up our lists of possible actions
		for(int i=0;i<components.size();i++) {
			EnemyAIComponent c=this.components.get(i);//we get the component
			int p=c.getPriorety();//we check it's priority value
		    if(!c.isItemComponent()) {
		    	EnemyAIMoveComponent MoveAI=(EnemyAIMoveComponent)c;
		    	if(MoveAI.FindMove(pcs, enemies, EnemyUsingAction, affourdableMoves)!=null) {
		    		this.PossibleActions.add(c);
		    		
		    	}
		    	
		    }else {
			    EnemyAIItemComponent ItemsAI=(EnemyAIItemComponent)c;
			    if(ItemsAI.FindItem(pcs, enemies, EnemyUsingAction)!=null) {
			    	this.PossibleActions.add(c);
			    }
		    	
		    }
			if(p!=lastPriorety || i==components.size()-1) {//if it is not the same as last time through then the list for that priority is done pick a random move or item and use it
				if(!this.PossibleActions.isEmpty()) {
				useAction(EnemyUsingAction, enemies, pcs);
				Start.DebugPrint("action Taken");
			    return true;
				}
			}
			
			
			
		}
		
		
		
		
		
		
		
		
	}
		
		return ActionTaken;
	}
	
	
	private void useAction(Enemy EnemyUsingAction,BattleEnemyField enemies,BattlePlayerField pcs) {
		//get random index
		Random r=new Random();
		int index=r.nextInt(this.PossibleActions.size());
	
	    EnemyAIComponent c=this.PossibleActions.get(index);
		if(!c.isItemComponent()) {
		    EnemyAIMoveComponent Movec=(EnemyAIMoveComponent)c;
		    Movec.UsefoundMove(pcs, enemies, EnemyUsingAction);
		
			
		}else {
			EnemyAIItemComponent Itemc=(EnemyAIItemComponent)c;
			Itemc.UsefoundItem(pcs, enemies, EnemyUsingAction);
		}
		
	}
	
	
	
	
}
