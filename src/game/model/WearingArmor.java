package game.model;

public class WearingArmor extends ArmorDecorator {
	
	public WearingArmor() {
		
		armor = new Armor();
		armor.putArmor();
	}
}
