package com.zqmony.ludumdare.entity.mob;

import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;
import com.zqmony.ludumdare.level.tile.Tile;
import com.zqmony.ludumdare.util.Sound;

public class Player extends Mob{

	private int xoff, yoff;
	private double xa, ya;
	
	private int health;
	private int dtimer = 0;
	
	public Player(Level level, double x, double y){
		super(level, x, y);
		spritea = Sprite.player;
		sprite = spritea[0];
		dir = 3;
		speed = 1;
		health = level.struc.getHealth();
	}
	
	public void update(){
		xa = 0; ya = 0;
		
		if(level.getInput().w || level.getInput().up) ya-=speed;
		if(level.getInput().a || level.getInput().left) xa-=speed;
		if(level.getInput().s || level.getInput().down) ya+=speed;
		if(level.getInput().d || level.getInput().right) xa+=speed;
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
			moving = true;
		}else{
			moving = false;
		}
		
		walkAnimation(spritea);
		
		if(ladder(xa, ya)){
			level.levelend = true;
		}
		
		if(dtimer!=0){
			dtimer--;
		}
		
		for(Entity e : level.getEntities()){
			if((e.x < x + sprite.width - 1 && e.x + e.sprite.width > x + 1) && (e.y < y + sprite.height - 1 && e.y + e.sprite.height > y + 1)){
				if(e instanceof Blob){
					if(dtimer==0)damage(1);
				}
				if(e instanceof Chaser){
					if(dtimer==0)damage(1);
				}
				if(e instanceof Plasma){
					if(dtimer==0)damage(1);
				}
			}
		}
		
		level.setPlayerX(x);
		level.setPlayerY(y);
		level.struc.setHealth(health);
		
	}
	
	public void damage(int h){
		health-=h;
		dtimer = 120;
		Sound.hit.play(false);
	}
	
	public boolean ladder(double xa, double ya){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			double xt = ((x) + c % 2 * 1) /16;
			double yt = ((y) + c / 2 * 1 + 15) /16;
			if(level.getTile((int)xt, (int)yt) == Tile.laddertile) solid = true;
		}
		return solid;
	}
	
	public void render(Screen screen){
		xoff = screen.width/2 - 8;
		yoff = screen.height/2 - 8;
		screen.renderPlayer(xoff, yoff, this);
		if(health == 3){
			screen.renderSpriteB(2, 2, Sprite.heart[0]);
			screen.renderSpriteB(20, 2, Sprite.heart[0]);
			screen.renderSpriteB(38, 2, Sprite.heart[0]);
		}
		if(health == 2){
			screen.renderSpriteB(2, 2, Sprite.heart[0]);
			screen.renderSpriteB(20, 2, Sprite.heart[0]);
			screen.renderSpriteB(38, 2, Sprite.heart[1]);
		}
		if(health == 1){
			screen.renderSpriteB(2, 2, Sprite.heart[0]);
			screen.renderSpriteB(20, 2, Sprite.heart[1]);
			screen.renderSpriteB(38, 2, Sprite.heart[1]);
		}
		if(dtimer > 0){
			int f = dtimer % 2;
			if(f == 1){
				Font.render(screen, "DAMAGE", 126, 185, 0xbb0000, 1);
			}else if(f == 0){
				Font.render(screen, "DAMAGE", 126, 185, 0xffffff, 1);
			}
		}
	}
	
	public double getPlayerX(){
		return x;
	}
	
	public double getPlayerY(){
		return y;
	}
	
	public void setX(double x){
		this.x  = x;
	}
	
	public void setY(double y){
		this.y  = y;
	}
}
