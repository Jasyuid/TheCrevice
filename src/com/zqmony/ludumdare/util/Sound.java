package com.zqmony.ludumdare.util;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static Sound select = new Sound("/sounds/select.wav");
	public static Sound hit = new Sound("/sounds/hit.wav");
	public static Sound enemydeath = new Sound("/sounds/enemydeath.wav");
	public static Sound start = new Sound("/sounds/start.wav");
	public static Sound die  = new Sound("/sounds/die.wav");
	public static Sound shoot = new Sound("/sounds/shoot.wav");
	public static Sound music = new Sound("/sounds/music.wav");
	
	private AudioClip sound;
	
	public Sound(String path){
		sound = Applet.newAudioClip(Sound.class.getResource(path));
	}
	
	public void play(boolean loop){
		if(loop) sound.loop();
		else sound.play();;
	}
	
	public void stop(){
		sound.stop();
	}
}
