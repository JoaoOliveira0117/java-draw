package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class CanvasWrapper extends JScrollPane {
	private static CanvasWrapper instance;

	private DrawableCanvas drawableCanvas;
	private JFrame frame;
	private JPanel panel;
	
    private Point dragStartPoint;
    private JScrollBar horizontalScrollBar;
    private JScrollBar verticalScrollBar;
	
	
	private CanvasWrapper() {
		frame = Frame.getFrame();
		panel = getWrapperPanel(frame.getSize());
        
		horizontalScrollBar = getHorizontalScrollBar();
		verticalScrollBar = getVerticalScrollBar();
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		setViewportView(panel);        

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStartPoint = e.getPoint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                dragStartPoint = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStartPoint != null) {
                    int dx = dragStartPoint.x - e.getPoint().x;
                    int dy = dragStartPoint.y - e.getPoint().y;
                    horizontalScrollBar.setValue(horizontalScrollBar.getValue() + dx);
                    verticalScrollBar.setValue(verticalScrollBar.getValue() + dy);
                    dragStartPoint = e.getPoint();
                }
            }
        });
	}
	
	public static CanvasWrapper getInstance() {
		if (instance == null) {
			instance = new CanvasWrapper();
		}
		
		return instance;
	}
	
	private JPanel getWrapperPanel(Dimension dimension) {
		JPanel panel = new JPanel(new GridBagLayout());
		
		panel.setBackground(Color.GRAY);
		panel.setMinimumSize(dimension);
		panel.setPreferredSize(dimension);
		
		return panel;
	}
	
	public void createDrawableCanvas(int width, int height) {
		if (drawableCanvas == null) {
			drawableCanvas = new DrawableCanvas(width, height);
			
			double borderWeight = 1.25;
			Dimension outerCanvasDimension = new Dimension((int) Math.round(width * borderWeight), (int) Math.round(height * borderWeight));
			panel.setPreferredSize(outerCanvasDimension);
			panel.add(drawableCanvas);
		}
		
		revalidate();
		repaint();
	}
	
	public void removeDrawableCanvas() {
		if (drawableCanvas != null) {
			panel.remove(drawableCanvas);
			drawableCanvas = null;
		}
		revalidate();
		repaint();
	}
	
	public DrawableCanvas getDrawableCanvas() {
		return drawableCanvas;
	}
}
