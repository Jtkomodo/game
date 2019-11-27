package battleClasses;

import animation.Animate;

public class ButtonCombo {

	final int Miss=0,Hit=1,ExHit=2;
	
	
	private int amountNeededRight,amountForEx;
	private int[] Combo;
	private int[] TriggerFrames;
	private Animate[] Animations;
	private boolean running=false;
	
	
	public ButtonCombo(int[] Combo,int[] TriggerFrames,Animate[] Animations,int amountNeededRight,int amountForEx) {
		this.amountNeededRight=amountNeededRight;
		this.amountForEx=amountForEx;
		this.Combo=Combo;
		this.TriggerFrames=TriggerFrames;
		this.Animations=Animations;
		
		
		
		
	}
	
   
	
	
	
	
	
	
	
	
}
