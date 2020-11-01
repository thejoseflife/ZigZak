package com.zigtap.main;
//Notes:


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

public class GameZT extends Canvas implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -2421751466973514721L;

	private BufferedImage spritesheet = null;
	private BufferedImage background = null;
	private BufferedImage backgrounda = null;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH * 9 / 12;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Player player;
	private FS fs;
	private Menu menu;
	private SpriteSheet ss;
	private Selection selection;
	
	private int bx = 0;
	private int bx2 = 2000;
	public static int velBX = 0;
	
	public static boolean paused = false;
	
	public static int SCORE = 0;
	public static int HIGHSCORE = 0;
	
	public static Random r = new Random();	

	public static enum STATE {
		MENU,
		GAME,
		FINISHED,
		HELP,
		SELECTION
	}
	
	public static STATE state = STATE.MENU;
	
	public void init() {
		handler = new Handler();
		fs = new FS();
		
		requestFocus();
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
			try {

				spritesheet = loader.loadImage("/sprite_sheet.png");
				background = loader.loadImage("/background2.png");
				backgrounda = loader.loadImage("/background2a.png");

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		ss = new SpriteSheet(spritesheet);
		
		selection = new Selection(this, ss);
		player = new Player(150, HEIGHT/2 - 32, ID.Player, handler, ss, selection);

		menu = new Menu(this, loader);
		
		handler.addObject(player);
		handler.addObject(new Wall(620, 0, ID.Wall, handler));
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int ticks = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println("Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		bx += velBX;
		bx2 += velBX;

		if(bx <= -2000) {
			bx = 2000;
		}
		
		if(bx2 <= -2000) {
			bx2 = 2000;
		}
		
		if(SCORE > HIGHSCORE) {
			HIGHSCORE = SCORE;
		}
		
		if(Wall.moving && !paused) {
			velBX = -1;
		} else {
			velBX = 0;
		}
		
		if(state == STATE.MENU) {
			menu.tick();
		}
		
		if(state == STATE.GAME) {
			handler.tick();
		}
		
		for(int i = 0; i < handler.object.size(); i++)	{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Wall) {
				if(tempObject.getX() == 500) {
					handler.addObject(new Wall(800, 0, ID.Wall, handler));
				}
			}
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//Graphics here v
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(state == STATE.GAME) {
			g2d.drawImage(background, bx, 0, this);
			g2d.drawImage(backgrounda, bx2, 0, this);
			
			if(paused) {
				Color tr = new Color(0, 0, 0, 160);
				g.setColor(tr);
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}
			
			handler.render(g);
			
			g.setColor(Color.WHITE);
			
		} else if(state == STATE.FINISHED) {
			fs.render(g);
		} else if(state == STATE.MENU) {
			menu.render(g);
		} else if(state == STATE.SELECTION) {
			selection.render(g);
		}
		
		//Graphics here ^
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("ZigTap");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		GameZT gameZT = new GameZT();
		frame.add(gameZT);
		frame.setVisible(true);
		gameZT.start();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("In");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("Out");
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//System.out.println("On");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("Off");
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		//System.out.println("Drag");
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("Move");
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public void restartGame() {
		//System.out.print("RESTART");
		SCORE = 0;
		Player.isDying = false;
		Player.isPlaying = true;
		bx = 0;
		bx2 = 2000;
		velBX = 0;
		handler.object.clear();
		handler.addObject(new Player(150, HEIGHT/2 - 32, ID.Player, handler, ss, selection));
		handler.addObject(new Wall(620, 0, ID.Wall, handler));
		state = STATE.GAME;
	}
	
}
