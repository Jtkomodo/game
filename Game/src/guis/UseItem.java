package guis;

import org.joml.Vector2f;

import Items.Items;
import Scripter.Proccesor;
import ScripterCommands.DrawString;
import battleClasses.BattleEntity;
import gameEngine.BattleSystem;
import gameEngine.Start;

public class UseItem extends GUIfunction {

	
	
	
	
	
	private BattleEntity p;
	private Items item;



	public UseItem(BattleEntity p,Items item) {
	this.p=p;
	this.item=item;
		
		
	}
	
	
	
	@Override
	public void invoke() {
		 Start.soundPlay=true;
		 
			boolean used= p.useItem(item);
			 if(used) {
				 Start.DebugPrint("used "+item.Item.getName());
				
				 Proccesor.addComandtoQueue(new DrawString("used "+item.Item.getName(),new Vector2f(-100,40),.5f,true,.5f));
				if( item.Item.isHealing())
				 Proccesor.addComandtoQueue(new DrawString("healed "+item.Item.getValue()+"!",new Vector2f(100,40),.5f,true,.5f));
				if( item.Item.isRestorSP())
					 Proccesor.addComandtoQueue(new DrawString("restored "+item.Item.getValue()+"!",new Vector2f(100,40),.5f,true,.5f));
					  
				 
			
				 BattleSystem.setPlayersTurn(false);
				 BattleSystem.setTurnFinished(true);
		 			 
			 }else {
				 Start.soundPlay=false;
				 Start.source.play(Start.NO);
			 }
		
	}

}
