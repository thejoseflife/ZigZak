package com.zigtap.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FS {
	
	public void render(Graphics g) {
		Font f = new Font("arial", Font.BOLD, 30);
		Font f1 = new Font("arial", Font.BOLD, 70);
		Font f2 = new Font("arial", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("High score: " + GameZT.HIGHSCORE, 300, 250);
		g.drawString("Final score: " + GameZT.SCORE, 130, 175);
		g.setFont(f1);
		g.drawString("Oh no!", 190, 100);
		g.setFont(f2);
		g.drawString("R to restart, M for menu", 190, 310);
	}

}
