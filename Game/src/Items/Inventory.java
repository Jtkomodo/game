package Items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Inventory {

	
	
	private HashMap<String,Integer> items=new HashMap<String,Integer>();
	private List<String> itemNames=new  LinkedList<String>();
	private List<Items> itemsList=new LinkedList<Items>();
	
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
		}
		
		
	}
	
	public void removeItem(Items item) {
		
		if(items.containsKey(item.Item.getName())) {
	       int amount=items.get(item.Item.getName());
	       if(amount==1) {
	    	   items.remove(item.Item.getName());
	    	   itemNames.remove(item.Item.getName());
	    	   itemsList.remove(item);
	       }else {
	    	   items.put(item.Item.getName(),amount-1);
	       }
			
		}
		
		
	}
	
	
	public boolean isEmpty() {
		return itemsList.isEmpty();
		
	}
	
	
	
	
	
	
	
}
