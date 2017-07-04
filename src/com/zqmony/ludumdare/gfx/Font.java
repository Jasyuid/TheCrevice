package com.zqmony.ludumdare.gfx;

public class Font {
	
	public static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ  " + "abcdefghijklmnopqrstuvwxyz  " + ".,!?\"'/\\<>0123456789+-=*:;()";
	
	public static void render(Screen screen, String msg, int x, int y, int color, int scale){
		
		for(int i = 0; i < msg.length(); i++){
			int charIndex = chars.indexOf(msg.charAt(i));
			if(charIndex >= 0) screen.renderText(x + (8*i), y, charIndex, color, scale); 
		}
	}
	
}
