package guis;

import org.joml.Vector2f;

import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Texture;

public class TextureElement extends UIElement {

	private Model model;
	private Texture texture;

	private float scale;
	private float width,height;
	
	//to do add: a Texture element to our gui library this will go with be loaded into the UIBoxState class it will also be able to be selected if we so desire
	
	
	
	
	public TextureElement(Texture texture,float width,float height,Vector2f offset,Vector2f uv,float scale){
		
		this.texture=texture;
		this.offset=offset;
		this.scale=scale;
		this.width=width;
		this.height=height;
		this.active=true;
		 
		this.model=new Model(width,height,uv.x,uv.y,texture.getW(),texture.getH());//makes the model for the texture
		
		
		
		
		
	}

	@Override
	public void drawElement(Vector2f Position) {
		Vector2f noffset=new Vector2f();offset.add(Position,noffset);
		
	Render.draw(model,noffset,0, scale, texture);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
