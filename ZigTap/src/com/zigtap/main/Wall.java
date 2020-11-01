package com.zigtap.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Wall extends GameObject {

	private Handler handler;
	
	private Random r = new Random();
	
	private Rectangle walltop;
	private Rectangle wallbottom;
	public static boolean moving = false;
	public static int det;
	
	private Color randomColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	
	public Wall(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		height = r.nextInt(370);
		
		walltop = new Rectangle(x, 0, 20, height);
		wallbottom = new Rectangle(x, height + 110, 20, 480 - height - 110);
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		walltop.x += velX;
		wallbottom.x += velX;
		
		if(!GameZT.paused) {
			if(moving) {
				velX = -4;
				det = -4;
			} else {
				velX = 0;
				det = 0;
			}
		} else {
			velX = 0;
			det = 0;
		}
		
		if(x + 20 <= 0) {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(randomColor);
		g2d.fill(walltop);
		g2d.fill(wallbottom);
		
		g2d.setStroke(new BasicStroke(5));
		g.setColor(Color.BLACK);
		g2d.drawLine((int)walltop.getX() + 20, (int)walltop.getY(), (int)walltop.getX() + 20, (int)walltop.getY() + height - 1);
		g2d.drawLine((int)wallbottom.getX() + 20, (int)wallbottom.getY() + 1, (int)wallbottom.getX() + 20, (int)wallbottom.getY() + 480 - height - 110);
		
	}

	public Rectangle getBoundsTop() {
		return walltop;
	}
	public Rectangle getBoundsBottom() {
		return wallbottom;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x + 20, y, 20, GameZT.HEIGHT);
	}
	
}
