package battleMoves;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import battleClasses.BattleEnemyField;
import battleClasses.BattleEntity;
import battleClasses.BattlePlayerField;
import battleClasses.TimedButtonCombo;
import battleClasses.TimedButtonPress;

public class Move {


   private HashMap<Integer,MoveComponent> components=new HashMap<Integer,MoveComponent>();
 
   private TimedButtonCombo TimedButton;
   private boolean timedPress;
   
   
 
   
   
   public Move(MoveComponent[] components) {
	    this.timedPress=false;
	    constuct(components);
	   
   }
   
   

  
public Move(MoveComponent[] components,TimedButtonCombo TimedButton) {
	      this.TimedButton=TimedButton;
	      this.timedPress=true;
	      constuct(components);
	   
   }
   
   
   private void constuct(MoveComponent[] components) {
	   for(int i=0;i<components.length;i++) {
			  MoveComponent component=components[i];
			   
			   if(!this.components.containsKey(component.getID())) {
				   
				   this.components.put(component.getID(),component);
				  
				  
			   }
			   
		   }
	   
   }
   
   
   
   
  




public boolean testIfMoveCanBeUsed(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity entitySelected) {
	   
	   boolean moveCanBeUsed=true;
	   
  Iterator<MoveComponent> i=this.components.values().iterator();
	   
	   
	   while(i.hasNext()) {
		   MoveComponent component=i.next();
		   if(!component.canMoveBeUsed(pcs, enemies, entityUsingMove, entitySelected)){
			   moveCanBeUsed=false;
			   break;
		   }
		   
	   }
	   
	   
	   
	   
	   return moveCanBeUsed;
	   
	   
   }
   
   
   
   
  


   public void useMove(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity SelectedEntity) {
	   
	   
        Iterator<MoveComponent> i=this.components.values().iterator();
	   
	   
	   while(i.hasNext()) {
		   MoveComponent c=i.next();
		 
		   if(entityUsingMove.isEnemy()) {
				 
		      if(!c.isTimedButtonDependent() || !this.timedPress) {
		    	  c.useMove(pcs, enemies, entityUsingMove, SelectedEntity);
			   }
		   }else {
			   c.useMove(pcs, enemies, entityUsingMove, SelectedEntity); 
			  
		   }
		   
	   }
	   
	   
	   
	   
   }
  
   
   public boolean update(BattlePlayerField pcs,BattleEnemyField enemies,BattleEntity entityUsingMove,BattleEntity SelectedEntity) {
	   int state=this.TimedButton.update();
	   boolean timedButtonDOne=false;
	   if(state!=TimedButton.NOTPUSHED) {
		   
		   timedButtonDOne=true;
		   
		   Iterator<MoveComponent> i=this.components.values().iterator();
		   
		   
		   while(i.hasNext()) {
			   MoveComponent component=i.next();
			   component.useMoveAfterPress(pcs, enemies, entityUsingMove, SelectedEntity,state);
		   }
		   
		   
	   }
	 return timedButtonDOne;
	   
   }
   
   

    public boolean HasSingleHealComponent() {
    	
    	
    	return this.components.containsKey(SingleHealComponent.COMPID);
    }

   public SingleHealComponent getSingleHealCompoent() {
	   return (SingleHealComponent)this.components.get(SingleHealComponent.COMPID);
   }
   
   
   public boolean HaSingleDamageComponent() {
	   return this.components.containsKey(SingleDamageComponent.COMPID);
   }
   
   public SingleDamageComponent getSingleDamageComponent() {
	   return (SingleDamageComponent) this.components.get(SingleDamageComponent.COMPID);
   }
   
   public boolean HasSpCostComponent() {
	   return this.components.containsKey(SpCostComponent.COMPID);
   }
   public SpCostComponent getSpCostComponent() {
	   return (SpCostComponent) this.components.get(SpCostComponent.COMPID);
   }
	
}



