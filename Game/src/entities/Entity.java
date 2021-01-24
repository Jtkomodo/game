package entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import battleMoves.MoveComponent;
import gameEngine.RenderEntity;
import gameEngine.Start;
import gameEngine.Texture;
import rendering.MainRenderHandler;
import rendering.ModelFramwork;

public class Entity {

    protected Vector2f position=new Vector2f();
    protected Vector2f Velocitity=new Vector2f();
    protected float size;
    protected Texture texture;
    
    
    private HashMap<Integer,EntityComponent> components=new HashMap<Integer,EntityComponent>();
	
	public Entity(EntityComponent[] components,Texture texture,float size) {
		addComponents(components);
	}
	public void GameLoop_Tick() {
		 Iterator<EntityComponent> i=this.components.values().iterator();
		 while(i.hasNext()) {
			 EntityComponent component=i.next();
			 component.GAMELOOP_TICK();
		 }
	}
	
	public void addComponents(EntityComponent[] components) {
		for(int i=0;i<components.length;i++) {
			AddComponent(components[i]);
		}
	}
   
	
	public void AddComponent(EntityComponent component) {
		this.components.put(component.getID(), component);
		component.INIT(this);
	}
	
	public boolean RemoveComponent(int ComponentID) {
		boolean removed=false;
		if(this.components.containsKey(ComponentID)) {
			EntityComponent entity=this.components.get(ComponentID);
			boolean disabled=entity.DISABLE();
		    removed=disabled && this.components.remove(ComponentID,entity);
		   
		}
		if(removed) {
			Start.DebugPrint("ComponentID:"+ComponentID+" remove succsess");
		}
		
		
		return removed;
	}
	
	public boolean hasComponent(int ID) {
		return this.components.containsKey(ID);
	}
	
	
	public <C extends EntityComponent> C getComponent(int ID){
		if(this.components.containsKey(ID)) {
			return (C)this.components.get(ID);
		}else {
			return null;
		}
		
	}
	
	
}
