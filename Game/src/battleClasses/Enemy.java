package battleClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Moves;
import Items.Inventory;
import Items.Item;
import Items.Items;
import Scripter.Proccesor;
import ScripterCommands.DrawModel;
import ScripterCommands.DrawString;
import enemyAI.EnemyAI;
import enemyAI.EnemyAIComponent;
import gameEngine.RenderEntity;
import gameEngine.Start;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;
import textrendering.TextBuilder;

public class Enemy extends BattleEntity{


	private EnemyAI enemyAI;


	public Enemy(Vector2f sizeForBar,Model model,Texture texture,float scale,String name,float atk, float def, float hp,float sp,float speed, Moves[] moves,Inventory inventory,EnemyAI enemyAI) {
		super(name,model,texture,scale, sizeForBar,atk, def, hp,sp,speed, moves,inventory);
		
	    this.texture=texture;
	 
	    super.isEnemy=true;
	    this.enemyAI=enemyAI;
	    
	    
	    
	    
	    
	}
	

	
	
	

	


	private ArrayList<Moves> makeListOfAffourdableMoves() {
		
		ArrayList<Moves> returnList=new ArrayList<Moves>();
		
		for(int i=0;i<this.movelist.size();i++) {
			
			
			Moves move=this.movelist.get(i);
			if(move.getCost()<=this.sp) {
				returnList.add(move);
				
			}
			
		}
	
			
		  
	if(!returnList.isEmpty()) {
		  Comparator<Moves> byCost = Comparator.comparing(Moves::getCost);
		  Collections.sort(returnList, byCost);    
			
	}
		
		
		return returnList;
		
		
		
	}
	

	
	//this is the generic ai for enemies later we will make different kinds of enemies that will extend this class and we will override the take turn method
	
	public boolean takeTurn(BattleEnemyField enemyField,BattlePlayerField players) {
		//this is going to be the method that is called when it is this enemy's turn it will do all the calculations to find out whether to attack or use a item
		
	
		
		///first we make a list of usable moves
		ArrayList<Moves> moves=makeListOfAffourdableMoves();// this will make a list moves the current enemy can afford
        
		boolean actionTaken= this.enemyAI.TakeAction(this, enemyField, players,moves.toArray(new Moves[moves.size()]));
	    if(!actionTaken) {
           Start.DebugPrint("no Action Taken");	    
	    	
	    }
	 return actionTaken;
	
	
		
	}
	
    
	

//	protected void useMove(Moves move,BattleEntity player) {
//		
//		
//		
//	
//			
//			 if(!move.isSingleHeal()) {
//				   
//				  float Damage=BattleFormulas.CalculateDamage(this, player,move.getDamage());
//				   
//						
//				
//			        player.decreseHp(Damage);
//			   
//			       
//			        Proccesor.addComandtoQueue(new DrawString(name+" used "+move.getName(),new Vector2f(-100,40),.5f,true,.5f));			
//			        Proccesor.addComandtoQueue(new DrawString(Math.round(Damage)+"!",new Vector2f(100,40),.5f,true,.5f));			
//				   
//			   }else {
//				
//				   
//				   float health=BattleFormulas.CalcaulateHealth(this, move.getDamage());
//				
//				IncreseHp(health);   
//				   Proccesor.addComandtoQueue(new DrawString(name+" used "+move.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
//			        Proccesor.addComandtoQueue(new DrawString("healed "+Math.round(health)+"!",new Vector2f(100,40),.5f,true,.5f));			
//				   
//			   }
//			
//			
//			
//			
//			
//			
//			
//			
//			
//		Start.DebugPrint("enemy used move "+move.getName());	
//			
//			
//		
//	}
	
	
	public float getScale() {
		return scale;
	}





	public void setScale(float scale) {
		this.scale = scale;
	}




	public Texture getTexture() {
		return texture;
	}

	

	
}
