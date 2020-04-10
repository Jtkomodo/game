package rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

import org.joml.Vector2f;
import org.joml.Vector3f;
import gameEngine.Start;
import Data.Constants;
import Data.Moves;
import battleClasses.Enemy;
import gameEngine.Entity;

public class MainRenderHandler {

	
	
	private static ArrayList<Entity> entities=new ArrayList<Entity>();
    
	
	public static void addToBatchedRender() {
		
	    Iterator<Entity> i=entities.iterator();	
		
	
		while(i.hasNext()) {
			
			Entity e=i.next();
			Vector3f p= e.getPosition();
			if(e.isMirror()) {
				MainBatchRender.Mirror();
			}
			if(e.isHasColor()) {
				
			if(!e.isHasNonSqaureSize()) {	
			MainBatchRender.addModel(e.getModel(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getSize(),e.getTexture(),e.getColor(),e.getUIPojeection());
			}else {
				MainBatchRender.addModel(e.getModel(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getNonSquareSize(),e.getTexture(),e.getColor(),e.getUIPojeection());
			}
			
			}else {
				if(!e.isHasNonSqaureSize()) {	
					
				
				MainBatchRender.addModel(e.getModel(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getSize(),e.getTexture(),e.getUIPojeection());
				}else {
					MainBatchRender.addModel(e.getModel(),new Vector2f(p.x,p.y) ,e.getAngle(), e.getNonSquareSize(),e.getTexture(),e.getUIPojeection());
					
				}
				
				}
			
			}
		
	}
	
	public static void addEntity(Entity e) {
		entities.add(e);
		if(e.isDrawDrawLine() && Start.showDrawLines) {
			entities.add( new Entity(Start.background,new Vector3f(e.getPosition().x,e.getPosition().y+e.getDrawLineOffset(),100),0,new Vector2f(64,1),Start.COLTEX,Constants.RED));
		}
		
	}
	public static void clear() {
		entities.clear();
		
	}
	
	
	public static void SortEntities() {
		
		if(!entities.isEmpty()) {
			Comparator<Entity> byZvalue = new Comparator<Entity>() {
		          
		          @Override
		          public int compare(Entity e1,Entity e2) {
		              Float v1 = e1.getPosition().z;
		              Float v2 = e2.getPosition().z;
		              int result=v1.compareTo(v2);
		              if(result==0) {
		            	 v1=e1.getPosition().y; 
		            	 v2=e2.getPosition().y+e2.getDrawLineOffset();
		            	 
		            	 result=v2.compareTo(v1);
		              }
		              
		              
		              return result;
		          }
		      };
			 	Collections.sort(entities, byZvalue);    
				
		}
	
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
