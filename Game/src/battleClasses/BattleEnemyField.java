package battleClasses;
import java.util.ArrayList;

import org.joml.Vector2f;

import gameEngine.Render;

public class BattleEnemyField {

	private final Vector2f Position1=new Vector2f(140,-20),Position2=new Vector2f(202,-88),Position3=new Vector2f(202,10),Position4=new Vector2f();
	private ArrayList<Enemy> ListOfEnemies = new ArrayList<Enemy>();
	
	
	
	public BattleEnemyField(Enemy[] enemies) {
		
		for(int i=0;(enemies.length<=4)? i<enemies.length:i<4;i++) {
			
			ListOfEnemies.add(enemies[i]);
		}
		
		
	}
	
	
	
	
	public void draw() {
	
		
		for(int i=this.ListOfEnemies.size()-1;i>=0;i--) {
			  Enemy e=this.ListOfEnemies.get(i);
		    switch(i) {
		    
		  
		    case 0:
		    	Render.draw(e.getModel(),this.Position1,0, e.getScale(),e.getTexture());
		    
		    break;
		    
		    case 1:
		    	Render.draw(e.getModel(),this.Position2,0, e.getScale()*1.05f,e.getTexture());
		    
		    break;
		    
		    
		    case 2:
		    	Render.draw(e.getModel(),this.Position3,0, e.getScale()*.9f,e.getTexture());
		    
		    break;
		    
		    case 3:
		    	Render.draw(e.getModel(),this.Position4,0, e.getScale(),e.getTexture());
		    
		    break;
		    }
			
			
			
			
		}
		
		
		
		
	}
	
	
	public boolean addEnemy(Enemy e) {
		if(this.ListOfEnemies.size()<=4) {
			return this.ListOfEnemies.add(e);
		}
		else {
			return false;
		}
		
	}
	
	public void removeEnemy(Enemy e) {
		this.ListOfEnemies.remove(e);
		
	}
	
	public void removeEnemy(int index) {
		if(index<this.ListOfEnemies.size()) {
			this.ListOfEnemies.remove(index);
		}
		
	}
	
	public void removeAllEnemies() {
		this.ListOfEnemies.clear();
	}
	public void addEnemies(Enemy[] enemies) {
	for(int i=0;(enemies.length<=4)? i<enemies.length:i<4;i++) {
			if(this.ListOfEnemies.size()<4) {
			ListOfEnemies.add(enemies[i]);}
			else {
				break;
			}
		}
		
	}
	
	public void ReplaceAll(Enemy[] enemies) {
		removeAllEnemies();
		addEnemies(enemies);
		
	}
	
	
	public Enemy getEnemy(int index) {
		if(index<this.ListOfEnemies.size()) {
			
			return this.ListOfEnemies.get(index);
		}
		else {
			return null;
		}
		
	}
	public Enemy[] getEnemies() {
		
		return (Enemy[]) this.ListOfEnemies.toArray();
	}
	
	
}
