package guis;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import java.util.LinkedList;
import java.util.Stack;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.ArrowKeySelecter;
import gameEngine.RenderEntity;
import gameEngine.Start;
import gameEngine.Texture;
import static input.GetInput.*;
import input.InputHandler;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class GUIManeger {

	
	private Stack<GUINode> backStack=new Stack<GUINode>();//this will be how we do go back actions
	private Stack<Vector2f> posStack=new Stack<Vector2f>();//this will be how we do go back actions
	private LinkedList<GUINode> ChildrenList;//this is the sub menu of the parent node
	private GUINode ParrentNode;//this is the node that is the parent of the current sub menu
	private int amountOfRows,amountOfCollumns;
	private int currentTopRow,AmountOfRowsBeforeScroll;
	private ArrowKeySelecter movement=new ArrowKeySelecter(false);
	private float widthOfEachStringSpot;
	private float width,height;
	private Texture ArrowTex=Start.testSprite;
	private Vector4f backgroundColor;
	private boolean currentlyActive=false;
	
	public GUIManeger(GUINode rootNode,Vector4f backgroundColor) {
	        this.backgroundColor=backgroundColor;
			this.ChildrenList=rootNode.getChildren();
			this.ParrentNode=rootNode;
			this.currentTopRow=0;
		    this.UpdateSlots(rootNode);
		    
		
		
	}
	public void open() {
		if(!this.currentlyActive) {
			this.currentlyActive=true;
			UpdateChanges();
			if(this.ParrentNode.isLeaf()) {
				goBack();
			}
		}
	}
    public void close() {
    	if(this.currentlyActive) {
    		if(!this.backStack.isEmpty()) {
    			this.changeParentNode(backStack.firstElement());
    			backStack.clear();
    		}
    		this.currentlyActive=false;
    	}
    }
    public void hide() {
    	this.currentlyActive=false;
    }
    
    
    
    public boolean isOpen() {
    	return this.currentlyActive;
    }
    public void InputUpdate() {
    	if(this.currentlyActive) {
    	int up=InputHandler.getStateofButton(GLFW_KEY_UP),down=InputHandler.getStateofButton(GLFW_KEY_DOWN)
    			,left=InputHandler.getStateofButton(GLFW_KEY_LEFT),right=InputHandler.getStateofButton(GLFW_KEY_RIGHT),
    		    Enter=InputHandler.getStateofButton(GLFW_KEY_ENTER),backspace=InputHandler.getStateofButton(GLFW_KEY_BACKSPACE);
    		
    			if(up==JUST_PUSHED) 
    				if(moveUp()) {
    					Start.source.play(Start.Move);
    				};
    			if(down==JUST_PUSHED)	
    				if(moveDown()) {
    					Start.source.play(Start.Move);
    				};
    			if(left==JUST_PUSHED)
    				if(moveLeft()) {
    					Start.source.play(Start.Move);
    				};
    			if(right==JUST_PUSHED)
    				if(moveRight()) {
    					Start.source.play(Start.Move);
    				};
    	        if(Enter==JUST_PUSHED) {
    				
    	        	Select();
    	        }
    	        if(backspace==JUST_PUSHED) {
    	        	Start.source.play(Start.Back);
    	        	goBack();
    	        }
    	        
    	 
    	        CheckScroll();
    	
    	
    	}
    } 
    
   
   private void CheckScroll() {
	   
	   Vector2f slot=this.movement.getCurrentPosition();
	   if(slot!=null) {
		   int row=(int)-slot.y;
		   int collum=(int)slot.x;
		   if((row)>=this.AmountOfRowsBeforeScroll) {
			   this.currentTopRow=(row+1)-this.AmountOfRowsBeforeScroll;
		   }
		   else {
			   this.currentTopRow=0;
		   }
		   float amount=((this.currentTopRow*this.amountOfCollumns)+(this.amountOfCollumns*this.AmountOfRowsBeforeScroll));
		   if(amount>this.ChildrenList.size()) {
			   amount=this.ChildrenList.size();
		   }

	   }
   }
    
    private void changeParentNode(GUINode node) {
    	this.ChildrenList=node.getChildren();
		this.ParrentNode=node;
		this.currentTopRow=0;
		this.movement.resetCurrentPosition();
		this.UpdateSlots(node);
	 
    }
    private void goBack() {
    	if(!this.backStack.isEmpty()) {
    		this.movement.resetCurrentPosition();
    		changeParentNode(this.backStack.pop());
    		this.movement.setCurrentPosition(this.posStack.pop());
    	}
    	Start.DebugPrint("WENT BACK");
    	}
  
    private GUINode getCurrentNodeFromSelector() {
    	Vector2f currentSlot=movement.getCurrentPosition();
		if(currentSlot!=null && !this.ParrentNode.isLeaf()) {
	    int i=(int)currentSlot.x+this.amountOfCollumns*(int)-currentSlot.y;
	   if(i<this.ChildrenList.size()) {
	    return this.ChildrenList.get(i);
	   }else {
		   return null;
	   }
	   }
		else {
			return null;
		}
    }
    
    
    private void Select() {
    	if(!this.ParrentNode.isLeaf()) {

    		GUINode currentNode=this.getCurrentNodeFromSelector();
    		if(currentNode!=null) {
    			if(currentNode.isLeaf()) {

    				if(currentNode.InvoleFunction()) {
                        Start.source.play(Start.Select);
    				}else {
    					Start.source.play(Start.NO);
    				}
    				this.UpdateChanges();

    				if(this.ParrentNode.isLeaf()) {
    					goBack();
    				}

    			}else {
    				this.backStack.add(ParrentNode);
    				this.posStack.add(this.movement.getCurrentPosition());
    				this.movement.resetCurrentPosition();
    				changeParentNode(currentNode);
    				Start.source.play(Start.Select);
    			}
    		}
    	}

    }
	public void UpdateChanges() {
		UpdateSlots(this.ParrentNode);
	}
	
	
	private void UpdateSlots(GUINode rootNode) {
		
		if(!rootNode.isLeaf() && this.currentlyActive) {
	     this.ChildrenList=rootNode.getChildren();
		 this.ParrentNode=rootNode;
		//find the string that has the highest length
		 this.movement.removeAllPositions();
	     if(!rootNode.isLeaf()) {
		
			float maxSize=0;
			LinkedList<GUINode> children=rootNode.getChildren();
			int collumns=rootNode.getAmountOfCollumns();//this is just the value of the amount of columns
			this.AmountOfRowsBeforeScroll=rootNode.getAmountOfRows();//this is just the value of the amount of rows
			this.amountOfCollumns=collumns;
			int amountOfElements=children.size();
			 int r=amountOfElements/collumns;
			 if((amountOfElements%collumns)!=0) {
			    	 r++;
			   }
			 this.amountOfRows=r;
			 int amount=this.amountOfCollumns*this.AmountOfRowsBeforeScroll;
				if(amount>this.ChildrenList.size()) {
					amount=this.ChildrenList.size();
				}
				
  
			 Vector2f[] slots=new Vector2f[children.size()];    
			 for(int i=0;i<children.size();i++) {
				TextBuilder textString=children.get(i).getString();
				if(textString!=null) { 
					
					float size=textString.getStringLength();
					slots[i]=(new Vector2f(i%this.amountOfCollumns,-(i/this.amountOfCollumns)));
					if(size>maxSize) {
						maxSize=size;
					}
				}
			}
	      this.widthOfEachStringSpot=maxSize;
	      movement.relpaceAllPositions(slots);
	     
	      //calculate the amount of rows needed
	   
	     
	     Start.DebugPrint("UPDATED");
	     }
	   	 
		}
	   
	    
	}
	
	
	
	private boolean moveUp() {
		return movement.moveUP();	
	
	}
	private boolean moveDown() {
		return movement.moveDown();
		
	}
	private boolean moveRight() {
		return movement.moveRight();
	
	}
	private boolean moveLeft() {
	  return movement.moveLeft();
	  
	}
	
	
	
	
	
	
	
	
	

	
   
	
		public void draw(Vector2f position,Vector2f padding,float sizeOfStrings) {
			if(this.currentlyActive) {
			height=sizeOfStrings*((this.AmountOfRowsBeforeScroll+1)*padding.y);
			width=sizeOfStrings*(((this.amountOfCollumns)*(this.widthOfEachStringSpot+padding.x))+padding.x);
			if(!this.ParrentNode.isLeaf()) {
				  float amount=((this.currentTopRow*this.amountOfCollumns)+(this.amountOfCollumns*this.AmountOfRowsBeforeScroll));
				   if(amount>this.ChildrenList.size()) {
					   amount=this.ChildrenList.size();
				   }
				
				for(int i=this.currentTopRow*this.amountOfCollumns;i<(amount);i++) {
					Vector2f slotOffset=new Vector2f(i%this.amountOfCollumns,(i/this.amountOfCollumns)+1);
					Vector2f newposition=new Vector2f();
					TextBuilder text=this.ChildrenList.get(i).getString();
					slotOffset.y=-((slotOffset.y-this.currentTopRow)*padding.y*sizeOfStrings);
					slotOffset.x=(slotOffset.x*sizeOfStrings*(this.widthOfEachStringSpot+padding.x));
					position.add(slotOffset,newposition);
					
				    text.drawString(newposition.x, newposition.y,sizeOfStrings);
					
				}
				
				Vector2f currentSlot=movement.getCurrentPosition();
				if(currentSlot!=null) {
			    int i=(int)currentSlot.x+this.amountOfCollumns*(int)-currentSlot.y;
			
			  
				Vector2f slotOffset=new Vector2f(i%this.amountOfCollumns,(i/this.amountOfCollumns)+1);
			    slotOffset.y=(-((slotOffset.y-this.currentTopRow)*padding.y*sizeOfStrings));
				slotOffset.x=(slotOffset.x*sizeOfStrings*(this.widthOfEachStringSpot+padding.x))-30*sizeOfStrings;
				MainRenderHandler.addEntity(new RenderEntity(Start.Arrow, new Vector3f(position.add(slotOffset,new Vector2f()),100000),0,sizeOfStrings*50,ArrowTex));//draw the arrow
				}
				
				
				
			}
			    GUINode node=this.getCurrentNodeFromSelector();
			    if(node!=null){
			    	node.InvokeGUINodeFuntion(position,padding,sizeOfStrings);
			    }
	   	   		MainRenderHandler.addEntity(new RenderEntity(Start.background,new Vector3f((position.x+width/2)-(padding.x*sizeOfStrings),position.y-(height/2),10000),0,new Vector2f(width+(padding.x*sizeOfStrings),height),Start.COLTEX,this.backgroundColor));
			
			}
	   		
	   		
	   	}
		public void reset() {
		    if(!this.backStack.isEmpty()) {
		    	this.changeParentNode(this.backStack.firstElement());
		    	this.backStack.clear();
		    	this.posStack.clear();
		    }
			this.movement.resetCurrentPosition();
			Start.DebugPrint("RESET");
		}
		public void setBackgroundColor(Vector4f backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		public float getWidth(float sizeOfStrings,Vector2f padding) {
			
			return 	sizeOfStrings*(((this.amountOfCollumns)*(this.widthOfEachStringSpot+padding.x))+padding.x);
		}
	
	
		
		
		
   public GUINode GetCurrentNode() {
	   return this.getCurrentNodeFromSelector();
   }
public GUINode getParrentNode() {
	return ParrentNode;
}
public void setParrentNode(GUINode parrentNode) {
	ParrentNode = parrentNode;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
