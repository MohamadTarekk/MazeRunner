package game.observer;

public class PlayerScore extends Observer {

	public PlayerScore(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}
	
	@Override
	public void update() {
		//TODO update the player score on info panel
	}

}
