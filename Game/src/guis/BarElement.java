package guis;

import org.joml.Vector2f;

import battleClasses.Bars;
import gameEngine.Start;
import textrendering.TextBuilder;

public class BarElement extends UIElement {

	
	
	

	private Bars bar;
	private TextBuilder text=new TextBuilder(Start.aakar); 
	private String string;
	
	
	public BarElement(String string,Bars bar,Vector2f offfset) {
		super(offfset);
		this.string=string;
		this.bar=bar;
	}

	@Override
    protected void drawElement(Vector2f Position) {
		Vector2f noffset=new Vector2f();
		offset.add(Position,noffset);
		this.text.setString(string+" "+Math.round(bar.getValue())+"/"+Math.round(bar.getMax()));
		

		bar.draw(noffset,text);	
	
	}
     
	public void setValue(float value) {
		this.bar.setValue(value);
	}
	
}
