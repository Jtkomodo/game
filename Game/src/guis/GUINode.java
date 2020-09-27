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
   private TextBuilder string=Start.text1;
   private GUINodeFunction Nodefunction;
   private String s;
   
   public GUINode(String string,GUIfunction function) {
	     this.function=function;
	     this.string.setString(string);
	     this.s=string;
   }
   public GUINode(String string,GUIfunction function,GUINodeFunction NodeFunction) {
	     this.function=function;
	     this.string.setString(string);
	     this.Nodefunction=NodeFunction;
	     this.s=string;
 }
   public int getAmountOfChildren() {
	   return this.childrenNodes.size();
   }
   
   
   public void setString(String string) {
	   this.s=string;
   }
    
   public  GUINode(GUINode[] nodes,String string,int amountOfCollumns,int amountOfRows) {
	   for(int i=0;i<nodes.length;i++) {
		   this.addChild(nodes[i]);
	   }
	   this.s=string;
	  setDiminsions(amountOfCollumns,amountOfRows);
   }
   
   
   public void setFunctuin(GUIfunction function) {
	   this.function=function;
   }
   
   public void InvokeGUINodeFuntion(Vector2f position,Vector2f padding,float size) {
	   if(this.Nodefunction!=null) {
		   this.Nodefunction.invoke(position,padding,size);
	   }
   }
   
   
   
   public void InvoleFunction() {
	   if(this.function!=null) {
	    this.function.invoke();;
	   }
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
	
	if(!this.isLeaf()) {
		this.string.setString(s+"->");
	}else {
		this.string.setString(s);
	}
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
public void removeLast() {
	if(!this.childrenNodes.isEmpty()) {
	this.childrenNodes.removeLast();
	}
}
public void removeFirst() {
	if(!this.childrenNodes.isEmpty()) {
	this.childrenNodes.removeFirst();
	}
}
	
}
