package game.model;

import java.awt.Graphics;
import java.util.LinkedList;


public class Weapon {

	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	
	public void tick() {
		for(Bullet b : bullets) {
			b.tick();
		}
	}
	
	public void render(Graphics g) {
		for(Bullet b : bullets) {
			b.render(g);
		}
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}

	public LinkedList<Bullet> getBullets() {
		return bullets;
	}
	

	
	
}
