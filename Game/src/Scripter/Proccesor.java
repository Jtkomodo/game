package Scripter;

import java.util.LinkedList;
import java.util.Queue;

import ScripterCommands.Commands;
import gameEngine.Start;

public class Proccesor {

	
	//this is for in the future this is going to allow anything that keeps players input from happening till the commands are done
	
	private static Queue<Commands> Commands=new LinkedList<Commands>();//this is the command queue that will go through each command one by one starting from the first to be added
	private static  boolean UserInputallowed=true;
	
	public static void addComandtoQueue(Commands command) {
		Commands.add(command);
		Start.DebugPrint("added command",Proccesor.class);
	}
	public static void proccesQueue(double time2) {
		           
	if(Commands.peek()!=null) {//if the queue isn't empty then find the command currently running
		
	    setUserInputallowed(!Commands.peek().isStopsInput());//halt player input if this is a command that stops player input
	if(!Commands.peek().iscompleted()) {//if the command has not been completed then start it or update it
		if(!Commands.peek().HasBeenStarted()) {
			
			Commands.peek().Start();
		}else {
			Commands.peek().Update(time2);
		}
		
		
		
	}else {//if it has been completed remove it from the queue
		
		Start.DebugPrint("removed command "+Commands.peek().getClass().getName(),Proccesor.class);
		Commands.poll();
		
	}
	}else {
		UserInputallowed=true;
	}
	
	
	
	}
	public static  boolean isUserInputallowed() {
		return UserInputallowed;
	}
	public static void setUserInputallowed(boolean userInputallowed) {
		UserInputallowed = userInputallowed;
	}
	
	
	
	
	
}
