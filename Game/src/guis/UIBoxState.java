package guis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector4f;


import gameEngine.Model;
import gameEngine.Render;
import gameEngine.Start;
import gameEngine.Texture;


public class UIBoxState {

	private boolean anyActive=false;
	private float width,height;//width and height of the UI box;
	
	private int beginElement=0;
	private int amountOfElements,offsetPositionOnlist=0;
	private List<UIElement> elementlist=new ArrayList<UIElement>();//all of the string elements
	private List<UIElement> Activeelementlist=new ArrayList<UIElement>();//only the string elements that have a action associated with it

	
	protected HashMap<Vector2f,Integer> Values=new HashMap<Vector2f,Integer>();//this is a way to get the index of the element from the position 
	protected HashMap<Integer,Vector2f> ValuesInverse=new HashMap<Integer,Vector2f>();
	protected HashMap<Float,Integer> Amountx=new HashMap<Float,Integer>();//this is to tell how many times the x value is used to know when to delete it from the list
	protected HashMap<Float,Integer> Amounty=new HashMap<Float,Integer>();//same as x but for y
	private List<Float> sortx=new ArrayList<Float>();//this is all the x values in the positions in order if we move right then we get increase the index if left decrease
	private List<Float> sorty=new ArrayList<Float>();//this is all the y values in order if the we go up then increase index if down then increase
	private Vector2f offsetPosition;//position in relation to the middle of the box
	private Vector4f color=null;//color of the box
	private Texture tex,ArrowTex=Start.testSprite;//textures for the box and the arrow
    private Model m,arrow;//models for drawing the box and the arrow 
    private float[] uv=Start.uvtextbox,Auv=Start.uvArrow,vert=Start.vert;
    private boolean currentlyActive=false/*this tells us if this box is the currently used one*/,alwaysShown=false;
	private int PositionIndexX=0,PositionIndexY=0;
	
	
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex) {
		
	construct(offsetPosition,width,height,elements,tex);
	}
	
	

	public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,boolean alwaysShown) {
		this.alwaysShown=alwaysShown;
		construct(offsetPosition,width,height,elements,tex);
		}
		
	public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,Vector4f color) {
		this.color=color;
		construct(offsetPosition,width,height,elements,tex);
		}
		
		public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,boolean alwaysShown,Vector4f color) {
			this.color=color;
			this.alwaysShown=alwaysShown;
			construct(offsetPosition,width,height,elements,tex);
			}
	
	
		public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,float u,float v) {
			
		construct(offsetPosition,width,height,elements,tex,u,v);
		}
		
		

		public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,boolean alwaysShown,float u,float v) {
			this.alwaysShown=alwaysShown;
			construct(offsetPosition,width,height,elements,tex,u,v);
			}
			
		public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,Vector4f color,float u,float v) {
			this.color=color;
			construct(offsetPosition,width,height,elements,tex,u,v);
			}
			
			public UIBoxState(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,boolean alwaysShown,Vector4f color,float u,float v) {
				this.color=color;
				this.alwaysShown=alwaysShown;
				construct(offsetPosition,width,height,elements,tex,u,v);
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
		if(this.anyActive) {
		this.offsetPositionOnlist=offsetPositionOnlist;
		Vector2f vector=ValuesInverse.get(offsetPositionOnlist);
		this.PositionIndexX=sortx.indexOf(vector.x);
		this.PositionIndexY=sorty.indexOf(vector.y);
		}
		}




	public int getAmouuntOfelements() {
		
	return this.amountOfElements;	
	}
	public void setUv(float[] uv) {
		this.uv = uv;
	}
	
	public void addElement(UIElement e) {
		elementlist.add(e);
	    
	
		if(e.isActive()) {
			this.anyActive=true;
			if((e.getoffset().x<=ValuesInverse.get(0).x) && ( e.getoffset().y<=ValuesInverse.get(0).y)) {
		    	this.beginElement=this.Activeelementlist.size();
		    	
		    }
			
			Activeelementlist.add(e);
			Vector2f Vector=e.getoffset();
	        Values.put(Vector,this.Activeelementlist.size()-1);
	        ValuesInverse.put(this.Activeelementlist.size()-1,Vector);
	        if(!sortx.contains(Vector.x))
	        sortx.add(Vector.x);
	        if(!sorty.contains(Vector.y))
	        sorty.add(Vector.y);
	        }
		Collections.sort(sortx);
		Collections.sort(sorty);

		
		Vector2f first=elementlist.get(0).getoffset();

		this.PositionIndexX=sortx.indexOf(first.x);
		this.PositionIndexY=sorty.indexOf(first.y);
	
		this.amountOfElements++;
	
	}
	public void removeElement(int index) {
	
		if(index<=this.amountOfElements) {
		
		UIElement e=elementlist.get(index);
		
		elementlist.remove(index);
		this.amountOfElements--;
		if(e.isActive()) {
			
			
			this.anyActive=true;
		
			Vector2f Vector=e.getoffset();
	        Values.remove(Vector);
	        ValuesInverse.remove(index);
	        Activeelementlist.remove(e);  
	       int x=Amountx.get(Vector.x);
	       int y=Amounty.get(Vector.y); 
	       if(x==1) {
	    	  sortx.remove(Vector.x); 
	    	  Amountx.remove(Vector.x);
	    	  
	       }else {
	    	  Amountx.put(Vector.x,x-1);
	       }
	       if(y==1) {
		    	  sorty.remove(Vector.y);
		    	  Amounty.remove(Vector.y);
		       }else{
		    	   Amounty.put(Vector.y,y-1);
		       }
	       
		
		for(int i=index;i<this.amountOfElements;i++) {
			 e=elementlist.get(i);
			
			 if(e.isActive()) {
					
				
						
				 
				 
				 
				    this.anyActive=true;
					 Vector=e.getoffset();
			        int value=Values.get(Vector);
			        Values.put(Vector, value-1);
			        ValuesInverse.put(value-1,Vector);
				 
			 }
			
		}
		
		
		Collections.sort(sortx);
		Collections.sort(sorty);
		this.offsetPositionOnlist=0;
		
		Vector2f first=elementlist.get(0).getoffset();

		this.PositionIndexX=sortx.indexOf(first.x);
		this.PositionIndexY=sorty.indexOf(first.y);
	
	
		
	
		}
		
		}
		
	}
	
	public void replaceElement(UIElement e,int index) {
		
	     removeElement(index);
	     addElement(e);
		
		
	}
	
	
    public void drawBox(Vector2f position) {
    	Vector2f noffsetPosition= new Vector2f();
    
    		position.add(this.offsetPosition,noffsetPosition);
    	
    	if(color==null) {	
    	Render.draw(m, noffsetPosition, 0, 1,tex);}
    	else {
    		Render.draw(m, noffsetPosition, 0, 1,tex,color);
    	}
    for(int i=0;i<this.amountOfElements;i++) {
    	UIElement element=this.elementlist.get(i);
    	
    	
    	element.drawElement(noffsetPosition);
    	
    	
    }
 if(currentlyActive && this.anyActive) {
	 
    Vector2f pos=new Vector2f(Activeelementlist.get(offsetPositionOnlist).getoffset());
    
   Vector2f pos2=new Vector2f(0);
   pos.add(noffsetPosition,pos2);
    Render.draw(arrow, pos2.sub(5,0,new Vector2f(0)),0,10,ArrowTex);
 }
    	
    	
    	
    }




	

	public void setHasarrow(boolean hasarrow) {
		this.currentlyActive = hasarrow;
	}
	
	
