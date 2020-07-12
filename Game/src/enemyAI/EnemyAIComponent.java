package enemyAI;

public abstract class EnemyAIComponent {

	
	
	private boolean isItemComponent;
	private int Priorety;




	public EnemyAIComponent(boolean isItemComponent,int Priorety) {
		this.isItemComponent=isItemComponent;
	}
	
	
	
	
	public int getPriorety() {
		return Priorety;
	}




	public boolean isItemComponent() {
		return isItemComponent;
	}


}
