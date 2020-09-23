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
	
	private boolean FieldMode=false;
	
	
	private Vector2f currentPosition=null;
	private Vector2f BeginPosition=null;
	private int CurrentIndexPositionX=0;
	private int CurrentIndexPositionY=0;
	
	
	

	public ArrowKeySelecter(Vector2f[] positions,boolean FieldMode) {
	     this.FieldMode=FieldMode;
		addPositions(positions);
	}
	public ArrowKeySelecter(boolean FieldMode) {
		this.FieldMode=FieldMode;
	}

	public void addPositions(Vector2f[] positions) {
		
		for(int i=0;i<positions.length;i++) {
			
			Vector2f vector=positions[i];
				
				
				//if__Active_______________________________________________________________
			
				
				   
					
				if(!this.positions.contains(vector)) {	
					if(this.BeginPosition==null) {
						this.BeginPosition=vector;
						
					}
									
					Vector2f vector2=this.BeginPosition;
					
					//if this element is before the begin element from left to right up to down then it becomes the new begin element
					if(vector.y>vector2.y) {
						
						
						this.BeginPosition=vector;
						
					}else if(vector.y==vector2.y) {
						if(vector.x<vector2.x) {
							this.BeginPosition=vector;
						}
						
					}
					//load in the Vector and Element to the Values map
					
					this.positions.add(vector);
					float x=vector.x;
					float y=vector.y;
					
					if(!this.xPos.contains(x)) {//add this x position if unique to the xPosition list
						xPos.add(x);
					}
					if(!this.yPos.contains(y)) {
						yPos.add(y);
					}
					
					
						
					
					//now add to the amount of each position chord in respected map 
					
					this.AmountOfXpos.put(x,(AmountOfXpos.getOrDefault(x,0))+1);
					this.AmountOfYpos.put(y,(AmountOfYpos.getOrDefault(y,0))+1);
					
					
					
				}
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
	
		if(this.currentPosition!=null && this.positions.contains(this.currentPosition)){
	    	Vector2f vector=this.currentPosition;
	    
	    	this.CurrentIndexPositionX=this.xPos.indexOf(vector.x);
	    	this.CurrentIndexPositionY=this.yPos.indexOf(vector.y);
	    	
	    	
	    	 this.BeginPosition=vector;
	    	
	    	}else if(!positions.isEmpty()) {
	    		//Vector2f vector=this.currentPosition;
	    			setActivePosition(this.positions.get(0));
	    			
	    	    	 this.BeginPosition=this.currentPosition;
	    		    	
	    	    	
	    	    	
	    		}
	    		
	    	else {	
	    	this.BeginPosition=null;
	    	}
	    		
}


	public void resetCurrentPosition() {
	if(positions.isEmpty()) {
		this.currentPosition=null;
		this.CurrentIndexPositionX=0;
		this.CurrentIndexPositionY=0;
		
		
	}else {
		setActivePosition(BeginPosition);
		
		
		
		
	}
	
}
public void setBySameOffset() {

    	Vector2f vector=this.currentPosition;
  if(vector!=null && this.positions.contains(vector)) {  
    	setActivePosition(vector);
    	
  }else {
	  resetCurrentPosition();
  }
    	
  if(BeginPosition==null) {
      resetBeginPosition();
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
	
    if(this.positions.contains(vector)) {
	
    	this.CurrentIndexPositionX=this.xPos.indexOf(vector.x);
    	this.CurrentIndexPositionY=this.yPos.indexOf(vector.y);
    	this.currentPosition=vector;
    }else {
    	this.currentPosition=null;
    }
    	
	}
	
	
	  public void removeAllPositions() {
	       
		  
        
         
            	
            	
            
            	
            	this.xPos.clear();
            	this.yPos.clear();
            	this.positions.clear();
            	this.AmountOfXpos.clear();
            	this.AmountOfYpos.clear();
               
            	resetBeginPosition();
            	
	  }
	
   public void relpaceAllPositions(Vector2f[] positions) {
	   removeAllPositions();
	   addPositions(positions);
	   resetBeginPosition();
	   resetCurrentPosition();
   }

	


	public void addPosition(Vector2f position) {
		
		addPositions(new Vector2f[] {position});
	}
	
	

	public boolean moveLeft() {
		boolean found=false;
		if(this.currentPosition!=null) {
			  //this.setActivePosition(this.currentPosition);

		float x=this.xPos.get(this.CurrentIndexPositionX);

	
	
		    Vector2f closest=new Vector2f(0);
		
		    float closestx=0;
		  
		    for(int i=0;i<this.positions.size();i++) {
		    	Vector2f vector=positions.get(i);
		    	if(!vector.equals(this.currentPosition) && x>vector.x) {
		    	   float value=x-vector.x;
		    	if(this.FieldMode) {
		    		if(vector.y!=this.currentPosition.y) {
		    			if(value<=closestx || closestx==0) {

		    				if(closestx!=value || closest.y<vector.y ) {
		    					closestx=value;
		    					closest=vector;
		    					found=true;
		    				}}
		    		}}else {
		    			if(vector.y==this.currentPosition.y) {
			    			if(value<=closestx || closestx==0) {

			    				if(closestx!=value || closest.y<vector.y ) {
			    					closestx=value;
			    					closest=vector;
			    					found=true;
			    				}
			    				}
		    		
		    		
		    		
		    		}
		    	
		    	}}
		    }
		    if(found) {
		    
		    	 setActivePosition(closest);
	    }
		        	
		
		Start.DebugPrint("current="+this.CurrentIndexPositionX+","+this.CurrentIndexPositionX+" indexs="+this.xPos.size()+","+this.yPos.size());
		}
		return found;
	}
	
	public boolean moveRight() {
		boolean found=false;
		if(this.currentPosition!=null) {
		
      //  this.setActivePosition(this.currentPosition);
		    float x=this.xPos.get(this.CurrentIndexPositionX);

	
	
		    Vector2f closest=new Vector2f(0);
		    Vector2f closestLine=new Vector2f(0);
		    float closestx=0;
		    float closestxOnSameLine=0;
		    for(int i=0;i<this.positions.size();i++) {
		    	Vector2f vector=positions.get(i);
		    	if(!vector.equals(this.currentPosition) && x<vector.x) {
		    	   float value=vector.x-x;
		    	
		    		if(this.FieldMode) {
		    			if(vector.y!=this.currentPosition.y) {
		    				if(value<=closestx || closestx==0) {
		    					if(value!=closestx|| closest.y>vector.y) {
		    						closestx=value;
		    						closest=vector;
		    						found=true;
		    					}
		    				}
		    	   }}else {
				    	if(!vector.equals(this.currentPosition) && x<vector.x) {
							if(vector.y==this.currentPosition.y) {
								if(value<=closestx || closestx==0) {
						    		if(value!=closestx|| closest.y>vector.y) {
						    		closestx=value;
						    		closest=vector;
						    		found=true;
						    		}
						    	
						    	}
								
							
				    	
				    	
				    	
				    	
				    	 }
		    	   }
		    	}
		
		
		
		    }
		    
		    
		    	  if(found) {
				
				    	 setActivePosition(closest);
		    }
				         	
		   
		
			
			 
				
			}
			Start.DebugPrint("current="+this.CurrentIndexPositionX+","+this.CurrentIndexPositionY+" indexs="+this.xPos.size()+","+this.yPos.size());
			}
		
		return found;
		
	
	}
	
	
	public boolean moveUP() {
		boolean found=false;
		if(this.currentPosition!=null) {
			//  this.setActivePosition(this.currentPosition);

		float y=this.yPos.get(this.CurrentIndexPositionY);

		
		boolean foundOnSameLine=false;		 
		    Vector2f closest=new Vector2f(0);
		    Vector2f closestLine=new Vector2f(0);
		    float closesty=0;
		    float closestyOnSameLine=0;
		    for(int i=0;i<this.positions.size();i++) {
		    	Vector2f vector=positions.get(i);
		    	if(!vector.equals(this.currentPosition) && y<vector.y) {
		    	   float value=vector.y-y;
		    	
		    	   
		    	   if(vector.x!=this.currentPosition.x) {
				    	if(value<=closesty || closesty==0) {
				    		closesty=value;
				    		closest=vector;
				    		found=true;
				    	}
				    	 }else {
				    		 if(value<=closestyOnSameLine || closestyOnSameLine==0) {
						    		closestyOnSameLine=value;
						    		closestLine=vector;
						    		found=true;
						    		foundOnSameLine=true;;
						    	}	 
				    		 
				    	 }
		    	}
		
		
		
		    }
		    
		    
		    	  if(found) {
				    	if(!foundOnSameLine) {
				    	 setActivePosition(closest);
				    	}else {
				    		setActivePosition(closestLine);
				    	}
				    }
				         	
		   
		Start.DebugPrint("current="+this.CurrentIndexPositionX+","+this.CurrentIndexPositionY+" indeys="+this.xPos.size()+","+this.yPos.size());
		}
		return found;
		
		
	}
	
	public boolean moveDown() {
		
		boolean found=false;
		if(this.currentPosition!=null) {
			//  this.setActivePosition(this.currentPosition);

		float y=this.yPos.get(this.CurrentIndexPositionY);

		
		boolean foundOnSameLine=false;		 
		    Vector2f closest=new Vector2f(0);
		    Vector2f closestLine=new Vector2f(0);
		    float closesty=0;
		    float closestyOnSameLine=0;
		    for(int i=0;i<this.positions.size();i++) {
		    	Vector2f vector=positions.get(i);
		    	if(!vector.equals(this.currentPosition) && y>vector.y) {
		    	   float value=y-vector.y;
		    	
		    	   
		    	   if(vector.x!=this.currentPosition.x) {
				    	if(value<=closesty || closesty==0) {
				    		closesty=value;
				    		closest=vector;
				    		found=true;
				    	}
				    	 }else {
				    		 if(value<=closestyOnSameLine || closestyOnSameLine==0) {
						    		closestyOnSameLine=value;
						    		closestLine=vector;
						    		found=true;
						    		foundOnSameLine=true;;
						    	}	 
				    		 
				    	 }
		    	}
		
		
		
		    }
		    
		    
		    	  if(found) {
				    	if(!foundOnSameLine) {
				    	 setActivePosition(closest);
				    	}else {
				    		setActivePosition(closestLine);
				    	}
				    }
				         	
		   
		Start.DebugPrint("current="+this.CurrentIndexPositionX+","+this.CurrentIndexPositionY+" indeys="+this.xPos.size()+","+this.yPos.size());
		}
		return found;
				
	}


	public Vector2f getCurrentPosition() {
		return currentPosition;
	}

    
	public Vector2f getBeginPosition() {
		return this.BeginPosition;
	}

	
	
	public Vector2f getIndex(Vector2f v) {
	  return new Vector2f(this.xPos.indexOf(v.x),this.yPos.indexOf(v.y));
		
	}


	public void setCurrentPosition(Vector2f position) {
		if(positions.contains(position)) {
		    setActivePosition(position);
		}
		}
	

}
