package guis;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector4f;


import gameEngine.Model;
import gameEngine.ModelFramwork;
import gameEngine.Render;
import gameEngine.Start;
import gameEngine.Texture;


public class UIBoxState {

	private boolean anyActive=false;
	private boolean alwaysShown=false;	
	private boolean hasArrow=false;
	
	private UIElement beginElement,ActiveElement;
	private int amountOfElements=0,offsetPositionOnlist=0;
	
	
	private List<UIElement> elementlist=new LinkedList<UIElement>();//all of the string elements
	private List<UIElement> Activeelementlist=new LinkedList<UIElement>();//only the string elements that have a action associated with it
	
	private HashMap<Float,Integer> AmountOfXpos=new HashMap<Float,Integer>();//holds the amount of each xpositions there are
	private HashMap<Float,Integer> AmountOfYpos=new HashMap<Float,Integer>();//same but for y
	
    private List<Float> xPos=new LinkedList<Float>();//list of each unique xPosition sorted	
    private List<Float> yPos=new LinkedList<Float>();//list of each unique yPosition sorted
    
    private HashMap<Vector2f,UIElement> values=new HashMap<Vector2f,UIElement>();//holds every active element with it's position as the key 
    
    
	
    //these are for detecting what position to move the cursor on based by direction so if we need to go down for instance we just take currentindexpositiony-1
    private int CurrentIndexPositionX;//this is the index of the xPos list we are currently on 
    private int CurrentIndexPositionY;//this is the index of the yPos list we are currently on
    
    
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
				for(int i=0;i<elements.length;i++) {
				
				if(elements[i].isActive()) {
					this.beginElement=elements[i];
					break;
					
				}
				}
				
				
				loadList(elements);
				setActiveElement(beginElement);
				}
			
				

				private void construct(Vector2f offsetPosition, float width, float height, UIElement[] elements,
						Texture tex, float u, float v) {
					this.offsetPosition=offsetPosition;
					this.tex=tex;
					//make the models
					
					m=new Model(width, height, u, v, tex.getW(),tex.getH());	
					arrow=new Model(vert,Auv);
					
					
					//look for the first element that is active it to begin
					for(int i=0;i<elements.length;i++) {
						if(elements[i].isActive()) {
							this.beginElement=elements[i];
							break;
							
						}
						}	
					
					loadList(elements);
					setActiveElement(this.beginElement);
				}

				
				
				
				
				
				
			




