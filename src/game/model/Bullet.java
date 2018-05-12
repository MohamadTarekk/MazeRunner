package game.model;

import java.awt.Graphics;

import game.graphics.Assets;

public class Bullet {
	
	private float x;
	private float y;
	private String directionOfBullet;
	public Bullet(float x , float y,String direction) {
		this.x = x;
		this.y = y;
		this.directionOfBullet = direction;
	}
	public void tick() {
		if(directionOfBullet.equals("Up"))
			y-=7;
		else if(directionOfBullet.equals("Down"))
			y+=7;
		else if(directionOfBullet.equals("Left"))
			x-=7;
		else
			x+=7;
	}
	
	public void render(Graphics g) {
		if(directionOfBullet.equals("Up"))
			g.drawImage(Assets.bullets[1], (int)x , (int) y, null);
		else if(directionOfBullet.equals("Down"))
			g.drawImage(Assets.bullets[3], (int)x , (int) y, null);
		else if(directionOfBullet.equals("Left"))
			g.drawImage(Assets.bullets[0], (int)x , (int) y, null);
		else
			g.drawImage(Assets.bullets[2], (int)x , (int) y, null);
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}

