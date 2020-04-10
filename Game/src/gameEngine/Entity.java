package gameEngine;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import rendering.ModelFramwork;

public class Entity {

	private ModelFramwork model;
	private Texture texture;
	private Vector3f position;
	private float size=-1;
    private float angle=0;
    private float DrawLineOffset=0;
	private Vector2f NonSquareSize;
	private Vector4f color=Constants.DEFAULT_COLOR;
    private boolean hasColor=false,hasNonSqaureSize=false,Mirror=false,UIPojeection=false,drawDrawLine=false;
	
    
    
    public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture) {
	    construct(model,position,angle,texture);
		this.size = size;
	}
	public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture) {
	    construct(model,position,angle,texture);
	    setSize(nonSquareSize);
	}
	public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,Vector4f color) {
		construct(model,position,angle,texture,color);
		this.size = size;
	}
	public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,Vector4f color) {
		construct(model,position,angle,texture,color);
	     setSize(nonSquareSize);
	}
	
	  public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,boolean Mirror) {
		    construct(model,position,angle,texture);
			this.Mirror=Mirror;
			this.size = size;
		}
		public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,boolean Mirror) {
		    construct(model,position,angle,texture);
			this.Mirror=Mirror;
			  setSize(nonSquareSize);
		}
		public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,Vector4f color,boolean Mirror) {
		
		    construct(model,position,angle,texture,color);
			this.Mirror=Mirror;
			this.size = size;
		}
		public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,Vector4f color,boolean Mirror) {
			construct(model,position,angle,texture,color);
			this.Mirror=Mirror;
			  setSize(nonSquareSize);
		}

		
		
		
		
		//ones with drawLineOffset value in constructor
		
		
		
		   public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,float DrawLineOffset,boolean drawLine) {
			    construct(model,position,angle,texture);
				this.size = size;
				this.DrawLineOffset=DrawLineOffset;
				this.drawDrawLine=drawLine;
			}
			public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,float DrawLineOffset,boolean drawLine) {
			    construct(model,position,angle,texture);
			    setSize(nonSquareSize);
			    this.DrawLineOffset=DrawLineOffset;
			    this.drawDrawLine=drawLine;
			}
			public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,Vector4f color,float DrawLineOffset,boolean drawLine) {
				construct(model,position,angle,texture,color);
				this.size = size;
				this.DrawLineOffset=DrawLineOffset;
				this.drawDrawLine=drawLine;
			}
			public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,Vector4f color,float DrawLineOffset,boolean drawLine) {
				construct(model,position,angle,texture,color);
			     setSize(nonSquareSize);
			     this.DrawLineOffset=DrawLineOffset;
			     this.drawDrawLine=drawLine;
			}
			
			  public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,boolean Mirror,float DrawLineOffset,boolean drawLine) {
				    construct(model,position,angle,texture);
					this.Mirror=Mirror;
					this.size = size;
					this.DrawLineOffset=DrawLineOffset;
					this.drawDrawLine=drawLine;
				}
				public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,boolean Mirror,float DrawLineOffset,boolean drawLine) {
				    construct(model,position,angle,texture);
					this.Mirror=Mirror;
					  setSize(nonSquareSize);
					  this.DrawLineOffset=DrawLineOffset;
					  this.drawDrawLine=drawLine;
				}
				public Entity(ModelFramwork model,Vector3f position,float angle, float size,Texture texture,Vector4f color,boolean Mirror,float DrawLineOffset,boolean drawLine) {
				
				    construct(model,position,angle,texture,color);
					this.Mirror=Mirror;
					this.size = size;
					this.DrawLineOffset=DrawLineOffset;
					this.drawDrawLine=drawLine;
				}
				public Entity(ModelFramwork model, Vector3f position,float angle, Vector2f nonSquareSize,Texture texture,Vector4f color,boolean Mirror,float DrawLineOffset,boolean drawLine) {
					construct(model,position,angle,texture,color);
					this.Mirror=Mirror;
					  setSize(nonSquareSize);
					  this.DrawLineOffset=DrawLineOffset;
					  this.drawDrawLine=drawLine;
				}
		
		
		
		
	private void construct(ModelFramwork model,Vector3f position,float angle,Texture texture,Vector4f color) {
		
		setColor(color);
		construct(model,position,angle,texture);
		
		
	}
		
		
	private void construct(ModelFramwork model,Vector3f position,float angle,Texture texture) {
		this.angle=angle;
    	this.texture=texture;
		this.model = model;
		this.position = position;
		
		
	}
		
		
		
		
		
	
	public ModelFramwork getModel() {
		return model;
	}
	public void setModel(ModelFramwork model) {
		this.model = model;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public float getSize() {
		return size;
	}
	public Vector2f getNonSquareSize() {
		return this.NonSquareSize;
		
	}
	
	public void setSize(float size) {
		this.size = size;
this.hasNonSqaureSize=false;
	}
	
	public void setSize(Vector2f size) {
		this.NonSquareSize = size;
		this.hasNonSqaureSize=true;
		this.size=-1;
	}
	
	
	

	public void RemoveColor() {
		this.hasColor=false;
		color=Constants.DEFAULT_COLOR;
	}
	public boolean isHasNonSqaureSize() {
		return hasNonSqaureSize;
	}
	public float getAngle() {
		return angle;
	}
	
	
	
	public Vector4f getColor() {
		return color;
	}
	public boolean isHasColor() {
		return hasColor;
	}
	public void setColor(Vector4f color) {
		this.color = color;
		this.hasColor=true;
	}
    

	public void setAngle(float angle) {
		this.angle = angle;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setMirror(boolean mirror) {
		this.Mirror=mirror;
	}
	
	public boolean isMirror() {
		return this.Mirror;
	}
	public boolean getUIPojeection() {
		return UIPojeection;
	}
	public void setUIPojeection(boolean uIPojeection) {
		UIPojeection = uIPojeection;
	}
	public float getDrawLineOffset() {
		return DrawLineOffset;
	}
	public void setDrawLineOffset(float drawLineOffset) {
		DrawLineOffset = drawLineOffset;
	}
	public boolean isDrawDrawLine() {
		return drawDrawLine;
	}
	public void setDrawDrawLine(boolean drawDrawLine) {
		this.drawDrawLine = drawDrawLine;
	}
	
	
	
	
	
	
	
}
