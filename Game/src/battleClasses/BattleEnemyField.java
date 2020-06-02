package battleClasses;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.BattleSystem;
import gameEngine.Start;
import gameEngine.Timer;

import input.GetInput;
import input.InputHandler;
import rendering.Render;
import textrendering.TextBuilder;

public class BattleEnemyField {

	private final Vector2f Position1=new Vector2f(140,-20),Position2=new Vector2f(202,-88),Position3=new Vector2f(202,10),Position4=new Vector2f(260,-20);
	private HashMap<Enemy,Vector2f> ListOfEnemies=new HashMap<Enemy,Vector2f>();
	private HashMap<Vector2f,Enemy> ListOfEnemiesI=new HashMap<Vector2f,Enemy>();
	private TextBuilder text=new TextBuilder(Start.aakar);
    private Vector2f currentPosition;
    private Enemy currentEnemy;
    private GetInput input=new GetInput();
	private boolean Selected=false;
	private double time;
	
	public BattleEnemyField(Enemy[] enemies) {
		
		for(int i=0;(enemies.length<=4)? i<enemies.length:i<4;i++) {
			Vector2f position=new Vector2f();
			switch(i) {
			
			case 0:
			     position=Position1;
			break;
			case 1:
			     position=Position2;
			break;
			case 2:
			     position=Position3;
			break;
			
			case 3:
			     position=Position4;
			break;
			
			
			
			}
			
		  ListOfEnemies.put(enemies[i],position);
		  ListOfEnemiesI.put(position,enemies[i]);
			
		}
	
	
		currentEnemy=enemies[0];
		this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);
		
	}
	
	public boolean updateField() {
		
		
	        //this will be what will check the enemy hp and remove enemies from list if 0
		    
		
		
		    //returns all enemies dead if the feild is empty
		     
		
		
		 Iterator<Map.Entry<Enemy,Vector2f>> itr = ListOfEnemies.entrySet().iterator(); 
	
		 while(itr.hasNext()) {
			 Map.Entry<Enemy, Vector2f> entry = itr.next();
             Enemy e=entry.getKey();
			 Vector2f v=entry.getValue();
			
             
             
             
			 
             
             if(e.isDead) {
            
				itr.remove();
			    this.ListOfEnemiesI.remove(v);
			}
			
			
		}

	        return this.ListOfEnemies.isEmpty();
	   
	}
	
	public void selectEnemy() {
		//this is where we will select which enemy we are doing a attack on
			double TimeTaken=0;
			if(this.Selected) {
				TimeTaken=Timer.getTIme()-time;	
			}else {
				Selected=true;
				this.time=Timer.getTIme();
			}
		 int up=input.getStateofButtonInstanced(GLFW_KEY_UP),down=input.getStateofButtonInstanced(GLFW_KEY_DOWN)
					,left=input.getStateofButtonInstanced(GLFW_KEY_LEFT),right=input.getStateofButtonInstanced(GLFW_KEY_RIGHT)
							,Back=input.getStateofButtonInstanced(GLFW_KEY_BACKSPACE),Select=input.getStateofButtonInstanced(GLFW_KEY_ENTER);
				    
			if(TimeTaken>=.1) {			
			if(Select==1) {
			
				
				Start.source.play(Start.Select);
				BattleSystem.UseAttack(Start.currentlyUsedMove, Start.p, this.currentEnemy);
				
			
				
				}
		      Vector2f oldPosition=this.currentPosition;
		 
					if(Back==1) {
						Start.source.play(Start.Back);
						 BattleSystem.setMoveCalled(false); 
						BattleSystem.setMoveInprogress(false);
						
					}
					if(up==1) 
					    GoUp();
					if(down==1)	
					     GoDown();
					if(left==1)
						  GoLeft();
					if(right==1)
						  GoRight();
					
				if(this.currentPosition!=oldPosition) {
					Start.source.play(Start.Move);
				}
					
					
		}
		
		
		
		     
		
	}
	
	
	public void setSelected(boolean selected) {
		Selected = selected;
	}

	public void reload(Enemy[] enemies) {
		this.ListOfEnemies.clear();
		this.ListOfEnemiesI.clear();
		for(int i=0;(enemies.length<=4)? i<enemies.length:i<4;i++) {
			Vector2f position=new Vector2f();
			switch(i) {
			
			case 0:
			     position=Position1;
			break;
			case 1:
			     position=Position2;
			break;
			case 2:
			     position=Position3;
			break;
			
			case 3:
			     position=Position4;
			break;
			
			
			
			}
			
		  ListOfEnemies.put(enemies[i],position);
		  ListOfEnemiesI.put(position,enemies[i]);
			
		}
		
		currentEnemy=enemies[0];
		this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);
		
	
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void GoRight() {
		
	
		
		float x=this.currentPosition.x;
	
		
		if(x==this.Position1.x) {
			
		
	
		if(this.ListOfEnemiesI.containsKey(Position4)) {
				this.currentEnemy=this.ListOfEnemiesI.get(Position4);	
		}	
		
		else if(this.ListOfEnemiesI.containsKey(Position2)) {
				this.currentEnemy=this.ListOfEnemiesI.get(Position2);
			
		}
			
		
		}else if(x==Position2.x || x==Position3.x) {
			
			if(this.ListOfEnemiesI.containsKey(Position4)) {
				this.currentEnemy=this.ListOfEnemiesI.get(Position4);
			}
           
			
		}
		
	   this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);	
		
		
		
		
	}

	private void GoLeft() {
	
		float x=this.currentPosition.x;
		if(this.ListOfEnemiesI.containsKey(Position1)) {
		this.currentEnemy=this.ListOfEnemiesI.get(Position1);
		}else if(x==Position4.x) { 
			
			if(this.ListOfEnemiesI.containsKey(Position2)) {
				this.currentEnemy=this.ListOfEnemiesI.get(Position2);
			}else if(this.ListOfEnemiesI.containsKey(Position3)) {
				this.currentEnemy=this.ListOfEnemiesI.get(Position3);
			}
		}
		
		this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);	
		
		
	}

	private void GoDown() {
		
		float y=this.currentPosition.y;
		int size= this.ListOfEnemies.size();
   if(this.ListOfEnemiesI.containsKey(Position2)) {
	   this.currentEnemy=this.ListOfEnemiesI.get(Position2);
		
		
	   
   }else if (y==Position3.y){
	   if(this.ListOfEnemiesI.containsKey(Position4)) {
			this.currentEnemy=this.ListOfEnemiesI.get(Position4);
	   }else if(this.ListOfEnemiesI.containsKey(Position1)) {
			this.currentEnemy=this.ListOfEnemiesI.get(Position1);
	   }
	   
   }
   this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);	
		
		
	}

	private void GoUp() {
		
		float y=this.currentPosition.y;
		
   if(this.ListOfEnemiesI.containsKey(Position3)) {
	   this.currentEnemy=this.ListOfEnemiesI.get(Position3);
		

   }else if(y==Position2.y) {
    if(this.ListOfEnemiesI.containsKey(Position1)) {
	   this.currentEnemy=this.ListOfEnemiesI.get(Position1);
   }else if(this.ListOfEnemies.containsKey(Position4)) {
	   this.currentEnemy=this.ListOfEnemiesI.get(Position4);
   }
   }
   
   
   
   
   
   this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);	
		
	}







	
	
	
	
	
	
	public void draw(boolean selecting) {
	
		
		for(Entry<Enemy,Vector2f> entry :this.ListOfEnemies.entrySet()) {
	           
	             Enemy e=entry.getKey();
	             Vector2f position=entry.getValue();
	             text.setString("HP:"+Math.round(e.getHp())+"/"+Math.round(e.getMaxHP()));
	            if(this.currentEnemy==e || !selecting) {
	            text.setZ(900);
	            e.setZ(800);
	            	e.draw(position, text);}else  {
	            	 e.draw(position, text,Constants.SPRITE_NOT_SELECTED_COLOR);
	             }
	              
	        } 
		
	}
	
	
	
	public Enemy getEnemy(int index) {
		
		ArrayList<Enemy> listTemp=new ArrayList<Enemy>(ListOfEnemies.keySet());
        if(index<listTemp.size() && index>=0) {
        	
        	
    		return listTemp.get(index);    	
        	
        }else {
        	return null;
        }
		
	
		
	}
	
	public void setCurrentEnemy(Enemy enemy) {
		if(this.ListOfEnemies.containsKey(enemy)) {
			this.currentEnemy=enemy;
			this.currentPosition=this.ListOfEnemies.get(enemy);
		}
		
	}
	public void setCurrentEnemy(int index) {
		ArrayList<Enemy> listTemp=new ArrayList<Enemy>(ListOfEnemies.keySet());
        if(index<listTemp.size() && index>=0) {
           	this.currentEnemy=listTemp.get(index);
        }
        this.currentPosition=this.ListOfEnemies.get(this.currentEnemy);
	}
	
	
	public Enemy getCurrentEnemy() {
		return this.currentEnemy;
	}
	
	public Enemy[] getEnemies() {
		
		ArrayList<Enemy> listTemp=new ArrayList<Enemy>(ListOfEnemies.keySet());
		return  listTemp.toArray(new Enemy[listTemp.size()]);
		
	}
	
   public Enemy[] getOtherEnemies(Enemy e) {
	   ArrayList<Enemy>  temp=new ArrayList<Enemy>(this.ListOfEnemies.keySet());
	   temp.remove(e);
	   return temp.toArray(new Enemy[temp.size()-1]);
	   
   }
	
	
	public int getAmountOfEnemies() {
		
		return this.ListOfEnemies.size();
	}
	

}
