package guis;

import org.joml.Vector2f;

import gameEngine.Model;
import gameEngine.Texture;

public class TextureElement {

	private Model model;
	private Texture texture;
	private Vector2f offset;
	private float scale;
	private float width,height;
	
	//to do add: a Texture element to our gui library this will go with be loaded into the UIBoxState class it will also be able to be selected if we so desire
	
	
	
	
	public TextureElement(Texture texture,float width,float height,Vector2f offset,Vector2f uv,float scale){
		
		this.texture=texture;
		this.offset=offset;
		this.scale=scale;
		this.width=width;
		this.height=height;
		
		 
		this.model=new Model(width,height,uv.x,uv.y,texture.getW(),texture.getH());//makes the model for the texture
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
