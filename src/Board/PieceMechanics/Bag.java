package Board.PieceMechanics;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {
	
	public ArrayList<Piece> bag = new ArrayList<>();
	
	public Piece[] allPieces = new Piece[10];
	
	public Bag() {
		initAllPieces();
		
		fill();
		shuffle();
	}
	
	public void fill() {
		clear();
		initAllPieces();
		for (int i = 0; i < allPieces.length; i++) {
			bag.add(allPieces[i]);
		}
	}
	
	public Piece getNextPiece() {
		if (bag.size() > 0) {
			Piece returnPiece = bag.get(0);
			bag.remove(0);
			return returnPiece;
		} 
		else {
			fill();
			shuffle();
			return getNextPiece();
		}
		
	}
	
	public void clear() {
		bag.clear();
	}
	
	public void shuffle() {
		Collections.shuffle(bag);
	}
	
	public void initAllPieces() {
		blockColor r = blockColor.RED;
		blockColor b = blockColor.BLUE;
		blockColor g = blockColor.GREEN;
		blockColor y = blockColor.YELLOW;
		allPieces[0] = new Piece(r,r);
		allPieces[1] = new Piece(r,b);
		allPieces[2] = new Piece(r,g);
		allPieces[3] = new Piece(r,y);
		allPieces[4] = new Piece(b,b);
		allPieces[5] = new Piece(b,g);
		allPieces[6] = new Piece(b,y);
		allPieces[7] = new Piece(g,g);
		allPieces[8] = new Piece(g,y);
		allPieces[9] = new Piece(y,y);
	}
}

