package game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.controller.Handler;
import game.graphics.Assets;

public class Bullet {
	
	private float x;
	private float y;
	private String directionOfBullet;
	private Rectangle bounds;
	private Handler handler;

	public Bullet(float x , float y,String direction,Handler handler) {
		this.x = x;
		this.y = y;
		this.directionOfBullet = direction;
		this.handler = handler;
		bounds = new Rectangle(0,0,32,32);
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
		checkifCollide();
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
	
	
	public void checkifCollide() {
		for(Item i : handler.getWorld().getItemManager().getItems()) {
				if(getCollisionBounds(0f, 0f).intersects(i.bounds)) {
					i.setPickedUp(true);
				}
		}
	}
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}

