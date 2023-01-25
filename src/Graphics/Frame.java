package Graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Input.InputHandler;

public class Frame {
	JFrame frame;
	InputHandler inputHandler;
	
	private GraphicsCanvas canvas = new GraphicsCanvas();
	
	public int width, height;

	public Frame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) screenSize.getWidth();
		int h = (int) screenSize.getHeight();
		
		
		inputHandler = new InputHandler();
		frame = new JFrame();
		frame.setSize(w,h);
		frame.add(canvas);
		frame.setMinimumSize(new Dimension(500,300));
		frame.setTitle("Stack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(inputHandler);
		frame.setVisible(true);
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}
	public JFrame getFrame() {
		return frame;
	}
	public GraphicsCanvas getCanvas() {
		return canvas;
	}
	
	
}
