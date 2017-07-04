package com.zqmony.ludumdare.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.entity.mob.Blob;
import com.zqmony.ludumdare.entity.mob.Chaser;
import com.zqmony.ludumdare.entity.mob.Plasma;
import com.zqmony.ludumdare.entity.mob.Player;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.util.Sound;

public class Level_Three extends Level{

	private int texts = 0;
	
	public Level_Three(String path, Structure struc){
		super(path, struc);
	}
	
	public void loadLevel(){
		try{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResourceAsStream(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		}catch (IOException e){
			e.printStackTrace();
		}
		generateLevel();
	}
	
	public void generateLevel(){
		b=-255;
		spawnx = 6*16;
		spawny = 5*16;
		for(int i = 0; i < 2; i++){
			addEntity(new Plasma(this, 10*16, 12*16));
		}
		for(int i = 0; i < 3; i++){
			addEntity(new Plasma(this, 13*16, 50*16));
		}
		for(int i = 0; i < 5; i++){
			addEntity(new Plasma(this, 49*16, 44*16));
		}
		addEntity(new Chaser(this, 23*16, 25*16));
		addEntity(new Chaser(this, 5*16, 24*16));
		addEntity(new Chaser(this, 16*16, 35*16));
		addEntity(new Chaser(this, 14*16, 46*16));
		addEntity(new Player(this, spawnx, spawny));
	}
	
	public void update(){
		if(levelend){
			b-=2;
			if(b <= -255){
				struc.setCheck(4);
				struc.setLevel(new BossLevel("/levels/bosslevel.png", struc));
			}
		}else if(levelstart){
			if(b == -255){
				for(Entity e : entities){
					e.update();
				}
			}
			
			if(b >= 0){
				b=0;
				setup = false;
			}else{
				b+=2;
			}
			if(!setup){
				if(input.enter){
					texts++;
					Sound.select.play(false);
				}
			}
		}else{
			for(Entity e : entities){
				e.update();
			}
		}
	}
	//36
	public void render2(Screen screen){
		if(levelstart){
			if(!setup){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:I have to say, I'm", 10, 167, 0xffffff, 1);
					Font.render(screen, "impressed your doing so well.", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "PLAYER:Thanks, I think...", 10, 167, 0xffffff, 1);
					Font.render(screen, "", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "BOSS:It's good becuase it", 10, 167, 0xffffff, 1);
					Font.render(screen, "seems as though it is only", 10, 177, 0xffffff, 1);
					Font.render(screen, "going to get harder.", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					Font.render(screen, "BOSS:Also, I hope you like", 10, 167, 0xffffff, 1);
					Font.render(screen, "mazes, your fellow miners", 10, 177, 0xffffff, 1);
					Font.render(screen, "don't map out the mines.", 10, 187, 0xffffff, 1);
				}else if(texts == 4){
					Font.render(screen, "PLAYER:I always thought we", 10, 167, 0xffffff, 1);
					Font.render(screen, "should, but I was afraid", 10, 177, 0xffffff, 1);
					Font.render(screen, "they would refuse my idea.", 10, 187, 0xffffff, 1);
				}else if(texts == 5){
					Font.render(screen, "PLAYER:I was bullied as a", 10, 167, 0xffffff, 1);
					Font.render(screen, "child and I have been", 10, 177, 0xffffff, 1);
					Font.render(screen, "scared ever since.", 10, 187, 0xffffff, 1);
				}else if(texts == 6){
					Font.render(screen, "BOSS:Thats very interesting.", 10, 167, 0xffffff, 1);
					Font.render(screen, "Now get moving! I'm hungry", 10, 177, 0xffffff, 1);
					Font.render(screen, "and need to order a pizza.", 10, 187, 0xffffff, 1);
				}else if(texts == 7){
					levelstart = false;
				}
			}
		}else if(levelend){
			Font.render(screen, "Entering Level 4", 10, 10, 0xdd0000, 1);
		}else{
			if((px/16 > 7 && px/16 < 12) && (py/16 > 3 && py/16 < 10)){
				Font.render(screen, "These blue monsters move", 60, 5, 0xffffff, 1);
				Font.render(screen, "in random patterns!", 60, 15, 0xffffff, 1);
			}
		}
	}
	
}