//___________load___list__and ___unload

				private void loadList(UIElement[] elements) {
					
					for(int i=0;i<elements.length;i++) {
					UIElement e=elements[i];
						this.elementlist.add(e);//add to element list
						
						//if__Active_______________________________________________________________
						if(e.isActive()) {
							this.Activeelementlist.add(e);//add to active element list if active
						     this.anyActive=true;
							
							
							if(this.beginElement==null) {
								this.beginElement=e;
								
							}
							Vector2f vector=e.getoffset();//get the offset of the vector								
							Vector2f vector2=this.beginElement.getoffset();
							
							//if this element is before the begin element from left to right up to down then it becomes the new begin element
							if(vector.y>vector2.y) {
								
								
								this.beginElement=e;
								
							}else if(vector.y==vector2.y) {
								if(vector.x<vector2.x) {
									this.beginElement=e;
								}
								
							}
							//load in the Vector and Element to the Values map
							
							this.values.put(vector, e);
							float x=vector.x;
							float y=vector.y;
							
							if(!this.xPos.contains(vector.x))//add this x position if unique to the xPosition list
								xPos.add(vector.x);
						
							if(!this.yPos.contains(vector.y))
								yPos.add(vector.y);
							
							
							
								
							
							//now add to the amount of each position chord in respected map 
							
							this.AmountOfXpos.put(x,(AmountOfXpos.getOrDefault(x,0))+1);
							this.AmountOfYpos.put(y,(AmountOfYpos.getOrDefault(y,0))+1);
							
							
							
						}
					 //____________________________________________________________________________________
					}
					
					
					//sort the two so that we can easily find the next position to go to based on direction
					Collections.sort(this.xPos);
					Collections.sort(this.yPos);
					
					
			
					if(this.ActiveElement==null && this.anyActive) {    	
						setActiveElement(this.beginElement);}
					this.amountOfElements=this.elementlist.size();
			      
				}
				
				public void unloadList(int[] indexs) {
					
					int amount=0;
					for(int i=0;i<indexs.length;i++) {
						
						if((indexs[i]-amount)<this.elementlist.size() && (indexs[i]-amount)>0) {
						UIElement e=this.elementlist.get(indexs[i]-amount);//get the element by the index making sure to account for ones already deleted in this loop
							this.elementlist.remove(e);//remove from element list
							amount++;
							if(e.isActive()) {
								this.Activeelementlist.remove(e);//remove from active element list if active
							    
								if(this.beginElement==e) {
							this.beginElement=null;
                                    for(int yi=0;yi<this.yPos.size();yi++) {
                                     for(int xi=0;xi<this.xPos.size();xi++) {
                                          	float x=xPos.get(xi);
                                          	float y=yPos.get(yi);
                                          	
                                          	Vector2f vector=new Vector2f(x,y);
                                          	if(this.values.containsKey(vector)) {
                                          		this.beginElement=values.get(vector);
                                          		
                                          	}
                                          	
                                          	
                                          }	
                                    	
                                    	
                                    	
                                    }
							}
								
								
								Vector2f vector=e.getoffset();
								//unload the Vector and Element to the Values map
								
								this.values.remove(vector);
								float x=vector.x;
								float y=vector.y;
								
								int amountx=this.AmountOfXpos.get(x);
								int amounty=this.AmountOfYpos.get(y);
								
								
								if(amountx==1) {//remove this x position if there is only 1
									xPos.remove(vector.x);
								   this.AmountOfXpos.remove(vector.x);
								}else {
									this.AmountOfXpos.put(vector.x, amountx-1);
								}
								
								
								if(amounty==1) {
									yPos.remove(vector.y);
									 this.AmountOfYpos.remove(vector.y);
								}else {
									
									this.AmountOfYpos.put(vector.y, amounty-1);
								}
								
								
									
								
							
								
								
							}
						}
						}
					
					Collections.sort(this.xPos);
					Collections.sort(this.yPos);
					
					
			
					if(!this.Activeelementlist.contains(this.ActiveElement)) {  	
					      this.setActiveElement(this.beginElement);	
					}
					
					this.anyActive=!Activeelementlist.isEmpty();
						this.amountOfElements=this.elementlist.size();
					
				}
				
				
				
		//____________________Adding____and___Removing_______________	
				 
				
				
				
				  public void addElements(UIElement[] elements) {
					
					loadList(elements);
				}
	          public void addElement(UIElement element) {
					
					loadList(new UIElement[] {element});
				}
				
			 public void removeElements(int[] indexs) {
				 
				 unloadList(indexs);
				 
			 }
			public void removeElement(int index) {
				unloadList(new int[] {index});
			}
				
			  private void removeAllActiveELements() {
		       
				  int amount=0;
		        
		         
		            	for(int i=0;i<this.amountOfElements;i++) {
							
							UIElement e=this.elementlist.get(i-amount);
						
								
							if(e.isActive()) {
								
									this.elementlist.remove(e);//remove from element list if active
								
		                         amount++;   
								}
							
							}
						
		            	
		            	this.amountOfElements=this.elementlist.size();
		            	this.anyActive=false;
		            	this.xPos.clear();
		            	this.yPos.clear();
		            	this.Activeelementlist.clear();
		            	this.AmountOfXpos.clear();
		            	this.AmountOfYpos.clear();
		            	this.values.clear();
		            	this.beginElement=null;
		            	
			  }
			
           public void relpaceALLActive(UIElement[] elements) {
        	   removeAllActiveELements();
        	   loadList(elements);
        	   setActiveElementBySameOffset();
           }

	



