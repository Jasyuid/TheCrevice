package com.zqmony.ludumdare;

import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.level.BossLevel;
import com.zqmony.ludumdare.level.Level;
import com.zqmony.ludumdare.level.Level_One;
import com.zqmony.ludumdare.level.Level_Three;
import com.zqmony.ludumdare.level.Level_Two;
import com.zqmony.ludumdare.level.SpawnLevel;
import com.zqmony.ludumdare.level.StartLevel;
import com.zqmony.ludumdare.menu.MainMenu;
import com.zqmony.ludumdare.menu.Menu;
import com.zqmony.ludumdare.util.Input;
import com.zqmony.ludumdare.util.Sound;

public class Structure {
	
	public int state;
	private int phealth = 3;
	private int b = -255;
	
	private boolean gameover = false;
	public boolean win = false;
	private int timed = 0;
	public int time = 0;
	
	private boolean start = true;
	
	private Menu menu;
	private Level level;
	private Input input;
	
	private int dif = 1;
	private int check = 0;
	
	public Structure(Input input){
		this.input = input;
		state = 0;
		setMenu(new MainMenu(this));
		setLevel(new BossLevel("/levels/bosslevel.png", this));
		reset();
	}
	
	public void setMenu(Menu menu){
		this.menu = menu;
	}
	
	public void setLevel(Level level){
		this.level = level;
	}
	
	public void reset(){
		phealth = 3;
		b = -255;
		gameover = false;
		win = false;
		setMenu(new MainMenu(this));
		setLevel(new StartLevel("/levels/start.png", this));
		timed = 0;
		time = 0;
		start = true;
		if(dif == 1) check = 0;
	}
	
	public void getReady()
	{
		Sound.music.play(true);
		if(dif == 0) {
			if(check == 0) setLevel(new StartLevel("/levels/start.png", this));
			else if(check == 1) setLevel(new Level_One("/levels/level_1.png", this));
			else if(check == 2) setLevel(new Level_Two("/levels/level_2.png", this));
			else if(check == 3) setLevel(new Level_Three("/levels/level_3.png", this));
			else if(check == 4) setLevel(new BossLevel("/levels/bosslevel.png", this));
		}
		else if(dif == 1) setLevel(new StartLevel("/levels/start.png", this));
		
		state = 1;
	}
	
	public void update(){
		if(state == 0){
			menu.update();
		}else if(state == 1){
			if(gameover){
				if(b>= 0){
					b=0;
				}else{
					b+=2;
				}
				if(input.enter){
					Sound.select.play(false);
					reset();
					state = 0;
				}
			}else if(win){
				Sound.music.stop();
				if(b>= 0){
					b=0;
				}else{
					b+=2;
				}
				if(input.enter){
					Sound.select.play(false);
					reset();
					check = 0;
					state = 0;
				}
			}else{
				level.update();
				if(phealth <= 0){
					Sound.die.play(false);
					gameover = true;
					Sound.music.stop();
				}
				timed++;
				if(timed == 60){
					timed = 0;
					time++;
				}

			}
		}
	}
	
	public void render(Screen screen){
		if(state == 0){
			menu.render(screen);
		}else if(state == 1){
			if(gameover){
				screen.setBrightness(b);
				screen.renderSpriteB(0, 0, Sprite.gameover);
				Font.render(screen, "GAME OVER", 114, 10, 0x990000, 1);
				Font.render(screen, "Press Enter to return to Menu", 10, 185, 0xffffff, 1);
			}else if(win){
				screen.setBrightness(b);
				screen.renderSpriteB(0, 0, Sprite.win);
				Font.render(screen, "YOU WIN", 122, 10, 0x009900, 1);
				Font.render(screen, "Time: " + time + " seconds", 5, 185, 0xffffff, 1);
				Font.render(screen, "Press Enter to return to Menu", 34, 20, 0xffffff, 1);
			}else{
				level.render(screen);
			}
		}
	}
	
	public Input getInput(){
		return input;
	}
	
	public int getHealth(){
		return phealth;
	}
	
	public void setHealth(int h){
		phealth  = h;
	}
	
	public int getDif(){
		return dif;
	}
	
	public void setDif(int dif){
		this.dif = dif;
	}
	
	public void setCheck(int check){
		this.check = check;
	}
}
