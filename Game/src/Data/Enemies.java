package Data;

public enum Enemies {
	
	
	
	
	
	
	
	
	E1("enemy",new float[]{
			20,//attack
			30,//Defense
			180,   //hp
			75 //sp			
			
	},new Moves[]{
		Moves.punch,
		
		Moves.Doublekick,
		Moves.heal,
	});
		
		
	private float atk;	
	private float def;
	private float hp;
	private float sp;
	private String name;
	private int amountOfMoves;
	private Moves[] moves;
	
	
	Enemies(String name,float[] stats ,Moves[] moves ){
		this.atk=stats[0];
		this.def=stats[1];
		this.hp=stats[2];
		this.sp=stats[3];
		this.moves=moves;
		this.amountOfMoves=moves.length;
		this.name=name;
		
	}

	public String getName() {
		return name;
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
	public float getSp() {
		return sp;
	}
	
	public Moves getmove(int move) {
		
	if(move<=amountOfMoves) {
		return moves[move];
	}
	else {
		return moves[amountOfMoves];
	}
	}

	public int getAmountOfMoves() {
		return amountOfMoves;
	}

	public Moves[] getMoves() {
		return moves;
	}

	public void setMoves(Moves[] moves) {
		this.moves = moves;
	}
	
	
	
	
	
	
	
	

}