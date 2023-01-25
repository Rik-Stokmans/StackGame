package Board.PieceMechanics;

import java.util.ArrayList;

import Board.Board;
import Startup.Main;

public class Piece {
	
	Block mainBlock, secondaryBlock;

	public Piece(blockColor c1, blockColor c2) {
		mainBlock = new Block(c1, new Location(2,0));
		secondaryBlock = new Block(c2, new Location(3,0));
	}

	public void softDrop() {
		//checks if there is a block under the fallingPiece
		if (!mainBlock.hasBase() && !secondaryBlock.hasBase()) {
			mainBlock.getLocation().translate(0,1);
			secondaryBlock.getLocation().translate(0, 1);
		}
	}
	
	public void hardDrop() {
		//checks if there is a block under the fallingPiece
		while (!mainBlock.hasBase() && !secondaryBlock.hasBase()) {
			mainBlock.getLocation().translate(0,1);
			secondaryBlock.getLocation().translate(0, 1);
		}
		solidify();
	}

	public void moveLeft() {
		if (mainBlock.canTranslate(-1, 0) && secondaryBlock.canTranslate(-1, 0)) {
			mainBlock.translate(-1, 0);
			secondaryBlock.translate(-1, 0);
		}
	}
	
	public void moveRight() {
		if (mainBlock.canTranslate(+1, 0) && secondaryBlock.canTranslate(+1, 0)) {
			mainBlock.translate(+1, 0);
			secondaryBlock.translate(+1, 0);
		}
	}

	public void rotateClockwise() {
		//case 1
		if (mainBlock.getLocation().getX()+1 == secondaryBlock.getLocation().getX()) {
			if (secondaryBlock.canTranslate(-1, -1)) {
				secondaryBlock.translate(-1, -1);
			}
			else if (secondaryBlock.canTranslate(-1, 0) && mainBlock.canTranslate(0, +1)) {
				secondaryBlock.translate(-1, 0);
				mainBlock.translate(0, +1);
			}
		}
		//case 2
		else if (mainBlock.getLocation().getY()+1 == secondaryBlock.getLocation().getY()) {
			if (secondaryBlock.canTranslate(-1, +1)) {
				secondaryBlock.translate(-1, +1);
			}
			else if (secondaryBlock.canTranslate(0, +1) && mainBlock.canTranslate(+1, 0)) {
				secondaryBlock.translate(0, +1);
				mainBlock.translate(+1, 0);
			}
		}
		//case 3
		else if (mainBlock.getLocation().getX()-1 == secondaryBlock.getLocation().getX()) {
			if (secondaryBlock.canTranslate(+1, +1)) {
				secondaryBlock.translate(+1, +1);
			}
			else if (secondaryBlock.canTranslate(+1, 0) && mainBlock.canTranslate(0, -1)) {
				secondaryBlock.translate(+1, 0);
				mainBlock.translate(0, -1);
			}
		}
		//case 4
		else if (mainBlock.getLocation().getY()-1 == secondaryBlock.getLocation().getY()) {
			if (secondaryBlock.canTranslate(+1, -1)) {
				secondaryBlock.translate(+1, -1);
			}
			else if (secondaryBlock.canTranslate(0, -1) && mainBlock.canTranslate(-1, 0)) {
				secondaryBlock.translate(0, -1);
				mainBlock.translate(-1, 0);
			}
		}
	}
	
	public void rotateCounterClockwise() {
		//case 1
		if (mainBlock.getLocation().getX()+1 == secondaryBlock.getLocation().getX()) {
			if (secondaryBlock.canTranslate(-1, +1)) {
				secondaryBlock.translate(-1, +1);
			}
			else if (secondaryBlock.canTranslate(-1, 0) && mainBlock.canTranslate(0, -1)) {
				secondaryBlock.translate(-1, 0);
				mainBlock.translate(0, -1);
			}
		}
		//case 2
		else if (mainBlock.getLocation().getY()-1 == secondaryBlock.getLocation().getY()) {
			if (secondaryBlock.canTranslate(-1, -1)) {
				secondaryBlock.translate(-1, -1);
			}
			else if (secondaryBlock.canTranslate(0, -1) && mainBlock.canTranslate(+1, 0)) {
				secondaryBlock.translate(0, -1);
				mainBlock.translate(+1, 0);
			}
		}
		//case 3
		else if (mainBlock.getLocation().getX()-1 == secondaryBlock.getLocation().getX()) {
			if (secondaryBlock.canTranslate(+1, -1)) {
				secondaryBlock.translate(+1, -1);
			}
			else if (secondaryBlock.canTranslate(+1, 0) && mainBlock.canTranslate(0, +1)) {
				secondaryBlock.translate(+1, 0);
				mainBlock.translate(0, +1);
			}
		}
		//case 4
		else if (mainBlock.getLocation().getY()+1 == secondaryBlock.getLocation().getY()) {
			if (secondaryBlock.canTranslate(+1, +1)) {
				secondaryBlock.translate(+1, +1);
			}
			else if (secondaryBlock.canTranslate(0, +1) && mainBlock.canTranslate(-1, 0)) {
				secondaryBlock.translate(0, +1);
				mainBlock.translate(-1, 0);
			}
		}
		
		
	}
	
	public void rotateFlip() {
		Block flipBlock = mainBlock;
		mainBlock = secondaryBlock;
		secondaryBlock = flipBlock;
	}
	
	public void solidify() {
		mainBlock.solidify();
		secondaryBlock.solidify();
		Main.board.removeFallingPiece();
		
		char[][] preUpdate = new char[6][8];
		int cycle = 0;
		int points = 0;
		int totalBlocksCleared = 0;
		
		
		while (Main.board.isDifferent(preUpdate)) {
			for (int i = 7; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					preUpdate[j][i] = Main.board.getBoard()[j][i];
				}
			}
			ArrayList<Location> longLinks = Main.board.getLongLinks(Main.board.getBoard());
			
			if (longLinks.size() > 0) {
				int cleared = longLinks.size();
				Main.board.removeLongLinks(longLinks);
				//calculate points
				if (cleared >= 4 && cleared < 8) {
					points += 100;
					points += cycle * 50;
					if (cleared - 4 > 0) {
						points += (cleared - 4) * 50;
					}
				} else {
					points += 500;
					points += cycle * 100;
					if (cleared - 8 > 0) {
						points += (cleared - 8) * 100;
					}
				}
				totalBlocksCleared += cleared;
				cycle++;
			}
			Main.board.enforceGravity();
			try {Thread.sleep(200);} catch (InterruptedException e) {}
		}
		//if ()
		if (totalBlocksCleared >= 25) {
			points += 15000;
		}
		else if (totalBlocksCleared >= 18) {
			points += 7500;
		}
		else if (totalBlocksCleared >= 12) {
			points += 2500;
		}
		if (Main.board.isClear()) {
			points += 3000;
		}
		
		System.out.println(points);
		int totalPoints = Main.board.getPoints() + points;
		Main.board.setPoints(totalPoints);
		
		Main.board.newFallingPiece();
	}
	
	public Block getMainBlock() {
		return mainBlock;
	}
	public Block getSecondaryBlock() {
		return secondaryBlock;
	}
}
