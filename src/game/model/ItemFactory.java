package game.model;

import game.graphics.Assets;

public class ItemFactory {

	public Item getItem(String name, int x, int y) {
		
		if(name.equals("Health Bomb")) {
			HealthBomb hb = new HealthBomb(Assets.healthBomb, "Health Bomb", 0);
			hb.setPosition(x, y);
			return hb;
		}
		else if(name.equals("Health Gift")) {
			HealthGift hg = new HealthGift(Assets.healthGift[0], "Health Gift", 1);
			hg.setPosition(x, y);
			return hg;
		}
		else
			return null;		
	}
}
