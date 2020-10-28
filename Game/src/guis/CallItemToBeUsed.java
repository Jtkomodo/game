package guis;

import org.joml.Vector2f;

import Items.Inventory;
import Items.Items;
import Scripter.Proccesor;
import ScripterCommands.DrawString;
import battleClasses.BattleEntity;
import gameEngine.BattleSystem;
import gameEngine.Start;

public class CallItemToBeUsed extends GUIfunction {

	
	
	
	
	

	private Items item;
	private Inventory inventory;



	public CallItemToBeUsed(Items item,Inventory inventory) {

	this.item=item;
	this.inventory=inventory;	
		
	}
	
	
	
	@Override
	public boolean invoke() {
		//sets the flag to see if use item is called
	    this.inventory.setCurrentlyUsingItem(item);
	    this.inventory.setUseItemCalled(true);
		return true;
		
	}

}
