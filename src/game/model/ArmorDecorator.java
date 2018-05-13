package game.model;

import game.model.entity.creatures.ConcretePlayer;
import game.model.entity.creatures.Player;

public class ArmorDecorator implements ConcretePlayer{
	
	protected Player player;
	
	public void setArmored() {
		
		player.setArmored();
	}
	
	public void evade() {
		
		player.evade();
	}
}
