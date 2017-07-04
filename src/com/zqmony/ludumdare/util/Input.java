package com.zqmony.ludumdare.util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener, FocusListener{
	
	private boolean[] keys = new boolean[65565];
	private int stop;
	public boolean w, a, s, d, up, down, left, right, space, shift, enter;
	
	public boolean focus = true;
	
	public void update(){
		w = keys[KeyEvent.VK_W];
		a = keys[KeyEvent.VK_A];
		s = keys[KeyEvent.VK_S];
		d = keys[KeyEvent.VK_D];
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
		shift = keys[KeyEvent.VK_SHIFT];
		enter = keys[KeyEvent.VK_ENTER];
		
		if(enter){
			stop++;
		}
		
		if(stop > 1){
			enter = false;
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		focus = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		focus = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]= true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]= false;
		if(!keys[KeyEvent.VK_ENTER]) stop = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
