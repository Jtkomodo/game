package battleClasses;

import org.joml.Vector2f;

import gameEngine.Texture;
import textrendering.TextBuilder;

public class HpBar extends Bars {

	private Texture hpbar;
	
	public HpBar(float maxHp,float currentHp,Vector2f BackgroundScale,Texture BackgroundTexture,Texture hpbar) {
		super(maxHp,currentHp, BackgroundScale, BackgroundTexture);
		this.hpbar=hpbar;
	}

	

	
	

	
	
	
	
	
	


	@Override
	public void draw(Vector2f position,TextBuilder text) {		
		
		
	draw(position,hpbar,text);
		
	}}
