package battleClasses;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.joml.Vector2f;

import Data.Constants;
import gameEngine.ArrowKeySelecter;
import gameEngine.BattleSystem;
import gameEngine.Start;
import gameEngine.Timer;
import input.GetInput;
import textrendering.TextBuilder;

public class BattlePlayerField {
    
	
	private HashMap<BattleEntity,Vector2f> ListOfpcs=new HashMap<BattleEntity,Vector2f>();
	private HashMap<Vector2f,BattleEntity> ListOfpcsI=new HashMap<Vector2f,BattleEntity>();//the inverse of the list so that we can get the pc by the positionVector
	private HashMap<Vector2f,BattleEntity> ListOfDeadPcs=new HashMap<Vector2f,BattleEntity>();//this is a list that we will use so that we can revive dead pcs if need be
	private TextBuilder text=new TextBuilder(Start.aakar);
    private GetInput input=new GetInput();
	private boolean Selected=false;
	private double time;
	private ArrowKeySelecter selector;
	
	
	
	public boolean EntityIsOnField(BattleEntity e) {
		return this.ListOfpcs.containsKey(e);
	}
	
	public Vector2f getPositionOfEntity(BattleEntity e) {
		
			return this.ListOfpcs.get(e);
		
	}
	
	
	public BattlePlayerField(BattleSlot[] slots) {
	   
		for(int i=0;i<slots.length;i++) {
			
			  BattleSlot s=slots[i];
				
				if(!s.isEnemy()) {
					BattleEntity e=s.getEntity();
					Vector2f vector=s.getPosition();
					
					if(!this.ListOfpcs.containsKey(e) && !this.ListOfpcsI.containsKey(vector)) {
			  ListOfpcs.put(e,vector);
			  ListOfpcsI.put(vector,e);
					}
				
				
				}
			}
		  
		   this.selector=new ArrowKeySelecter(ListOfpcsI.keySet().toArray(new Vector2f[ListOfpcsI.size()]),true);
		   
	    
	}
	
	public void addPcs(BattleSlot[] slots) {
		for(int i=0;i<slots.length;i++) {
			
			addPc(slots[i]);
		}
		
	}
	
	
	
	public void addPc(BattleSlot slot) {
		
		BattleEntity p=slot.getEntity();
		Vector2f position=slot.getPosition();
		
		if(!this.ListOfpcs.containsKey(p) && !this.ListOfpcsI.containsKey(position) && !slot.isEnemy()) {
			
			this.ListOfpcs.put(p,position);
			this.ListOfpcsI.put(position, p);
			this.selector.addPosition(position);
		}else if(!slot.isEnemy() && !this.ListOfpcs.containsKey(p) && this.ListOfpcsI.containsKey(position)) {
			
			//if this is not a enemy and if the new pc does not already exist ,but the postion is taken then 
			//replace the current pc in that position with the new one
			
			BattleEntity Oldpc=this.ListOfpcsI.get(position);//get the oldPC that was in that position
		    this.ListOfpcs.remove(Oldpc);//remove the old pc and the position
		    this.ListOfpcs.put(p, position);//now place the new pc in the position
			this.ListOfpcsI.put(position,p);//repalces the old pc with the new one in the correct position 
			this.selector.addPosition(position);
		   //now both of these lists are exact inverses of the other still
			
			
		}
		if(this.ListOfpcs.size()!=this.ListOfpcsI.size()) {
			Start.DebugPrint("the lists are not inverses somthing went wrong");
			
		}
		
	}
     

	public void removePcs(BattleSlot[] slots) {
		for(int i=0;i<slots.length;i++) {
			removePc(slots[i]);
			
		}
		
	}
	
	
	public void removePc(BattleSlot slot) {
		BattleEntity p=slot.getEntity();
		Vector2f position=slot.getPosition();
	if(this.ListOfpcs.containsKey(p) && this.ListOfpcsI.containsKey(position)) {
			
			this.ListOfpcs.remove(p);
			this.ListOfpcsI.remove(position);
			this.selector.removePosition(position);
		}
		
	}
	
	private void removeAllPcs() {
		this.ListOfpcs.clear();
		this.ListOfpcsI.clear();
	    this.selector.removeAllPositions();
		
	}
	
	
	
	
	public void replaceAllPcs(BattleSlot[] slots) {
		removeAllPcs();
		addPcs(slots);
	
	}
	
	
	
	
	
