package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Plasma extends Mob{
	
	private double xa, ya;
	private double xt, yt;
	private int delay = 0;
	
	public Plasma(Level level, double x, double y){
		super(level, x, y);
		spritea = Sprite.plasma;
		sprite = spritea[3];
		dir = 3;
		speed = 1;
		if(level.struc.getDif() == 0) speed = 0.5;
	}
	
	public Plasma(Level level, double x, double y, boolean lock){
		super(level, x, y);
		spritea = Sprite.plasma;
		sprite = spritea[3];
		dir = 3;
		speed = 1;
		if(level.struc.getDif() == 0) speed = 0.5;
		if(lock && level.struc.getDif() == 0) speed = 0.4;
	}
	
	public void update(){
		xa = 0; ya = 0;
		
		if(delay <= 0){
			if(r.nextInt(2) == 0){
				xt = r.nextInt(3) - 1;
				yt = r.nextInt(3) - 1;
				xt *= speed;
				yt *= speed;
			}
			delay  = 60;
		}	
		if(delay > 0) delay--;
		
		xa = xt;
		ya = yt;
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
		}
		
		sprite = spritea[dir];
	}
	
	public void render(Screen screen){
		screen.renderMob((int)x, (int)y, this);
	}
}
