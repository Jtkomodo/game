package Data;

public enum Moves {

	punch(5,false,0,false),//damage,special,cost,healing move
	heal(20,true,25,true);
	
	
	
	
	
	private float damage,cost;
	private boolean specailMove,health;
	
	
	
	Moves(float damage,boolean specailMove,float cost,boolean health){
		
		this.damage=damage;
		this.cost=cost;
		this.health=health;
		this.specailMove=specailMove;
		
		
		
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
