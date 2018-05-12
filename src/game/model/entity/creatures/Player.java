package game.model.entity.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.controller.Handler;
import game.graphics.Animation;
import game.graphics.Assets;
import game.model.Bullet;
import game.model.Inventory;
import game.model.Item;
import game.model.Weapon;


public class Player extends Creature {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	private Inventory inventory;
	Weapon weapon = new Weapon();
	public static int firedBullets = 0;
	private boolean emptyMagazine = false;
	

	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 6;
		bounds.y = 22;
		bounds.width = 19;
		bounds.height = 10;
		
		//Animations
		animDown = new Animation(250, Assets.player_down);
		animUp = new Animation(250, Assets.player_up);
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		//Animations
		animRight.tick();
		animLeft.tick();
		animDown.tick();
		animUp.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		// Inventory
		inventory.tick();
		//Bullets
		weapon.tick();
		
	}
	
	public boolean isBreakable(int tile) {
		if(tile == 2)
			return true;
		return false;
	}

	public boolean isNearby(int i, int j) {
		int xPos = (int) (x+bounds.x)/32;
		int yPos = (int) (y+bounds.y)/32;
		if(lastState.equals("Left")) {
			if( (yPos == j) && (xPos - i == 1) )
				return true;
		} else if(lastState.equals("Right")) {
			if( (yPos == j) && (xPos - i == -1) )
				return true;
		} else if(lastState.equals("Up")) {
			if( (xPos == i) && (yPos - j == 1) )
				return true;
		} else
			if( (xPos == i) && (yPos - j == -1) )
				return true;
		return false;
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			return;
		}
		
		attackTimer = 0;
		/* malhsash lazma dlwa2ty kda kda mafiish monsters lsa
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
				return;
			}
		}*/
		int[][] myTiles = handler.getWorld().getTiles();
		for(int i = 0; i<myTiles.length; i++) {
			for(int j = 0; j<myTiles.length; j++) {
				if(isBreakable(myTiles[i][j]) && isNearby(i, j))
					myTiles[i][j] = 1;
			}
		}
		handler.getWorld().setTiles(myTiles);
		for(Item item : handler.getWorld().getItemManager().getItems()) {
			if(item.isDistructable() && isNearby(item.getX()/32, item.getY()/32)) {
				item.setPickedUp(true);
			}
		}
	}
	
	@Override
	public void die(){
		System.out.println("You lose");
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;

		if(inventory.isActive())
			return;
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		if(handler.getKeyManager().space && !handler.getKeyManager().isShooting &&!emptyMagazine) {
			handler.getKeyManager().isShooting = true;
			weapon.addBullet(new Bullet(x-handler.getGameCamera().getxOffset()
					,y - handler.getGameCamera().getyOffset(),lastState, handler));
			firedBullets ++;
			if(firedBullets == 20)
				emptyMagazine = true;
		}
	}


	@Override
	public void render(Graphics g) {
		if(!handler.getKeyManager().up && !handler.getKeyManager().down && !handler.getKeyManager().left && !handler.getKeyManager().right)
			g.drawImage(Assets.player_right[1], (int) (x - handler.getGameCamera().getxOffset()), 
					(int) (y - handler.getGameCamera().getyOffset()),width,height, null);
		else
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		weapon.render(g);
	}
	
	
	public void postRender(Graphics g){
		inventory.render(g);
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
			return Assets.player_left[0];
		} else if(lastState.equals("Right")) {
			return Assets.player_right[0];
		} else if(lastState.equals("Up")) {
			return Assets.player_up[0];
		} else
			return Assets.player_down[0];
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Weapon getWeapon() {
		return weapon;
	}
}
