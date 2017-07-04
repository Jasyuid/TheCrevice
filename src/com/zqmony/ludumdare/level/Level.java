package com.zqmony.ludumdare.level;

import java.util.ArrayList;
import java.util.List;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.tile.Tile;
import com.zqmony.ludumdare.util.Input;

public class Level {
	
	protected String path;
	protected int width, height;
	public int[] tiles;
	
	protected int levelx, levely;
	protected int px, py;
	protected int spawnx, spawny;
	
	public boolean levelend = false;
	public boolean levelstart = true;
	protected boolean setup = true;
	protected Tile def;
	protected Tile other;
	protected Tile other2;
	
	protected int b = 0;
	
	public boolean end = false;
	
	protected List<Entity> entities = new ArrayList<Entity>();
	public Structure struc;
	protected Input input;
	
	public Level(String path, Structure struc){
		this.path = path;
		this.struc = struc;
		input = struc.getInput();
		def = Tile.ddirttile;
		other = Tile.groundtile;
		other2 = Tile.groundtile;
		loadLevel();
	}
	
	public void loadLevel(){
		generateLevel();
	}
	
	public void generateLevel(){
		
	}
	
	public void levelEnd(){
		
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void update(){
		for(Entity e : entities){
			e.update();
		}
	}
	
	public void render(Screen screen){
		levelx = -(px - screen.width/2 + 8);
		levely = -(py - screen.height/2 + 16);
		
		screen.setOffset(levelx, levely);
		
		int x0 = -levelx >> 5;
		int x1 = (-levelx + screen.width + 32) >> 5;
		int y0 = -levely >> 5;
		int y1 = (-levely + screen.height + 32) >> 5;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				if(x+y*32 < 0 || x+y*32 > 64*64){
					def.render(x, y, screen);
					continue;
				}
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for(Entity e : entities){
			e.render(screen);
		}
		screen.setBrightness(b);
		
		render2(screen);
		
	}
	
	public void render2(Screen screen){
		
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height) return def;
		if(tiles[x+y*width] == 0xffffffff) return Tile.groundtile;
		if(tiles[x+y*width] == 0xff19ff00) return Tile.grasstile;
		if(tiles[x+y*width] == 0xffff9900) return Tile.woodtile;
		if(tiles[x+y*width] == 0xff000001) return Tile.sesgraphtile;
		
		if(tiles[x+y*width] == 0xffe5e5e5) return Tile.walltile[0];
		if(tiles[x+y*width] == 0xffcccccc) return Tile.walltile[1];
		if(tiles[x+y*width] == 0xffb2b2b2) return Tile.walltile[2];
		if(tiles[x+y*width] == 0xff999999) return Tile.walltile[3];
		if(tiles[x+y*width] == 0xff7f7f7f) return Tile.walltile[4];
		if(tiles[x+y*width] == 0xff666666) return Tile.walltile[5];
		if(tiles[x+y*width] == 0xff4c4c4c) return Tile.walltile[6];
		if(tiles[x+y*width] == 0xff333333) return Tile.walltile[7];
		
		if(tiles[x+y*width] == 0xff2d2d2d) return Tile.walltile[8];
		if(tiles[x+y*width] == 0xff282828) return Tile.walltile[9];
		if(tiles[x+y*width] == 0xff232323) return Tile.walltile[10];
		if(tiles[x+y*width] == 0xff1c1c1c) return Tile.walltile[11];
		
		if(tiles[x+y*width] == 0xff0000e5) return Tile.holetile[0];
		if(tiles[x+y*width] == 0xff0000cc) return Tile.holetile[1];
		if(tiles[x+y*width] == 0xff0000b2) return Tile.holetile[2];
		if(tiles[x+y*width] == 0xff000099) return Tile.holetile[3];
		if(tiles[x+y*width] == 0xff00007f) return Tile.holetile[4];
		if(tiles[x+y*width] == 0xff000066) return Tile.holetile[5];
		if(tiles[x+y*width] == 0xff00004c) return Tile.holetile[6];
		if(tiles[x+y*width] == 0xff000033) return Tile.holetile[7];
		if(tiles[x+y*width] == 0xff000019) return Tile.holetile[8];
		
		if(tiles[x+y*width] == 0xff610000) return Tile.rocktile;
		if(tiles[x+y*width] == 0xffffff00) return Tile.barriertile;
		
		if(tiles[x+y*width] == 0xffff0000) return Tile.bricktile;
		
		if(tiles[x+y*width] == 0xff0000ff) return Tile.laddertile;
		if(tiles[x+y*width] == 0xff00ffff) return Tile.spawntile;
		
		if(tiles[x+y*width] == 0xffff00ff) return other;
		if(tiles[x+y*width] == 0xffff00fe) return other2;
		
		if(tiles[x+y*width] == 0xff000000) return Tile.ddirttile;
		
		return Tile.voidtile;
	}
	
	public Input getInput(){
		return input;
	}
	
	public void setPlayerX(double px){
		this.px = (int)px;
	}
	
	public void setPlayerY(double py){
		this.py = (int)py;
	}
	
	public List<Entity> getEntities(){
		return entities;
	}
}
