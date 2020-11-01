package com.zigtap.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.zigtap.main.GameZT.STATE;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private GameZT game;
	
	public KeyInput(Handler handler, GameZT game) {
		this.handler = handler;
		this.game = game;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.id == ID.Player) {
				if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && Player.isPlaying && !GameZT.paused) {
					Player.goingUp = true;
					tempObject.velY = -5;
					Wall.moving = true;
				} else if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && Player.isPlaying && !GameZT.paused) {
					Player.goingUp = false;
					tempObject.velY = 5;
					Wall.moving = true;
				}
				if(GameZT.state == STATE.FINISHED) {
					if(key == KeyEvent.VK_R) {
						game.restartGame();
					}
				} else if(GameZT.state == STATE.GAME) {
					if(key == KeyEvent.VK_ESCAPE && !Player.isDying) {
						if(GameZT.paused) {
							GameZT.paused = false;
						} else {
							GameZT.paused = true;
						}
					}
				}
			}
		}
		
		if(key == KeyEvent.VK_M && GameZT.state == STATE.FINISHED) {
			game.restartGame();
			GameZT.state = STATE.MENU;
			
		}
		
	}
	
}

