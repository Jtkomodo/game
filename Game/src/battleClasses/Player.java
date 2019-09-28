package battleClasses;

import java.util.ArrayList;
import java.util.List;

import Data.Moves;

public class Player {

	private float atk,def,hp;
	private List<Moves> movelist= new ArrayList<Moves>();
	private List<Moves> spmovelist=new ArrayList<Moves>();;
	
	
	public Player(float atk,float def,float hp,Moves[] moves) {
		
    this.atk=atk;
    this.def=def;
    this.hp=hp;
    
    
    
	for(int i=0;i<moves.length;i++) {
	if(moves[i].isSpecailMove()) {
		spmovelist.add(moves[i]);
	}else {
	     movelist.add(moves[i]);}
	     }
	
	
	
    
    
    
    
		
	}
	
	public float getAtk() {
		return atk;
	}

	public void setAtk(float atk) {
		this.atk = atk;
	}

	public float getDef() {
		return def;
	}

	public void setDef(float def) {
		this.def = def;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	
	
	public void  addMove(Moves move) {
		
		movelist.add(move);
		
	}
	
	
   public void  addMoves(Moves moves[]) {
		
		for(int i=0;i<moves.length;i++) {
			movelist.add(moves[i]);
			
		}
		
		
	}
	
	
	
	
	public Moves[] getmoves() {
		
		Moves returnMoves[]=new Moves[movelist.size()];
	movelist.toArray(returnMoves);
		return returnMoves;
		
	}
	
public Moves[] getspmoves() {
		
		Moves returnMoves[]=new Moves[spmovelist.size()];
	spmovelist.toArray(returnMoves);
		return returnMoves;
		
	}
	
	
	
	
	
	
	
	
	
}
