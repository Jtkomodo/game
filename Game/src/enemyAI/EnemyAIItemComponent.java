package enemyAI;

import java.util.ArrayList;

import Data.Moves;
import Items.Item;
import Items.Items;
import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import battleClasses.Enemy;
import gameEngine.BattleSystem;
import gameEngine.Start;

public abstract class EnemyAIItemComponent extends EnemyAIComponent {

	
	private static final boolean IS_ITEM_COMPONENT=true;
	private int ID;//every component has a static ID that is unique
	
	protected BattleEntity selectedEntity;
	protected Item FoundItem;
	
	public EnemyAIItemComponent(int ID, int Priorety) {
		super(IS_ITEM_COMPONENT, Priorety);
	   this.ID=ID;
	}
	
	
	
   public abstract Item FindItem(BattlePlayerField pcs,BattleEnemyField enemies,Enemy entityUsingMove);//this is called by AI first to find a item and the selected pc we will use it on
   
   public void UsefoundItem(BattlePlayerField pcs,BattleEnemyField enemies,Enemy entityUsingMove){
	      
	if(this.FoundItem!=null) {
		BattleSystem.setItemUsed(true);
		Item i= this.FoundItem;
		if(this.FoundItem.isHealing()) {
		    this.selectedEntity.IncreseHp(i.getValue());
			
		}else if(this.FoundItem.isRestorSP()) {
			this.selectedEntity.IncreseSp(i.getValue());
			Start.source.play(Start.Heal);
		}
		
		
	}//this is called second after enemyAI has decided it actually wants to use this AI
   }
	
	//getters
	
    public int getID() {
		return ID;
	}
	
 
	
}
