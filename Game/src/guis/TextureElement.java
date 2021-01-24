package guis;

import org.joml.Vector2f;
import org.joml.Vector3f;

import gameEngine.RenderEntity;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;

public class TextureElement extends UIElement {

	private Model model;
	private Texture texture;
    private float z=10002;
	private float scale;
	private float width,height;
	
	//to do add: a Texture element to our gui library this will go with be loaded into the UIBoxState class it will also be able to be selected if we so desire
	
	
	
	
	public TextureElement(Texture texture,float width,float height,Vector2f offset,Vector2f uv,float scale){
		super(offset);
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
		Vector3f noffset=new Vector3f();new Vector3f(offset,0).add(new Vector3f(Position,z),noffset);
		
	   MainRenderHandler.addEntity(new RenderEntity(model,noffset,0, scale, texture));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
