	package game.observer;

public class PlayerHealth extends Observer {

	public PlayerHealth(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}
	
	@Override
	public void update() {
		//TODO update player health on info panel
	}

}
