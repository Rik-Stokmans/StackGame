package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Startup.Main;

public class InputHandler implements KeyListener {
	
	public final int moveLeftKey = 65;
	public final int moveRightKey = 68;
	public final int moveSoftDropKey = 83;
	public final int moveHardDropKey = 32;
	public final int rotateLeftKey = 81;
	public final int rotateRightKey = 69;
	public final int rotateFlipKey = 87;
	
	public final int pauseKey = 80;
	public final int startKey = 10;
	
	
	public InputPackage inputs = new InputPackage();

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (!Main.isGamePaused()) {
			if (keyCode == moveLeftKey)
				inputs.setMove(move.LEFT);
			else if (keyCode == moveRightKey)
				inputs.setMove(move.RIGHT);
			else if (keyCode == moveSoftDropKey)
				inputs.setMove(move.SOFTDROP);
			else if (keyCode == moveHardDropKey)
				inputs.setMove(move.HARDDROP);
			else if (keyCode == rotateLeftKey)
				inputs.setRotation(rotation.COUNTERCLOCKWISE);
			else if (keyCode == rotateRightKey)
				inputs.setRotation(rotation.CLOCKWISE);
			else if (keyCode == rotateFlipKey)
				inputs.setRotation(rotation.FLIP);
		}
		
		//pause button
		if (keyCode == pauseKey)
			Main.setGamePaused(!Main.isGamePaused());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public InputPackage getInputPackage() {
		return inputs;
	}

}
