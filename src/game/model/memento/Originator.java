package game.model.memento;

import game.controller.State;

public class Originator {
	
	private State state;
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getStatet() {
		return state;
	}
	
	public Memento saveStateToMemento() {
		return new Memento(state);
	}
	
	public void getStateFromMemento(Memento memento) {
		state = memento.getState();
	}

}
