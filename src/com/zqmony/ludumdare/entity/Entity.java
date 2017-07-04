package com.zqmony.ludumdare.entity;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Entity {
	
	protected Level level;
	public double x, y;
	public Sprite sprite;
	public int state;
	public boolean rogue;
	
	public Entity(Level level, double x, double y){
		this.level = level;
		this.x = x;
		this.y = y;
	}
	
	public Entity(Level level){
		this.level = level;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
}
