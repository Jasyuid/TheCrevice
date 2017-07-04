package com.zqmony.ludumdare.menu;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.util.Input;

public class Menu {
	
	protected Structure struc;
	protected Input input;
	
	protected int selected;
	
	public Menu(Structure struc){
		this.struc = struc;
		input = struc.getInput();
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
}
