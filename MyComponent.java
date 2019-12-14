package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class MyComponent extends JComponent {

	
	private static final long serialVersionUID = 1L;

	public MyComponent() {
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setVisible(true);

		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				GameEngine.mouseevent = e;
			}

			public void mouseReleased(MouseEvent e) {
				GameEngine.mouseevent = e;
			}

			public void mouseEntered(MouseEvent e) {
				GameEngine.mouseevent = e;
			}

			public void mouseExited(MouseEvent e) {
				GameEngine.mouseevent = e;
			}

			public void mouseClicked(MouseEvent e) {
				GameEngine.mouseevent = e;
			}
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				GameEngine.mouseMotionEvent = e;
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				GameEngine.mouseMotionEvent = e;
			}
		});

	}

	public void paint(Graphics g) {
		if (GameObject2D.gameObjects != null) {
			for (int cont = 0; cont < GameObject2D.gameObjects.size(); cont++) {
				GameObject2D.gameObjects.get(cont).paint(g);
			}
		}
	}

}
