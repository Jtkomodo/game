package battleClasses;

import java.util.Random;

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
	
	
	
	
	public static float CalculateDamage(Enemy enemy,BattleEntity player,float baseDamage) {
		
		//this is where we will put the code for the damage formula for enemy vs player
			
			
			
			return 0;
			
		}
	
 private static float getLuckyMultiplier() {
	 
	 /*this is the formula for calculating whether we got a luck hit for now it's just a 30% chance*/
	 Random r=new Random();
	 
	  float random =r.nextFloat();
	 float result;
	 Start.DebugPrint("random "+random);
	 if(random<=.30) {
		 result=1.5f;
		 Start.DebugPrint("LUCKY HIT");
		 
	 }else {
		 result=1;
	 }
	 
	 
	 return result;
	 
 }
	
	
		
  public static BattleEntity calcuateWhoGoesFirst(BattleEntity entities[]) {
	  
	  //this calculates which entity goes first based on it's stats
	  
	  
	  
	  return null;
	  
  }
	
	
	
}
