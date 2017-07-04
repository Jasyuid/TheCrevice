package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Boss extends Mob{

	private double xa, ya;
	private int timer;
	
	public Boss(Level level, double x, double y){
		super(level, x, y);
		spritea = sprite.boss;
		sprite = spritea[3];
		dir = 3;
		speed = 1;
	}
	
	public void update(){
		xa = 0; ya = 0;
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
			moving = true;
		}else{
			moving = false;
		}
		
		sprite = spritea[dir];
	}
	
	public void render(Screen screen){
		screen.renderMob((int)x, (int)y, this);
	}
}
