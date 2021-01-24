package battleClasses;


import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.ArrowKeySelecter;
import gameEngine.BattleSystem;
import gameEngine.Start;
import gameEngine.Texture;
import gameEngine.Timer;

import input.GetInput;
import input.InputHandler;
import rendering.Render;
import textrendering.TextBuilder;
/**
 *
 * 
 * @author Jesse Talbot
 *
 */
public class BattleEnemyField {

   
	private HashMap<Enemy,Vector2f> ListOfEnemies=new HashMap<Enemy,Vector2f>();
	private HashMap<Vector2f,Enemy> ListOfEnemiesI=new HashMap<Vector2f,Enemy>();
	private TextBuilder text=new TextBuilder(Start.aakar);
	private GetInput input=new GetInput();
	private boolean Selected=false;
	private double time;
	private ArrowKeySelecter selector;




	/**
	 * Creates a new field and places the enemies in the correct slots
	 * 
	 * @param enemies {@link BattleSlot}[] the slots to add to the field
	 */
	public BattleEnemyField(BattleSlot[] enemies) {

		for(int i=0;i<enemies.length;i++) {

			BattleSlot s=enemies[i];

			if(s.isEnemy()) {
				Enemy e=(Enemy)s.getEntity();
				Vector2f vector=s.getPosition();

				if(!this.ListOfEnemies.containsKey(e) && !this.ListOfEnemiesI.containsKey(vector)) {
					ListOfEnemies.put(e,vector);
					ListOfEnemiesI.put(vector,e);
				}


			}
		}

		this.selector=new ArrowKeySelecter(ListOfEnemiesI.keySet().toArray(new Vector2f[ListOfEnemiesI.size()]),true);

	}
	/**
	 * This loop though all the {@link Enemy Enemies} and will remove any  if {@linkplain Enemy#isDead()} returns false.
	 * If all the enemies are dead then this will return true;
	 * 
	 *
	 * @return returns a boolean that will tell us if all the enemies are dead 
	 */
	public boolean updateField() {


		//this will be what will check the enemy hp and remove enemies from list if 0



		//returns all enemies dead if the feild is empty



		Iterator<Map.Entry<Enemy,Vector2f>> itr = ListOfEnemies.entrySet().iterator(); 

		while(itr.hasNext()) {
			Map.Entry<Enemy, Vector2f> entry = itr.next();
			Enemy e=entry.getKey();
			Vector2f v=entry.getValue();






			if(e.isDead()) {

				itr.remove();
				this.ListOfEnemiesI.remove(v);
				this.selector.removePosition(v);

			}


		}



		return this.ListOfEnemies.isEmpty();

	}
	/**This method handles all the input for the enemy field selector.
	 * This is how we select a enemy to use something on.Use {@link #getCurrentEnemy()}
	 * to get the enemy selected if this returns true
	 * 
	 * 
	 * @return returns a boolean to tell us if a enemy was selected
	 */
	public boolean selectEnemy() {
		//this is where we will select which enemy we are doing a attack on
		double TimeTaken=0;
		if(this.Selected) {
			TimeTaken=Timer.getTIme()-time;	
		}else {
			Selected=true;
			this.time=Timer.getTIme();
		}


		int up=input.getStateofButton(GLFW_KEY_UP),down=input.getStateofButton(GLFW_KEY_DOWN)
				,left=input.getStateofButton(GLFW_KEY_LEFT),right=input.getStateofButton(GLFW_KEY_RIGHT)
				,Back=input.getStateofButton(GLFW_KEY_BACKSPACE),Select=input.getStateofButton(GLFW_KEY_ENTER);

		if(TimeTaken>=.1) {			
			if(Select==1) {
				if(!this.ListOfEnemies.isEmpty()) {

					//Start.source.play(Start.Select);
					////BattleSystem.UseAttack(Start.currentlyUsedMove, Start.p, this.ListOfEnemiesI.get(selector.getCurrentPosition()));
					return true;
				}else {
					Start.source.play(Start.NO);
				}

			}
			Vector2f oldPosition=selector.getCurrentPosition();

			if(Back==1) {
				Start.source.play(Start.Back);
				BattleSystem.setSelectingEnemy(false);
				BattleSystem.setMoveCalled(false); 
				this.Selected=false;
			}
			if(up==1) 
				selector.moveUP();
			if(down==1)	
				selector.moveDown();
			if(left==1)
				selector.moveLeft();
			if(right==1)
				selector.moveRight();

			if(this.selector.getCurrentPosition()!=oldPosition) {
				Start.source.play(Start.Move);
			}


		}
		return false;




	}

