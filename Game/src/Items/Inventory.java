package Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Inventory {

	
	
	private HashMap<String,Integer> items=new HashMap<String,Integer>();
	private List<String> itemNames=new  LinkedList<String>();
	private List<Item> itemsList=new LinkedList<Item>();
	
	public Inventory(Item[] ListOfUniqueItems,int[] itemAmount) {
		if(ListOfUniqueItems.length==itemAmount.length) {
		for(int i=0;i<ListOfUniqueItems.length;i++) {
			
			Item item=ListOfUniqueItems[i];
			int amount=itemAmount[i];
			
			if(amount>0 && !items.containsKey(item.getName())) {
			this.items.put(item.getName(),amount);
			this.itemNames.add(item.getName());
			this.itemsList.add(item);
			}
			
		}
		}
		
	}
	
	public Item[] getItems() {
		
		Item[] result=new Item[itemNames.size()];
		
		for(int i=0;i<result.length;i++) {
			result[i]=itemsList.get(i);
		}
		
		
		
		return result;
	}
	
	public int getAmountOfItem(Item item) {
		return items.getOrDefault(item.getName(),0);
		
	}
	
	
	public void addItem(Item item){
		
		
		
		if(items.containsKey(item.getName())) {
			int amount=items.get(item.getName());
			items.put(item.getName(),amount+1);
		}else {
			itemNames.add(item.getName());
			itemsList.add(item);
			items.put(item.getName(),1);
		}
		
		
	}
	
	public void removeItem(Item item) {
		
		if(items.containsKey(item.getName())) {
	       int amount=items.get(item.getName());
	       if(amount==1) {
	    	   items.remove(item.getName());
	    	   itemNames.remove(item.getName());
	    	   itemsList.remove(item);
	       }else {
	    	   items.put(item.getName(),amount-1);
	       }
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
