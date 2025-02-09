package Data;

import animation.Animate;
import customExceptions.EnemyHasToManyMoves;
import customExceptions.EnemyStatsNotCorrectFormat;
import enemyAI.EnemyAI;
import gameEngine.Start;
import enemyAI.*;
public enum Enemies {
	
	
	
	

	
	
	E1("E1",new float[]{
			10,//attack
			30,//Defense
			180,   //hp
			70, //sp
			48-48//speed
			
	},new Moves[]{
		Moves.lazer,
		Moves.Doublekick,
		Moves.heal,
		Moves.SupprHeal
	
	},new EnemyAI(
		new EnemyAIComponent[]{
				new SelfHealthCheckMoveAI(1,.50f),
				new SelfSPCheckAI(2,.50f)
			})
			
    );
		
	private final int amountOfstats=5;
    private final int maxAmountOfMoves=4;
	
	
	private float atk;	
	private float def;
	private float hp;
	private float sp;
	private float speed;
	private String name;
	private int amountOfMoves;
	private Moves[] moves;
	private EnemyAI enemyAI;
	
	Enemies(String name,float[] stats ,Moves[] moves,EnemyAI enemyAI){
		this.enemyAI=enemyAI;
		try{
		if(stats.length!=amountOfstats) {
			throw new EnemyStatsNotCorrectFormat(name);
		}
	   if(moves.length>maxAmountOfMoves) {
		   
			throw new EnemyHasToManyMoves(name);
	   }
		
		
		
		
		this.atk=stats[0];
		this.def=stats[1];
		this.hp=stats[2];
		this.sp=stats[3];
		this.speed=stats[4];
		
		
		
		this.moves=moves;
		this.amountOfMoves=moves.length;
		this.name=name;
		}catch(EnemyStatsNotCorrectFormat enemystatsincorrect) {
			enemystatsincorrect.printStackTrace();
			System.exit(1);
			
		} catch (EnemyHasToManyMoves e) {
		
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	public float getSpeed() {
		return speed;
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

	public EnemyAI getEnemyAI() {
		return enemyAI;
	}
	
	
	
	
	
	
	
	

}