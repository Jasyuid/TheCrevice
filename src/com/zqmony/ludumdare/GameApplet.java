package com.zqmony.ludumdare;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet{
	
	Game game = new Game();
	
	public void init(){
		game.init();
		setLayout(new BorderLayout());
		add(game);
		setSize(900, 600);
	}
	
	public void start(){
		game.start();
	}
	
	public void stop(){
		game.stop();
	}
}
