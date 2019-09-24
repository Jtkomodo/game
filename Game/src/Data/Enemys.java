package Data;

public enum Enemys {

	
	Enemy1(new float[]{
			20,//attack
			30,//Defense
			130   //hp
			
	},new Moves[]{
		Moves.punch,
		Moves.heal
	});
		
		
	private float atk;	
	private float def;
	private float hp;
	private int amountOfMoves;
	private Moves[] moves;
	
	Enemys(float[] stats ,Moves[] moves ){
		this.atk=stats[0];
		this.def=stats[1];
		this.hp=stats[2];
		this.moves=moves;
		this.amountOfMoves=moves.length;
		
		
	}
	
	
	
	public Moves getMove(int index) {
		return moves[index];
		
	}

	public float getAtk() {
		return atk;
	}

	public float getDef() {
		return def;
	}

	public float getHp() {
		return hp;
	}
	
	
	public float getAmountOfMoves() {
		return this.amountOfMoves;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
