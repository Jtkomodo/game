package battleClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.joml.Vector2f;

import Data.Moves;
import Scripter.Proccesor;
import ScripterCommands.DrawString;
import gameEngine.Start;

public class BattleFormulas {

	//TODO we will store every formula for the battle system here 
	
	
	
	public static float CalculateDamage(BattleEntity player,Enemy enemy,int gradeOfMove,float baseDamage) {
		
	/*this is where we will put the code for the damage formula for player vs enemy 
	 * if the move used was one with a Timed button then pass in the grade that was achieved  
	 */	
		float multiplier = ((player.getAtk()-enemy.getDef())/100)+1;
		
		if(gradeOfMove == 1)
			multiplier*=0.5;
		else if(gradeOfMove == 3)
			multiplier*=2;
		
		
		
		return multiplier*baseDamage*getLuckyMultiplier();
		
	}
	
	
	public static float CalculateHeath(BattleEntity player,int gradeOfMove,float baseHealth) {
		/*this is the calculation for a healing timed combo move*/
		
	 float multiplier=1;
		
	 if(gradeOfMove == 1)
			multiplier=0.5f;
		else if(gradeOfMove == 3)
			multiplier=2;
		
		
		
		return baseHealth*multiplier;
		
		
	}
	
	public static float CalcaulateHealth(BattleEntity e,float baseHealth) {
		
//this is enemies calculate Health formula
	    Random r=new Random();
	    float random=r.nextFloat();
	int grade=2;
	    
	    if(random<=.10) {
	    	grade=3;
	    }
	    
	    else {
	    	random=r.nextFloat();
	    	if(random<=.15) {
	    	grade=1;
	    	}
	    }
	    return CalculateHeath(e,grade,baseHealth);
		
		
	}
	
	
	
	
	
	public static float CalculateDamage(Enemy enemy,BattleEntity player,float baseDamage) {
		
		    Random r=new Random();
		    float random=r.nextFloat();
		int grade=2;
		    
		    if(random<=.10) {
		    	grade=3;
		    }
		    
		    else {
		    	random=r.nextFloat();
		    	if(random<=.15) {
		    	grade=1;
		    	}
		    }
		    
		    
		    
			float multiplier = ((enemy.getAtk()-player.getDef())/100)+1;
			
			if(grade == 1)
				multiplier*=0.5;
			else if(grade == 3)
				multiplier*=2;
			
			
			
			return multiplier*baseDamage*getLuckyMultiplier();
		
			
			
			
		}
	
 private static float getLuckyMultiplier() {
	 
	 /*this is the formula for calculating whether we got a luck hit for now it's just a 30% chance*/
	 Random r=new Random();
	 
	  float random =r.nextFloat();
	 float result;
	 Start.DebugPrint("random "+random);
	 if(random<=.3) {
		 result=1.5f;
		 Start.DebugPrint("LUCKY HIT");
		 Proccesor.addComandtoItorator(new DrawString("Lucky!",new Vector2f(100,80),.5f,true,.5));
		 
	 }else {
		 result=1;
	 }
	 
	 
	 return result;
	 
 }
	
	
		
  public static LinkedList<BattleEntity> calcuateTurnOrder(BattleEntity players[],Enemy[] enemies) {
	  
	  //this calculates which entity goes first based on it's stats
	  
	  Random r=new Random();
	  int adder=0;	  
	  BattleEntity entity=null;
	  float highestSpeed=0;
	  
	  HashMap<BattleEntity,Float> map=new HashMap<BattleEntity,Float>();
	  //load map with entities and speeds
	  for(int i=0;i<players.length;i++) {
		  float random=r.nextFloat();
		  map.put(players[i],players[i].getSpeed()+random);
		  
		  
		  
	  }
	  
      for(int i=0;i<enemies.length;i++) {
    	  float random=r.nextFloat();
		  map.put(enemies[i],enemies[i].getSpeed()+random);
	  
	  }
	  
	  
	  //sort map by speed
	  
      
    	  
      Comparator<Entry<BattleEntity,Float>> valueComparator = new Comparator<Entry<BattleEntity,Float>>() {
          
          @Override
          public int compare(Entry<BattleEntity, Float> e1, Entry<BattleEntity, Float> e2) {
              Float v1 = e1.getValue();
              Float v2 = e2.getValue();
              return v1.compareTo(v2);
          }
      };
      	  
      List<Entry<BattleEntity, Float>> listOfEntries = new LinkedList<Entry<BattleEntity,Float>>(map.entrySet());
      Collections.sort(listOfEntries, valueComparator);
      LinkedList<BattleEntity> sortedByValue = new LinkedList<BattleEntity>();
      for(Entry<BattleEntity,Float> entry : listOfEntries){
          sortedByValue.add(entry.getKey());
      }
      return sortedByValue;
}

  
	  
	  
	  
	  
	  
	 
	  
  
	
	
	
}
