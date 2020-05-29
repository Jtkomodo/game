package customExceptions;



public class EnemyStatsNotCorrectFormat extends Exception {

	
	
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnemyStatsNotCorrectFormat(String enemyName) {
		
		super("enemy:"+enemyName+" stats not defined correctly there are 5 stats");
	}
	
}
