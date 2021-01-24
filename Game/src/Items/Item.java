package Items;

import org.joml.Vector2f;
import org.joml.Vector3f;

import battleClasses.BattleEntity;
import gameEngine.RenderEntity;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;

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
	   MainRenderHandler.addEntity( new RenderEntity(model, new Vector3f(position,10000),0, scale, texture));
		
		
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
