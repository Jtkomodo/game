package battleClasses;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedList;

import org.joml.Vector2f;

import Data.Constants;
import gameEngine.Start;
import textrendering.TextBuilder;

public class BattlePlayerField {
    
	private final Vector2f Position1=new Vector2f(-90,40),Position2=new Vector2f(-90,-20);
	private LinkedList<BattleEntity> listOfALLPcs=new LinkedList<BattleEntity>(); 
	private LinkedList<BattleEntity> listOfAlivePcs=new LinkedList<BattleEntity>(); 
	private BattleEntity CurrentPC1,CurrentPC2,CurrentlyUsingPC;
	private boolean hasPC1=false,hasPC2=false;
	private TextBuilder text=new TextBuilder(Start.aakar);
	private BattleEntity CurrentPC;
	
	public BattlePlayerField(BattleEntity[] pcs,BattleEntity currentpc1,BattleEntity currentpc2) {
	   
		for(int i=0;i<pcs.length;i++) {
			BattleEntity pc=pcs[i];
			listOfALLPcs.add(pc);
			if(!pc.isDead) {
				listOfAlivePcs.add(pc);				
			}
		}
		if(currentpc2!=null) {
			hasPC2=true;
			CurrentPC2=currentpc2;
			this.CurrentPC=currentpc2;
		}
		if(currentpc1!=null) {
			hasPC1=true;
			CurrentPC1=currentpc1;
			this.CurrentPC=currentpc1;
					
		}
		
	
	}
	
	
	

	




	public BattlePlayerField(BattleEntity[] pcs,BattleEntity currentpc1) {
	   
		   
				for(int i=0;i<pcs.length;i++) {
					BattleEntity pc=pcs[i];
					listOfALLPcs.add(pc);
					if(!pc.isDead) {
						listOfAlivePcs.add(pc);				
					}
				}
				if(currentpc1!=null) {
					hasPC1=true;
					CurrentPC1=currentpc1;
					this.CurrentPC=currentpc1;
				}
				
		
	}
	
	
	public void switchPC1(BattleEntity newPC1) {
		if(!this.listOfALLPcs.contains(newPC1)) {
			this.listOfALLPcs.add(newPC1);
			
		}
		
		if(!newPC1.isDead) {
		   this.CurrentPC1=newPC1;
		   
		   if(!this.listOfAlivePcs.contains(newPC1)) {
			   
			   this.listOfAlivePcs.add(newPC1);
		   }
		   
		}
		
		
	}
	
    public void switchPC2(BattleEntity newPC2) {
    	if(!this.listOfALLPcs.contains(newPC2)) {
			this.listOfALLPcs.add(newPC2);
			
		}
		
		if(!newPC2.isDead) {
		   this.CurrentPC2=newPC2;
   if(!this.listOfAlivePcs.contains(newPC2)) {
			   
			   this.listOfAlivePcs.add(newPC2);
		   }
		}
		
		
		
	}
	
	public void SelectCurrentPC(BattleEntity p) {
		//this is for selecting PC1 or PC2
		if(p.equals(CurrentPC1) || p.equals(CurrentPC2)) {
		CurrentlyUsingPC=p;
		}
	}
	
	
	public void draw(boolean selecting) {
		
		//draw pc1
		if(this.CurrentPC1!=null && !this.CurrentPC1.isDead()) {
			 text.setString("HP:"+Math.round(this.CurrentPC1.getHp())+"/"+Math.round(this.CurrentPC1.getMaxHP()));
		  
			 if(this.CurrentPC==CurrentPC1 || !selecting) {
			   text.setZ(900);
	            this.CurrentPC1.setZ(800);
	            this.CurrentPC1.draw(Position1, text);
		   }else {
			   this.CurrentPC1.draw(Position1, text,Constants.SPRITE_NOT_SELECTED_COLOR);
		   }
		}
		//draw pc2
		if(this.CurrentPC2!=null && !this.CurrentPC2.isDead()) {
			 text.setString("HP:"+Math.round(this.CurrentPC2.getHp())+"/"+Math.round(this.CurrentPC2.getMaxHP()));
		  
			 if(this.CurrentPC==CurrentPC2 || !selecting) {
			   text.setZ(900);
	            this.CurrentPC2.setZ(800);
	            this.CurrentPC2.draw(Position1, text);
		   }else {
			   this.CurrentPC2.draw(Position1, text,Constants.SPRITE_NOT_SELECTED_COLOR);
		   }
		}
	}
	
	public boolean update() {//returns if all pcs are dead 
		//returns
		
		
		
		
		
		return false;
	}
	
	
	
	
	public LinkedList<BattleEntity> getListOfAlivePcs() {
		return listOfAlivePcs;
	}




	public void setListOfAlivePcs(LinkedList<BattleEntity> listOfAlivePcs) {
		this.listOfAlivePcs = listOfAlivePcs;
	}




	public BattleEntity getCurrentPC1() {
		return CurrentPC1;
	}




	public BattleEntity getCurrentPC2() {
		return CurrentPC2;
	}
	
	
	
	
	
	
}
