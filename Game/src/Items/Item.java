package Items;

import org.joml.Vector2f;

import battleClasses.BattleEntity;
import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Texture;

public abstract class Item {

	private String name;
    private Texture texture;
	private Model model;
	private float basePrice;
	protected float value;
	

	protected boolean healing=false,restorSP=false;
	
	
	


	protected Item(String name,String textureName,float basePrice) {
		
		this.name=name;
		this.basePrice=basePrice;
		float Vert[]=new float[] {
				
				
						-0.5f,+0.5f,
						0.5f,0.5f,
						0.5f,-0.5f,
						-0.5f,-0.5f};
		
		float[] Uv={
				0,0,
				1,0,
				1,1,
				0,1};	
		
		model= new Model(Vert,Uv);
		texture= new Texture(textureName);
		
	}
	
	
	public String getName() {
		return name;
	}


	public abstract boolean useItem(BattleEntity entity);
	
	
	
	public void draw(Vector2f position,float scale) {
		Render.draw(model, position,0, scale, texture);
		
		
	}
	
	
	public float getBasePrice() {
		return this.basePrice;
	}
	
	public boolean isHealing() {
		return healing;
	}

	
	
	public float getValue() {
		return value;
	}
	
	

	public boolean isRestorSP() {
		return restorSP;
	}

	
	
}