//______________STATE__MOVEMENT________________
	public boolean DecreasePositionIndeX() {
		//go left
		
		boolean found=false;//this is to tell if we found a value
		if(this.CurrentIndexPositionX!=0 && this.anyActive) {
			int xI=this.CurrentIndexPositionX-1;
			
			
			//first check if there is one that exist on the same line
			
			float x=this.xPos.get(xI);
			float y=this.yPos.get(this.CurrentIndexPositionY);
			
			Vector2f vector=new Vector2f(x,y);
			if(this.values.containsKey(vector)) {
				
				setActiveElement(this.values.get(vector));
				found=true;
				}else {
				
					
			
				      ///check below line 
				
				        for(int i=this.CurrentIndexPositionY;i>=0;i--) {//this is the y position of the "line"
				        	
				        	 for(int v=xI;v>=0;v--) {//this is the x position of the "line"
						        	
				        		   x=this.xPos.get(v);
				        		   y=this.yPos.get(i);
				        		 
				        		 
						        	vector=new Vector2f(x,y);
						        	
						        	if(this.values.containsKey(vector)) {
						        		found=true;
						        		setActiveElement(this.values.get(vector));
						        	     break;
						        	}
						        	
						        	
				        	 }
						        	
				        	
				        	
				        }
				
				
				
				
				      //above line
				if(!found) {
				
				        for(int i=this.CurrentIndexPositionY;i<this.yPos.size();i++) {//this is the y position of the "line"
				        	
				        	 for(int v=xI;v>=0;v--) {//this is the x position of the "line"
						        	
				        		   x=this.xPos.get(v);
				        		   y=this.yPos.get(i);
				        		 
				        		 
						        	vector=new Vector2f(x,y);
						        	
						        	if(this.values.containsKey(vector)) {
						        		found=true;
						        		setActiveElement(this.values.get(vector));
						        	     break;
						        	}
						        	
						        	
				        	 }
						        	
				        	
				        	
				        }
				
				}
				
				
				
				
				
				
				}
			return found;
			}else {
		
		
		return false;
			}
		
	}





	public boolean IncreasePositionIndexX() {
	//go right
		
		//if this is the last x position
	
		if(this.CurrentIndexPositionX!=this.xPos.size()-1 && this.anyActive) {
			int xI=this.CurrentIndexPositionX+1;
			
			boolean found=false;//this is to tell if we found a value
			//first check if there is one that exist on the same line
			
			float x=this.xPos.get(xI);
			float y=this.yPos.get(this.CurrentIndexPositionY);
			
			Vector2f vector=new Vector2f(x,y);
			if(this.values.containsKey(vector)) {
				setActiveElement(this.values.get(vector));
				found=true;
				}else {
					
					
			
				   
					//check on and below line
					  for(int i=this.CurrentIndexPositionY;i>=0;i--) {//this is the y position of the "line"
				        	
					    	
					    	 for(int v=xI ;v<this.xPos.size();v++) {//this is the x position of the "line"
						        	
				        		   x=this.xPos.get(v);
				        		   y=this.yPos.get(i);
				        		 
				        		 
						        	vector=new Vector2f(x,y);
						        	
						        	if(this.values.containsKey(vector)) {
						        		
						        		found=true;
						        		setActiveElement(this.values.get(vector));
						        	     break;
						        	}
						        	
						        	
				        	 }
						        	
				        	
				        	
				        }
				      
				
				
				
				      //above line
				if(found==false) {
				  
					for(int i=this.CurrentIndexPositionY;i<this.yPos.size();i++) {//this is the y position of the "line"
			        	
			        	 for(int v=xI;v<this.xPos.size();v++) {//this is the x position of the "line"
					        	
			        		   x=this.xPos.get(v);
			        		   y=this.yPos.get(i);
			        		 
			        		 
					        	vector=new Vector2f(x,y);
					        	
					        	if(this.values.containsKey(vector)) {
					        		found=true;
					        		setActiveElement(this.values.get(vector));
					        	     break;
					        	}
					        	
					        	
			        	 }
					        	
			        	
			        	
			        }
			
			
				        
				}
	        		 
	        		 
	        	 }
				
				
				
				
				
			return found;
		}else {
	
	
	return false;
		}
		
		
	}





	public boolean IncreasePositionIndexY() {
	//this stands for bringing the cursor up
		
		
		
		//if this is the last y position
		
		if(this.CurrentIndexPositionY!=this.yPos.size()-1 && this.anyActive) {
			int yI=this.CurrentIndexPositionY+1;
			
			
			//first check if there is one that exist on the same line
			
			float x=this.xPos.get(this.CurrentIndexPositionX);
			float y=this.yPos.get(yI);
			
			Vector2f vector=new Vector2f(x,y);
			boolean found=false;
			if(this.values.containsKey(vector)) {
				setActiveElement(this.values.get(vector));
				found=true;
				}else {
				
					//check above line
			
			        	 for(int i=0;i<this.xPos.size();i++) {//this is the x position of the "line"
					        	
			        		   x=this.xPos.get(i);
			        		   y=this.yPos.get(yI);
			        		 
			        		 
					        	vector=new Vector2f(x,y);
					        	
					        	if(this.values.containsKey(vector)) {
					        		
					        		setActiveElement(this.values.get(vector));
					        	    found=true; 
					        		break;
					        	     
					        	}
					        	
					        	
			        	 
					        	
			        	
			        	
					}
					       	
			        	
			   }
			return found;
		}else {
	
	
	return false;
		}
		
	}





	public boolean DecreasePositionIndexY() {
		//this stands for bringing the cursor down
		
		
		
	
		if(this.CurrentIndexPositionY!=0 && this.anyActive) {
			int yI=this.CurrentIndexPositionY-1;
			
			//first check if there is one that exist on the same line
			
			float x=this.xPos.get(this.CurrentIndexPositionX);
			float y=this.yPos.get(yI);

			boolean found=false;
			
			Vector2f vector=new Vector2f(x,y);
			if(this.values.containsKey(vector)) {
				setActiveElement(this.values.get(vector));
				found=true;
				}else {
					
					
			
				  
					
					//check above line
			
			        	 for(int i=0;i>=0;i--) {//this is the x position of the "line"
					        	
			        		   x=this.xPos.get(i);
			        		   y=this.yPos.get(yI);
			        		 
			        		 
					        	vector=new Vector2f(x,y);
					        	
					        	if(this.values.containsKey(vector)) {
					        		
					        		setActiveElement(this.values.get(vector));
					        		found=true;
					        	     break;
					        	}
					        	
					        	
			        	 
					        	
			        	
			        	
					}
					       	
			        	
			   }
			return found;
			}else {
		
		
		return false;
			}
	}




