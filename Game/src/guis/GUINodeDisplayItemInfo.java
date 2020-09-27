package guis;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Data.Constants;
import Items.Inventory;
import Items.Item;
import Items.Items;
import gameEngine.Entity;
import gameEngine.Start;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class GUINodeDisplayItemInfo extends GUINodeFunction {

	
	private Inventory inventory;
	private Items item;
	private TextBuilder text=Start.text1;
	
	public GUINodeDisplayItemInfo(Inventory inventory,Items item){
		this.inventory=inventory;
		this.item=item;
	}
	
	
	
	
	
	@Override
	public void invoke(Vector2f position,Vector2f padding,float sizeOfStrings) {
	    Item item=this.item.Item;
		int amount=this.inventory.getAmountOfItem(item);
		String itemName=item.getName();
		String amountInfo="amount      "+amount;
		String restoringInfo="restores "+(int)item.getValue();
		
		if(item.isHealing()) {
		   restoringInfo+=" hp";
		}else if(item.isRestorSP()) {
		   restoringInfo+=" sp";
		}
	    float maxWidth=0;
	    String[] strings ={itemName,amountInfo,restoringInfo};
	    for(int i=0;i<3;i++) {
	    	text.setString(strings[i]);
	    	float width=text.getStringLength();
	    	if(width>maxWidth) {
				maxWidth=width;
			}
	    }
	    float width=sizeOfStrings*((maxWidth+padding.x)+padding.x);
		float height=sizeOfStrings*((4)*padding.y);
         Vector2f newPosition=new Vector2f();
	    position.sub(width+(padding.x*sizeOfStrings),0,newPosition);
		for(int i=0;i<3;i++) {
			text.setString(strings[i]);
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
