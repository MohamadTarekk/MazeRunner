package game.worlds;

import java.awt.Graphics;

import game.controller.Handler;
import game.model.ItemFactory;
import game.model.ItemManager;
import game.model.entity.EntityManager;
import game.model.entity.creatures.Monster;
import game.model.entity.creatures.Player;
import game.utils.Utils;
import game.view.Tile;


public class World {

	private ItemFactory itemFactory = new ItemFactory();
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	// Item
	private ItemManager itemManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 32, 32));
		itemManager = new ItemManager(handler);
		/*
		itemManager.addItem(Item.healthBomb.createNew(3*32, 32));
		itemManager.addItem(Item.healthGift.createNew(2*32, 32));
		//*/
		
		itemManager.addItem(itemFactory.getItem("Health Bomb", 9*32, 1*32));
		itemManager.addItem(itemFactory.getItem("Health Gift", 2*32, 32));
		itemManager.addItem(itemFactory.getItem("Armor Gift", 3*32, 32));
		itemManager.addItem(itemFactory.getItem("Bullets Gift", 3*32, 2*32));
		itemManager.addItem(itemFactory.getItem("Big Health Bomb", 10*32, 32));
		//*/
		// Temporary entity code!
		/*
		entityManager.addEntity(new Tree(handler, 132, 250));
		entityManager.addEntity(new Rock(handler, 132, 450));
		entityManager.addEntity(new Rock(handler, 350, 300));
		entityManager.addEntity(new Rock(handler, 400, 345));
		entityManager.addEntity(new Tree(handler, 625, 325));
		//*/
		loadWorld(path);
		
		// To initialize the inventory
		itemManager.addItem(itemFactory.getItem("Bullets Gift", spawnX, spawnY));

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		entityManager.addEntity(new Monster(handler, 32, 2*32));;
	}
	
	public void tick(){
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.grassTile;
		return t;
	}
	
	public void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int[][] getTiles() {
		return tiles;
	}

	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}

	public ItemFactory getItemFactory() {
		return itemFactory;
	}

	public void setItemFactory(ItemFactory itemFactory) {
		this.itemFactory = itemFactory;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
