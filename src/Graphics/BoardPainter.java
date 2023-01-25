package Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.*;

import Board.PieceMechanics.Block;
import Board.PieceMechanics.Location;
import Startup.Main;

public class BoardPainter {

	public static void PaintBoard(double startWidth, double startHeight, double boardPixelWidth, double boardPixelHeight, Graphics2D g2d) {
		//general variables
		char[][] board = Main.board.getBoard();
		int boardWidth = board.length;
		int boardHeight = board[boardWidth-1].length;
		double squareSize = boardPixelWidth / boardWidth;
		Block mainBlock = null;
		Block secBlock = null;
		
		if (Main.board.getFallingPiece() != null) {
			mainBlock = Main.board.getFallingPiece().getMainBlock();
			secBlock = Main.board.getFallingPiece().getSecondaryBlock();
		}
		
		//colors
		//background
		Color background = new Color(38, 35, 53);
		//red
		Color red = new Color(255, 71, 156);
		Color redShadow = new Color(171, 36, 99);
		Color redHighlight = new Color(255, 71, 156);
		//blue
		Color blue = new Color(53, 181, 255);
		Color blueShadow = new Color(24, 119, 173);
		Color blueHighlight = new Color(53, 181, 255);
		//green
		Color green = new Color(0, 255, 63);
		Color greenShadow = new Color(0, 158, 39);
		Color greenHighlight = new Color(0, 255, 63);
		//yellow
		Color yellow = new Color(255, 251, 56);
		Color yellowShadow = new Color(173, 171, 31);
		Color yellowHighlight = new Color(255, 251, 56);
		//Connection Point
		Color dot = new Color(140, 140, 140);
		Color dotShadow = new Color(76, 76, 76);
		Color dotHighlight = new Color(150, 150, 150);
		//connection rod
		Color rod = new Color(128, 128, 128);
		Color rodShadow = new Color(76, 76, 76);
		Color rodHighlight = new Color(153, 153, 153);
		
		//block attributes
		double blockSize = squareSize * 0.9;
		int blockCornerRadius = (int) (squareSize * 0.4);
		int blockShadowOffset = (int) (blockSize * 0.04);
		
		//block decoration attributes 
		int centerDotSize = (int) (blockSize * 0.16);
		int centerDotOffset = (int) ((blockSize - centerDotSize) / 2);
		int centerDotCornerRadius = (int) (blockSize * 0.08);
		int centerDotAccentSize = (int) (blockSize * 0.1);
		int centerDotAccentOffset = (int) ((blockSize - centerDotAccentSize) / 2);
		int centerDotShadowOffset = (int) (blockSize * 0.032);
		
		
		//rod attributes
		int connectLineThickness = (int) (blockSize * 0.06);
		int connectLineOffset = (int) ((blockSize - connectLineThickness) / 2);
		int connectLineShadowOffset = (int) (blockSize * 0.03);
				
		AffineTransform reset = g2d.getTransform();
		//starting translation
		g2d.translate(startWidth, startHeight);
		
		
		//paints the background
		g2d.setColor(background);
		g2d.fillRoundRect(0,0, (int) boardPixelWidth, (int) boardPixelHeight, (int) (boardPixelWidth * 0.05), (int) (boardPixelWidth * 0.05));
		
		//code to paint the blocks
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				g2d.translate(j * squareSize + (squareSize - blockSize)/2, i * squareSize + (squareSize - blockSize)/2);
				if (board[j][i] != 'e') {
					if (board[j][i] == 'r') {
						g2d.setColor(redShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(red);
					}
					else if (board[j][i] == 'b') {
						g2d.setColor(blueShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(blue);
					}
					else if (board[j][i] == 'g') {
						g2d.setColor(greenShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(green);
					}
					else if (board[j][i] == 'y') {
						g2d.setColor(yellowShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(yellow);
					}
					g2d.fillRoundRect(0, 0, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
				}
				else if (mainBlock != null && mainBlock.getLocation().getX() == j && mainBlock.getLocation().getY() == i) {
					if (mainBlock.getBlockName() == 'r') {
						g2d.setColor(redShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(red);
					}
					else if (mainBlock.getBlockName() == 'b') {
						g2d.setColor(blueShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(blue);
					}
					else if (mainBlock.getBlockName() == 'g') {
						g2d.setColor(greenShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(green);
					}
					else if (mainBlock.getBlockName() == 'y') {
						g2d.setColor(yellowShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(yellow);
					}
					g2d.fillRoundRect(0, 0, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
				}
				else if (secBlock != null && secBlock.getLocation().getX() == j && secBlock.getLocation().getY() == i) {
					if (secBlock.getBlockName() == 'r') {
						g2d.setColor(redShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(red);
					}
					else if (secBlock.getBlockName() == 'b') {
						g2d.setColor(blueShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(blue);
					}
					else if (secBlock.getBlockName() == 'g') {
						g2d.setColor(greenShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(green);
					}
					else if (secBlock.getBlockName() == 'y') {
						g2d.setColor(yellowShadow);
						g2d.fillRoundRect(0 - blockShadowOffset, 0 + blockShadowOffset, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
						g2d.setColor(yellow);
					}
					g2d.fillRoundRect(0, 0, (int) blockSize, (int) blockSize, blockCornerRadius, blockCornerRadius);
				}
				g2d.translate((j * squareSize + (squareSize - blockSize)/2) * -1, (i * squareSize + (squareSize - blockSize)/2) * -1);
			}
		}
		
		//code to paint the connections
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				g2d.translate(j * squareSize + (squareSize - blockSize)/2, i * squareSize + (squareSize - blockSize)/2);
				if (board[j][i] != 'e') {
					if (j+1 >= 0 && i >= 0 && j+1 < board.length && i < board[j].length) {
						if (board[j][i] == board[j+1][i]) {
							g2d.setColor(dotShadow);
							g2d.fillRoundRect(centerDotOffset - centerDotShadowOffset, centerDotOffset + centerDotShadowOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					if (j-1 >= 0 && i >= 0 && j-1 < board.length && i < board[j].length) {
						if (board[j][i] == board[j-1][i]) {
							g2d.setColor(dotShadow);
							g2d.fillRoundRect(centerDotOffset - centerDotShadowOffset, centerDotOffset + centerDotShadowOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					if (j >= 0 && i+1 >= 0 && j < board.length && i+1 < board[j].length) {
						if (board[j][i] == board[j][i+1]) {
							g2d.setColor(dotShadow);
							g2d.fillRoundRect(centerDotOffset - centerDotShadowOffset, centerDotOffset + centerDotShadowOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					if (j >= 0 && i-1 >= 0 && j < board.length && i-1 < board[j].length) {
						if (board[j][i] == board[j][i-1]) {
							g2d.setColor(dotShadow);
							g2d.fillRoundRect(centerDotOffset - centerDotShadowOffset, centerDotOffset + centerDotShadowOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					//left
					if (j-1 >= 0 && i >= 0 && j-1 < board.length && i < board[j].length) {
						if (board[j][i] == board[j-1][i]) {
							g2d.setColor(rodShadow);
							g2d.fillRoundRect(connectLineOffset - (int) (squareSize - connectLineThickness/2) + connectLineShadowOffset * -1, connectLineOffset + connectLineShadowOffset, (int) (squareSize + connectLineThickness/2), connectLineThickness, 0, 0);
							g2d.setColor(rod);
							g2d.fillRoundRect(connectLineOffset - (int) (squareSize - connectLineThickness/2), connectLineOffset, (int) (squareSize + connectLineThickness/2), connectLineThickness, 0, 0);
							g2d.setColor(rodShadow);
							g2d.fillRoundRect(centerDotOffset - centerDotShadowOffset/2, centerDotOffset + (centerDotSize-connectLineThickness)/2, centerDotSize, connectLineThickness, 0, 0);
						}
					}
					//down
					if (j >= 0 && i+1 >= 0 && j < board.length && i+1 < board[j].length) {
						if (board[j][i] == board[j][i+1]) {
							g2d.setColor(rodShadow);
							g2d.fillRoundRect(connectLineOffset - connectLineShadowOffset, connectLineOffset + connectLineShadowOffset, connectLineThickness, (int) (squareSize - connectLineThickness/2), 0, 0);
							g2d.setColor(rod);
							g2d.fillRoundRect(connectLineOffset, connectLineOffset, connectLineThickness, (int) (squareSize - connectLineThickness/2), 0, 0);
							g2d.setColor(rodShadow);
							g2d.fillRoundRect(centerDotOffset + (centerDotSize-connectLineThickness)/2, centerDotOffset + centerDotShadowOffset/2, connectLineThickness, centerDotSize, 0, 0);
						}
					}
				}
				g2d.translate((j * squareSize + (squareSize - blockSize)/2) * -1, (i * squareSize + (squareSize - blockSize)/2) * -1);
			}
		}
		
		//code to paint the block decorations
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				g2d.translate(j * squareSize + (squareSize - blockSize)/2, i * squareSize + (squareSize - blockSize)/2);
				if (board[j][i] != 'e') {
					if (j+1 >= 0 && i >= 0 && j+1 < board.length && i < board[j].length) {
						if (board[j][i] == board[j+1][i]) {
							g2d.setColor(dot);
							g2d.fillRoundRect(centerDotOffset, centerDotOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
							g2d.setColor(dotHighlight);
							g2d.fillRoundRect(centerDotAccentOffset, centerDotAccentOffset, centerDotAccentSize, centerDotAccentSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					if (j-1 >= 0 && i >= 0 && j-1 < board.length && i < board[j].length) {
						if (board[j][i] == board[j-1][i]) {
							g2d.setColor(dot);
							g2d.fillRoundRect(centerDotOffset, centerDotOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
							g2d.setColor(dotHighlight);
							g2d.fillRoundRect(centerDotAccentOffset, centerDotAccentOffset, centerDotAccentSize, centerDotAccentSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					if (j >= 0 && i+1 >= 0 && j < board.length && i+1 < board[j].length) {
						if (board[j][i] == board[j][i+1]) {
							g2d.setColor(dot);
							g2d.fillRoundRect(centerDotOffset, centerDotOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
							g2d.setColor(dotHighlight);
							g2d.fillRoundRect(centerDotAccentOffset, centerDotAccentOffset, centerDotAccentSize, centerDotAccentSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
					if (j >= 0 && i-1 >= 0 && j < board.length && i-1 < board[j].length) {
						if (board[j][i] == board[j][i-1]) {
							g2d.setColor(dot);
							g2d.fillRoundRect(centerDotOffset, centerDotOffset, centerDotSize, centerDotSize, centerDotCornerRadius, centerDotCornerRadius);
							g2d.setColor(dotHighlight);
							g2d.fillRoundRect(centerDotAccentOffset, centerDotAccentOffset, centerDotAccentSize, centerDotAccentSize, centerDotCornerRadius, centerDotCornerRadius);
						}
					}
				}
				g2d.translate((j * squareSize + (squareSize - blockSize)/2) * -1, (i * squareSize + (squareSize - blockSize)/2) * -1);
			}
		}
		
		//code to paint the indicators for the next piece
		double indicatorBackdropSize = boardPixelWidth/15;
		double indicatorSize = boardPixelWidth/30;
		double indicatorOffset = 0;
		
		if (Main.board.getNextPiece() != null) {
			char color = Main.board.getNextPiece().getMainBlock().getBlockName();
			g2d.translate(indicatorOffset, indicatorOffset);
			g2d.setColor(background);
			g2d.fillRoundRect(0, 0, (int) indicatorBackdropSize, (int) indicatorBackdropSize, (int) indicatorBackdropSize, (int) indicatorBackdropSize);
			
			if (color == 'r') g2d.setColor(red);
			else if (color == 'b') g2d.setColor(blue);
			else if (color == 'g') g2d.setColor(green);
			else if (color == 'y') g2d.setColor(yellow);
			
			g2d.fillRoundRect((int) ((indicatorBackdropSize - indicatorSize)/2), (int) ((indicatorBackdropSize - indicatorSize)/2), (int) indicatorSize, (int) indicatorSize, (int) indicatorSize, (int) indicatorSize);
			g2d.translate(indicatorOffset * -1, indicatorOffset * -1);
		
			g2d.translate(boardPixelWidth - indicatorOffset/2 - indicatorBackdropSize, indicatorOffset);
			g2d.setColor(background);
			g2d.fillRoundRect(0, 0, (int) indicatorBackdropSize, (int) indicatorBackdropSize, (int) indicatorBackdropSize, (int) indicatorBackdropSize);
			
			color = Main.board.getNextPiece().getSecondaryBlock().getBlockName();
			if (color == 'r') g2d.setColor(red);
			else if (color == 'b') g2d.setColor(blue);
			else if (color == 'g') g2d.setColor(green);
			else if (color == 'y') g2d.setColor(yellow);
			
			g2d.fillRoundRect((int) ((indicatorBackdropSize - indicatorSize)/2), (int) ((indicatorBackdropSize - indicatorSize)/2), (int) indicatorSize, (int) indicatorSize, (int) indicatorSize, (int) indicatorSize);
			g2d.translate((boardPixelWidth - indicatorOffset/2 - indicatorBackdropSize) * -1, indicatorOffset * -1);
		}
		
		
		//pause overlay
		if (Main.gamePaused) {
			AffineTransform pauseButtonReset = g2d.getTransform();
			int pauseButtonSize = 175;
			
			g2d.setStroke(new BasicStroke(pauseButtonSize/25, 
										  BasicStroke.CAP_ROUND, 
										  BasicStroke.JOIN_ROUND));
			
			g2d.translate(boardPixelWidth/2 - pauseButtonSize/2, boardPixelHeight/2 - pauseButtonSize/2);
			
			//point locations of the triangle
			Location point1 = new Location(pauseButtonSize/3, pauseButtonSize/4);
			Location point2 = new Location(pauseButtonSize/3, (pauseButtonSize/4) * 3);
			Location point3 = new Location(point1.getX(), point1.getY());
			Location point4 = new Location(point2.getX(), point2.getY());
			Location point5 = new Location((pauseButtonSize/4) * 3, pauseButtonSize/2);
			
			//draws the background
			g2d.setColor(new Color(150, 150, 150, 200));
			g2d.fillRoundRect((int) (pauseButtonSize/4) * -1, (int) (pauseButtonSize/8) * -1, (int) (pauseButtonSize * 1.5), (int) (pauseButtonSize * 1.5), pauseButtonSize/3, pauseButtonSize/3);
			
			
			//draws the circle and triangle
			g2d.setColor(new Color(25,25,25, 200));
			g2d.drawOval(0, 0, pauseButtonSize, pauseButtonSize);
			g2d.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
			g2d.drawLine(point3.getX(), point3.getY(), point5.getX(), point5.getY());
			g2d.drawLine(point4.getX(), point4.getY(), point5.getX(), point5.getY());
			
			g2d.setFont(new Font("Monospaced", Font.BOLD, (int) (pauseButtonSize/8)));
			
			g2d.translate(pauseButtonSize/18, pauseButtonSize * 1.20);
			g2d.drawString("'p' To Start", 0, 0);
			
			g2d.setTransform(pauseButtonReset);
		}
		
		
		
		
		
		
		
		//resetting to previous translation
		g2d.setTransform(reset);
	}
}