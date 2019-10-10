package gameEngine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import Data.Moves;
import textrendering.TextBuilder;

public class UIBoxState {

	
	private float width,height;//width and height of the UI box;
	private int amountOfElements,offsetPositionOnlist=0;
	private List<UIStringElement> elementlist=new ArrayList<UIStringElement>();
	private List<UIStringElement> Activeelementlist=new ArrayList<UIStringElement>();	
	private TextBuilder text=new TextBuilder(Start.aakar);
	private Vector2f offsetPosition;
	private Texture tex,ArrowTex=Start.testSprite;
    private Model m,arrow;
    private float[] uv=Start.uvtextbox,Auv=Start.uvArrow,vert=Start.vert;
    private boolean currentlyActive=false/*this tells us if this box is the currently used one*/,alwaysShown=false; 
	
	
	
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex) {
		
	construct(offsetPosition,width,height,elements,tex);
	}
	
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex,boolean alwaysShown) {
		this.alwaysShown=alwaysShown;
		construct(offsetPosition,width,height,elements,tex);
		}
		
	
	
	public boolean isAlwaysShown() {
		return alwaysShown;
	}

	public Vector2f getoffsetPosition() {
		return offsetPosition;
	}




	public void setoffsetPosition(Vector2f offsetPosition) {
		this.offsetPosition = offsetPosition;
	}




	public int getOffsetPositionOnlist() {
		return offsetPositionOnlist;
	}




	public void setOffsetPositionOnlist(int offsetPositionOnlist) {
		this.offsetPositionOnlist = offsetPositionOnlist;
	}




	public int getAmouuntOfelements() {
		
	return this.amountOfElements;	
	}
	
	public void addElement(UIStringElement e) {
		elementlist.add(e);
		
		this.amountOfElements++;
	}
	public void removeElement(int index) {
		
		elementlist.remove(index);
	}
    public void drawBox(Vector2f position) {
    	Vector2f noffsetPosition= new Vector2f();
    
    		position.add(this.offsetPosition,noffsetPosition);
    	
    	Renderer.draw(m, noffsetPosition, 0, 1,tex);
    for(int i=0;i<this.amountOfElements;i++) {
    	UIStringElement element=this.elementlist.get(i);
    	
    	
    	element.drawElement(noffsetPosition);
    	
    	
    }
 if(currentlyActive) {
    Vector2f pos=new Vector2f(Activeelementlist.get(offsetPositionOnlist).getoffset());
    
   Vector2f pos2=new Vector2f(0);
   pos.add(noffsetPosition,pos2);
    Renderer.draw(arrow, pos2.sub(5,0,new Vector2f(0)),0,10,ArrowTex);
 }
    	
    	
    	
    }




	

	public void setHasarrow(boolean hasarrow) {
		this.currentlyActive = hasarrow;
	}
	
	
	
	
	
	private void construct(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex) {
		int[] ind= {
				0,1,2,
				2,3,0	
					
			};
		
		this.tex=tex;
		this.offsetPosition=offsetPosition;
		this.width=width;
		this.height=height;
		this.amountOfElements=elements.length;
		
		 float[] vertText={
				   -width,+height,
					width,height,
					width,-height,
					-width,-height};
		 
		 
		 

		
		m=new Model(vertText,uv,ind);
		arrow=new Model(vert,Auv,ind);
		for(int i=0;i<elements.length;i++) {
		UIStringElement e=elements[i];
			
		elementlist.add(e);
		if(e.isActive()) {
		Activeelementlist.add(e);
		}
		}
		
		
		
	}
	
	
}
