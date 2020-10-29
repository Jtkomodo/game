package guis;

import org.joml.Vector2f;
import org.joml.Vector3f;

import Data.Constants;
import battleClasses.BattleEntity;
import gameEngine.Entity;
import gameEngine.Start;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class DisplayPCInfo extends GUINodeFunction {
	private TextBuilder text=Start.text1;
    private BattleEntity p;
	
	public DisplayPCInfo(BattleEntity p) {
		this.p=p;
		
	}
	
	
	
	
	
	@Override
	public void invoke(Vector2f position, Vector2f padding, float sizeOfStrings) {
		float x=position.x;
		float y=position.y;
		Vector2f scale=p.getHpbar().getBackgroundScale();
	    text.setString("SP: "+Math.round(p.getSp())+"/"+Math.round(p.getMaxsp()));
		float h=text.getStringHieght();
		float width=((scale.x+padding.x*sizeOfStrings)*2)+(padding.x*sizeOfStrings);
		float height=(scale.y+(h*sizeOfStrings)+(padding.y()*sizeOfStrings));
		p.getHpbar().draw(new Vector2f(x-scale.x,(y-(scale.y+(padding.y*sizeOfStrings))+(h/2*sizeOfStrings))),text);
		text.setString("HP:"+Math.round(p.getHp())+"/"+Math.round(p.getMaxHP()));      
		p.getHpbar().draw(new Vector2f((x-((scale.x+padding.x*sizeOfStrings)*2)),(y-(scale.y+(padding.y*sizeOfStrings))+(h/2*sizeOfStrings))),text);
	    Vector2f newPosition=new Vector2f();
	   position.sub(width+(padding.x*sizeOfStrings),0,newPosition);
	    MainRenderHandler.addEntity(new Entity(Start.background,new Vector3f((newPosition.x+width/2)-(padding.x*sizeOfStrings),newPosition.y-(height/2),p.getHpbar().getZ()-1),0,new Vector2f(width+(padding.x*sizeOfStrings),height),Start.COLTEX,Constants.BLACK));
			

	}

}
