package Board.PieceMechanics;

public class Location {
	int x, y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(int transX, int transY) {
		this.x += transX;
		this.y += transY;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
