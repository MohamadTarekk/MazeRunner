package game.observer;

import game.controller.Handler;

public abstract class Observer {

	protected Subject subject;
	protected Handler handler;
	public abstract void update();
}
