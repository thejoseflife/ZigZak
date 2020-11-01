package com.zigtap.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import com.zigtap.main.GameZT.STATE;

public class Selection implements MouseListener, MouseMotionListener {

	public int selection = 0;
	private int sel = 0;
	
	private BufferedImage sel1;
	private BufferedImage sel2;
	private BufferedImage sel3;
	private BufferedImage sel4;
	private BufferedImage sel5;
	
	private Rectangle sel1r = new Rectangle(80, 200, 64, 64);
	private Rectangle sel2r = new Rectangle(180, 200, 64, 64);
	private Rectangle sel3r = new Rectangle(280, 200, 64, 64);
	private Rectangle sel4r = new Rectangle(380, 200, 64, 64);
	private Rectangle sel5r = new Rectangle(480, 200, 64, 64);
	
	public Selection(GameZT game, SpriteSheet ss) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		
		sel1 = ss.grabImage(1, 1, 64, 64);
		sel2 = ss.grabImage(3, 1, 64, 64);
		sel3 = ss.grabImage(1, 2, 64, 64);
		sel4 = ss.grabImage(3, 2, 64, 64);
		sel5 = ss.grabImage(1, 3, 64, 64);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g.drawImage(sel1, 80, 200, null);
		g.drawImage(sel2, 180, 200, null);
		g.drawImage(sel3, 280, 200, null);
		g.drawImage(sel4, 380, 200, null);
		g.drawImage(sel5, 480, 200, null);
		
		g.setColor(Color.WHITE);
		
		if(sel == 1) {
			g2d.draw(sel1r);
		} else if(sel == 2) {
			g2d.draw(sel2r);
		} else if(sel == 3) {
			g2d.draw(sel3r);
		} else if(sel == 4) {
			g2d.draw(sel4r);
		} else if(sel == 5) {
			g2d.draw(sel5r);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Choose Color:", 138, 50);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		
		if(sel1r.contains(p)) {
			sel = 1;
		} else if(sel2r.contains(p)) {
			sel = 2;
		} else if(sel3r.contains(p)) {
			sel = 3;
		} else if(sel4r.contains(p)) {
			sel = 4;
		} else if(sel5r.contains(p)) {
			sel = 5;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		
		if(GameZT.state == STATE.SELECTION) {
		if(sel1r.contains(p)) {
			selection = 1;
			GameZT.state = STATE.GAME;
		} else if(sel2r.contains(p)) {
			selection = 2;
			GameZT.state = STATE.GAME;
		} else if(sel3r.contains(p)) {
			selection = 3;
			GameZT.state = STATE.GAME;
		} else if(sel4r.contains(p)) {
			selection = 4;
			GameZT.state = STATE.GAME;
		} else if(sel5r.contains(p)) {
			selection = 5;
			GameZT.state = STATE.GAME;
		}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
