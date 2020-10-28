package guis;

import Items.Inventory;
import battleClasses.BattleEntity;

public class GUIUseCurrentItemOnPC extends GUIfunction {

	
	private BattleEntity entity;
	private Inventory inventory;
	
	
	public GUIUseCurrentItemOnPC(BattleEntity entity,Inventory inventory) {
		this.entity=entity;
		this.inventory=inventory;
		
	}
	
	
	
	
	
	
	
	@Override
	public boolean invoke() {
		boolean used=this.inventory.UseCurrentIem(entity);
		this.inventory.setUseItemCalled(!used);
		return used;
		
	}

}
