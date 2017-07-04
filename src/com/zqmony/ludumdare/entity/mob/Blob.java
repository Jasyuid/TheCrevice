package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Blob extends Mob{

	private double xa, ya;
	private int xt, yt;
	private int timer;
	private boolean lock;
	
	public Blob(Level level, double x, double y, int xt, int yt){
		super(level, x, y);
		this.xt = xt;
		this.yt = yt;
		spritea = Sprite.blob;
		sprite = spritea[3];
		dir = 3;
		speed = 1.5;
		if(level.struc.getDif() == 0) speed = 1;
	}
	
	public Blob(Level level, double x, double y, int xt, int yt, boolean lock){
		super(level, x, y);
		this.xt = xt;
		this.yt = yt;
		spritea = Sprite.blob;
		sprite = spritea[3];
		dir = 3;
		speed = 1.5;
		if(level.struc.getDif() == 0) speed = 1;
	}
	
	public void update(){
		xa = 0; ya = 0;
		
		timer++;
		if(timer > (xt*32+yt*32)/speed){
			timer = 0;
		}else if(timer > (xt*32+yt*16)/speed){
			ya-=speed;
		}else if(timer > (xt*16+yt*16)/speed){
			xa-=speed;
		}else if(timer > (xt*16)/speed){
			ya+=speed;
		}else if(timer >= 0){
			xa += speed;
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
