package guis;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

import gameEngine.ArrowKeySelecter;
import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.ModelFramwork;
import rendering.Render;


public class UIBoxState {

	private boolean anyActive=false;
	private boolean alwaysShown=false;	
	private boolean hasArrow=false;
	private float z=10000;
	
	private List<UIElement> elementlist=new LinkedList<UIElement>();//all of the string elements
     private HashMap<Vector2f,UIElement> values=new HashMap<Vector2f,UIElement>();//holds every active element with it's position as the key 
    
    private ArrowKeySelecter selector=new ArrowKeySelecter();
	
    //these are for detecting what position to move the cursor on based by direction so if we need to go down for instance we just take currentindexpositiony-1
 
	private Vector4f color=null;
	private Vector2fc offsetPosition;
	private Texture tex=Start.textbox,ArrowTex=Start.testSprite;
	private float[] uv=Start.uvtextbox,Auv=Start.uvArrow,vert=Start.vert;
	private ModelFramwork m,arrow;;
	private boolean currentlyActive;
	
	
	
	//CONSTRUCTORS__________________________________________________________________________________________________________________________________
	
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
			
			
			
			
			
			
				
				
				private void construct(Vector2f offsetPosition, float width, float height, UIElement[] elements, Texture tex) {
				this.offsetPosition=offsetPosition;
				this.tex=tex;
			
				//make the models
				 float[] vertText={
						   -width,+height,
							width,height,
							width,-height,
							-width,-height};
				 
				 
				

				
				m=new Model(vertText,uv);
				arrow=new Model(vert,Auv);
				
				
				//look for the first element that is active it to begin
				
				
				loadList(elements);
				setActiveElement(this.selector.getBeginPosition());
				}
			
				

				private void construct(Vector2f offsetPosition, float width, float height, UIElement[] elements,
						Texture tex, float u, float v) {
					this.offsetPosition=offsetPosition;
					this.tex=tex;
					//make the models
					
					m=new Model(width, height, u, v, tex.getW(),tex.getH());	
					arrow=new Model(vert,Auv);
					
					
					//look for the first element that is active it to begin
									
					loadList(elements);
					setActiveElement(this.selector.getBeginPosition());
				}

				
				
				
				
				
				
			




//___________load___list__and ___unload

				private void loadList(UIElement[] elements) {
					
					for(int i=0;i<elements.length;i++) {
					UIElement e=elements[i];
					this.elementlist.add(e);
					this.values.put(e.getoffset(), e);
					if(e.isActive()) {
						this.anyActive=true;
				    this.selector.addPosition(e.getoffset());
					}
					}
					
				}			
				
				
		//____________________Adding____and___Removing_______________	
				 
				
				
				
				  public void addElements(UIElement[] elements) {
					
					loadList(elements);
				}
	          public void addElement(UIElement element) {
					
					loadList(new UIElement[] {element});
				}
				
			  private void removeAllActiveELements() {
		       
				  int amount=0;
		        
				  int amountInlist=this.elementlist.size();
		            	for(int i=0;i<amountInlist;i++) {
							
							UIElement e=this.elementlist.get(i-amount);
						
								
							if(e.isActive()) {
								
									this.elementlist.remove(e);//remove from element list if active
								
		                         amount++;   
								}
							
							}
						
		            	
		            
		            	this.anyActive=false;
         	            this.values.clear();
		            	selector.removeAllPositions();
		            	
			  }
			
           public void relpaceALLActive(UIElement[] elements) {
        	   removeAllActiveELements();
        	   loadList(elements);
        	   selector.setBySameOffset();
           }

	



//______________STATE__MOVEMENT________________
	public boolean DecreasePositionIndeX() {
	boolean found=selector.moveLeft();
	if(found) {
		setActiveElement(selector.getCurrentPosition());
		
	}
	
	
	return found;	
		
			}
		
	





	public boolean IncreasePositionIndexX() {
		
		boolean found=selector.moveRight();
		if(found) {
			setActiveElement(selector.getCurrentPosition());
			
		}
		
		
		return found;	
			
		
		
	
	}





	public boolean IncreasePositionIndexY() {
		
		boolean found=selector.moveUP();
		if(found) {
			setActiveElement(selector.getCurrentPosition());
			
		}
		
		
		return found;	
			

		
	}





	public boolean DecreasePositionIndexY() {
		
		boolean found=selector.moveDown();
		if(found) {
			setActiveElement(selector.getCurrentPosition());
			
		}
		
		
		return found;	
			
		

	}




//_____________DRAW_______________________________________
	public void drawBox(Vector2f position) {
		
		Vector2f noffsetPosition= new Vector2f(0);//new position
	    
		position.add(this.offsetPosition,noffsetPosition);//new position=position+the offset position
	
		
		
	//draw the actual box first	
	if(color==null) {	
		  
		  MainRenderHandler.addEntity(new Entity(m, new Vector3f(noffsetPosition,z), 0, 1,tex));
		  }
	else {
		MainRenderHandler.addEntity(new Entity(m, new Vector3f(noffsetPosition,z), 0, 1,tex,color));
	}
	
	
	//Iterate through the elements and draw each one with this new position
for(int i=0;i<this.elementlist.size();i++) {
	UIElement element=this.elementlist.get(i);
//	Vector2f index=selector.getIndex(element.getoffset());
//	Start.text1.setString(""+Math.round(index.x)+","+Math.round(index.y));
//	Vector2f p=noffsetPosition.add(element.getoffset(),new Vector2f());
//	Start.text1.drawString(p.x-200, p.y, 0.25f);
//	
	element.drawElement(noffsetPosition);
	
	
}

//now draw the arrow before the currently active element
if(currentlyActive && this.anyActive && hasArrow) {//check if this state is currently active first and that there even is a active element
	 
    Vector2f pos=this.selector.getCurrentPosition();//get the position
    
   Vector2f pos2=new Vector2f(0);//placeholder
   pos.add(noffsetPosition,pos2);
   MainRenderHandler.addEntity(new Entity(arrow, new Vector3f(pos2,z+1).sub(5,0,0,new Vector3f(0)),0,10,ArrowTex));//draw the arrow
 }
		
		
	}
	
	
	
	
	



	


//Setters____________________________________________

   

	public void setActiveElement(Vector2f position) {
	
          selector.setCurrentPosition(position);
	}
    
	
	
	public void setActive(boolean b) {
		this.hasArrow=b;
		this.currentlyActive=b;
	
	}
	
	public void reset() {
		selector.resetCurrentPosition();
	
	}
	
	
	
	
//Getters___________________________________________	
	public boolean isAnyActive() {
		return anyActive;
	}
	
	
	
	public UIElement getActiveEllement() {

         		
		return values.get(selector.getCurrentPosition());
	}
	
public UIStringElement getActiveStringEllement() {

	UIElement activeElement=this.values.get(this.selector.getCurrentPosition());
	
		if(activeElement instanceof UIStringElement) {
			
			
		
		return (UIStringElement) activeElement;
		}else {
			return null;
		}
		
		
		}
	
	
	
    public boolean isAlwaysShown() {
		
		return this.alwaysShown;
	}





	public float getZ() {
		return z;
	}






	public void setZ(float z) {
		this.z = z;
	}

}

