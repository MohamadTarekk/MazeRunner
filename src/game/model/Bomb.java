package game.model;

import java.awt.image.BufferedImage;

public abstract class Bomb extends Item {

	public Bomb(BufferedImage texture, String name, int id) {
		super(texture, name, id);
		setDistructable(true);
	}
}
