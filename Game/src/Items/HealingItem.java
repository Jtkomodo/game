package Items;

import battleClasses.BattleEntity;
import gameEngine.Texture;

public class HealingItem extends Item {

	private float howMuchItHeals;
	
	
	public HealingItem(String name, String textureName,float howMuchItHeals,float basePrice) {
		super(name, textureName,basePrice);
	    this.howMuchItHeals=howMuchItHeals;
	}

	@Override
	public boolean useItem(BattleEntity entity) {
		
		if(entity.getHp()!=entity.getMaxHP()) {
		entity.IncreseHp(this.howMuchItHeals);
		return true;
		}else {
			return false;
		}
	}
     
}
