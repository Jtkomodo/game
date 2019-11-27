package entitys;

import Collisions.CollisionFunctions;
import gameEngine.Model;
import gameEngine.Texture;

public class DynamicEntity extends Entity{

	private CollisionFunctions collsion;
	
	
	DynamicEntity(Model model, Texture texture,CollisionFunctions colision) {
		super(model, texture);
		this.collsion=colision;
	}

	
    
	
	
	
}
