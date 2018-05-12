package game.model;

import java.awt.image.BufferedImage;

public class Bomb extends Item {

	public Bomb(BufferedImage texture, String name, int id) {
		super(texture, name, id);

	}
	
	@Override
	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().hurt(1);
		}
	}
	
}
