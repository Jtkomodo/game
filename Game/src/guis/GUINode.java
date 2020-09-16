package guis;

import java.util.LinkedList;

import org.joml.Vector2f;

import gameEngine.ArrowKeySelecter;
import gameEngine.Start;
import textrendering.TextBuilder;

public class GUINode {
	
   
	
   private LinkedList<GUINode> childrenNodes=new LinkedList<GUINode>();
   private GUIfunction function=null;
   private int amountOfCollumns=1,amountOfRows=1;
   private TextBuilder string=new TextBuilder(Start.aakar);
   
   public GUINode(String string,GUIfunction function) {
	     this.function=function;
	     this.string.setString(string);
   }
   
    
   public  GUINode(GUINode[] nodes,int amountOfCollumns,int amountOfRows) {
	   for(int i=0;i<nodes.length;i++) {
		   this.addChild(nodes[i]);
	   }
	  setDiminsions(amountOfCollumns,amountOfRows);
   }
   
   
   public void setFunctuin(GUIfunction function) {
	   this.function=function;
   }
   
   public GUIfunction getFunction() {
	   return this.function;
   }
   public void addChild(GUINode node) {
	   this.childrenNodes.add(node);
	   
   }
   public void addChildren(GUINode[] nodes) {
	   for(int i=0;i<nodes.length;i++) {
		   this.addChild(nodes[i]);
	   }
   }
   
   public LinkedList<GUINode> getChildren(){
	   return this.childrenNodes;
   }
   
   public void remove(GUINode node) {
	   this.childrenNodes.remove(node);
	   
   }
   
   
   public void removeAllChildren() {
	   this.childrenNodes.clear();
	   
   }
   public boolean isLeaf() {
	   return this.childrenNodes.isEmpty();
	   
   }


public TextBuilder getString() {
	return this.string;
}




public void setDiminsions(int amountOfCollumns,int amountOfRows) {
	if(amountOfCollumns>0) {
	 this.amountOfCollumns=amountOfCollumns;
	}else {
		this.amountOfCollumns=1;
	}
	if(amountOfRows>0) {
	 this.amountOfRows=amountOfRows;
	}else {
		this.amountOfRows=1;
	}
	
	}


public int getAmountOfCollumns() {
	return amountOfCollumns;
}


public int getAmountOfRows() {
	return amountOfRows;
}
	
}
