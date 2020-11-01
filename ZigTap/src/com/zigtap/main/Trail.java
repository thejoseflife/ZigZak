package com.zigtap.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Trail extends GameObject {

	private Color randomColor;
	private Random r = new Random();
	private Handler handler;

	private float width = 1;
	private float height = 1;

	public Trail(int x, int y, ID id, Player player, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		randomColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));

	}

	@Override
	public void tick() {
		
		x += Wall.det;
		
		if(x < -50) {
			handler.removeObject(this);
		}
		width += 2;
		height += 2;
		if(height >= 15 || width >= 15) {
			height = 15;
			width = 15;
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(randomColor);
		g2d.setStroke(new BasicStroke(3));
		g.drawRect(x + 16, y + 15, (int)width, (int)height);
		
	}

	@Override
	public Rectangle getBounds() {
		
		return null;
	}

}
