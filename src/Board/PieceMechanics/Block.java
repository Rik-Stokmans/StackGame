package Board.PieceMechanics;

import Startup.Main;

public class Block {
	
	blockColor color;
	char blockName;
	Location location;
	
	public Block(blockColor color, Location location) {
		this.color = color;
		if (color == blockColor.RED) blockName = 'r';
		else if (color == blockColor.BLUE) blockName = 'b';
		else if (color == blockColor.GREEN) blockName = 'g';
		else if (color == blockColor.YELLOW) blockName = 'y';
		
		this.location = location;
	}
	
	public boolean hasBase() {
		char[][] board = Main.board.getBoard();
		//checks if there is a block under the block
		if (location.y+1 == Main.board.getHeight()) {
			return true;
		}
		else if (board[location.x][location.y+1] != 'e') {
			return true;
		}
		return false;
	}

	public boolean canTranslate(int tX, int tY) {
		char[][] board = Main.board.getBoard();
		int x = location.getX();
		int y = location.getY();
		if (x+tX>=0 && x+tX<6 && y-tY>=0 && y-tY<8) {
			if (board[x+tX][y-tY] == 'e') {
				return true;
			}
		}
		
		return false;
	}
	
	public void translate(int tX, int tY) {
		location.x += tX;
		location.y -= tY;
	}

	public void solidify() {
		char[][] board = Main.board.getBoard();
		board[location.x][location.y] = blockName;
	}
	
	public blockColor getColor() {
		return color;
	}
	public void setColor(blockColor color) {
		this.color = color;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public char getBlockName() {
		return blockName;
	}
	
	
}
