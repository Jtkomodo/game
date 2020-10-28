package Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import battleClasses.BattleEntity;
import guis.GUINode;
import guis.GUINodeDisplayItemInfo;
import guis.CallItemToBeUsed;

public class Inventory {

	
	
	private HashMap<String,Integer> items=new HashMap<String,Integer>();
	private HashMap<String,GUINode> itemNodes=new HashMap<String,GUINode>();
	private List<String> itemNames=new  LinkedList<String>();
	private List<Items> itemsList=new LinkedList<Items>();
	private GUINode bagNode=null;
	private boolean UseItemCalled=false;
	private Items currentlyUsingItem;
	
	public Inventory(Items[] ListOfUniqueItems,int[] itemAmount) {
		if(ListOfUniqueItems.length==itemAmount.length) {
		for(int i=0;i<ListOfUniqueItems.length;i++) {
			
			Items item=ListOfUniqueItems[i];
			int amount=itemAmount[i];
			
			if(amount>0 && !items.containsKey(item.Item.getName())) {
			this.items.put(item.Item.getName(),amount);
			this.itemNames.add(item.Item.getName());
			this.itemsList.add(item);
			}
			
		}
		}
		
		
	}
	
	
	public Inventory(Items[] ListOfUniqueItems,int[] itemAmount,GUINode bagNode) {
		this.bagNode=bagNode;
		
		if(ListOfUniqueItems.length==itemAmount.length) {
		for(int i=0;i<ListOfUniqueItems.length;i++) {
			
			Items item=ListOfUniqueItems[i];
			int amount=itemAmount[i];
			
			if(amount>0 && !items.containsKey(item.Item.getName())) {
			this.items.put(item.Item.getName(),amount);
			this.itemNames.add(item.Item.getName());
			this.itemsList.add(item);
			GUINode node=new GUINode(item.Item.getName(),new CallItemToBeUsed(item,this),new GUINodeDisplayItemInfo(this,item));
			itemNodes.put(item.Item.getName(), node);
			bagNode.addChild(node);}
			
		}
		}
		
	}
	
	public Items[] getItems() {
		
		Items[] result=new Items[itemNames.size()];
		
		for(int i=0;i<result.length;i++) {
			result[i]=itemsList.get(i);
		}
		
		
		
		return result;
	}
	
	public int getAmountOfItem(Item iTM) {
		return items.getOrDefault(iTM.getName(),0);
		
	}
	
	
	public void addItem(Items item){
		
		
		
		if(items.containsKey(item.Item.getName())) {
			int amount=items.get(item.Item.getName());
			items.put(item.Item.getName(),amount+1);
		}else {
			itemNames.add(item.Item.getName());
			itemsList.add(item);
			items.put(item.Item.getName(),1);
			if(this.bagNode!=null) {
			GUINode node=new GUINode(item.Item.getName(),new CallItemToBeUsed(item,this),new GUINodeDisplayItemInfo(this,item));
			itemNodes.put(item.Item.getName(), node);
			bagNode.addChild(node);}
		}
		
		
	}
	
	public void removeItem(Items item) {
		
		if(items.containsKey(item.Item.getName())) {
	       int amount=items.get(item.Item.getName());
	       if(amount==1) {
	    	   items.remove(item.Item.getName());
	    	   itemNames.remove(item.Item.getName());
	    	   itemsList.remove(item);
	    		if(this.bagNode!=null) {
	    	    GUINode node=itemNodes.get(item.Item.getName());
				itemNodes.remove(item.Item.getName());
				bagNode.remove(node);}
	       }else {
	    	   items.put(item.Item.getName(),amount-1);
	       }
			
		}
		
		
	}
	
	
	public boolean isEmpty() {
		return itemsList.isEmpty();
		
	}

	
	
	public boolean UseCurrentIem(BattleEntity e) {
		if(this.currentlyUsingItem!=null && e!=null) {
		return e.useItem(currentlyUsingItem);
		}else {
			return  false;
		}
		
		}
	
	
	
	
	
	

	public Items getCurrentlyUsingItem() {
		return currentlyUsingItem;
	}


	public void setCurrentlyUsingItem(Items currentlyUsingItem) {
		this.currentlyUsingItem = currentlyUsingItem;
	}

   
	
	
	
	public boolean isUseItemCalled() {
		return UseItemCalled;
	}


	public void setUseItemCalled(boolean useItemCalled) {
		UseItemCalled = useItemCalled;
	}
	
	
	
	
	
	
	
}
