package Input;

public class InputPackage {
	
	public move move;
	public rotation rotation;
	
	public InputPackage() {
		this.move = null;
		this.rotation = null;
	}
	
	public void clear() {
		this.move = null;
		this.rotation = null;
	}
	
	public move getMove() {
		return move;
	}
	public void setMove(move x) {
		this.move = x;
	}
	public rotation getRotation() {
		return rotation;
	}
	public void setRotation(rotation x) {
		this.rotation = x;
	}
	
}
