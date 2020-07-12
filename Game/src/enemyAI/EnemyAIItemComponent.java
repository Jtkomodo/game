package enemyAI;

import java.util.ArrayList;

import Data.Moves;
import Items.Items;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;

public abstract class EnemyAIItemComponent extends EnemyAIComponent {

	
	private static final boolean IS_ITEM_COMPONENT=true;
	private int ID;//every component has a static ID that is unique
	
	private BattleEntity selectedEntity;
	private Items FoundItem;
	
	public EnemyAIItemComponent(int ID, int Priorety) {
		super(IS_ITEM_COMPONENT, Priorety);
	   this.ID=ID;
	}
	
	
	
   public abstract Items FindItem(BattlePlayerField pcs,BattleEnemyField enemies,Enemy entityUsingMove);//this is called by AI first to find a item and the selected pc we will use it on
   
   public abstract void UsefoundItem(BattlePlayerField pcs,BattleEnemyField enemies,Enemy entityUsingMove);//this is called second after enemyAI has decided it actually wants to use this AI

	
	//getters
	
    public int getID() {
		return ID;
	}
	
 
	
}
