package com.zqmony.ludumdare.level.tile;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;

public class SesGraphTile extends Tile{

	public SesGraphTile(Sprite[] spritea){
		super(spritea);
	}
	
	public void update(){
		frames++;
		if(frames > 10){
			frames = 0;
		}else if(frames > 5){
			frame = 1;
		}else if(frames >= 0){
			frame = 0;
		}
		
		sprite = spritea[frame];
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}
}
