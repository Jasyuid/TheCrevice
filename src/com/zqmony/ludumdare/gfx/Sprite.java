package com.zqmony.ludumdare.gfx;

public class Sprite {
	
	private Image image;
	
	public int width, height;
	public int[] pixels;
	
	public static Sprite grasstile = new Sprite(0, 3, Image.tilesheet);
	public static Sprite bricktile = new Sprite(1, 3, Image.tilesheet);
	public static Sprite woodtile = new Sprite(2, 3, Image.tilesheet);
	public static Sprite[] sisgraph = {new Sprite(3, 3, Image.tilesheet), 
									   new Sprite(4, 3, Image.tilesheet)};
	
	public static Sprite laddertile = new Sprite(4, 0, Image.tilesheet);
	public static Sprite spawntile = new Sprite(7, 0, Image.tilesheet);
	
	public static Sprite dirttile = new Sprite(1, 1, Image.tilesheet);
	public static Sprite rocktile = new Sprite(5, 0, Image.tilesheet);
	public static Sprite barriertile = new Sprite(6, 0, Image.tilesheet);
	
	public static Sprite[] walltile = {new Sprite(0, 0, Image.tilesheet),
									   new Sprite(0, 1, Image.tilesheet),
									   new Sprite(0, 2, Image.tilesheet),
									   new Sprite(1, 2, Image.tilesheet),
									   new Sprite(2, 2, Image.tilesheet),
									   new Sprite(2, 1, Image.tilesheet),
									   new Sprite(2, 0, Image.tilesheet),
									   new Sprite(1, 0, Image.tilesheet),
									   new Sprite(3, 1, Image.tilesheet),
									   new Sprite(4, 1, Image.tilesheet),
									   new Sprite(4, 2, Image.tilesheet),
									   new Sprite(3, 2, Image.tilesheet)};
	
	public static Sprite[] holetile = {new Sprite(1, 5, Image.tilesheet),
									   new Sprite(0, 4, Image.tilesheet),
									   new Sprite(0, 5, Image.tilesheet),
									   new Sprite(0, 6, Image.tilesheet),
									   new Sprite(1, 6, Image.tilesheet),
									   new Sprite(2, 6, Image.tilesheet),
									   new Sprite(2, 5, Image.tilesheet),
									   new Sprite(2, 4, Image.tilesheet),
									   new Sprite(1, 4, Image.tilesheet)};
	
	public static Sprite ddirttile = new Sprite(3, 0, Image.tilesheet);
	public static Sprite voidtile = new Sprite(16, 16, 0x994400);
	
	public static Sprite[] player = {new Sprite(0, 0, Image.mobsheet),
									 new Sprite(0, 1, Image.mobsheet),
									 new Sprite(0, 2, Image.mobsheet),
									 new Sprite(1, 0, Image.mobsheet),
									 new Sprite(1, 1, Image.mobsheet),
									 new Sprite(1, 2, Image.mobsheet),
									 new Sprite(2, 0, Image.mobsheet),
									 new Sprite(2, 1, Image.mobsheet),
									 new Sprite(2, 2, Image.mobsheet),
									 new Sprite(3, 0, Image.mobsheet),
									 new Sprite(3, 1, Image.mobsheet),
									 new Sprite(3, 2, Image.mobsheet)};
	public static Sprite[] boss = {new Sprite(0, 3, Image.mobsheet),
								   new Sprite(1, 3, Image.mobsheet),
								   new Sprite(2, 3, Image.mobsheet),
								   new Sprite(3, 3, Image.mobsheet)};
	public static Sprite[] blob = {new Sprite(4, 0, Image.mobsheet),
								   new Sprite(5, 0, Image.mobsheet),
								   new Sprite(6, 0, Image.mobsheet),
								   new Sprite(7, 0, Image.mobsheet)};
	public static Sprite[] chaser = {new Sprite(4, 1, Image.mobsheet),
									 new Sprite(5, 1, Image.mobsheet),
									 new Sprite(6, 1, Image.mobsheet),
									 new Sprite(7, 1, Image.mobsheet)};
	public static Sprite[] plasma = {new Sprite(4, 2, Image.mobsheet),
									 new Sprite(5, 2, Image.mobsheet),
									 new Sprite(6, 2, Image.mobsheet),
									 new Sprite(7, 2, Image.mobsheet)};
	public static Sprite[] enemy = {new Sprite(9, 0, Image.mobsheet),
									new Sprite(10, 0, Image.mobsheet),
									new Sprite(11, 0, Image.mobsheet),
									new Sprite(12, 0, Image.mobsheet)};	
	
	public static Sprite[] plasmashot = {new Sprite(8, 0, Image.mobsheet),
										 new Sprite(8, 1, Image.mobsheet)};
	
	public static Sprite textbox = new Sprite(0, 4, 300, 36, Image.gui);
	public static Sprite[] heart = {new Sprite(0, 7, Image.gui),
									new Sprite(1, 7, Image.gui)};
	
	public static Sprite title = new Sprite(0, 0, 240, 48, Image.gui);
	public static Sprite menuback = new Sprite(0, 0, 300, 200, Image.menuback);
	public static Sprite gameover = new Sprite(0, 0, 300, 200, Image.gameover);
	public static Sprite win = new Sprite(0, 0, 300, 200, Image.win);
	
	public Sprite(int xp, int yp, Image image){
		width = 16;
		height = 16;
		pixels = new int[width*height];
		xp*=16;
		yp*=16;
		this.image = image;
		load(xp, yp);
	}
	
	public Sprite(int xp, int yp, int width, int height, Image image){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		xp*=16;
		yp*=16;
		this.image = image;
		load(xp, yp);
	}
	
	public Sprite(int width, int height, int color){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = color;
		}
	}
	
	public void load(int xp, int yp){
		for(int y = 0; y < height; y++){
			int yy = yp + y;
			for(int x = 0; x < width; x++){
				int xx = xp + x;
				pixels[x + y * width] = image.pixels[xx + yy * image.width];
			}
		}
	}
}
