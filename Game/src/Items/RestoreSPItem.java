package Items;

import battleClasses.BattleEntity;

public class RestoreSPItem extends Item {

	private float howMuchItRestores;
	
	protected RestoreSPItem(String name, String textureName,float howMuchItRestores, float basePrice) {
		super(name, textureName, basePrice);
		this.howMuchItRestores=howMuchItRestores;
		this.value=this.howMuchItRestores;
		this.restorSP=true;
	}

	@Override
	public boolean useItem(BattleEntity entity) {
	  
		if(entity.getSp()!=entity.getMaxsp()) {
		entity.IncreseSp(this.howMuchItRestores);
		return true;
		}
		else {
			return false;
		}
	}
   
	
	
}
