package battleClasses;

import org.joml.Vector2f;
import org.joml.Vector4f;

import gameEngine.Texture;
import textrendering.TextBuilder;

public class HpBar extends Bars {

	private Texture hpbar;
	
	public HpBar(float maxHp,float currentHp,Vector2f BackgroundScale,Texture BackgroundTexture,Texture hpbar) {
		super(maxHp,currentHp, BackgroundScale, BackgroundTexture);
		this.hpbar=hpbar;
	}

	

	public HpBar(float maxHp,float currentHp,Vector2f BackgroundScale,Texture BackgroundTexture,Texture hpbar,Vector4f barColor,Vector4f barColorLow) {
		super(maxHp,currentHp, BackgroundScale, BackgroundTexture);
		this.hpbar=hpbar;
		this.Barcolor=barColor;
		this.lowValuecolor=barColorLow;
	}

	
	
	

	
	
	
	
	
	


	@Override
	public void draw(Vector2f position,TextBuilder text) {		
		
		
	draw(position,hpbar,text);
		
	}}
