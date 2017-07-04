package com.zqmony.ludumdare.menu;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.util.Input;
import com.zqmony.ludumdare.util.Sound;

public class MainMenu extends Menu{
	
	public MainMenu(Structure struc){
		super(struc);
	}
	
	public void update(){
		if(selected == 0 && input.enter) struc.setMenu(new GameMenu(struc));
		if(selected == 1 && input.enter) struc.setMenu(new AboutMenu(struc));
		
		if((input.down || input.s) && selected == 0){
			selected++;
			Sound.select.play(false);
		}
		if((input.up || input.w) && selected == 1){
			selected--;
			Sound.select.play(false);
		}

	}
	
	public void render(Screen screen){
		screen.renderSprite(0, 0, Sprite.menuback);
		screen.renderSprite(screen.width/2 - 120, 20, Sprite.title);
		if(selected == 0){
			Font.render(screen, "Play", 134, 100, 0x00ddff, 1);
			Font.render(screen, "About", 130, 115, 0xffffff, 1);
		}else if(selected == 1){
			Font.render(screen, "Play", 134, 100, 0xffffff, 1);
			Font.render(screen, "About", 130, 115, 0x00ddff, 1);
		}
		Font.render(screen, "Made by Justin Symmank", 5, 185, 0xffffff, 1);
	}
}
