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
import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;
import textrendering.TextBuilder;

public class Enemy extends BattleEntity{

	private String name;
	private Model model;
    private Texture texture;  
    private float scale;
    private float z=1000;


	public Enemy(Vector2f sizeForBar,Model model,Texture texture,float scale,String name,float atk, float def, float hp,float sp,float speed, Moves[] moves,Inventory inventory) {
		super(sizeForBar,atk, def, hp,sp,speed, moves,inventory);
		this.name=name;
	    this.model=model;
	    this.texture=texture;
	    this.scale=scale;
	    super.isEnemy=true;
	}

	public String getName() {
		return name;
	}

	public void draw(Vector2f position,TextBuilder text) {
		
		MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z), 0, scale, texture));
		this.hpbar.draw(position.add(0,80,new Vector2f()),text);
		
		
		
	}

   public void draw(Vector2f position,TextBuilder text,Vector4f color) {
		
	    MainRenderHandler.addEntity(new Entity(model, new Vector3f(position,z), 0, scale, texture,color));
		this.hpbar.draw(position.add(0,80,new Vector2f()),text);
		
		
		
	}


	private ArrayList<Moves> makeListOfUseableMoves() {
		
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
	
	public void takeTurn(BattleEnemyField enemyField,BattleEntity player) {
		//this is going to be the method that is called when it is this enemy's turn it will do all the calculations to find out whether to attack or use a item
		
	
		
		///first we make a list of usable moves
	
		
		
	ArrayList<Moves> moves=makeListOfUseableMoves();

	
	
	
	if(!moves.isEmpty()) {
	boolean actionTaken=false;
	
	
	//next we check enemy's hp
	
    actionTaken=SelfHealthCheck(moves,player,.25f,.20f);
	
  	
	
	
	if(!actionTaken) 
	 actionTaken=SelfCheckSp(.15f);
	
	if(!actionTaken) 
		actionTaken=UseMostPowerfulOrKillingMove(moves, player);
	
		
		
	
	
	//if somehow there was no action taken randomly pick the next move
	if(!actionTaken) {
		
		Random r=new Random();
		int random=r.nextInt(moves.size()-1);
		
		actionTaken=useMove(moves.get(random),player);
		Start.DebugPrint("used random");
	}
	}else {
		
	    Proccesor.addComandtoQueue(new DrawString(name+" couldn't "+"act",new Vector2f(-100,40),.5f,true,.5f));
		
	}
		
		
	}
	
    
	

	protected boolean useMove(Moves move,BattleEntity player) {
		
		boolean used=useMove(move);
		
		if(used) {
			
			 if(!move.isHeal()) {
				   
				  float Damage=BattleFormulas.CalculateDamage(this, player,move.getDamage());
				   
						
				
			        player.decreseHp(Damage);
			   
			       
			        Proccesor.addComandtoQueue(new DrawString(name+" used "+move.getName(),new Vector2f(-100,40),.5f,true,.5f));			
			        Proccesor.addComandtoQueue(new DrawString(Math.round(Damage)+"!",new Vector2f(100,40),.5f,true,.5f));			
				   
			   }else {
				
				   
				   float health=BattleFormulas.CalcaulateHealth(this, move.getDamage());
				
				IncreseHp(health);   
				   Proccesor.addComandtoQueue(new DrawString(name+" used "+move.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
			        Proccesor.addComandtoQueue(new DrawString("healed "+Math.round(health)+"!",new Vector2f(100,40),.5f,true,.5f));			
				   
			   }
			
			
			
			
			
			
			
			
			
		Start.DebugPrint("enemy used move "+move.getName());	
			
			
		}
		
		
		
		
		
		
		return used;
	}
	
	
	
	
	
	protected boolean SelfHealthCheck(ArrayList<Moves> moves,BattleEntity player,float percentage,float percentageChanceToUseKillerMove) {
		
		
		
		
		Items[] items=this.inventory.getItems();
		for(int i=0;i<items.length;i++) {
		Start.DebugPrint(items[i].name());
	}
		
		
		
		boolean actionTaken=false;
		//if HP is less than value----------------------------------------------------------------------------------------------------------
		if(this.hp<(this.maxHP*percentage)) {
			//heal up
			
			//we first check if we have a item that heals
			boolean healingItemFound=false;
			
			Items itemWithHighestHeal=null;
			ArrayList<Items> healingItems=new ArrayList<Items>();
			if(!this.inventory.isEmpty()) {
			for(int i=0;i<this.inventory.getItems().length;i++) {
				
				Items item=this.inventory.getItems()[i];
			  	if(item.Item.isHealing()) {
			  		
			  		healingItems.add(item);
			  		
			  		
			  		//we find the highest healing item
			  		
			  		if(itemWithHighestHeal!=null) {
			  			
			  		  if(item.Item.getValue()>itemWithHighestHeal.Item.getValue()) {
			  			  
			  			  itemWithHighestHeal=item;
			  		  }
			  			
			  			
			  		}else {
			  			itemWithHighestHeal=item;
			  		}
			  		
			  		
			  		
			  		
			  		
			  	}
			
			
			
			}
			healingItemFound=!healingItems.isEmpty();
			}
			
			
			//now we check if there is a healing move
		    boolean healingMoveFound=false;
		    Moves HighestHealingMove=null;
		    Moves KillerMove=null;
		    
		    ArrayList<Moves> healingMoves=new ArrayList<Moves>();
		    
			for(int i=0;i<moves.size();i++) {
				
				Moves move=moves.get(i);
				if(move.isHeal()) {
					
					healingMoves.add(move);
					
				if(HighestHealingMove!=null) {
					
					if(move.getDamage()>HighestHealingMove.getDamage()) {
						HighestHealingMove=move;
						
					}
					
				}else {
					HighestHealingMove=move;
					
				}
				
				
				
				}else {
					if(move.getDamage()>=player.getHp()) {
						KillerMove=move;
						
					}
					
					
				}
				
				
				
				
				
				
				
			}
			healingMoveFound=!healingMoves.isEmpty();

			if(healingMoveFound || healingItemFound) {
				Random r=new Random();
				float random=r.nextFloat();
				
				if(KillerMove!=null) {
					
					if(random<percentageChanceToUseKillerMove) {
						actionTaken=useMove(KillerMove,player);
					}
					
					
					
				}
				
				
				
				
			if(!actionTaken) {
				
			if(healingMoveFound && healingItemFound) {
				float value1=itemWithHighestHeal.Item.getValue();
				float value2=HighestHealingMove.getDamage();
				

				if(value1>value2) {
					
					actionTaken=useItem(itemWithHighestHeal);
					 Proccesor.addComandtoQueue(new DrawString(name+" used "+itemWithHighestHeal.Item.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
				        Proccesor.addComandtoQueue(new DrawString("healed "+Math.round(itemWithHighestHeal.Item.getValue()),new Vector2f(100,40),.5f,true,.5f));			
					   
					
				}else if(value1<value2) {
					
				     
					actionTaken=useMove(HighestHealingMove,player);
				}else {
					
					 random=r.nextFloat();
					
					
					if(random<=.5) {
						
						actionTaken=useItem(itemWithHighestHeal);
						 Proccesor.addComandtoQueue(new DrawString(name+" used "+itemWithHighestHeal.Item.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
					        Proccesor.addComandtoQueue(new DrawString("healed "+Math.round(itemWithHighestHeal.Item.getValue()),new Vector2f(100,40),.5f,true,.5f));			
						   
						
					}else {
						actionTaken=useMove(HighestHealingMove,player);
					}
					
					
				}
				
				
			}else if(healingMoveFound){
				
				actionTaken=useMove(HighestHealingMove,player);
				
				
			}else if(healingItemFound){
				actionTaken=useItem(itemWithHighestHeal);
				 Proccesor.addComandtoQueue(new DrawString(name+" used "+itemWithHighestHeal.Item.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
			        Proccesor.addComandtoQueue(new DrawString("healed "+Math.round(itemWithHighestHeal.Item.getValue()),new Vector2f(100,40),.5f,true,.5f));			
				   
				
				
			}
			}
			}
			
			Start.DebugPrint("actiontaken= "+actionTaken+"("+healingMoveFound+","+healingItemFound+")");		
		}
		//-------------------------------------------------------------------------------------------------------------------------
		
		
		
		return actionTaken;
		
	}
	
	
	
	
	protected boolean SelfCheckSp(float percantageChanceToUse) {
		
		
//look for lowest and highest restoring sp items______________________________________________________________________
		boolean actionTaken=false;
	  	boolean SPRestoringItemFound=false;
		Items HighestRestoring=null;
		Items LowestRestoring=null;
		ArrayList<Items> spRestoreItems=new ArrayList<Items>();
		if(!this.inventory.isEmpty()) {
		for(int i=0;i<this.inventory.getItems().length;i++) {
			
			Items item=this.inventory.getItems()[i];
		  	if(item.Item.isRestorSP()) {
		  		
		  		spRestoreItems.add(item);
		  		
		  		
		  		//we find the highest restoring item
		  		
		  	
		  		if(HighestRestoring!=null) {
		  		
		  			if(item.Item.getValue()>HighestRestoring.Item.getValue())
		  			HighestRestoring=item;
		  		
		  		
		  		
		  		}else {
		  			
		  			HighestRestoring=item;
		  		}
		  		
		  		
		  		if(LowestRestoring!=null) {
			  		
		  			if(item.Item.getValue()<LowestRestoring.Item.getValue())
		  			HighestRestoring=item;
		  		
		  		
		  		
		  		}else {
		  			
		  			LowestRestoring=item;
		  		}
		  		
		  		
		  		
		  	}
		
		
		
		}

		SPRestoringItemFound=!spRestoreItems.isEmpty();
		
		
	//_____________________________________________________________________________________________________________________
	
		 //if sp <Lowest Item cost------------------------------------------------------------------------------------------------
if(SPRestoringItemFound) {
	Start.DebugPrint("sp="+sp+"spcost="+LowestRestoring.Item.getValue());
		if(sp<(LowestRestoring.Item.getValue())) {
			
	
	
		
		
	
		
		
	
		if(HighestRestoring!=null) {
			actionTaken=useItem(HighestRestoring);
			 Proccesor.addComandtoQueue(new DrawString(name+" used "+HighestRestoring.Item.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
		        Proccesor.addComandtoQueue(new DrawString("restored "+Math.round(HighestRestoring.Item.getValue()),new Vector2f(100,40),.5f,true,.5f));			
			   
			
		}
		
		
		}else if(sp<HighestRestoring.Item.getValue()) {
			Random r=new Random();
			float random=r.nextFloat();
			if(random<=percantageChanceToUse) {
				actionTaken=useItem(LowestRestoring);
				 Proccesor.addComandtoQueue(new DrawString(name+" used "+LowestRestoring.Item.getName(),new Vector2f(-100,40),.5f,true,1.5f));			
			        Proccesor.addComandtoQueue(new DrawString("restored "+Math.round(LowestRestoring.Item.getValue()),new Vector2f(100,40),.5f,true,.5f));			
				   
			}
			
			
			
		}
		
	}
	}
		
		
		
		
		
		
		
		
		
		return actionTaken;
		
		
		
		
	}
	
	
	protected boolean UseMostPowerfulOrKillingMove(ArrayList<Moves> moves,BattleEntity player) {
		boolean actionTaken=false;
		
		
		
		
		
		
		
		
		Moves MostPowerfullAttack=null;
		Moves LeastCostlyMoveThatcouldKill=null;
		boolean isALeastCostly=false;
		boolean isADamage=false;
		float MostPowerfullDamge=0;
		float leastCostly=moves.get(moves.size()-1).getCost();
		float playersHP=player.getHp();
		
		//find the most powerful attack and the least costly that could kill the player
		for(int i=moves.size()-1;i>=0;i--) {
			Moves move=moves.get(i);
			
			//find most powerful attack--------------------
			if(!move.isHeal()) {
				isADamage=true;
			if(MostPowerfullAttack!=null) {
				
				if(move.getDamage()>MostPowerfullDamge) {
					MostPowerfullAttack=move;
				    MostPowerfullDamge=move.getDamage();
				}
				
			}else {
				MostPowerfullAttack=move;
			    MostPowerfullDamge=move.getDamage();
			}
			//--------------------------------------------
			
			//find the least costly that could kill the player
			
			
				if(move.getDamage()>=playersHP && move.getCost()<=leastCostly) {
					LeastCostlyMoveThatcouldKill=move;
					leastCostly=move.getCost();
					
				}		
					
				}
				
				
				
			
			}
		isALeastCostly=!(LeastCostlyMoveThatcouldKill==null);
		
		//there is a least costly move that could kill use that one
		if(isADamage) {
			
		if(isALeastCostly) {
			
			actionTaken=useMove(LeastCostlyMoveThatcouldKill,player);
			
			
		}
		//Otherwise use the most powerful move
		else {
			
			actionTaken=useMove(MostPowerfullAttack,player);
		}
		
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return actionTaken;
		
	}
	
	
	public Model getModel() {
		return model;
	}

	public float getScale() {
		return scale;
	}





	public void setScale(float scale) {
		this.scale = scale;
	}




	public Texture getTexture() {
		return texture;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}


	
}
