package com.zqmony.ludumdare;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.zqmony.ludumdare.gfx.Screen;
import com.zqmony.ludumdare.gfx.Sprite;
import com.zqmony.ludumdare.util.Input;


public class Game extends Canvas implements Runnable{
	
	private final int WIDTH = 300;
	private final int HEIGHT = 200;
	private final int SCALE = 3;
	private JFrame frame;
	private String title = "The Crevice";
	
	private Thread thread;
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private Input input;
	private Structure struc;
	
	public void init(){
		Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		
		input = new Input();
		addKeyListener(input);
		addFocusListener(input);
		
		screen = new Screen(WIDTH, HEIGHT, SCALE);
		struc = new Structure(input);
	}
	
	public synchronized void start(){
		if(!running){
			running = true;
			thread = new Thread(this, "Game Thread");
			thread.start();
		}
	}
	
	public synchronized void stop(){
		if(running){
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		requestFocus();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			while(delta >= 1){
				ticks++;
				update();
				delta -= 1;
				frames++;
				render();
			}
			
			
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer+=1000;
				System.out.println("UPS: " + ticks + "; FPS: " + frames);
				frames = 0;
				ticks =0;
			}
		}
	}

	public void update(){
		input.update();
		struc.update();
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		struc.render(screen);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.init();
		
		game.frame = new JFrame();
		game.frame.setTitle(game.title);
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}
