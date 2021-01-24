package entities;

import gameEngine.RenderEntity;

public class ComponentRender extends EntityComponent {

	private final static int COMPID=0x2001;//EntityComponent -1

	
	
	public RenderEntity e;
	public Entity entity;
	
	public ComponentRender(RenderEntity e) {
		super(COMPID);
		this.e=e;
	}

	@Override
	protected void INIT(Entity entity) {
		
		this.entity=entity;
	}
	@Override
	protected boolean DISABLE() {
	
		return false;
	}

	@Override
	protected void GAMELOOP_TICK() {
		
		
	}

	@Override
	protected void RENDER_TICK() {
		// TODO Auto-generated method stub
		
	}

}
