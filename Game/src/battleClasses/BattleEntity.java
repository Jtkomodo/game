package battleClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


import org.joml.Vector2f;

import Data.Moves;
import Items.Inventory;
import Items.Item;
import Items.Items;
import gameEngine.Start;

public class BattleEntity {
   
	
	protected float atk,def,hp,sp,speed;
	


	protected float maxATK,maxDEF,maxHP,maxsp;
	protected List<Moves> movelist= new ArrayList<Moves>();
	protected List<Moves> NormalMoveList= new ArrayList<Moves>();
	protected List<Moves> spmovelist=new ArrayList<Moves>();//this will be sorted by the spCost
	protected HashMap<String,Moves> moveStrings=new HashMap<String,Moves>();
	protected Inventory inventory;
	protected Moves lastUsedMove;
	
	
	public BattleEntity(float atk,float def,float hp,float sp,float speed,Moves[] moves,Inventory inventory) {
	this.inventory=inventory;	
    this.atk=atk;
    this.def=def;
    this.hp=hp;
    this.maxsp=sp;
    this.maxATK=atk;
    this.maxDEF=def;
    this.maxHP=hp;
    this.sp=sp;
    this.speed=speed;
    
	for(int i=0;i<moves.length;i++) {
	    Moves move=moves[i];
		moveStrings.put(move.getName(),move);
        movelist.add(move);
		if(move.isSpecailMove()) {
		spmovelist.add(move);
	}else {
		NormalMoveList.add(move);
	}
	   
	 
	 	}
	
	
	
	//sort splist by cost
	if(!spmovelist.isEmpty()) {
	  Comparator<Moves> byCost = Comparator.comparing(Moves::getCost);
	 	Collections.sort(spmovelist, byCost);    
	
	}
  
  
    
		
	}
	
	public void setLastUsedMove(Moves lastUsedMove) {
		this.lastUsedMove = lastUsedMove;
	}
	
	public Moves getLastUsedMove() {
		return this.lastUsedMove;
	}
	
	public float getSp() {
		return sp;
	}

	public void setSp(float sp) {
		if(sp<=maxsp)
		this.sp = sp;
	}

	public float getMaxsp() {
		return maxsp;
	}

	public void setMaxsp(float maxsp) {
		this.maxsp = maxsp;
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
		if(hp<=maxHP)
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

	public void IncreseSp(float sp) {
		float newsp=this.sp+sp;
		if(newsp>this.maxsp) {
			newsp=this.maxsp;
		}
		
		this.sp=newsp;
		
	}
	public void decreseSp(float sp) {
		float newsp=this.sp-sp;
		if(newsp<0) {
			newsp=0;
		}
		
		this.sp=newsp;
		
	}


	
	
	
	public void  addMove(Moves move) {
		
		if(move.isSpecailMove()) {
			spmovelist.add(move);
		}else {
		     movelist.add(move);
		     }
		if(!spmovelist.isEmpty()) {
			  Comparator<Moves> byCost = Comparator.comparing(Moves::getCost);
			 	Collections.sort(spmovelist, byCost);    
			
			}
		  
		
		
	}
	
	
   public void  addMoves(Moves moves[]) {
		
		for(int i=0;i<moves.length;i++) {
			if(moves[i].isSpecailMove()) {
				spmovelist.add(moves[i]);
			}else {
			     movelist.add(moves[i]);}
			     }
		if(!spmovelist.isEmpty()) {
			  Comparator<Moves> byCost = Comparator.comparing(Moves::getCost);
			 	Collections.sort(spmovelist, byCost);    
			
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

public boolean useItem(Items item) {
	
	
	boolean used=item.Item.useItem(this);
	if(used) {
	this.inventory.removeItem(item);
	Start.DebugPrint(this.toString()+"used item "+item.Item.getName());
	}
	
	
	return used;
	
}

public boolean useMove(Moves move) {
	boolean used=this.movelist.contains(move);
	
if(move.getCost()<=this.sp) {
	decreseSp(move.getCost());
	this.lastUsedMove=move;
	
}else {
	used=false;
}
	
	return used;
}



public void addItemToInventory(Items item) {
	
	
	
	this.inventory.addItem(item);
	
	
	
}
public float getSpeed() {
	return speed;
}

public void setInventory(Inventory inventory) {
	this.inventory = inventory;
}
	
}
