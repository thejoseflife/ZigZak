package com.zigtap.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

	public static boolean goingUp = true;
	public static boolean isPlaying = true;
	public static boolean isDying = false;
	
	private boolean passing = false;
	
	private BufferedImage upx;
	private BufferedImage downx;
	
	private Line2D side1;
	private Line2D side2;
	private Line2D side3;
	private Handler handler;
	private SpriteSheet ss;
	private Selection selection;
	
	private float timer = 0;
	
	public Player(int x, int y, ID id, Handler handler, SpriteSheet ss, Selection selection) {
		super(x, y, id);
		this.handler = handler;
		this.selection = selection;
		this.ss = ss;
		
	}

	@Override
	public void tick() {
		x += velX;
		
		if(selection.selection == 1) {
			upx = ss.grabImage(1, 1, 64, 64);
			downx = ss.grabImage(2, 1, 64, 64);
		} else if(selection.selection == 2) {
			upx = ss.grabImage(3, 1, 64, 64);
			downx = ss.grabImage(4, 1, 64, 64);
		} else if(selection.selection == 3) {
			upx = ss.grabImage(1, 2, 64, 64);
			downx = ss.grabImage(2, 2, 64, 64);
		} else if(selection.selection == 4) {
			upx = ss.grabImage(3, 2, 64, 64);
			downx = ss.grabImage(4, 2, 64, 64);
		} else if(selection.selection == 5) {
			upx = ss.grabImage(1, 3, 64, 64);
			downx = ss.grabImage(2, 3, 64, 64);
		}
		
		if(!GameZT.paused) {
			y += velY;
		}
		
		if(goingUp) {
			side1 = new Line2D.Float(x, y + 11, x + 32, y);
			side2 = new Line2D.Float(x, y + 11, x + 20, y + 30);
			side3 = new Line2D.Float(x + 32, y, x + 20, y + 30);
		} else if(!goingUp) {
			side1 = new Line2D.Float(x, y + 19, x + 20, y - 1);
			side2 = new Line2D.Float(x, y + 20, x + 31, y + 32);
			side3 = new Line2D.Float(x + 20, y, x + 31, y + 30);
		}
		
		Collision();
		
		if(y > GameZT.HEIGHT - 48) {
			isPlaying = false;
			isDying = true;
			GameZT.state = GameZT.STATE.FINISHED;
		}
		
		if(y < 0|| y > GameZT.HEIGHT - 60) {
			isPlaying = false;
			isDying = true;
			velY = 5;
			Wall.moving = false;
			
		}
		if(Wall.moving && !GameZT.paused) {
			timer++;
			
			if(timer >= 4) {
			handler.addObject(new Trail(x, y, ID.Trail, this, handler));
			timer = 0;
			}
		}
	}
	
	public void Collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Wall) {
				if(boundsIntersects(((Wall)tempObject).getBoundsTop()) || boundsIntersects(((Wall)tempObject).getBoundsBottom())) {
					isPlaying = false;
					isDying = true;
					velY = 5;
					Wall.moving = false;
				} else if(boundsIntersects(tempObject.getBounds()) && !passing) {
					GameZT.SCORE++;
					passing = true;
				} else if(x >= tempObject.getX() + 40 && x <= tempObject.getX() + 60) {
					passing = false;
				}
			}
		}
	}


	@Override
	public void render(Graphics g) {
		
		Font f1 = new Font("arial", Font.BOLD, 50);
		
			g.setFont(f1);
			g.setColor(Color.WHITE);
			if(isDying) {
				g.drawString("Whoops!", GameZT.WIDTH / 2 - 100, GameZT.HEIGHT / 2 - 50);
			}
			
		Graphics2D g2d = (Graphics2D)g;
		
		if(goingUp) {
			g2d.drawImage(upx, x, y, 32, 32, null);
		} else if(!goingUp) {
			g2d.drawImage(downx, x, y, 32, 32, null);
		}

	}

	@Override
	public Rectangle getBounds() {
		return null;
		
	}
	
	public boolean boundsIntersects(Rectangle r) {
		if(side1.intersects(r) || side2.intersects(r) || side3.intersects(r)) {
			return true;
		}
		return false;
	}

}
