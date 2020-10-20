package guis;

import java.util.LinkedList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Data.Constants;
import Data.Moves;
import Items.Item;
import battleMoves.Move;
import gameEngine.Entity;
import gameEngine.Start;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class GUINodeDisplayMoveInfo extends GUINodeFunction {
    
	
	  
    private LinkedList<String> strings=new LinkedList<String>();
    private float widthOfEachString;
    private TextBuilder text=Start.text1;
	private Moves move;
	
	public GUINodeDisplayMoveInfo(Moves move) {
	this.move=move;	
	String name=move.getName();		
    int soCost=move.getCost();
    strings.add(name);
    
    
    if(soCost!=0) {
    	strings.add(soCost+" sp");
    }
    int damage=move.getDamage();
    if(damage!=0) {
    	strings.add(damage+" atk");
    }
    int heals=move.getSingleSelectedHealingAmount();
    if(heals!=0) {
    	strings.add("heals "+heals); 	
    }
    int selfHeals=move.getSelfHealAmount();
    if(selfHeals!=0) {
    	strings.add("heals(self only) "+selfHeals);
    }
	for(int i=0;i<strings.size();i++) {
		text.setString(strings.get(i));
    	float width=text.getStringLength();
    	if(width>this.widthOfEachString) {
			this.widthOfEachString=width;
		}
		
	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void invoke(Vector2f position, Vector2f padding, float sizeOfStrings) {
		   
		  float width=sizeOfStrings*((this.widthOfEachString+padding.x)+padding.x);
			float height=sizeOfStrings*((this.strings.size()+1)*padding.y);
	         Vector2f newPosition=new Vector2f();
		    position.sub(width+(padding.x*sizeOfStrings),0,newPosition);
			for(int i=0;i<strings.size();i++) {
				text.setString(strings.get(i));
				float y=-((i+1)*padding.y*sizeOfStrings);;
				Vector2f nPosition=new Vector2f();
				newPosition.add(0,y,nPosition);
				float z=text.getZ();
				text.setZ(1000001);
				text.drawString(nPosition.x,nPosition.y,sizeOfStrings);
			    text.setZ(z);
				
			}
			
		  MainRenderHandler.addEntity(new Entity(Start.background,new Vector3f((newPosition.x+width/2)-(padding.x*sizeOfStrings),newPosition.y-(height/2),1000000),0,new Vector2f(width+(padding.x*sizeOfStrings),height),Start.COLTEX,Constants.BLACK));
			

		    
		  
		    
		    
		    
		 
		   
		   
		

	}

}
