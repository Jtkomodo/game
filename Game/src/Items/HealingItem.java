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
	public void useItem(BattleEntity entity) {
		 entity.IncreseHp(this.howMuchItHeals);

	}
     
}
