package Items;

public enum Items {

	
   hpPotion(new HealingItem("hpPotion" ,"Whitebox",25, 25)), SuperHpPotion(new HealingItem("SHpPotion" ,"Whitebox",50, 25)),
   spRestore(new RestoreSPItem("spRestore","Whitebox",15,30)), spSuperRestore(new RestoreSPItem("spSuperRestore","Whitebox",30,90));
	
	
	

	public Item Item; 
	
	   Items(Item item){
	this.Item=item;
		
		
	}	
	

	
	
	
	
	
	
	
	
	
	
	
}
