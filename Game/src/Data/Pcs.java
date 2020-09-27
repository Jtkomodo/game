package Data;

public enum Pcs {
	
	
	
	
	
	
	
	
	C1(new float[]{
			20,//attack
			30,//Defense
			200,   //hp
			100, //sp
			50//speed
			
	},new Moves[]{
		Moves.lazer,
		Moves.heal
	}),
	C2(new float[]{
			20,//attack
			30,//Defense
			200,   //hp
			100, //sp
			50//speed
			
	},new Moves[]{
		Moves.Doublekick,
		Moves.lazer,
		Moves.SupprHeal,
		Moves.heal
	});;
		
		
	private float atk;	
	private float def;
	private float hp;
	private float sp;
	private float speed;
	private int amountOfMoves;
	private Moves[] moves;
	
	
	Pcs(float[] stats ,Moves[] moves ){
		this.atk=stats[0];
		this.def=stats[1];
		this.hp=stats[2];
		this.sp=stats[3];
		this.speed=stats[4];
		this.moves=moves;
		this.amountOfMoves=moves.length;
		
		
	}

	public float getSpeed() {
		return speed;
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
