package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Chaser extends Mob{

	private double xa, ya;
	private double xdist, ydist, distance;
	private boolean lock;
	private double maxd;
	
	public Chaser(Level level, double x, double y){
		super(level, x, y);
		spritea = Sprite.chaser;
		sprite = spritea[3];
		dir = 3;
		speed = 0.75;
		lock = false;
		maxd = 80;
		if(level.struc.getDif() == 0) speed = 0.5;
	}
	
	public Chaser(Level level, double x, double y, boolean lock){
		super(level, x, y);
		spritea = Sprite.chaser;
		sprite = spritea[3];
		dir = 3;
		speed = 0.75;
		if(level.struc.getDif() == 0) speed = 0.5;
		this.lock = lock;
		if(lock){
			speed = 0.2;
			maxd = 120;
			if(level.struc.getDif() == 0){
				speed = 0.15;
				maxd = 200;
			}
			
		}
	}
	
	public void update(){
		xa = 0; ya = 0;
		
		for(Entity e : level.getEntities()){
			if(e instanceof Player){
				xdist = e.x - x;
				ydist = e.y - y;
				distance = Math.sqrt((xdist*xdist) + (ydist*ydist));
				if(distance < maxd){
					if(x +- 1 < e.x || x < e.x) xa+=speed;
					if(x +- 1 > e.x || x > e.x) xa-=speed;
					if(y +- 1 < e.y || y < e.y) ya+=speed;
					if(y +- 1 > e.y || y > e.y) ya-=speed;
				}
			}
		}
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
		}
		
		sprite = spritea[dir];
	}
	
	public void render(Screen screen){
		screen.renderMob((int)x, (int)y, this);
	}
	
}
