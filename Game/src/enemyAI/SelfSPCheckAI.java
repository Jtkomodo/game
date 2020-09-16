package enemyAI;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import Data.Moves;
import Items.Item;
import Items.Items;
import battleClasses.BattleEnemyField;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;

public class SelfSPCheckAI extends EnemyAIItemComponent {

	
	private static final int COMPID=1;
    private float percentage;

	public SelfSPCheckAI(int Priorety,float percentage){
		super(COMPID,Priorety);
        this.percentage=percentage;		
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public Item FindItem(BattlePlayerField pcs, BattleEnemyField enemies, Enemy entityUsingMove) {
	    Item foundItem=null;
		float currentSP=entityUsingMove.getSp();
		float spMax=entityUsingMove.getMaxsp();
	    if(currentSP<=(spMax*percentage)) {//if the current sp is lower than the percentage that we set up then we want to find a item that will restore it
	    	float amountNeededToRestore=spMax-currentSP;
	    	LinkedList<Item> possibleItems=new LinkedList<Item>();
	    
	    	Items[] items=entityUsingMove.getItems();
	    	for(int i=0;i<items.length;i++) {
	    		Item item=items[i].Item;
	    		if(item.isRestorSP()) {
	    		  possibleItems.add(item);
	    		}
	    		
	    	}
	    
	    	
	       Comparator<Item> c=Comparator.comparing(Item::getValue);
		   Collections.sort(possibleItems,c);
		    int foundItemCount=0;
		    
	       for(int i=possibleItems.size()-1;i>=0;i--) {
	    	     Item item=possibleItems.get(i);
	    	     int itemCount=entityUsingMove.getAmountOfItem(item);
	    	     if(foundItem==null) {
	    	    	 foundItem=item;
	    	    	 foundItemCount=itemCount;
	    	    	 
	    	     }
	    	     
	    	     else if((itemCount>foundItemCount) && item.getValue()>=(amountNeededToRestore-(amountNeededToRestore*.05))) { //if the amount of items in the inventory is higher than the last and the item restores up to 95% of the amount needed to restore
	    	    	 foundItem=item;
	    	    	 foundItemCount=itemCount;
	    	    	 
	    	     }
	    	
	       }
	    }
	    
		
		
		this.FoundItem=foundItem;
		this.selectedEntity=entityUsingMove;
		
		return foundItem;
	}

	

}
