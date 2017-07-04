package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class EnemyProjectile extends Mob{
	
	double xa, ya;
	
	public EnemyProjectile(Level level, double x, double y, double xa, double ya){
		super(level, x, y);
		this.xa = xa;
		this.ya = ya;
		spritea = Sprite.plasmashot;
		sprite = spritea[0];
	}
	
	public void update(){
		move(xa, ya);
	}
	
	public void render(Screen screen){
		screen.renderMob((int)x, (int)y, this);
	}
}
