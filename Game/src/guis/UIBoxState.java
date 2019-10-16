package guis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Data.Moves;
import gameEngine.Model;
import gameEngine.Renderer;
import gameEngine.Start;
import gameEngine.Texture;
import textrendering.TextBuilder;

public class UIBoxState {

	
	private float width,height;//width and height of the UI box;
	private int amountOfElements,offsetPositionOnlist=0;
	private List<UIStringElement> elementlist=new ArrayList<UIStringElement>();//all of the string elements
	private List<UIStringElement> Activeelementlist=new ArrayList<UIStringElement>();//only the string elements that have a action associated with it
	protected HashMap<Vector2f,Integer> Values=new HashMap<Vector2f,Integer>();//this is a way to get the index of the element from the position 
	protected HashMap<Integer,Vector2f> ValuesInverse=new HashMap<Integer,Vector2f>();
	private List<Float> sortx=new ArrayList<Float>();//this is all the x values in the positions in order if we move right then we get increase the index if left decrease
	private List<Float> sorty=new ArrayList<Float>();//this is all the y values in order if the we go up then increase index if down then increase
	private Vector2f offsetPosition;//position in relation to the middle of the box
	private Vector4f color=null;//color of the box
	private Texture tex,ArrowTex=Start.testSprite;//textures for the box and the arrow
    private Model m,arrow;//models for drawing the box and the arrow 
    private float[] uv=Start.uvtextbox,Auv=Start.uvArrow,vert=Start.vert;
    private boolean currentlyActive=false/*this tells us if this box is the currently used one*/,alwaysShown=false;
	private int PositionIndexX=0,PositionIndexY=0;
	
	
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex) {
		
	construct(offsetPosition,width,height,elements,tex);
	}
	
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex,boolean alwaysShown) {
		this.alwaysShown=alwaysShown;
		construct(offsetPosition,width,height,elements,tex);
		}
		
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex,Vector4f color) {
		this.color=color;
		construct(offsetPosition,width,height,elements,tex);
		}
		
		public UIBoxState(Vector2f offsetPosition,float width,float height,UIStringElement[] elements,Texture tex,boolean alwaysShown,Vector4f color) {
			this.color=color;
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
		this.offsetPositionOnlist=offsetPositionOnlist;
		Vector2f vector=ValuesInverse.get(offsetPositionOnlist);
		this.PositionIndexX=sortx.indexOf(vector.x);
		this.PositionIndexY=sorty.indexOf(vector.y);
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
		this.amountOfElements--;
	}
	
	public void replaceElemnt(UIStringElement e,int index) {
		
		this.elementlist.set(index, e);
	}
	
	
    public void drawBox(Vector2f position) {
    	Vector2f noffsetPosition= new Vector2f();
    
    		position.add(this.offsetPosition,noffsetPosition);
    	
    	if(color==null) {	
    	Renderer.draw(m, noffsetPosition, 0, 1,tex);}
    	else {
    		Renderer.draw(m, noffsetPosition, 0, 1,tex,color);
    	}
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
		
		Vector2f first=elements[0].getoffset();
		int i2=0;
		for(int i=0;i<elements.length;i++) {
		UIStringElement e=elements[i];
		
		
		
		elementlist.add(e);
		if(e.isActive()) {
			
		Activeelementlist.add(e);
		Vector2f Vector=e.getoffset();
        Values.put(Vector,i2);
        ValuesInverse.put(i2,Vector);
        if(!sortx.contains(Vector.x))
        sortx.add(Vector.x);
        if(!sorty.contains(Vector.y))
        sorty.add(Vector.y);
        i2++;
		
		}
	
		}
		
		
		
		Collections.sort(sortx);
		Collections.sort(sorty);
		
		
		this.PositionIndexX=sortx.indexOf(first.x);
		this.PositionIndexY=sorty.indexOf(first.y);
	
	}
	
	
	

	

	public int getPositionIndexX() {
		return PositionIndexX;
	}

	
	public int getPositionIndexY() {
		return PositionIndexY;
	}

	public void  IncreasePositionIndexX() {
		if(PositionIndexX!=sortx.size()-1) {
			PositionIndexX++;
			
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		if(!Values.containsKey(vector)) {
		for(int i=0;i<sorty.size();i++) {
			 x=this.sortx.get(this.PositionIndexX);
			 y=this.sorty.get(i);
			
			 vector=new Vector2f(x,y);
			
			if(Values.containsKey(vector)) {
				this.PositionIndexY=i;
				break;
			}
		}
		}
		changeOffsetPositionOnList();
		
		}
	}

	
	public void DecreasePositionIndexY() {
		if(PositionIndexY!=0) {
		PositionIndexY--;
		
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		if(!Values.containsKey(vector)) {
		
		for(int i=0;i<sorty.size();i++) {
			 x=this.sortx.get(i);
			 y=this.sorty.get(this.PositionIndexY);
			
			 vector=new Vector2f(x,y);
			
			if(Values.containsKey(vector)) {
				this.PositionIndexX=i;
				break;
			}
		}
		}
		changeOffsetPositionOnList();
	
		}
	}
	
	public void  IncreasePositionIndexY() {
		if(PositionIndexY!=sorty.size()-1) {
			PositionIndexY++;
			
		
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		if(!Values.containsKey(vector)) {
		
		
		for(int i=0;i<sorty.size();i++) {
			 x=this.sortx.get(i);
			 y=this.sorty.get(this.PositionIndexY);
			
			vector=new Vector2f(x,y);
			
			if(Values.containsKey(vector)) {
				this.PositionIndexX=i;
				break;
			}
		}
		}
		changeOffsetPositionOnList();
		}
	}

	
	public void DecreasePositionIndeX() {
		if(PositionIndexX!=0) {
		PositionIndexX--;
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		if(!Values.containsKey(vector)) {
			
		
		
		
		for(int i=0;i<sorty.size();i++) {
			 x=this.sortx.get(this.PositionIndexX);
			 y=this.sorty.get(i);
			
			 vector=new Vector2f(x,y);
			
			if(Values.containsKey(vector)) {
				this.PositionIndexY=i;
				break;
			}
		}
		
		}
		changeOffsetPositionOnList();
		}
	}
	public UIStringElement getStringElement(int index) {
		
		return elementlist.get(index);
	}
	
	
	
	public UIStringElement getStringActiveElement(Vector2f position) {
		return this.Activeelementlist.get(Values.get(position));
	}
	
	private void changeOffsetPositionOnList() {
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
	
		this.offsetPositionOnlist=this.Values.get(vector);
	}
	
	
}
