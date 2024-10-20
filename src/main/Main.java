package main;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import components.CanvasWrapper;
import components.Frame;
import components.MenuBar;

public class Main {

	public static void main(String[] args) {
		JFrame frame = Frame.getFrame();
		frame.setTitle("JavaDraw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		
		CanvasWrapper canvas = CanvasWrapper.getInstance();
		JMenuBar menuBar = (JMenuBar) new MenuBar();
		frame.setJMenuBar(menuBar);
		frame.add(canvas);
		
		frame.setVisible(true);
	}
}
