package com.zqmony.ludumdare.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.entity.mob.Blob;
import com.zqmony.ludumdare.entity.mob.Chaser;
import com.zqmony.ludumdare.entity.mob.Player;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.util.Sound;

public class Level_Two extends Level{

private int texts = 0;
	
	public Level_Two(String path, Structure struc){
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
		spawnx = 8*16;
		spawny = 42*16;
		addEntity(new Chaser(this, 22*16, 42*16));
		addEntity(new Chaser(this, 48*16, 28*16));
		addEntity(new Blob(this, 9*16, 28*16, 5, 0));
		addEntity(new Player(this, spawnx, spawny));
	}
	
	public void update(){
		if(levelend){
			b-=2;
			if(b <= -255){
				struc.setCheck(3);
				struc.setLevel(new Level_Three("/levels/level_3.png", struc));
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
					Font.render(screen, "BOSS:What were those red blobs?", 10, 167, 0xffffff, 1);
					Font.render(screen, "I've never seen anything like", 10, 177, 0xffffff, 1);
					Font.render(screen, "them before!", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "BOSS:Luckliy, it appears as", 10, 167, 0xffffff, 1);
					Font.render(screen, "though there inteligence is", 10, 177, 0xffffff, 1);
					Font.render(screen, "lacking.", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "PLAYER:Easy for you to say,", 10, 167, 0xffffff, 1);
					Font.render(screen, "I nearly died trying to avoid", 10, 177, 0xffffff, 1);
					Font.render(screen, "them!", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					Font.render(screen, "PLAYER:Can't you just call", 10, 167, 0xffffff, 1);
					Font.render(screen, "the police or military and", 10, 177, 0xffffff, 1);
					Font.render(screen, "let them take care of it.", 10, 187, 0xffffff, 1);
				}else if(texts == 4){
					Font.render(screen, "BOSS:I can't for two reasons.", 10, 167, 0xffffff, 1);
					Font.render(screen, "First, they will probally", 10, 177, 0xffffff, 1);
					Font.render(screen, "charge us for services.", 10, 187, 0xffffff, 1);
				}else if(texts == 5){
					Font.render(screen, "BOSS:Second, a signal coming", 10, 167, 0xffffff, 1);
					Font.render(screen, "from underground is blocking", 10, 177, 0xffffff, 1);
					Font.render(screen, "our communication features.", 10, 187, 0xffffff, 1);
				}else if(texts == 6){
					Font.render(screen, "PLAYER:Then can't you just", 10, 167, 0xffffff, 1);
					Font.render(screen, "go out and ask for help.", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 7){
					Font.render(screen, "BOSS:You know I'm to lazy", 10, 167, 0xffffff, 1);
					Font.render(screen, "for that. Besides, I have a", 10, 177, 0xffffff, 1);
					Font.render(screen, "loyal worker to do it.", 10, 187, 0xffffff, 1);
				}else if(texts == 8){
					Font.render(screen, "PLAYER:I need a new job...", 10, 167, 0xffffff, 1);
					Font.render(screen, "", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 9){
					levelstart = false;
				}
			}
		}else if(levelend){
			Font.render(screen, "Entering Level 3", 10, 10, 0xd9d900, 1);
		}else{
			if((px/16 > 10 && px/16 < 20) && (py/16 > 36 && py/16 < 48)){
				Font.render(screen, "Some monsters will chase", 60, 5, 0xffffff, 1);
				Font.render(screen, "you. Try and get out of", 60, 15, 0xffffff, 1);
				Font.render(screen, "their range.", 60, 25, 0xffffff, 1);
			}
		}
	}
	
}