	public boolean updateField() {
	
	 Iterator<Map.Entry<BattleEntity,Vector2f>> itr = ListOfpcs.entrySet().iterator(); 
		
	 while(itr.hasNext()) {
		 Map.Entry<BattleEntity, Vector2f> entry = itr.next();
         BattleEntity p=entry.getKey();
		 Vector2f v=entry.getValue();
		
         
         
         
		 
         
         if(p.isDead()) {
        
			itr.remove();
			this.ListOfpcs.remove(p);
		    this.ListOfpcsI.remove(v);
		    this.selector.removePosition(v);
		    this.ListOfDeadPcs.put(v, p);
		}
		
	}
    Iterator<Map.Entry<Vector2f,BattleEntity>> itr2 = this.ListOfDeadPcs.entrySet().iterator(); 
	while(itr2.hasNext()) {
		Map.Entry<Vector2f,BattleEntity> entry=itr2.next();
	    BattleEntity p=entry.getValue();
	    Vector2f v=entry.getKey();
	    
	    if(!p.isDead()) {
			this.ListOfDeadPcs.remove(v);
			this.ListOfpcs.put(p, v);
			this.ListOfpcsI.put(v, p);
			this.selector.addPosition(v);
		}
		
		
	}
	 
	 
	 
	 
	 

        return this.ListOfpcs.isEmpty();
	}
	
	
	
	
	public boolean selectPC() {
		//this is where we will select which enemy we are doing a attack on
			double TimeTaken=0;
			if(this.Selected) {
				TimeTaken=Timer.getTIme()-time;	
			}else {
				Selected=true;
				this.time=Timer.getTIme();
			}
		 int up=input.getStateofButton(GLFW_KEY_UP),down=input.getStateofButton(GLFW_KEY_DOWN)
					,left=input.getStateofButton(GLFW_KEY_LEFT),right=input.getStateofButton(GLFW_KEY_RIGHT)
							,Back=input.getStateofButton(GLFW_KEY_BACKSPACE),Select=input.getStateofButton(GLFW_KEY_ENTER);
				    
			if(TimeTaken>=.1) {			
			if(Select==1) {
			
				if(!this.ListOfpcs.isEmpty()) {
				
				////BattleSystem.UseAttack(Start.currentlyUsedMove, Start.p, this.ListOfEnemiesI.get(selector.getCurrentPosition()));
				return true;
				}else {
					
					Start.source.play(Start.NO);
				}
				
				}
		   Vector2f oldPosition=selector.getCurrentPosition();
		 
					if(Back==1) {
						Start.source.play(Start.Back);
						BattleSystem.setSelectingPC(false);
						BattleSystem.setMoveCalled(false); 
						BattleSystem.setItemUsed(false);
						this.Selected=false;
					}
					if(up==1) 
					   selector.moveUP();
					if(down==1)	
					     selector.moveDown();
					if(left==1)
						  selector.moveLeft();
					if(right==1)
						  selector.moveRight();
					
				if(this.selector.getCurrentPosition()!=oldPosition) {
					Start.source.play(Start.Move);
				}
					
					
		}
		return false;
		
		
		     
		
	}



public void draw(boolean selecting) {
	
		
		for(Entry<BattleEntity,Vector2f> entry :this.ListOfpcs.entrySet()) {
	           
	             BattleEntity p=entry.getKey();
	             Vector2f position=entry.getValue();
	             text.setZ(900);
		            p.setZ(800);
	      
	            
	           	    text.setString("SP: "+Math.round(p.getSp())+"/"+Math.round(p.getMaxsp()));
	            	p.drawSPBAR(new Vector2f(position.x,position.y+100), text);
	                text.setString("HP:"+Math.round(p.getHp())+"/"+Math.round(p.getMaxHP()));      	
	            if(this.ListOfpcsI.get(selector.getCurrentPosition())==p || !selecting) {
	           
	            	p.draw(position, text,true);
	            	
	       	 	 
}else  {
	            	 p.draw(position, text,Constants.SPRITE_NOT_SELECTED_COLOR,true);
	            
	            
	             }
	              
	        } 
		
	}
	
	
public BattleEntity[] getAlivePCs() {
	
	return this.ListOfpcsI.values().toArray(new BattleEntity[this.ListOfpcsI.size()]);
}
public BattleEntity[] getDeadPCs() {
	return this.ListOfDeadPcs.values().toArray(new BattleEntity[this.ListOfDeadPcs.size()]);
}

public BattleEntity getCurrentPC() {
	return this.ListOfpcsI.get(selector.getCurrentPosition());
}

public void ResetSelected() {
	this.Selected=false;
	
}
	
	
	
}