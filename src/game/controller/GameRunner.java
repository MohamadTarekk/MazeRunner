package game.controller;


public class GameRunner {

	public static void main(String[] args){
		Game game = new Game("Maze Runner", 640, 480);
		game.start();
	}
}
