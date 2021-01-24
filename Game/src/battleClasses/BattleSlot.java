package battleClasses;

import org.joml.Vector2f;

import textrendering.TextBuilder;
/**This reserves a spot in one of the fields({@link BattlePlayerField},{@link BattleEnemyField}
 * and places the {@link BattleEntity} in it. 
 * @author Jesse Talbot
 *
 */
public class BattleSlot {

	
	private Vector2f position;
	private BattleEntity entity;
	
	
	
	public BattleSlot(Vector2f position,BattleEntity entity) {
		
		this.position=position;
		this.entity=entity;
		
	}
	
	public void draw(TextBuilder text) {
		
			entity.draw(position,text);
			
		
	     
		
	}
	public boolean isDead() {
		return entity.isDead;
	}
   	
    public boolean isEnemy() {
    	return entity.isEnemy;
    }


	public Vector2f getPosition() {
		return new Vector2f(position);
	}
   

	public BattleEntity getEntity() {
		return entity;
	}
	
}
