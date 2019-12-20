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
	
	public Item(String name,String textureName,float basePrice) {
		
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


	public abstract void useItem(BattleEntity entity);
	
	
	
	public void draw(Vector2f position,float scale) {
		Render.draw(model, position,0, scale, texture);
		
		
	}
	
	
	private float getBasePrice() {
		return this.basePrice;
	}
	
	
	
	
	
	
	
	
	
	
}
