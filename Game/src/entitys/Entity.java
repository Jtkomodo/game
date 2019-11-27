package entitys;

import org.joml.Vector2f;

import gameEngine.Model;
import gameEngine.Renderer;
import gameEngine.Texture;

public class Entity {

	
	protected Vector2f position=new Vector2f(0);
	protected float angle=0,scale=1;
	public float getAngle() {
		return angle;
	}


	public void draw() {
		Renderer.draw(model, position, angle, scale,texture);
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}



	public float getScale() {
		return scale;
	}



	public void setScale(float scale) {
		this.scale = scale;
	}



	public Texture getTexture() {
		return texture;
	}



	public void setTexture(Texture texture) {
		this.texture = texture;
	}



	public Model getModel() {
		return model;
	}



	public void setModel(Model model) {
		this.model = model;
	}

	protected Texture texture;
	protected Model model;
	
	Entity(Model model,Texture texture){
		
		this.model=model;
		this.texture=texture;
		
	}
	
	
	
	
	
	public void setPosition(Vector2f position) {
		this.position=position;
		
	}
	
	public Vector2f getPosition() {
		return this.position;
		
	}
	
}
