package com.zqmony.ludumdare.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	private String path;
	
	public int width;
	public int height;
	public int[] pixels;
	
	public static Image tilesheet = new Image("/textures/sheet.png");
	public static Image mobsheet = new Image("/textures/mobsheet.png");
	public static Image font = new Image("/textures/font.png");
	public static Image gui = new Image("/textures/gui.png");
	public static Image menuback = new Image("/textures/menuback.png");
	public static Image gameover = new Image("/textures/gameover.png");
	public static Image win = new Image("/textures/win.png");
	
	public Image(String path){
		this.path = path;
		load();
	}
	
	public void load(){
		BufferedImage image = null;
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(image == null) return;
		
		width = image.getWidth();
		height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}
}
