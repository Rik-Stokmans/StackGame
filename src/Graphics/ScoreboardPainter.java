package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Startup.Main;

public class ScoreboardPainter {

	public static void PaintScoreboard(double startWidth, double startHeight, double scoreboardPixelWidth, double scoreboardPixelHeight, Graphics2D g2d) {
		
		//colors
		//background
		Color background = new Color(38, 35, 53);
		
		//variables
		int score = Main.board.getPoints();
		
		
		
		AffineTransform reset = g2d.getTransform();
		//starting translation
		g2d.translate(startWidth, startHeight);
		
		//code to draw the scoreboard
		g2d.setColor(background);
		g2d.fillRoundRect(0,0, (int) scoreboardPixelWidth, (int) scoreboardPixelHeight, (int) (scoreboardPixelWidth * 0.07), (int) (scoreboardPixelWidth * 0.07));
		
		if (scoreboardPixelWidth > scoreboardPixelHeight) {
			//horizontal
			String number = String.valueOf(score);
			char[] digits = number.toCharArray();
			g2d.setColor(new Color(255, 187, 108));
			g2d.translate(scoreboardPixelHeight * 0.1, scoreboardPixelHeight * 0.8);
			g2d.setFont(new Font("Monospaced", Font.PLAIN, (int) (scoreboardPixelHeight * 0.8)));
			
			for (char digit : digits) {
				g2d.drawString(Character.toString(digit), 0, 0);
				g2d.translate(scoreboardPixelWidth/6, 0);
			}
			
			
		}
		else {
			//vertical
			String number = String.valueOf(score);
			char[] digits = number.toCharArray();
			g2d.setColor(new Color(255, 187, 108));
			g2d.translate(scoreboardPixelWidth * 0.25, scoreboardPixelWidth * 0.8);
			g2d.setFont(new Font("Monospaced", Font.PLAIN, (int) (scoreboardPixelWidth * 0.8)));
			
			for (char digit : digits) {
				g2d.drawString(Character.toString(digit), 0, 0);
				g2d.translate(0, scoreboardPixelHeight/6);
			}
		}
		
		
		
		
		//resetting to previous translation
		g2d.setTransform(reset);
	}
}
