package battleClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joml.Vector2f;

import Data.Moves;
import Items.Inventory;
import Items.Item;

public class BattleEntity {
   
	private float atk,def,hp;
	private float maxATK,maxDEF,maxHP;
	private List<Moves> movelist= new ArrayList<Moves>();
	private List<Moves> spmovelist=new ArrayList<Moves>();;
	private HashMap<String,Moves> moveStrings=new HashMap<String,Moves>();
	private Inventory inventory;
	
	
	public BattleEntity(float atk,float def,float hp,Moves[] moves,Inventory inventory) {
	this.inventory=inventory;	
    this.atk=atk;
    this.def=def;
    this.hp=hp;
    this.maxATK=atk;
    this.maxDEF=def;
    this.maxHP=hp;
    
    
	for(int i=0;i<moves.length;i++) {
	    Moves move=moves[i];
		moveStrings.put(move.getName(),move);
	if(move.isSpecailMove()) {
		spmovelist.add(move);
	}else {
	     movelist.add(move);}
	     }
	
	
	
    
    
    
    
		
	}
	
	public float getMaxATK() {
		return maxATK;
	}

	public void setMaxATK(float maxATK) {
		this.maxATK = maxATK;
	}

	public float getMaxDEF() {
		return maxDEF;
	}

	public void setMaxDEF(float maxDEF) {
		this.maxDEF = maxDEF;
	}

	public float getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(float maxHP) {
		this.maxHP = maxHP;
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
	public void IncreseHp(float hp) {
		float newhp=this.hp+hp;
		if(newhp>this.maxHP) {
			newhp=this.maxHP;
		}
		
		this.hp=newhp;
		
	}
	public void decreseHp(float hp) {
		float newhp=this.hp-hp;
		if(newhp<0) {
			newhp=0;
		}
		
		this.hp=newhp;
		
	}

	
	
	public void  addMove(Moves move) {
		
		if(move.isSpecailMove()) {
			spmovelist.add(move);
		}else {
		     movelist.add(move);
		     }
		     
		
		
	}
	
	
   public void  addMoves(Moves moves[]) {
		
		for(int i=0;i<moves.length;i++) {
			if(moves[i].isSpecailMove()) {
				spmovelist.add(moves[i]);
			}else {
			     movelist.add(moves[i]);}
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
	
	
public int  getAmountofMoves() {
	return this.movelist.size();
	
}
	
public int  getAmountofSPMoves() {
	return this.spmovelist.size();
	
}
		
	
public Moves getmoveFromString(String name) {
	
	return moveStrings.get(name);
	
}

public void useItem(Item item) {
	
	
	item.useItem(this);
	this.inventory.removeItem(item);
	
	
	
}

public void addItemToInventory(Item item) {
	
	
	
	this.inventory.addItem(item);
	
	
	
}


	
}
