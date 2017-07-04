package com.zqmony.ludumdare.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.zqmony.ludumdare.Structure;
import com.zqmony.ludumdare.entity.mob.Boss;
import com.zqmony.ludumdare.entity.mob.Player;
import com.zqmony.ludumdare.util.Input;

public class SpawnLevel extends Level{
	
	public SpawnLevel(String path, Structure struc){
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
		spawnx = 2*16;
		spawny = 3*16;
		addEntity(new Boss(this, 16, 16));
		addEntity(new Player(this, spawnx, spawny));
	}
	
	
}	
