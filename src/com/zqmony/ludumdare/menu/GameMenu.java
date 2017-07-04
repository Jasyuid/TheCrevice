package com.zqmony.ludumdare.menu;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.gfx.Font;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.util.Sound;

public class GameMenu extends Menu{
	
	private boolean stop = false;
	
	public GameMenu(Structure struc){
		super(struc);
	}
	
	public void update(){
		if(selected == 0 && input.enter){
			Sound.start.play(false);
			struc.setDif(1);
			struc.getReady();
		}
		if(selected == 1 && input.enter){
			Sound.start.play(false);
			struc.setDif(0);
			struc.getReady();
		}
		if(selected == 2 && input.enter) struc.setMenu(new MainMenu(struc));
		
		if((input.down || input.s) && selected <= 1 && !stop){
			selected++;
			Sound.select.play(false);
			stop = true;
		}
		if((input.up || input.w) && selected >= 1 && !stop){
			selected--;
			Sound.select.play(false);
			stop = true;
		}
		if(!input.up && !input.down && !input.w && !input.s) stop = false;

	}
	
	public void render(Screen screen){
		screen.renderSprite(0, 0, Sprite.menuback);
		if(selected == 0){
			Font.render(screen, "Original Version (Hard)", 60, 30, 0x00ddff, 1);
			Font.render(screen, "Revised Version (Easy)", 64, 45, 0xffffff, 1);
			Font.render(screen, "Back", 134, 60, 0xffffff, 1);
			Font.render(screen, "The version made for Ludum Dare", 20, 100, 0xffffff, 1);
			Font.render(screen, "*No checkpoints and faster enemies*", 15, 130, 0x00dd00, 1);
		}else if(selected == 1){
			Font.render(screen, "Original Version (Hard)", 60, 30, 0xffffff, 1);
			Font.render(screen, "Revised Version (Easy)", 64, 45, 0x00ddff, 1);
			Font.render(screen, "Back", 134, 60, 0xffffff, 1);
			Font.render(screen, "An easier version made after", 35, 100, 0xffffff, 1);
			Font.render(screen, "Ludum Dare", 110, 115, 0xffffff, 1);
			Font.render(screen, "*Checkpoints and slower enemies*", 28, 130, 0x00dd00, 1);
		}else if(selected == 2){
			Font.render(screen, "Original Version (Hard)", 60, 30, 0xffffff, 1);
			Font.render(screen, "Revised Version (Easy)", 64, 45, 0xffffff, 1);
			Font.render(screen, "Back", 134, 60, 0x00ddff, 1);
		}
	}
	
}
