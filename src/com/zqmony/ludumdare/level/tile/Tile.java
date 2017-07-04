package com.zqmony.ludumdare.level.tile;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;

public class Tile {
	
	public static Tile grasstile = new GroundTile(Sprite.grasstile);
	public static Tile bricktile = new WallTile(Sprite.bricktile);
	public static Tile woodtile = new GroundTile(Sprite.woodtile);
	public static Tile sesgraphtile = new SesGraphTile(Sprite.sisgraph);
	
	public static Tile groundtile = new GroundTile(Sprite.dirttile);
	public static Tile spawntile = new GroundTile(Sprite.spawntile);
	
	public static Tile[] walltile = {new WallTile(Sprite.walltile[0]),
									 new WallTile(Sprite.walltile[1]),
									 new WallTile(Sprite.walltile[2]),
									 new WallTile(Sprite.walltile[3]),
									 new WallTile(Sprite.walltile[4]),
									 new WallTile(Sprite.walltile[5]),
									 new WallTile(Sprite.walltile[6]),
									 new WallTile(Sprite.walltile[7]),
									 new WallTile(Sprite.walltile[8]),
									 new WallTile(Sprite.walltile[9]),
									 new WallTile(Sprite.walltile[10]),
									 new WallTile(Sprite.walltile[11])};
	
	public static Tile[] holetile = {new HoleTile(Sprite.holetile[0]),
									 new HoleTile(Sprite.holetile[1]),
									 new HoleTile(Sprite.holetile[2]),
									 new HoleTile(Sprite.holetile[3]),
									 new HoleTile(Sprite.holetile[4]),
									 new HoleTile(Sprite.holetile[5]),
									 new HoleTile(Sprite.holetile[6]),
									 new HoleTile(Sprite.holetile[7]),
									 new HoleTile(Sprite.holetile[8])};
	
	public static Tile barriertile = new WallTile(Sprite.barriertile);
	public static Tile rocktile = new WallTile(Sprite.rocktile);
		
	public static Tile ddirttile = new DarkDirtTile(Sprite.ddirttile);
	
	public static Tile laddertile = new LadderTile(Sprite.laddertile);
	
	public static Tile voidtile = new VoidTile(Sprite.voidtile);
	
	public Sprite sprite;
	
	protected Sprite[] spritea;
	protected int frames;
	protected int frame;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public Tile(Sprite[] spritea){
		this.spritea = spritea;
		sprite = spritea[0];
	}
	
	public void update(){
		
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}
}
