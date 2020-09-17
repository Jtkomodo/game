package guis;

import java.util.LinkedList;
import java.util.Stack;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import Data.Constants;
import gameEngine.Entity;
import gameEngine.Start;
import rendering.MainRenderHandler;
import textrendering.TextBuilder;

public class GUIManeger {

	
	private Stack<GUINode> backStack=new Stack<GUINode>();//this will be how we do go back actions
	private LinkedList<Vector2f> positions=new LinkedList<Vector2f>();
	private LinkedList<GUINode> ChildrenList;//this is the sub menu of the parent node
	private GUINode ParrentNode;//this is the node that is the parent of the current sub menu
	private GUINode currentNode;//this is the node that we currently have the cursor on
	private int amountOfRows,amountOfCollumns;
	private int amountOfElements;
	private int currentRow,currrentCollumn,AmountOfRowsBeforeScroll;
	private float widthOfEachStringSpot;
	private Vector2f position;
	private Vector2f padding;
	private float width,height;
	private float sizeOfStrings;
   
	
	
	public GUIManeger(GUINode rootNode,Vector2f position,Vector2f padding,float sizeOfStrings) {
	
			this.ChildrenList=rootNode.getChildren();
			this.ParrentNode=rootNode;
		    this.SetPositions(position, padding, sizeOfStrings);
		
		
	}
    
    public void update() {
    	UpdatePositions(this.ParrentNode);
    }
    
    
	public void SetPositions(Vector2f position,Vector2f padding,float sizeOfstrings) {
		this.position=position;
		this.padding=padding;
		this.sizeOfStrings=sizeOfstrings;
		
		
		UpdatePositions(this.ParrentNode);
		
	}
	
	private void UpdatePositions(GUINode rootNode) {
		if(!rootNode.isLeaf()) {
	     this.ChildrenList=rootNode.getChildren();
		 this.ParrentNode=rootNode;
	     this.positions.clear();
		//find the string that has the highest length
	     if(!rootNode.isLeaf()) {
		
			float maxSize=0;
			LinkedList<GUINode> children=rootNode.getChildren();
			
			for(int i=0;i<children.size();i++) {
				TextBuilder textString=children.get(i).getString();
				if(textString!=null) { 
					
					float size=textString.getStringLength();
					 
					if(size>maxSize) {
						maxSize=size;
					}
				}
			}
	      this.widthOfEachStringSpot=maxSize;
	      this.currentNode=children.getFirst();
	 
	      
	      //calculate the amount of rows needed
	     int collumns=rootNode.getAmountOfCollumns();//this is just the value of the amount of columns
	     this.AmountOfRowsBeforeScroll=rootNode.getAmountOfRows();//this is just the value of the amount of rows
	     this.amountOfCollumns=collumns;
	     this.amountOfElements=children.size();
	     int r=this.amountOfElements/collumns;
	     if((this.amountOfElements%collumns)!=0) {
	    	 r++;
	     }
	     this.amountOfRows=r;
	     
	     
	     }
	     
	     
	 	
		 height=0;
		 width=this.amountOfCollumns*(this.widthOfEachStringSpot+padding.x);
	   		for(int i=0;i<this.AmountOfRowsBeforeScroll;i++) {
	   			float maxHeight=0;
	   			for(int j=0;j<this.amountOfCollumns;j++) {
	   				
	   				if(i*this.amountOfCollumns+j>=this.ChildrenList.size()) {
	   					break;
	   				}
	   				
	   				GUINode node=this.ChildrenList.get(i*this.amountOfCollumns+j);
	   				
	   				if(node==null) {
	   					break;
	   				}
	   			   TextBuilder text=node.getString();
	   			   float x=position.x+(sizeOfStrings*(this.widthOfEachStringSpot+padding.x))*j;
	   			   float y=(position.y-(((padding.y)*sizeOfStrings)*(i+1)));
	   			   this.positions.add(new Vector2f(x,y));
	   			//   text.drawString(x,y, sizeOfStrings);
	   			if(text.getStringHieght()>maxHeight) {
	   				maxHeight=text.getStringHieght();
	   			}
	   				
//	   		    MainRenderHandler.addEntity(new Entity(Start.background, new Vector3f(x,y,1000000), 0, 1,Start.COLTEX,Constants.RED));
	   		    
	   			}
	   		   height+=(maxHeight+padding.y);
	   		 
	   		}
	   		width*=sizeOfStrings;
	   		height*=sizeOfStrings;
	   		
	   
	   		
		}
	     
	    
	}
	

	public void draw(Vector4f color) {
		if(!this.ParrentNode.isLeaf()) {				
		for(int i=0;i<this.positions.size();i++) {
            TextBuilder text=this.ChildrenList.get(i).getString();
			Vector2f position=this.positions.get(i);
			text.drawString(position.x, position.y, this.sizeOfStrings,color);
			
		}
   		MainRenderHandler.addEntity(new Entity(Start.background,new Vector3f((position.x+width/2)-(padding.x*sizeOfStrings),position.y-(height/2),10000),0,new Vector2f(width+(padding.x*sizeOfStrings),height),Start.COLTEX,Constants.BLACK));
		}
   		
		
	}
	
	
   
	public void draw() {
		if(!this.ParrentNode.isLeaf()) {
       for(int i=0;i<this.positions.size();i++) {
            TextBuilder text=this.ChildrenList.get(i).getString();
			Vector2f position=this.positions.get(i);
			text.drawString(position.x, position.y, this.sizeOfStrings);
			
		}
   		MainRenderHandler.addEntity(new Entity(Start.background,new Vector3f((position.x+width/2)-(padding.x*sizeOfStrings),position.y-(height/2),10000),0,new Vector2f(width+(padding.x*sizeOfStrings),height),Start.COLTEX,Constants.BLACK));
		}
   		
   		
   	}
	
	
  
	
	
	
	
	
	
	
	
	
	
	
	
	
}