   /**
    *deletes the old field and creates a new one with the
    *new slots. 
    *
   * @param enemies {@link BattleSlot}[] the slots to add to the field
    */
	public void reload(BattleSlot[] enemies) {
		this.ListOfEnemies.clear();
		this.ListOfEnemiesI.clear();

		for(int i=0;i<enemies.length;i++) {

			BattleSlot s=enemies[i];

			if(s.isEnemy()) {
				Enemy e=(Enemy)s.getEntity();
				Vector2f vector=s.getPosition();

				if(!this.ListOfEnemies.containsKey(e)&& !this.ListOfEnemiesI.containsKey(vector)) {
					ListOfEnemies.put(e,vector);
					ListOfEnemiesI.put(vector,e);
				}


			}
		}

		this.selector.relpaceAllPositions(ListOfEnemiesI.keySet().toArray(new Vector2f[ListOfEnemiesI.size()]));




	}












   /**draws the field to the screen
    * 
    * @param selecting boolean to let us know if we need to draw in the selecting mode
    */

	public void draw(boolean selecting) {


		for(Entry<Enemy,Vector2f> entry :this.ListOfEnemies.entrySet()) {

			Enemy e=entry.getKey();
			Vector2f position=entry.getValue();
			text.setZ(900);
			e.setZ(800);
			text.setString("HP:"+Math.round(e.getHp())+"/"+Math.round(e.getMaxHP()));
			if(this.ListOfEnemiesI.get(selector.getCurrentPosition())==e || !selecting) {

				e.draw(position, text);}else  {
					e.draw(position, text,Constants.SPRITE_NOT_SELECTED_COLOR);
				}

		} 

	}


   /**Gets the {@link Enemy} in the list at the specified index
    * 
    * @param index index in list
    * @return the enemy at that index or null if none was found
    */
	public Enemy getEnemy(int index) {

		LinkedList<Enemy> listTemp=new LinkedList<Enemy>(ListOfEnemies.keySet());
		if(index<listTemp.size() && index>=0) {


			return listTemp.get(index);    	

		}else {
			return null;
		}



	}
   /**
    * sets the currentEnemy to the specified {@link Enemy} if it is in the field
    * @param enemy the enemy wanting to switch to.
    */
	public void setCurrentEnemy(Enemy enemy) {
		if(this.ListOfEnemies.containsKey(enemy)) {

			selector.setCurrentPosition(this.ListOfEnemies.get(enemy));
		}

	}


   /**
    * returns the enemy that is the current enemy this means the one that the {@link ArrowKeySelecter selector} has selected
    * @return the currrentEnemy
    */
	public Enemy getCurrentEnemy() {
		return this.ListOfEnemiesI.get(selector.getCurrentPosition());
	}

	/**
	 * returns the list of enemies as a array
	 * @return {@link Enemy}[] the lit of enemies as a array
	 */
	public Enemy[] getEnemies() {

		LinkedList<Enemy> listTemp=new LinkedList<Enemy>(ListOfEnemies.keySet());
		return  listTemp.toArray(new Enemy[listTemp.size()]);

	}
    /**Gets the all the enemies that are not the specified one as a array
     * 
     * @param e the specified {@link Enemy}
     * @return a array containing all the other enemies
     */
	public Enemy[] getOtherEnemies(Enemy e) {
		LinkedList<Enemy>  temp=new LinkedList<Enemy>(this.ListOfEnemies.keySet());
		temp.remove(e);
		return temp.toArray(new Enemy[temp.size()-1]);

	}

   /**
    * gets the amount of enemies on the field
    * @return the amount of enemies
    */
	public int getAmountOfEnemies() {

		return this.ListOfEnemies.size();
	}
    /**
     * sets selected to false
     */
	public void ResetSelected() {
		this.Selected=false;

	}


}
