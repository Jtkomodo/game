package battleClasses;

import java.util.LinkedList;

import org.joml.Vector2f;

import gameEngine.ArrowKeySelecter;
import gameEngine.Start;

public class BattleSlotList {

	private  LinkedList<BattleSlot> slots=new LinkedList<BattleSlot>();//all slots currently added
	private  LinkedList<BattleSlot> availableSlots=new LinkedList<BattleSlot>();//only the ones that are available
	private  LinkedList<BattleSlot> UsedSlots=new LinkedList<BattleSlot>();//this is all the slots currently ocupied by somthing
	private  LinkedList<Vector2f> slotPositions=new LinkedList<Vector2f>();

	public BattleSlotList(BattleSlot[] slots) {
		addSlots(slots);
		
		
	}
	
	
	
	
	
	public void addSlots(BattleSlot[] slots) {
		for(int i=0;i<slots.length;i++) {
			addSlot(slots[i]);
			
		}
		
	  
	}
	
	
	
	public  void addSlot(BattleSlot slot) {
	
		if(!slotPositions.contains(slot.getPosition()) && slot.getEntity()!=null) {
			slots.add(slot);
			slotPositions.add(slot.getPosition());
		    UsedSlots.add(slot);
		
		}
	
		
		}
	
	public void replaceAllSlots(BattleSlot[] slots) {
		removeAllSlots();
		addSlots(slots);
	}

	
	
	private void removeAllSlots() {
	    BattleSlot[] slots=getAllSlots();    
		for(int i=0;i<slots.length;i++) {
			removeSlot(slots[i]);
			
		}
	}





	public void removeSlots(BattleSlot[] slots) {
		for(int i=0;i<slots.length;i++) {
			removeSlot(slots[i]);
			
		}
		
	}
	
	
	
	
	public  void removeSlot(BattleSlot slot) {
		
		if(slots.contains(slot)) {
			slots.remove(slot);
			slotPositions.remove(slot.getPosition());
			availableSlots.remove(slot);
		    UsedSlots.remove(slot);
		}
	}
	
	
	public  void makeSlotAvailable(BattleSlot slot) {
		
		addSlot(slot);
		if(slots.contains(slot)&&!availableSlots.contains(slot)) {
			availableSlots.add(slot);
			UsedSlots.remove(slot);
			}
		
	}

	
	public  boolean isSlotAvailable(BattleSlot slot) {
		return availableSlots.contains(slot);
		
	}
  
    public BattleSlot[] getAllSlots() {

		return  slots.toArray(new BattleSlot[slots.size()]);
		
	}
	
    public BattleSlot[] getAvailableSlots() {

		return  this.availableSlots.toArray(new BattleSlot[this.availableSlots.size()]);
		
	}
	
	
    public BattleSlot[] getUsedSlots() {

		return  this.UsedSlots.toArray(new BattleSlot[this.UsedSlots.size()]);
		
	}
	
	
	
	
	
}
