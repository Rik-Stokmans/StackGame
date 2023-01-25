package Board;

import java.util.ArrayList;

import Board.PieceMechanics.Bag;
import Board.PieceMechanics.Block;
import Board.PieceMechanics.Location;
import Board.PieceMechanics.Piece;
import Input.*;
import Sound.SoundPlayer;
import Startup.Main;

public class Board {
	
	int updateCount = 0;
	
	int points = 0;
	
	int highScore = 0;
	ArrayList<Integer> scores = new ArrayList<>();
	
	Bag bag;
	
	char[][] board;
	int w, h;
	Piece fallingPiece;
	Piece nextPiece;
	
	public Board() {
		w = 6; 
		h = 8;
		board = new char[w][h];
		
		init();
	}
	
	public void init() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				board[i][j] = 'e';
			}
		}
		bag = new Bag();
		newFallingPiece();
	}
	
	public void update() {
		if (fallingPiece != null) {
			updateCount++;
			
			double waitTime = (points * -0.0015 + 1000);
			
			if (updateCount * Main.MS_PER_UPDATE > waitTime) {
				if (fallingPiece.getMainBlock().hasBase()) {
					fallingPiece.solidify();
				}
				else if (fallingPiece.getSecondaryBlock().hasBase()) {
					fallingPiece.solidify();
				}
				else {
					fallingPiece.softDrop();
				}
				updateCount = 0;
				
			}
			
			InputPackage inputs = Main.frame.getInputHandler().getInputPackage();
			move move = inputs.getMove();
			rotation rotation = inputs.getRotation();
			inputs.clear();
			
			if (move == Input.move.SOFTDROP) {
				fallingPiece.softDrop();
				updateCount = 0;
			}
			if (move == Input.move.HARDDROP) {
				fallingPiece.hardDrop();
				updateCount = 0;
			}
			if (move == Input.move.LEFT) {
				fallingPiece.moveLeft();
			}
			if (move == Input.move.RIGHT) {
				fallingPiece.moveRight();
			}
			if (rotation == Input.rotation.CLOCKWISE) {
				fallingPiece.rotateClockwise();
				if (updateCount > 0) updateCount--;
			}
			if (rotation == Input.rotation.COUNTERCLOCKWISE) {
				fallingPiece.rotateCounterClockwise();
				if (updateCount > 0) updateCount--;
			}
			if (rotation == Input.rotation.FLIP) {
				fallingPiece.rotateFlip();
				if (updateCount > 0) updateCount--;
			}
		}
	}
	
	public ArrayList<Location> getRegionLocations(char[][] board, int x, int y, char color) {
		if (x < 0 || y < 0 || x >= board.length || y >= board[x].length) {
			return new ArrayList<>();
		}
		if (board[x][y] != color) {
			return new ArrayList<>();
		}
		board[x][y] = 'e';
		
		ArrayList<Location> region = new ArrayList<>();
		region.add(new Location(x, y));
		
		ArrayList<Location> toBeAdded = new ArrayList<>();
		toBeAdded = getRegionLocations(board, x+1, y, color);
		if (toBeAdded.size() > 0) {
			for (Location loc : toBeAdded) {
				region.add(loc);
			}
		}
		toBeAdded.clear();
		toBeAdded = getRegionLocations(board, x-1, y, color);
		if (toBeAdded.size() > 0) {
			for (Location loc : toBeAdded) {
				region.add(loc);
			}
		}
		toBeAdded.clear();
		toBeAdded = getRegionLocations(board, x, y+1, color);
		if (toBeAdded.size() > 0) {
			for (Location loc : toBeAdded) {
				region.add(loc);
			}
		}
		toBeAdded.clear();
		toBeAdded = getRegionLocations(board, x, y-1, color);
		if (toBeAdded.size() > 0) {
			for (Location loc : toBeAdded) {
				region.add(loc);
			}
		}
		return region;
	}
	
	public ArrayList<Location> getLongLinks(char[][] board) {
		//need to get a list of all locations that are associated with a chain of 4 or more
		char[][] boardCopy = new char[6][8];
		
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				boardCopy[j][i] = board[j][i];
			}
		}
	
		ArrayList<Location> LongLinks = new ArrayList<>();
		
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (boardCopy[j][i] != 'e') {
					char currentColor = boardCopy[j][i];
				
					ArrayList<Location> region = getRegionLocations(boardCopy, j, i, currentColor);
				
					if (region.size() >= 5) {
						for (Location loc : region) {
							LongLinks.add(loc);
						}
					}
				}
				
			}
		}
		return LongLinks;
	}
	
	public void removeLongLinks(ArrayList<Location> longLinks) {
		for (Location loc : longLinks) {
			board[loc.getX()][loc.getY()] = 'e';
		}
	}
	
	public void enforceGravity() {
		char[][] boardCopy = new char[6][8];
		
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				boardCopy[j][i] = board[j][i];
			}
		}
		
		
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (boardCopy[j][i] != 'e') {
					char currentColor = boardCopy[j][i];
				
					ArrayList<Location> region = getRegionLocations(boardCopy, j, i, currentColor);
				
					boolean canMoveDown = true;
					
					for (Location loc : region) {
						if (loc.getX() < 0 || loc.getY()+1 < 0 || loc.getX() >= w || loc.getY()+1 >= h) {
							canMoveDown = false;
							break;
						}
						
						boolean contains = false;
						for (Location loc2 : region) {
							if (loc2.getX() == loc.getX() && loc2.getY() == loc.getY()+1) {
								contains = true;
								break;
							}
						}
						if (!contains) {
							if (board[loc.getX()][loc.getY()+1] != 'e') canMoveDown = false;
						}
						
						
						
					}
					if (canMoveDown) {
						char[][] boardCopy2 = new char[6][8];
						
						for (int i2 = 7; i2 >= 0; i2--) {
							for (int j2 = 0; j2 < 6; j2++) {
								boardCopy2[j][i] = board[j][i];
							}
						}
						for (Location loc : region) {
							board[loc.getX()][loc.getY()] = 'e';
						}
						for (Location loc : region) {
							board[loc.getX()][loc.getY()+1] = currentColor;
						}
						
					}
				}
			}
		}
	}
	
	public void newFallingPiece() {
		//SoundPlayer.playSound("song.wav");
		if (board[2][0] != 'e' || board[3][0] != 'e') {
			init();//resets the board because a new piece could not spawn
			scores.add(points);
			if (points > highScore) {
				highScore = points;
				System.out.println("New High Score!");
			}
			points = 0;
		}
		if (nextPiece != null) {
			fallingPiece = nextPiece;
			nextPiece = bag.getNextPiece();
		}
		else {
			fallingPiece = bag.getNextPiece();
			nextPiece = bag.getNextPiece();
		}
	}

	public void print() {
		System.out.println("\n\n\n\n\n");
		for (int i = 0; i < h; i++) {
			System.out.print("|");
			for (int j = 0; j < w; j++) {
				if (fallingPiece != null) {
					
					Block mainBlock = fallingPiece.getMainBlock();
					Block secBlock = fallingPiece.getSecondaryBlock();
					Location blockLocMain = mainBlock.getLocation();
					Location blockLocSec = secBlock.getLocation();
				
					if (blockLocMain.getX() == j && blockLocMain.getY() == i) {
						System.out.print(mainBlock.getBlockName());
					}
					else if (blockLocSec.getX() == j && blockLocSec.getY() == i) {
						System.out.print(secBlock.getBlockName());
					}
					else if (board[j][i] == 'e') System.out.print(" ");
					else System.out.print(board[j][i]);
				}
				
				else if (board[j][i] == 'e') System.out.print(" ");
				else System.out.print(board[j][i]);
			}
			System.out.println("|");
		}
	}
	
	public boolean isDifferent(char[][] comparison) {
		boolean isDifferent = false;
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (board[j][i] != comparison[j][i]) {
					isDifferent = true;
				}
			}
		}
		return isDifferent;
	}
	
	public boolean isClear() {
		boolean isClear = true;
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (board[j][i] != 'e') isClear = false;
			}
		}
		return isClear;
	}

	public char[][] getBoard() {
		return board;
	}
	public int getHeight() {
		return h;
	}
	public void removeFallingPiece() {
		fallingPiece = null;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Piece getFallingPiece() {
		return fallingPiece;
	}
	public Piece getNextPiece() {
		return nextPiece;
	}
	
}


