package Data;

public enum Moves {

	punch("punch",5,false,0,false),//damage,special,cost,healing move
	heal("heal",20,true,25,true);
	
	
	
	
	
	private float damage,cost;
	private boolean specailMove,health;
	private String name;
	
	
	Moves(String name,float damage,boolean specailMove,float cost,boolean health){
		this.name=name;
		this.damage=damage;
		this.cost=cost;
		this.health=health;
		this.specailMove=specailMove;
		
		
		
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}


	public float getCost() {
		return cost;
	}


	public boolean isSpecailMove() {
		return specailMove;
	}


	public boolean isHeal() {
		return health;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
}
