package customExceptions;

public class EnemyHasToManyMoves extends Exception {

	/**
	 * 
	 */
	
public EnemyHasToManyMoves(String enemyName) {
		
		super("enemy:"+enemyName+" has to many moves(max 4)");
	}
	
	
	
	private static final long serialVersionUID = 1L;

}
