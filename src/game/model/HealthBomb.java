package game.model;

import java.awt.image.BufferedImage;

public class HealthBomb extends Bomb {

	public HealthBomb(BufferedImage texture, String name, int id) {
		super(texture, name, id);
		setDistructable(false);
	}
	
	@Override
	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore() - 10);
			handler.getWorld().getEntityManager().getPlayer().hurt(1);
		}
	}	
}
