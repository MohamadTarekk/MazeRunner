package game.model;

public class Armor implements ConcreteArmor {

	private boolean armor;
	
	public Armor() {

		armor = false;
	}
	
	public void putArmor() {
		
		armor = true;
	}
	
	@Override
	public boolean isArmored() {
	
		return armor;
	}
	
	@Override
	public void sacrifice() {
		
		armor = false;
	}
	

}
