package Items;

public enum Items {

	
   hpPotion(new HealingItem("hpPotion" ,"whitebox",25, 25)), SuperHpPotion(new HealingItem("SHpPotion" ,"whitebox",50, 25)),
   spRestore(new RestoreSPItem("spRestore","whitebox",15,30)), spSuperRestore(new RestoreSPItem("spSuperRestore","whitebox",30,90));
	
	
	

	public Item Item; 
	
	   Items(Item item){
	this.Item=item;
		
		
	}	
	

	
	
	
	
	
	
	
	
	
	
	
}
