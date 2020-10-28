package guis;

import battleClasses.BattleEntity;

public class FullHeal extends GUIfunction {

	
	private BattleEntity e;
	
	
	
	
	public FullHeal(BattleEntity e) {
		
		this.e=e;
		
	}
	
	
	
	
	
	
	@Override
	public boolean invoke() {
		e.setHp(e.getMaxHP());
		e.setSp(e.getMaxsp());
		return true;

	}

}
