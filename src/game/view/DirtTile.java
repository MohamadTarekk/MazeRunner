package game.view;

import game.graphics.Assets;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
	
	@Override
	public boolean isBreakable(){
		return true;
	}
}
