package Graphics;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JComponent;

import Startup.Main;

@SuppressWarnings("serial")
public class GraphicsCanvas extends JComponent {
	
	public GraphicsCanvas() {
		
	}
	
	protected void paintComponent(Graphics g) {
		//gets the size of the window
		Dimension s = Main.frame.getFrame().getContentPane().getSize();
		double width = s.getWidth();
		double height = s.getHeight();
		
		//Converts Standard Graphics "g" to Graphics2D "g2d"
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform reset = g2d.getTransform();
		//AntiAliasing (Makes The Graphics Smoother)
		RenderingHints rh = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		
		//renders the background
		g2d.setColor(new Color(80, 60, 82));
		Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
		g2d.fill(background);

		//variables for the board paint method
		int boardWidth = Main.board.getBoard().length;
		int boardHeight = Main.board.getBoard()[boardWidth-1].length;
		double boardPixelHeight;
		double boardPixelWidth;
		double contentMargin = 0.01;
		double widthWithoutMargin = width - (height * contentMargin * 3);
		double heightWithoutMargin = height - (height * contentMargin * 2);
		
		//variables for the scoreboard paint method
		double scoreboardPixelHeight;
		double scoreboardPixelWidth;
		double scoreboardOffsetHeight;
		double scoreboardOffsetWidth;
		
		
		//case 1 (widescreen)
		if (widthWithoutMargin/heightWithoutMargin >= 1.25) {
			//board
			boardPixelHeight = height - (height * contentMargin * 2);
			boardPixelWidth = (boardPixelHeight / 8) * 6;
			//scoreboard
			scoreboardPixelHeight = boardPixelHeight / 8;
			scoreboardPixelWidth = boardPixelHeight / 2;
			scoreboardOffsetWidth = boardPixelWidth + (height * contentMargin * 2);
			scoreboardOffsetHeight = contentMargin * height;
		}
		
		//case 2 (square)
		else if (widthWithoutMargin/heightWithoutMargin >= 0.875) {
			//board
			boardPixelHeight = height - (height * contentMargin * 2);
			boardPixelWidth = (boardPixelHeight / 8) * 6;
			//scoreboard
			scoreboardPixelHeight = boardPixelHeight;
			scoreboardPixelWidth = boardPixelHeight / 8;
			scoreboardOffsetWidth = boardPixelWidth + (height * contentMargin * 2);
			scoreboardOffsetHeight = contentMargin * height;
		}
		
		//case 3 (phone)
		else {
			widthWithoutMargin = width - (height * contentMargin * 2);
			heightWithoutMargin = height - (height * contentMargin * 3);
			if (widthWithoutMargin/heightWithoutMargin < 0.6667) {
				//width
				//board
				boardPixelWidth = width - (2 * contentMargin * height);
				boardPixelHeight = (boardPixelWidth / 6) * 8;
			} else {
				//height
				//board
				boardPixelHeight = (heightWithoutMargin / 9) * 8;
				boardPixelWidth = (boardPixelHeight / 8) * 6;
			}
			//scoreboard
			scoreboardPixelHeight = boardPixelHeight / 8;
			scoreboardPixelWidth = (boardPixelHeight / 8) * 6;
			scoreboardOffsetWidth = contentMargin * height;
			scoreboardOffsetHeight = boardPixelHeight + (height * contentMargin * 2);
			
		}
		BoardPainter.PaintBoard(height*contentMargin, height*contentMargin, boardPixelWidth, boardPixelHeight, g2d);
		
		ScoreboardPainter.PaintScoreboard(scoreboardOffsetWidth, scoreboardOffsetHeight, scoreboardPixelWidth, scoreboardPixelHeight, g2d);
		
		g2d.setTransform(reset);
		
	}
}
