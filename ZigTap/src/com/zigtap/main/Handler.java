package com.zigtap.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
			
		}
		g.setColor(Color.white);
		g.drawString("" + GameZT.SCORE, 100, 100);
		
		if(Player.isDying) {
			g.drawString("Whoops!", GameZT.WIDTH / 2 - 100, GameZT.HEIGHT / 2 - 50);
		}
		
		g.dispose();
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
