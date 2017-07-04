package com.zqmony.ludumdare.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.Entity;
import com.zqmony.ludumdare.entity.mob.Blob;
import com.zqmony.ludumdare.entity.mob.Chaser;
import com.zqmony.ludumdare.entity.mob.Enemy;
import com.zqmony.ludumdare.entity.mob.Plasma;
import com.zqmony.ludumdare.entity.mob.Player;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.tile.Tile;
import com.zqmony.ludumdare.util.Sound;

public class BossLevel extends Level{
	
	private int texts = 0;
	private boolean trigger1, trigger2, trigger3, trigger4;
	private boolean spent1, spent2, spent3, spent4;
	private int up;
	private int timer = 0;
	private Random r = new Random();
	
	private boolean endshot = false;
	private int shotx;
	private int shoty;
	
	private int estate = 3;
	private boolean rogue = false;
	private double ex;
	private double ey;
	private int stimer;
	
	public BossLevel(String path, Structure struc){
		super(path, struc);
		up = 0;
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
		spawnx = 31*16;
		spawny = 52*16;
		addEntity(new Enemy(this, 31*16, 27*16));
		addEntity(new Player(this, spawnx, spawny));
	}
	
	public void update(){
		if(levelend){
			if(input.enter && texts < 3){
				Sound.select.play(false);
				texts++;
			}
			if(texts == 3){
				b-=2;
				if(b <= -255){
					struc.win = true;
				}
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
				if(up == 0){
					if(e instanceof Player) e.update();
				}else if(up == 1){
					
				}else if(up == 2){
					e.update();
				}else if(up == 3){
					if(!(e instanceof Player)) e.update();
				}
			}
			if(py == 32*16 && !spent1){
				trigger1 = true;
				if(input.enter){
					Sound.select.play(false);
					texts++;
				}
				up = 1;
			}
			if(!trigger2 && spent1){
				up = 2;
				for(Entity e : entities){
					if(e instanceof Enemy){
						e.state = 0;
						estate  = 0;
					}
				}
				timer++;
				if(timer > 1000){
					trigger2 = true;
					timer = 0;
				}
			}
			if(trigger2 && !spent2){
				up = 3;
				for(Entity e : entities){
					if(e instanceof Enemy){
						estate = 3;
						e.state = 3;
					}
					if(e instanceof Blob){
						e.x = 0;
						e.y = 0;
					}
				}
				if(input.enter){
					Sound.select.play(false);
					texts++;
				}
			}
			if(!trigger3 && spent2){
				up = 2;
				for(Entity e : entities){
					if(e instanceof Enemy){
						estate  = 1;
						e.state = 1;
					}
				}
				timer++;
				if(timer > 1200){
					trigger3 = true;
					timer = 0;
				}
			}
			if(trigger3 && !spent3){
				up = 3;
				for(Entity e : entities){
					if(e instanceof Enemy){
						estate = 3;
						e.state = 3;
					}
					if(e instanceof Chaser){
						e.x = 0;
						e.y = 0;
					}
				}
				if(input.enter){
					Sound.select.play(false);
					texts++;
				}
			}
			if(!trigger4 && spent3){
				up = 2;
				for(Entity e : entities){
					if(e instanceof Enemy){
						e.state = 2;
						estate = 2;
					}
				}
				timer++;
				if(timer > 1400){
					trigger4 = true;
					timer = 0;
				}
			}
			if(trigger4 && !spent4){
				estate = 3;
				for(Entity e : entities){
					if(e instanceof Enemy){
						e.state = 3;
					}
					if(e instanceof Plasma){
						e.x = 0;
						e.y = 0;
					}
					if(e instanceof Player){
						e.x = 31*16;
						e.y = 32*16;
					}
				}
				other2  = Tile.rocktile;
				if(input.enter){
					Sound.select.play(false);
					texts++;
				}
			}
			if(spent4){
				up = 2;
				for(Entity e : entities){
					if(e instanceof Enemy){
						e.rogue = true;
						rogue = true;
					}
					if(e instanceof Player) e.rogue = true;
				}
			}
			
			
			stimer++;
			if(estate == 0){
				if(stimer > 200){;
					addEntity(new Blob(this, (26 + r.nextInt(11))*16, 30*16, 0, 5, true));
					stimer = 0;
				}
			}else if(estate == 1){
				if(stimer > 300){
					addEntity(new Chaser(this, (26 + r.nextInt(11))*16, 30*16, true));
					stimer = 0;
				}
			}else if(estate == 2){
				if(stimer > 150){
					addEntity(new Plasma(this, (26 + r.nextInt(11))*16, 30*16, true));
					stimer = 0;
				}
			}else if(estate == 3){
				
			}	
			
			if(rogue){
				if(stimer > 30){
					for(Entity e : entities){
						e.state = r.nextInt(3);
					}
					stimer = 0;

				}
				if(input.space && !endshot){
					Sound.shoot.play(false);
					up = 3;
					shoty = 100;
					endshot = true;
					System.out.println("hi");
				}
				if(endshot){
					shoty--;
					System.out.println("hio");
					if(shoty < 10){
						for(Entity e : entities){
							if(e instanceof Enemy){
								e.x = 0;
								e.y = 0;
							}
						}
						Sound.enemydeath.play(false);
						levelend = true;
						
					}
				}
			}
		}
	}
	//36
	public void render2(Screen screen){
		if(levelstart){
			if(!setup){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:Ok, your finally at the", 10, 167, 0xffffff, 1);
					Font.render(screen, "bottem. Whatever is causing", 10, 177, 0xffffff, 1);
					Font.render(screen, "this is somewhere is here.", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "PLAYER:I don't see much here.", 10, 167, 0xffffff, 1);
					Font.render(screen, "Mabey I should just head back", 10, 177, 0xffffff, 1);
					Font.render(screen, "and call it a day.", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "BOSS:Don't worry, were almost", 10, 167, 0xffffff, 1);
					Font.render(screen, "there. Just believe in", 10, 177, 0xffffff, 1);
					Font.render(screen, "yourself.", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					Font.render(screen, "PLAYER:Enough with telling me", 10, 167, 0xffffff, 1);
					Font.render(screen, "I'm special. I'm not!", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 4){
					Font.render(screen, "PLAYER: I'm sorry, please", 10, 167, 0xffffff, 1);
					Font.render(screen, "don't hurt me!", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 5){
					Font.render(screen, "BOSS:Just go. I still need", 10, 167, 0xffffff, 1);
					Font.render(screen, "a pizza.", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 6){
					levelstart = false;
					texts = 0;
				}
			}
		}else if(levelend){
			if(texts == 0){
				screen.renderSprite(0, 164, Sprite.textbox);
				Font.render(screen, "BOSS:AMAZING! I never knew you", 10, 167, 0xffffff, 1);
				Font.render(screen, "could do that! I can make", 10, 177, 0xffffff, 1);
				Font.render(screen, "money off of you.", 10, 187, 0xffffff, 1);
			}else if(texts == 1){
				screen.renderSprite(0, 164, Sprite.textbox);
				Font.render(screen, "BOSS:Anyway, WE WON! WE ARE", 10, 167, 0xffffff, 1);
				Font.render(screen, "SAFE! I CAN FINALLY GET A", 10, 177, 0xffffff, 1);
				Font.render(screen, "PIZZA!", 10, 187, 0xffffff, 1);
			}else if(texts == 2){
				screen.renderSprite(0, 164, Sprite.textbox);
				Font.render(screen, "PLAYER:I did it! There", 10, 167, 0xffffff, 1);
				Font.render(screen, "really was something beneath", 10, 177, 0xffffff, 1);
				Font.render(screen, "my surface!", 10, 187, 0xffffff, 1);
			}else if(texts == 3){
				
			}
		}else{
			if(trigger1 && !spent1){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:WHAT IS THAT! It must", 10, 167, 0xffffff, 1);
					Font.render(screen, "have come out of that", 10, 177, 0xffffff, 1);
					Font.render(screen, "crevice.", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "BOSS:I usually don't care for", 10, 167, 0xffffff, 1);
					Font.render(screen, "other's safety, but GET OUT", 10, 177, 0xffffff, 1);
					Font.render(screen, "OF THERE!", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					texts = 0;
					spent1 = true;
				}
			}
			if(trigger2 && !spent2){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:It seems that it can", 10, 167, 0xffffff, 1);
					Font.render(screen, "change form. Stay on your", 10, 177, 0xffffff, 1);
					Font.render(screen, "toes!", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					texts = 0;
					spent2 = true;
				}
			}
			if(trigger3 && !spent3){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:There has to be a way to", 10, 167, 0xffffff, 1);
					Font.render(screen, "beat him! Just keep avoiding", 10, 177, 0xffffff, 1);
					Font.render(screen, "his attacks!", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "BOSS:WHY DOES LIFE HATE ME!", 10, 167, 0xffffff, 1);
					Font.render(screen, "SOMEONE SAVE ME!", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "PLAYER:Sometimes I wonder why", 10, 167, 0xffffff, 1);
					Font.render(screen, "I'm threatened by him.", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					texts = 0;
					spent3 = true;
				}
			}
			if(trigger4 && !spent4){
				screen.renderSprite(0, 164, Sprite.textbox);
				if(texts == 0){
					Font.render(screen, "BOSS:Well, I'm giving up on", 10, 167, 0xffffff, 1);
					Font.render(screen, "life. To clear my heart I'm", 10, 177, 0xffffff, 1);
					Font.render(screen, "going to tell you the truth.", 10, 187, 0xffffff, 1);
				}else if(texts == 1){
					Font.render(screen, "BOSS:I don't think there is", 10, 167, 0xffffff, 1);
					Font.render(screen, "anything special about you.", 10, 177, 0xffffff, 1);
					Font.render(screen, "", 10, 187, 0xffffff, 1);
				}else if(texts == 2){
					Font.render(screen, "PLAYER:I still have to try", 10, 167, 0xffffff, 1);
					Font.render(screen, "something. Being weak is", 10, 177, 0xffffff, 1);
					Font.render(screen, "not going to save me.", 10, 187, 0xffffff, 1);
				}else if(texts == 3){
					texts = 0;
					spent4 = true;
				}
			}
			if(spent1){
				other = Tile.rocktile;
			}
			if(rogue && !endshot){
				Font.render(screen, "Press Space to unlock", 60, 5, 0xffffff, 1);
				Font.render(screen, "your potential", 60, 15, 0xffffff, 1);
			}
			if(endshot){
				screen.renderSpriteB(screen.width/2 -8, shoty, Sprite.plasmashot[shoty % 2]);
			}
		}
	}
	
}
