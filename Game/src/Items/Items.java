package Items;

public enum Items {

	
   hpPotion(new HealingItem("hpPotion" ,"Whitebox",25, 25)), SuperHpPotion(new HealingItem("SuperHpPotion" ,"Whitebox",50, 25)),
   spRestore(new RestoreSPItem("spRestore","Whitebox",15,30));
	
	
	

	public Item Item; 
	
	   Items(Item item){
	this.Item=item;
		
		
	}	
	

	
	
	
	
	
	
	
	
	
	
	
}
