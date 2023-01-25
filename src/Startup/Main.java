package Startup;


import Board.Board;
import Graphics.Frame;

public class Main {
	
	public static Board board;
	public static Frame frame;
	public static long MS_PER_UPDATE;
	public static long MS_PER_RENDER;
	public static boolean gamePaused = true;
	
	public static void main(String[] args) {
		frame = new Frame();
		board = new Board();
		MS_PER_UPDATE = 40;
		MS_PER_RENDER = 10;
		
		/*
			Game Loop
		*/
		Thread gameLoop = new Thread(){
			public void run() {
				while (true) {
					if (!gamePaused) {
						long current = System.currentTimeMillis();
							
						board.update();
						
						long sleepTime = current + MS_PER_UPDATE - System.currentTimeMillis();
						if (sleepTime > 0) {
							try {
								Thread.sleep(sleepTime);
							} catch (InterruptedException e) {}
						}
					}
					else {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {}
					}
				}
		    }
		};
		gameLoop.start();
		System.out.println("Game Loop Started");
		
		Thread renderLoop = new Thread(){
			public void run() {
				while (true)
				{
					long current = System.currentTimeMillis();
					
					frame.getCanvas().repaint();
					
					long sleepTime = current + MS_PER_RENDER - System.currentTimeMillis();
					if (sleepTime > 0) {
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {}
					}
				}
		    }
		};
		renderLoop.start();
		System.out.println("Render Loop Started");
	}

	
	
	public static boolean isGamePaused() {
		return gamePaused;
	}
	public static void setGamePaused(boolean gamePaused) {
		Main.gamePaused = gamePaused;
	}
	
}
