package battleClasses;

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
		
		return multiplier*baseDamage;
		
	}
	
	
	
	public static float CalculateDamage(Enemy enemy,BattleEntity player,float baseDamage) {
		
		//this is where we will put the code for the damage formula for enemy vs player
			
			
			
			return 0;
			
		}
		
  public static BattleEntity calcuateWhoGoesFirst(BattleEntity entities[]) {
	  
	  //this calculates which entity goes first based on it's stats
	  
	  
	  
	  return null;
	  
  }
	
	
	
}
