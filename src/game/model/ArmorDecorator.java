package game.model;

public abstract class ArmorDecorator implements ConcreteArmor {

	protected Armor armor;
	
	@Override
	public boolean isArmored() {
	
		return armor.isArmored();
	}
	
	@Override
	public void sacrifice() {
		
		armor.sacrifice();
	}
	

}