private void construct(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex,float u,float v) {
		
		this.tex=tex;
		this.offsetPosition=offsetPosition;
		this.width=width;
		this.height=height;
		this.amountOfElements=elements.length;
		
		m=new Model(width, height, u, v, tex.getW(),tex.getH());
		arrow=new Model(vert,Auv);
		
		loadList(elements);
		
}
	
private void loadList(UIElement elements[]) {
	Vector2f first=elements[0].getoffset();
	int i2=0;
	boolean start=false;
	
	for(int i=0;i<elements.length;i++) {
	UIElement e=elements[i];
	
	
	
	elementlist.add(e);
	if(e.isActive()) {
		this.anyActive=true;
	if(!start) {
		this.beginElement=i2;
		start=true;
	}
		
		
	
	
		
	Activeelementlist.add(e);
	Vector2f Vector=e.getoffset();
    Values.put(Vector,i2);
    ValuesInverse.put(i2,Vector);
    
    
    int x=this.Amountx.getOrDefault(Vector.x,0);
    int y=this.Amounty.getOrDefault(Vector.y,0);
    Amountx.put(Vector.x,x+1);
    Amounty.put(Vector.y,y+1);
    if(!sortx.contains(Vector.x)) {
    sortx.add(Vector.x);}
    if(!sorty.contains(Vector.y)) {
    sorty.add(Vector.y);}
    i2++;
	
	}

	}
	
	
	
	Collections.sort(sortx);
	Collections.sort(sorty);


	this.PositionIndexX=sortx.indexOf(first.x);
	this.PositionIndexY=sorty.indexOf(first.y);
	
	
	
	
}



	private void construct(Vector2f offsetPosition,float width,float height,UIElement[] elements,Texture tex) {
		
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
		 
		 
		 

		
		m=new Model(vertText,uv);
		arrow=new Model(vert,Auv);
		
		loadList(elements);
	
	
	}
	
	
	

	

	public int getBeginElement() {
		return beginElement;
	}

	public int getPositionIndexX() {
		return PositionIndexX;
	}

	
	public int getPositionIndexY() {
		return PositionIndexY;
	}

	public void  IncreasePositionIndexX() {
		if(PositionIndexX!=sortx.size()-1 && this.anyActive ) {
			PositionIndexX++;
			//first just check if that position exist on our map
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		
		
		
		if(!Values.containsKey(vector)) {//if it doesn't then we check on the line for another element
		boolean found=false;	//this is a boolean to tell if we found a Element
		
		//check on and below line
		
		for(int v=this.PositionIndexY;v>=0;v--) {
			for(int i=PositionIndexX;i<sortx.size();i++) {
				 x=this.sortx.get(i);
				 y=this.sorty.get(v);
				
				 vector=new Vector2f(x,y);
				
				if(Values.containsKey(vector)) {
					this.PositionIndexX=i;
					this.PositionIndexY=v;
					found=true;
					break;
					
				}}
			if(found) {
				break;
			}
			}
		
		
		
		
		
		
		
		//check above line
		if(!found) {
			for(int v=this.PositionIndexY;v<sorty.size();v++) {
				for(int i=PositionIndexX;i<sortx.size();i++) {
					 x=this.sortx.get(i);
					 y=this.sorty.get(v);
					
					 vector=new Vector2f(x,y);
					
					if(Values.containsKey(vector)) {
						this.PositionIndexX=i;
						this.PositionIndexY=v;
						found=true;
						break;
						
					}}
				if(found) {
					break;
				}
				}
		}
		}
		changeOffsetPositionOnList();//after we got the values set we now change the position on the list
		
		}
	}

	
	public void DecreasePositionIndexY() {
		if(PositionIndexY!=0 && this.anyActive) {
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
		if(PositionIndexY!=sorty.size()-1 && this.anyActive) {
			PositionIndexY++;
			
		
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		if(!Values.containsKey(vector)) {
		
		
		for(int i=0;i<sortx.size();i++) {
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
		if(PositionIndexX!=0 && this.anyActive) {
		PositionIndexX--;
		
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
		if(!Values.containsKey(vector)) {
			
		
		
			boolean found=false;	//this is a boolean to tell if we found a Element
			
			//check on and below line
			
			for(int v=this.PositionIndexY;v>=0;v--) {
				for(int i=PositionIndexX;i>=0;i--) {
					 x=this.sortx.get(i);
					 y=this.sorty.get(v);
					
					 vector=new Vector2f(x,y);
					
					if(Values.containsKey(vector)) {
						this.PositionIndexX=i;
						this.PositionIndexY=v;
						found=true;
						break;
						
					}}
				if(found) {
					break;
				}
				}
			
			
			
			
			
			
			
			//check above line
			if(!found) {
				for(int v=this.PositionIndexY;v<sorty.size();v++) {
					for(int i=PositionIndexX;i>=0;i--) {
						 x=this.sortx.get(i);
						 y=this.sorty.get(v);
						
						 vector=new Vector2f(x,y);
						
						if(Values.containsKey(vector)) {
							this.PositionIndexX=i;
							this.PositionIndexY=v;
							found=true;
							break;
							
						}}
					if(found) {
						break;
					}
					}
			}
		}
		changeOffsetPositionOnList();
		}
	}
	public UIElement getStringElement(int index) {
		return elementlist.get(index);
		
	}
	
	public UIElement getStringActiveElement(int index) {
		if(this.Activeelementlist.size()!=0) {
		
		
		return Activeelementlist.get(index);
		}
		else return null;
	}
	
	public UIElement getStringActiveElement(Vector2f position) {
		return this.Activeelementlist.get(Values.get(position));
	}
	
	private void changeOffsetPositionOnList() {
		float x=this.sortx.get(this.PositionIndexX);
		float y=this.sorty.get(this.PositionIndexY);
		
		Vector2f vector=new Vector2f(x,y);
		
	
		this.offsetPositionOnlist=this.Values.get(vector);
	}
	
	
	
	public boolean isAnyActive() {
		return anyActive;
	}




	
}
