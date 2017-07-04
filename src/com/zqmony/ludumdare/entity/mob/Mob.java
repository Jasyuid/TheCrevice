package com.zqmony.ludumdare.entity.mob;

import java.util.Random;

import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.Level;

public class Mob extends Entity{

	public int width, height;
	
	protected int dir = 3;
	protected boolean moving;
	protected int walkframe, walkdelay;
	protected Sprite[] spritea;
	
	protected double speed;
	protected Random r = new Random();
	
	public Mob(Level level, double x, double y){
		super(level, x, y);
	}
	
	public Mob(Level level){
		super(level);
	}
	
	public void move(double xa, double ya){
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = 0;
		if(xa < 0) dir = 2;
		if(ya < 0) dir = 1;
		if(ya > 0) dir = 3;
		
		while(xa != 0){
			if(Math.abs(xa) > 1){
				if(!collision(abs(xa), ya)){
					this.x += abs(xa);
				}
				xa -= abs(xa);
			}else{
				if(!collision(abs(xa), ya)){
					this.x += xa;
				}
				xa = 0;
			}
		}
		
		while(ya != 0){
			if(Math.abs(ya) > 1){
				if(!collision(xa, abs(ya))){
					this.y += abs(ya);
				}
				ya -= abs(ya);
			}else{
				if(!collision(xa, abs(ya))){
					this.y += ya;
				}
				ya = 0;
			}
		}
	}
	
	public void moveEnemy(double xa, double ya){
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		while(xa != 0){
			if(Math.abs(xa) > 1){
				this.x += abs(xa);
				xa -= abs(xa);
			}else{
				if(!collision(abs(xa), ya)){
					this.x += xa;
				}
				xa = 0;
			}
		}
		
		while(ya != 0){
			if(Math.abs(ya) > 1){
				this.y += abs(ya);
				ya -= abs(ya);
			}else{
				this.y += ya;
				ya = 0;
			}
		}
	}
	
	public double abs(double value){
		if(value > 0) return 1;
		return - 1;
	}
	
	public boolean collision(double xa, double ya){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			double xt = ((x + xa) + c % 2 * 15) /16;
			double yt = ((y + ya) + c / 2 * 8 + 15) /16;
			if(level.getTile((int)xt, (int)yt).solid()) solid = true;
		}
		return solid;
	}
	
	public boolean collisionE(double xa, double ya){
		boolean coll = false;
		for(Entity e : level.getEntities()){
			if(!(e instanceof Player)){
				if((int)(e.x/16) == (int)(x + xa)/16 && (int)(e.y/16) == (int)(y + ya)/16){
					coll = true;
					
				}
				System.out.println((int)e.x/16 + " " + (int)x/16 + "  "  + (int)e.y/16 + " " + (int)y/16);
			}	
		}
		return coll;
	}
	
	public void walkAnimation(Sprite[] spritea){
		if(moving) walkdelay++;
		
		if(walkdelay >= 10){
			walkdelay = 0;
			walkframe++;
		}
		
		if(!moving) walkdelay = 0;
		
		if(dir == 0){
			if(moving){
				if(walkframe % 2 == 1){
					sprite = spritea[1];
				}else{
					sprite = spritea[2];
				}
			}else{
				sprite = spritea[0];
			}
		}else if(dir == 1){
			if(moving){
				if(walkframe % 2 == 1){
					sprite = spritea[4];
				}else{
					sprite = spritea[5];
				}
			}else{
				sprite = spritea[3];
			}
		}else if(dir == 2){
			if(moving){
				if(walkframe % 2 == 1){
					sprite = spritea[7];
				}else{
					sprite = spritea[8];
				}
			}else{
				sprite = spritea[6];
			}
		}else if(dir == 3){
			if(moving){
				if(walkframe % 2 == 1){
					sprite = spritea[10];
				}else{
					sprite = spritea[11];
				}
			}else{
				sprite = spritea[9];
			}
		}
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
}
