package gameEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.joml.Vector2f;

import guis.UIElement;

public class ArrowKeySelecter {
	
	private HashMap<Float,Integer> AmountOfXpos=new HashMap<Float,Integer>();//holds the amount of each xpositions there are
	private HashMap<Float,Integer> AmountOfYpos=new HashMap<Float,Integer>();//same but for y
    private List<Float> xPos=new LinkedList<Float>();//list of each unique xPosition sorted	
	private List<Float> yPos=new LinkedList<Float>();//list of each unique yPosition sorted
	private List<Vector2f> positions=new LinkedList<Vector2f>();	
	
	
	
	
	private Vector2f currentPosition;
	private Vector2f BeginPosition;
	private int CurrentIndexPositionX=0;
	private int CurrentIndexPositionY=0;
	
	
	public ArrowKeySelecter(Vector2f[] positions) {
		addPositions(positions);
		
		
	}


	public void addPositions(Vector2f[] positions) {
		
		for(int i=0;i<positions.length;i++) {
			
			Vector2f p=positions[i];
				
				
				//if__Active_______________________________________________________________
			
				
				   
					
					
					if(this.BeginPosition==null) {
						this.BeginPosition=p;
						
					}
					Vector2f vector=p;//get the offset of the vector								
					Vector2f vector2=this.BeginPosition;
					
					//if this element is before the begin element from left to right up to down then it becomes the new begin element
					if(vector.y>vector2.y) {
						
						
						this.BeginPosition=p;
						
					}else if(vector.y==vector2.y) {
						if(vector.x<vector2.x) {
							this.BeginPosition=p;
						}
						
					}
					//load in the Vector and Element to the Values map
					
					this.positions.add(vector);
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
			
			
			
			//sort the two so that we can easily find the next position to go to based on direction
			Collections.sort(this.xPos);
			Collections.sort(this.yPos);
			
			
	
			if(this.currentPosition==null) {    	
				setActivePosition(this.BeginPosition);}
		
	}
	
	
   public void removePosition(Vector2f p) {
	   
	   if(this.positions.contains(p)) {
			int amountx=this.AmountOfXpos.get(p.x);
			int amounty=this.AmountOfYpos.get(p.y);	
			if(amountx==1) {	
			this.AmountOfXpos.remove(p.x);
			this.xPos.remove(p.x);
			}
			else {	
				this.AmountOfXpos.put(p.x, --amountx);
				
			}
			
			if(amounty==1) {	
				this.AmountOfYpos.remove(p.y);
				this.yPos.remove(p.y);
				}
				else {	
					this.AmountOfYpos.put(p.y, --amounty);
					
				}
			
				
		   this.positions.remove(p);
				
		
		   if(this.BeginPosition.equals(p)) {
				  resetBeginPosition(); 
				   
			   }   
		   
		if(this.currentPosition.equals(p)) {
			
			resetCurrentPosition();
		}
	  
	   }
   }
	
	private void resetBeginPosition() {
	
		if(positions.isEmpty()) {
            this.BeginPosition=new Vector2f(0,0);
		}else {
			
			this.BeginPosition=positions.get(0);
		}
}


	private void resetCurrentPosition() {
	if(positions.isEmpty()) {
		this.currentPosition=null;
		this.CurrentIndexPositionX=0;
		this.CurrentIndexPositionY=0;
		
		
	}else {
		setActivePosition(BeginPosition);
		
		
		
		
	}
	
}


	public void removePositions(Vector2f[] positions) {
		
		
		
		for(int i=0;i<positions.length;i++) {
			Vector2f p=positions[i];
			removePosition(p);
			
			
			
			
			
			
		}
		
		Collections.sort(this.xPos);
		Collections.sort(this.yPos);
		
	}


	private void setActivePosition(Vector2f vector) {
	
    
		 if(vector!=null) {	
    	this.CurrentIndexPositionX=this.xPos.indexOf(vector.x);
    	this.CurrentIndexPositionY=this.yPos.indexOf(vector.y);
    	this.currentPosition=vector;
  
    	
    }else {
       this.currentPosition=null;
      
    		   
    }
		
	}
	
	
	  private void removeAllPositions() {
	       
		  int amount=0;
        
         
            	
            	
            
            	
            	this.xPos.clear();
            	this.yPos.clear();
            	this.positions.clear();
            	this.AmountOfXpos.clear();
            	this.AmountOfYpos.clear();
            
            	resetBeginPosition();
            	
	  }
	
   public void relpaceALLActive(Vector2f[] positions) {
	   removeAllPositions();
	   addPositions(positions);
	  
   }

	


	public void addPosition(Vector2f position) {
		
		addPositions(new Vector2f[] {position});
	}
	
	

	public void moveLeft() {
		
		
	}
	
	public void moveRight() {
		
		
	}
	
	
	public void moveUP() {
		
		
	}
	
	public void moveDown() {
		
		
	}


	public Vector2f getCurrentPosition() {
		return currentPosition;
	}



	
	
	
	

}
