package com.zqmony.ludumdare.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.entity.mob.Boss;
import com.zqmony.ludumdare.entity.mob.Player;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.tile.Tile;
import com.zqmony.ludumdare.util.Sound;

public class StartLevel extends Level{
	
	private int texts;
	private int vib;
	
	public StartLevel(String path, Structure struc){
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
		def=Tile.grasstile;
		b=-255;
		spawnx = 2*16;
		spawny = 4*16;
		texts = 0;
		addEntity(new Boss(this, 3*16, 2*16));
		addEntity(new Player(this, spawnx, spawny));
		
	}
	
	public void update(){
		if(levelend){
			b-=2;
			if(b <= -255){
				struc.setCheck(1);
				struc.setLevel(new Level_One("/levels/level_1.png", struc));
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
				b++;
			}
			
			if(!setup){
				if(input.enter){
					texts++;;
					Sound.select.play(false);
				}
			}
		}else{
			for(Entity e : entities){
				e.update();
			}
		}
		Tile.sesgraphtile.update();
	}
	//36
	public void render2(Screen screen){
		if(levelstart){
			if(!setup){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:Do you hear all of these", 10, 167, 0xffffff, 1);
					Font.render(screen, "tremors! Something big must be", 10, 177, 0xffffff, 1);
					Font.render(screen, "happening in the mines.", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "BOSS:We need to go down there", 10, 167, 0xffffff, 1);
					Font.render(screen, "and investigate. Otherwise my", 10, 177, 0xffffff, 1);
					Font.render(screen, "coffee will spill.", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "BOSS:By we, I mean you do the dirty", 10, 167, 0xffffff, 1);
					Font.render(screen, "work and I guide you safely from", 10, 177, 0xffffff, 1);
					Font.render(screen, "the surface.", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					Font.render(screen, "PLAYER:Sir, if I go down there and", 10, 167, 0xffffff, 1);
					Font.render(screen, "its dangerous, there is no way I", 10, 177, 0xffffff, 1);
					Font.render(screen, "will make it back!", 10, 187, 0xffffff, 1);
				}else if(texts == 4){
					Font.render(screen, "PLAYER:There is nothing special", 10, 167, 0xffffff, 1);
					Font.render(screen, "about me...", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 5){
					Font.render(screen, "BOSS:More the reason to go. You may", 10, 167, 0xffffff, 1);
					Font.render(screen, "find out secrets behind your weak", 10, 177, 0xffffff, 1);
					Font.render(screen, "and harmless appearence.", 10, 187, 0xffffff, 1);
				}else if(texts == 6){
					Font.render(screen, "BOSS:Now go down that ladder and", 10, 167, 0xffffff, 1);
					Font.render(screen, "find some answers! Be sure to bring", 10, 177, 0xffffff, 1);
					Font.render(screen, "a walkie talkie.", 10, 187, 0xffffff, 1);
				}else if(texts == 7){
					Font.render(screen, "PLAYER:I never get a choice...", 10, 167, 0xffffff, 1);
					Font.render(screen, "", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 8){
					levelstart = false;
				}
			}
		}else if(levelend){
			Font.render(screen, "Entering Level 1", 10, 10, 0x00ddff, 1);
		}else{
			Font.render(screen, "Use the arrow or WASD keys to move", 10, 10, 0xffffff, 1);
		}
	}
}