//_____________DRAW_______________________________________
	public void drawBox(Vector2f position) {
		
		Vector2f noffsetPosition= new Vector2f(0);//new position
	    
		position.add(this.offsetPosition,noffsetPosition);//new position=position+the offset position
	
		
		
	//draw the actual box first	
	if(color==null) {	
	Render.draw(m, noffsetPosition, 0, 1,tex);}
	else {
		Render.draw(m, noffsetPosition, 0, 1,tex,color);
	}
	
	
	//Iterate through the elements and draw each one with this new position
for(int i=0;i<this.amountOfElements;i++) {
	UIElement element=this.elementlist.get(i);
	
	
	element.drawElement(noffsetPosition);
	
	
}

//now draw the arrow before the currently active element
if(currentlyActive && this.anyActive && hasArrow) {//check if this state is currently active first and that there even is a active element
	 
    Vector2f pos=this.ActiveElement.getoffset();//get the position
    
   Vector2f pos2=new Vector2f(0);//placeholder
   pos.add(noffsetPosition,pos2);
    Render.draw(arrow, pos2.sub(5,0,new Vector2f(0)),0,10,ArrowTex);//draw the arrow
 }
		
		
	}
	
	
	
	
	



	


//Setters____________________________________________

   
    private void setActiveElement(UIElement e) {
    	
    if(e!=null) {	
    	Vector2f vector=e.getoffset();
    	
    	this.CurrentIndexPositionX=this.xPos.indexOf(vector.x);
    	this.CurrentIndexPositionY=this.yPos.indexOf(vector.y);
    	this.ActiveElement=e;
  
    	
    }else {
       this.ActiveElement=null;
      
    		   
    }
    	
    }
    public void setActiveElement(int value) {
    	
    	if(value<this.elementlist.size()) {
    	UIElement e=this.elementlist.get(value);
    	
        if(e!=null) {	
        	Vector2f vector=e.getoffset();
        	
        	this.CurrentIndexPositionX=this.xPos.indexOf(vector.x);
        	this.CurrentIndexPositionY=this.yPos.indexOf(vector.y);
        	this.ActiveElement=e;
      
        	
        }
    	}
        }
    private void setActiveElementBySameOffset() {
    	
    	if(this.ActiveElement!=null && this.anyActive==true){
    	Vector2f vector=this.ActiveElement.getoffset();
    
    	this.CurrentIndexPositionX=this.xPos.indexOf(vector.x);
    	this.CurrentIndexPositionY=this.yPos.indexOf(vector.y);
    	
    	if(beginElement==null) {
    	this.beginElement=this.Activeelementlist.get(0);
    	}
    	this.ActiveElement=this.values.getOrDefault(vector,this.beginElement);
    	
    	
    	
    	}
    }
    
    
	
	
	public void setActive(boolean b) {
		this.hasArrow=b;
		this.currentlyActive=b;
		
	}
	
	public void reset() {
		setActiveElement(this.beginElement);
		
	}
	
	
	
	
//Getters___________________________________________	
	public boolean isAnyActive() {
		return anyActive;
	}
	
	
	
	public UIElement getActiveEllement() {
		
		return this.ActiveElement;
	}
	
public UIStringElement getActiveStringEllement() {
		if(this.ActiveElement instanceof UIStringElement) {
			
			
		
		return (UIStringElement) this.ActiveElement;
		}else {
			return null;
		}
		
		
		}
	
	
	
    public boolean isAlwaysShown() {
		
		return this.alwaysShown;
	}

}

