package com.zqmony.ludumdare.gfx;

import com.zqmony.ludumdare.entity.mob.Mob;
import com.zqmony.ludumdare.level.tile.Tile;

public class Screen{
	
	public int width, height, scale;
	
	public int[] pixels;
	private int xOffset, yOffset;
	private int brightness;
	
	public Screen(int width, int height, int scale){
		this.width = width;
		this.height = height;
		this.scale = scale;
		pixels = new int[width*height];
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite){
		for(int y = 0; y < sprite.height; y++){
			int yy = y + yp;
			for(int x = 0; x < sprite.width; x++){
				int xx = x + xp;
				if(xx < -sprite.width || xx >= width || yy < 0 || yy >= height) break;
				if(xx < 0) xx = 0;
				int col = sprite.pixels[x + y * sprite.width];
				if(col != 0xffff00ff && col != 0xffd100d1) pixels[xx + yy * width] = col;
			}
		}
	}
	
	public void renderSpriteB(int xp, int yp, Sprite sprite){
		for(int y = 0; y < sprite.height; y++){
			int yy = y + yp;
			for(int x = 0; x < sprite.width; x++){
				int xx = x + xp;
				if(xx < -sprite.width || xx >= width || yy < 0 || yy >= height) break;
				if(xx < 0) xx = 0;
				int col = sprite.pixels[x + y * sprite.width];
				if(col != 0xffff00ff && col != 0xffd100d1){
					col = Light.changeBrightness(col, brightness);
					pixels[xx + yy * width] = col;
				}
			}
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile){
		xp += xOffset;
		yp += yOffset;
		for(int y = 0; y < tile.sprite.height; y++){
			int yy = y + yp;
			for(int x = 0; x < tile.sprite.width; x++){
				int xx = x + xp;
				if(xx >= width || xx < -tile.sprite.width || yy >= height || yy < -tile.sprite.height) break;
				if(yy < 0)yy = 0;
				if(xx < 0)xx = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.width];
				if(col != 0xffff00ff && col != 0xffd100d1){
					col = Light.changeBrightness(col, brightness);
					pixels[xx + yy * width] = col;
				}
			}
		}
	}
	
	public void renderMob(int xp, int yp, Mob mob){
		xp += xOffset;
		yp += yOffset;
		renderPlayer(xp, yp, mob);
	}
	
	public void renderPlayer(int xp, int yp, Mob mob){
		for(int y = 0; y < mob.sprite.height; y++){
			int yy = y + yp;
			for(int x = 0; x < mob.sprite.width; x++){
				int xx = x + xp;
				if(xx >= width || xx < -mob.sprite.width || yy >= height || yy < 0) break;
				if(yy < 0)yy = 0;
				if(xx < 0)xx = 0;
				int col = mob.sprite.pixels[x + y * mob.sprite.width];
				if(col != 0xffff00ff && col != 0xffd100d1){
					col = Light.changeBrightness(col, brightness);
					pixels[xx + yy * width] = col;
				}
			}
		}
	}
	
	public void renderText(int xp, int yp, int character, int color, int scale){
		int offset = ((character % 28) << 3) + ((character / 28) << 3) * Image.font.width;
		
		for(int y = 0; y < 8; y++){
			int yy = yp + y;
			
			for(int x = 0; x < 8; x++){
				int xx = xp + x;
				
				if(xx >= width || xx < -8 || yy >= height || yy < -8) break;
				if(yy < 0)yy = 0;
				if(xx < 0)xx = 0;
				
				int col = Image.font.pixels[x + y * Image.font.width + offset];
				if(col == 0xffffffff) col = color;
				if(col != 0xffff00ff && col != 0xffd100d1) pixels[xx + yy * width] = col;
				
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public int getBrightness(){
		return brightness;
	}
	
	public void setBrightness(int b){
		brightness = b;
	}
}
