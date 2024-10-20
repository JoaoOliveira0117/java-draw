package components;

import javax.swing.JFrame;

public class Frame {
	static JFrame frame;
	
	public static JFrame getFrame() {
		if (frame == null) {
			frame = new JFrame();
		}
		
		return frame;
	}
}
