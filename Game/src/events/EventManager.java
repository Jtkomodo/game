package events;

import java.util.Iterator;
import java.util.LinkedList;

import org.joml.Vector2f;

import gameEngine.Start;

public class EventManager {

	
   private static LinkedList<Event> events=new LinkedList<Event>();
	
	
	public static void  Update(Vector2f position) {
		
	    Iterator<Event> i= events.iterator();
		
		while(i.hasNext()) {
			
			Event event=i.next();
			if(!event.HasStarted()) {
				event.Start();
			}				
			
			
			if(!event.HasEnded()) {
				
				
			    event.Update(position);
			}else {
				event.Finsh();
				i.remove();
				Start.DebugPrint("event "+event.toString()+" has finished");
			}
			
			
		}
		
	}
	public static void UpdateInput() {
		for(int i=0;i<events.size();i++) {
			
			events.get(i).UpdateInput();
			
		}
	}
	
	public static void endAllEvents() {
		   Iterator<Event> i= events.iterator();
			
			while(i.hasNext()) {
				
				Event event=i.next();
				event.Finsh();
				i.remove();
				Start.DebugPrint("event "+event.toString()+" has finished");
			}
		
	}
	
	public static void addEvent(Event event) {
		if(!events.contains(event)) {
			events.add(event);
			Start.DebugPrint("event "+event.toString()+" was added");
		}
	}
	
	
	
	
	
}
