package com.zigtap.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.zigtap.main.GameZT.STATE;

public class Menu implements MouseListener, MouseMotionListener {
	
	private float starth = 100;
	private float startw = 200;
	private float startg = 0;
	private float startx = 310 - (startw/2);
	private float starty = 150;
	
	private float quith = 100;
	private float quitw = 200;
	private float quitg = 0;
	private float quitx = 310 - (quitw/2);
	private float quity = 300;
	
	private BufferedImage title;
	
	private Color purrrple = new Color(103, 0, 112);
	private Point p;
	
	private RoundRectangle2D start;
	private RoundRectangle2D quit;
	
	public Menu(GameZT game, BufferedImageLoader loader) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		
		try {
			title = loader.loadImage("/title.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g.drawImage(title, 60, 0, 500, 150, null);
		
		g.setColor(Color.WHITE);
		start = new RoundRectangle2D.Float(startx, starty, startw, starth, 70, 70);
		quit = new RoundRectangle2D.Float(quitx, quity, quitw, quith, 70, 70);
		g2d.fill(start);
		g2d.fill(quit);
		
		g.setColor(purrrple);
		g.setFont(new Font("arial", Font.BOLD, 70));
		g.drawString("Start", 225, 220);
		g.drawString("Quit", 235, 370);
		
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("by Josef Sajonz", 430, 155);
		
	}
	
	public void tick() {
		starth += startg;
		startw += startg;
		
		if(startw <= 230 && startg > 0) {
			startx -= startg/2;
			starty -= startg/2;
		}
		
		if(startg < 0 && startw >= 200) {
			startx -= startg/2;
			starty -= startg/2;
		}
		
		if(startw >= 230) {
			startw = 230;
		}
		if(starth >= 130) {
			starth = 130;
		}
		
		if(startw <= 200) {
			startw = 200;
		}
		
		if(starth <= 100) {
			starth = 100;
		}
		
		quith += quitg;
		quitw += quitg;
		
		if(quitw <= 230 && quitg > 0) {
			quitx -= quitg/2;
			quity -= quitg/2;
		}
		
		if(quitg < 0 && quitw >= 200) {
			quitx -= quitg/2;
			quity -= quitg/2;
		}
		
		if(quitw >= 230) {
			quitw = 230;
		}
		if(quith >= 130) {
			quith = 130;
		}
		
		if(quitw <= 200) {
			quitw = 200;
		}
		
		if(quith <= 100) {
			quith = 100;
		}
		
		if(p != null) {
			if(start.contains(p)) {
				startg = 3;
			} else {
				startg = -3;
			}
		
			if(quit.contains(p)) {
				quitg = 3;
			} else {
				quitg = -3;
			}	
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		p = e.getPoint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(GameZT.state == STATE.MENU) {
			if(quit.contains(e.getPoint())) System.exit(1);
			if(start.contains(e.getPoint())) GameZT.state = STATE.SELECTION;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
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
