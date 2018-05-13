package game.model.entity.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.controller.Handler;
import game.graphics.Animation;
import game.graphics.Assets;

public class Monster extends Creature {

	//Animations
	private Animation animDown, animUp, animLeft, animRight;

	public Monster(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		setHealth(2);
		
		//Animations
		animDown = new Animation(250, Assets.monster_down);
		animUp = new Animation(250, Assets.monster_up);
		animLeft = new Animation(250, Assets.monster_left);
		animRight = new Animation(250, Assets.monster_right);
	}
	
	String lastState = "Down";
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			lastState = "Left";
			return animLeft.getCurrentFrame();
		} else if(xMove > 0) {
			lastState = "Right";
			return animRight.getCurrentFrame();
		} else if(yMove < 0) {
			lastState = "Up";
			return animUp.getCurrentFrame();
		} else if(yMove > 0) {
			lastState = "Down";
			return animDown.getCurrentFrame();
		} else
			return getLastStateImage();
	}

	private BufferedImage getLastStateImage() {
		if(lastState.equals("Left")) {
			return Assets.monster_left[0];
		} else if(lastState.equals("Right")) {
			return Assets.monster_right[0];
		} else if(lastState.equals("Up")) {
			return Assets.monster_up[0];
		} else
			return Assets.monster_down[0];
	}

	public void move() {
		xMove = yMove = 0;
		if(lastState.equals("Down")) {
			yMove += speed;
		}
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	@Override
	public void tick() {
		//Animations
		animRight.tick();
		animLeft.tick();
		animDown.tick();
		animUp.tick();
		move();
		/*
		//Movement
		getInput();
		move();
		// Attack
		checkAttacks();
		//*/
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		System.out.println("Monster Killed!");
	}
}
