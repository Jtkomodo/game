package battleClasses;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import Data.Moves;
import Items.Inventory;
import Items.Item;
import Items.Items;
import animation.Animate;
import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import textrendering.TextBuilder;

public class BattleEntity {
   
	
	public boolean isEnemy() {
		return isEnemy;
	}

	protected float atk,def,hp,sp,speed;
	


	protected float maxATK,maxDEF,maxHP,maxsp;
	protected List<Moves> movelist= new LinkedList<Moves>();
	protected List<Moves> NormalMoveList= new LinkedList<Moves>();
	protected List<Moves> spmovelist=new LinkedList<Moves>();//this will be sorted by the spCost
	protected HashMap<String,Moves> moveStrings=new HashMap<String,Moves>();
    protected HpBar SPBAr;
	protected Inventory inventory;
	protected Moves lastUsedMove;
	protected HpBar hpbar;
	protected boolean isEnemy=false;
	protected boolean isDead=false;
	protected Model model;
	protected float scale;
	protected Texture texture;
	protected  float z=1000;

	
	public BattleEntity( Model model,Texture texture,float scale, Vector2f sizeForHealthBar, float atk,float def,float hp,float sp,float speed,Moves[] moves,Inventory inventory) {
	this.inventory=inventory;	
    this.atk=atk;
    this.def=def;
    this.hp=hp;
    this.maxsp=sp;
    this.maxATK=atk;
    this.scale=scale;
    this.maxDEF=def;
    this.maxHP=hp;
    this.texture=texture;
    this.sp=sp;
    this.speed=speed;
    this.model=model;
    this.texture=texture;

    this.hpbar=new HpBar(maxHP,hp, sizeForHealthBar, Start.HealthBarBackground, Start.COLTEX);
    this.SPBAr=new  HpBar(maxsp,sp,new Vector2f(80,10),Start.HealthBarBackground, Start.COLTEX,Constants.BAR_COLOR_YELLOW,Constants.BAR_COLOR_YELLOW); 
    
    
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
		if(sp<=maxsp) {
			this.sp = sp;
			this.SPBAr.setValue(sp);
		}
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
		
		this.hpbar.setValue(this.hp);
	}
	public HpBar getHpbar() {
		return hpbar;
	}

	public void IncreseHp(float hp) {
		float newhp=this.hp+hp;
		if(newhp>this.maxHP) {
			newhp=this.maxHP;
		}
		
		this.hp=newhp;
		this.hpbar.setValue(this.hp);
		
		
	}
	public void decreseHp(float hp) {
		float newhp=this.hp-hp;
		if(newhp<0) {
			newhp=0;
			
		}
		
		
		if(newhp==0) {
			isDead=true;
			
		}else {
			isDead=false;
		}
		
		this.hp=newhp;
		this.hpbar.setValue(this.hp);
	
	}

	public boolean isDead() {
		return hp<=0;
	}


	public void IncreseSp(float sp) {
		float newsp=this.sp+sp;
		if(newsp>this.maxsp) {
			newsp=this.maxsp;
		}
		
		setSp(newsp);
		
	}
	public void decreseSp(float sp) {
		float newsp=this.sp-sp;
		if(newsp<0) {
			newsp=0;
		}
		
		setSp(newsp);
		
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
	if(item.Item.isHealing()) {
		Start.source.play(Start.Heal);
	}
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
public boolean testIfMoveCanBeUsed(Moves move) {
	
	boolean used=this.movelist.contains(move);
	
if(move.getCost()<=this.sp) {
	
	return true;
	
}else {
	used=false;
}

	
	return used;
}
	

public void draw(Vector2f position,TextBuilder text) {
	
	MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z), 0, scale, texture));
	this.hpbar.draw(position.add(0,80,new Vector2f()),text);
	
	
	
}

public void draw(Vector2f position,TextBuilder text,Vector4f color) {
	
    MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z), 0, scale, texture,color));
	this.hpbar.draw(position.add(0,80,new Vector2f()),text);
	
	
	
}

public void draw(Vector2f position,TextBuilder text,boolean Mirror) {
	
	MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z), 0, scale, texture,Mirror));
	this.hpbar.draw(position.add(0,80,new Vector2f()),text);
	
	
	
}

public void draw(Vector2f position,TextBuilder text,Vector4f color,boolean Mirror) {
	
    MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z), 0, scale, texture,color,Mirror));
	this.hpbar.draw(position.add(0,80,new Vector2f()),text);
	
	
	
}
public void drawSPBAR(Vector2f position,TextBuilder text) {
	this.SPBAr.draw(position,text);
	
}



public Model getModel() {
	return model;
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
public float getZ() {
	return z;
}

public void setZ(float z) {
	this.z = z;
}

}

