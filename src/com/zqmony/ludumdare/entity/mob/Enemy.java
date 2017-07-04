package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Enemy extends Mob{
	
	private double xa, ya;
	private double xo, yo;
	private double hs;
	
	public Enemy(Level level, double x, double y){
		super(level, x, y);
		xo = x;
		yo = y;
		spritea = Sprite.enemy;
		sprite = spritea[3];
		speed = 1.5;
		hs = speed;
		state = 3;
	}
	
	public void update(){
		xa = 0; ya = 0;
		
		if(x < xo - 70){
			hs = speed;
		}else if(x > xo + 70){
			hs = -speed;
		}
		
		if(rogue){
			if(x < xo +-1 && x < xo) hs = speed;
			if(x > xo +-1 && x > xo) hs = -speed;
		}else{
			if(x < xo - 70){
				hs = speed;
			}else if(x > xo + 70){
				hs = -speed;
			}
		}
		
		xa = hs;
		
		if(xa != 0 || ya != 0){
			moveEnemy(xa, ya);
		}
		
		sprite = spritea[state];
		
	}
	
	public void render(Screen screen){
		screen.renderMob((int)x, (int)y, this);
	}

}
