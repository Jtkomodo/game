package battleClasses;

import java.util.LinkedList;

import org.joml.Vector2f;

public class BattleSlotListHandeler {

	private static LinkedList<BattleSlot> slots=new LinkedList<BattleSlot>();
	private static LinkedList<BattleSlot> availableSlots=new LinkedList<BattleSlot>();
	private static LinkedList<Vector2f> slotPositions=new LinkedList<Vector2f>();
	private static LinkedList<BattleSlot> Playerslots=new LinkedList<BattleSlot>();
	
	public static void addSlot(BattleSlot slot) {
	
		if(!slotPositions.contains(slot.getPosition())) {
			slots.add(slot);
			slotPositions.add(slot.getPosition());
			if(!slot.isEnemy()) {
				Playerslots.add(slot);
			}
		}
		
		
		
		}
	
	public static void removeSlot(BattleSlot slot) {
		
		if(slots.contains(slot)) {
			slots.remove(slot);
			slotPositions.remove(slot.getPosition());
			availableSlots.remove(slot);
			if(!slot.isEnemy()) {
				Playerslots.remove(slot);
			}
		}
	}
	
	
	public static void makeSlotAvailable(BattleSlot slot) {
		
		addSlot(slot);
		if(slots.contains(slot)&&!availableSlots.contains(slot)) {
			availableSlots.add(slot);
			}
		
	}

	
	public static boolean isSlotAvailable(BattleSlot slot) {
		return availableSlots.contains(slot);
		
	}
  
    public BattleSlot[] getPlayerSlots() {

		return  Playerslots.toArray(new BattleSlot[Playerslots.size()]);
		
	}
	
	
	
	
	
	
	
	
}
