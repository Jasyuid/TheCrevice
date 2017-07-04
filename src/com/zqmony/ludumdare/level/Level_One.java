package com.zqmony.ludumdare.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.entity.mob.Blob;
import com.zqmony.ludumdare.entity.mob.Boss;
import com.zqmony.ludumdare.entity.mob.Player;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.tile.Tile;
import com.zqmony.ludumdare.util.Sound;

public class Level_One extends Level{

	private int texts = 0;
	
	public Level_One(String path, Structure struc){
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
		spawnx = 7*16;
		spawny = 26*16;
		addEntity(new Blob(this, 20*16, 23*16, 0, 5));
		addEntity(new Blob(this, 22*16, 23*16, 0, 5));
		addEntity(new Blob(this, 24*16, 23*16, 0, 5));
		addEntity(new Blob(this, 26*16, 23*16, 0, 5));
		addEntity(new Blob(this, 25*16, 11*16, 7, 0));
		addEntity(new Player(this, spawnx, spawny));
	}
	
	public void update(){
		if(levelend){
			b-=2;
			if(b <= -255){
				struc.setCheck(2);
				struc.setLevel(new Level_Two("/levels/level_2.png", struc));
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
					texts++;;;
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
					Font.render(screen, "BOSS:Welcome to the mines. Be", 10, 167, 0xffffff, 1);
					Font.render(screen, "careful, I'm picking up the", 10, 177, 0xffffff, 1);
					Font.render(screen, "signals of moving objects.", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "BOSS:If you are injured to", 10, 167, 0xffffff, 1);
					Font.render(screen, "the point of imovability, I", 10, 177, 0xffffff, 1);
					Font.render(screen, "have no way to rescue you.", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "BOSS:It will be the end your", 10, 167, 0xffffff, 1);
					Font.render(screen, "journey.", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					Font.render(screen, "PLAYER:But I have no way of", 10, 167, 0xffffff, 1);
					Font.render(screen, "defending myself, I'll die", 10, 177, 0xffffff, 1);
					Font.render(screen, "for sure!", 10, 187, 0xffffff, 1);
				}else if(texts == 4){
					Font.render(screen, "BOSS:Dont worry, just trust", 10, 167, 0xffffff, 1);
					Font.render(screen, "in yourself. You are more", 10, 177, 0xffffff, 1);
					Font.render(screen, "special then you know.", 10, 187, 0xffffff, 1);
				}else if(texts == 5){
					Font.render(screen, "PLAYER:I doubt it, but I'll", 10, 167, 0xffffff, 1);
					Font.render(screen, "go because I see no way of", 10, 177, 0xffffff, 1);
					Font.render(screen, "getting out of this.", 10, 187, 0xffffff, 1);
				}else if(texts == 6){
					levelstart = false;
				}
			}
		}else if(levelend){
			Font.render(screen, "Entering Level 2", 10, 10, 0x00dd00, 1);
		}else{
			if((px/16 > 15 && px/16 < 30) && (py/16 > 20 && py/16 < 31)){
				Font.render(screen, "Watch out for Monsters!", 60, 5, 0xffffff, 1);
			}
			if((px/16 > 23 && px/16 < 35) && (py/16 > 8 && py/16 < 14)){
				Font.render(screen, "There is no way to refill", 60, 5, 0xffffff, 1);
				Font.render(screen, "life!", 60, 15, 0xffffff, 1);
			}
		}
	}
}
