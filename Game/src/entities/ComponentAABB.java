package entities;

import Collisions.AABB;
import Collisions.ColisionHandeler;
import gameEngine.Start;

public class ComponentAABB extends EntityComponent{

	
	
	private static final int COMPID=2000;//EntityComponent -0
	
	
	private AABB aabb;
	private Entity entity;
	
	
	public ComponentAABB(AABB aabb) {
		super(COMPID);
		this.aabb=aabb;
	}
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
		
		this.entity=entity;
		ColisionHandeler.addCollision(aabb);
		
	}

	@Override
	protected boolean DISABLE() {
		boolean disabled=ColisionHandeler.remove(aabb);
		if(disabled) {
			Start.DebugPrint("AABBCOMPONENT DISABLED AND COLLISION REMOVED");
		}
		return disabled;
		
	}




	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub
		
	}




	@Override
	protected void RENDER_TICK() {
	
		
	}

}
