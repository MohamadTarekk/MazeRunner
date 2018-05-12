package game.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	public static BufferedImage bomb;
	public static BufferedImage stone, stone0, stone5, stone6, stone7, stone8, stone9;
	public static BufferedImage dirt, grass, tree, rock;
	public static BufferedImage wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;

	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		wood = sheet.crop(width, height, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		// dirt = sheet.crop(width, 0, width, height);
		// grass = sheet.crop(width * 2, 0, width, height);
		// stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
		
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/woodWall.png"));
		dirt = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/path.png"));
		grass = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/bomb.png"));
		bomb = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone1.png"));
		stone = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone0.png"));
		stone0 = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone5.png"));
		stone5 = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone6.png"));
		stone6 = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone7.png"));
		stone7 = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone8.png"));
		stone8 = sheet.crop(0, 0, width, height);
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/stone9.png"));
		stone9 = sheet.crop(0, 0, width, height);

	}
	
	public static BufferedImage getStone(int id) {
		return stone;
		/*
		switch(id) {
		case 0:
			return stone0;
		case 5:
			return stone5;
		case 6:
			return stone6;
		case 7:
			return stone7;
		case 8:
			return stone8;
		default:
			return stone9;
		}
		//*/
	}
}
