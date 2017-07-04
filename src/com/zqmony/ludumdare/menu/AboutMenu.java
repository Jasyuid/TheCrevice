package com.zqmony.ludumdare.menu;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;

public class AboutMenu extends Menu{
	
	public AboutMenu(Structure struc){
		super(struc);
		int selected = 0;
	}
	
	public void update(){
		if(input.enter) struc.setMenu(new MainMenu(struc));
	}
	
	public void render(Screen screen){
		screen.renderSprite(0, 0, Sprite.menuback);
		Font.render(screen, "The Crevice is a game made for", 15, 15, 0xffffff, 1);
		Font.render(screen, "the Ludum Dare 29, a game", 15, 25, 0x0ffffff, 1);
		Font.render(screen, "development event where you have", 15, 35, 0xffffff, 1);
		Font.render(screen, "48 hours to make a game from", 15, 45, 0xffffff, 1);
		Font.render(screen, "scratch that follows a theme. This", 15, 55, 0xffffff, 1);
		Font.render(screen, "time the theme was \"Beneath the", 15, 65, 0xffffff, 1);
		Font.render(screen, "surface\"", 15, 75, 0xffffff, 1);
		Font.render(screen, "*NOTE* This game was originaly", 15, 100, 0x00dd00, 1);
		Font.render(screen, "made under the name \"ZQmony\".", 15, 110, 0x00dd00, 1);
		Font.render(screen, "If you want to see the game on", 15, 120, 0x00dd00, 1);
		Font.render(screen, "the LudumDare website, it will", 15, 130, 0x00dd00, 1);
		Font.render(screen, "be under that user.", 15, 140, 0x00dd00, 1);
		Font.render(screen, "Back", 10, 185, 0x00ddff, 1);
	}
}
