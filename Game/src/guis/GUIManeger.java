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
import gameEngine.Entity;
import gameEngine.Start;
import gameEngine.Texture;
import input.InputHandler;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class GUIManeger {

	
	private Stack<GUINode> backStack=new Stack<GUINode>();//this will be how we do go back actions
	private LinkedList<GUINode> ChildrenList;//this is the sub menu of the parent node
	private GUINode ParrentNode;//this is the node that is the parent of the current sub menu
	private GUINode currentNode;//this is the node that we currently have the cursor on
	private int amountOfRows,amountOfCollumns;
	private int currentTopRow,AmountOfRowsBeforeScroll;
	private ArrowKeySelecter movement=new ArrowKeySelecter(false);
	private float widthOfEachStringSpot;
	private float width,height;
	private Texture ArrowTex=Start.testSprite;
	private boolean currentlyActive=false;
	
	
	public GUIManeger(GUINode rootNode) {
	
			this.ChildrenList=rootNode.getChildren();
			this.ParrentNode=rootNode;
			this.currentTopRow=0;
		    this.UpdateSlots(rootNode);
		    
		
		
	}
	public void open() {
		this.currentlyActive=true;
	}
    public void close() {
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
    		
    			if(up==1) 
    				moveUp();
    			if(down==1)	
    			   moveDown();
    			if(left==1)
    				moveLeft();
    			if(right==1)
    				moveRight();
    	        if(Enter==1) {
    	        	Select();
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
    
    
    
    
    private void Select() {
		Vector2f currentSlot=movement.getCurrentPosition();
		if(currentSlot!=null && !this.ParrentNode.isLeaf()) {
	    int i=(int)currentSlot.x+this.amountOfCollumns*(int)-currentSlot.y;
	    this.currentNode=this.ChildrenList.get(i);
	    this.ParrentNode.remove(this.currentNode);
	    this.UpdateChanges();
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
	
	
	
	private void moveUp() {
		movement.moveUP();	
		Start.DebugPrint("UP______________");
	}
	private void moveDown() {
		movement.moveDown();
		Start.DebugPrint("DOWN______________");
	}
	private void moveRight() {
		movement.moveRight();
		Start.DebugPrint("RIGHT______________");
	}
	private void moveLeft() {
	  movement.moveLeft();
	  	Start.DebugPrint("LEFT______________");
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
				slotOffset.x=(slotOffset.x*sizeOfStrings*(this.widthOfEachStringSpot+padding.x))-(padding.x/2)*sizeOfStrings;
				MainRenderHandler.addEntity(new Entity(Start.Arrow, new Vector3f(position.add(slotOffset,new Vector2f()),100000),0,10,ArrowTex));//draw the arrow
				}
				
				
				
			}
	   	   		MainRenderHandler.addEntity(new Entity(Start.background,new Vector3f((position.x+width/2)-(padding.x*sizeOfStrings),position.y-(height/2),10000),0,new Vector2f(width+(padding.x*sizeOfStrings),height),Start.COLTEX,Constants.BLACK));
			
			}
	   		
	   		
	   	}
	
	
  
	
	
	
	
	
	
	
	
	
	
	
	
	
}
