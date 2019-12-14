package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;

public abstract class GameEngine extends JFrame {

	
	private static final long serialVersionUID = 1L;
	protected static MouseEvent mouseevent, mouseMotionEvent;
	private static int frameWidth;
	private static int frameHeight;
	public static boolean rightKeyPressed, leftKeyPressed, upKeyPressed,
			downKeyPressed, pPressed, enterPressed, escPressed = false;
	public static double fps;
	public static boolean update, pause = false, paint = true;

	public abstract void init();

	public abstract void update();

	public abstract void draw(Graphics g);

	public GameEngine(String nome, int frameWidth, int frameHeight) {
		super(nome);
		GameEngine.frameHeight = frameHeight;
		GameEngine.frameWidth = frameWidth;
		this.initWindow();
		long getTime = 0;
		long tempoSegundo = 0;
		long iteracoes = 0;
		setLocation(80, 100);

		while (true) {
			// System.out.printf("\nfps = %.2f\n", GameEngine.fps);
			getTime = System.currentTimeMillis();
			if (!GameEngine.pause) {
				this.update();
			}

			this.repaint(0, 0, this.getWidth(), this.getHeight());

			if (GameObject2D.gameObjects != null) {
				for (int cont = 0; cont < GameObject2D.gameObjects.size(); cont++) {

					if (mouseevent != null)
						GameObject2D.gameObjects.get(cont).updateMouseEvent(
								mouseevent, mouseMotionEvent);
				}
			}

			this.pause(16);

			GameEngine.fps = (1000D / ((double) System.currentTimeMillis() - (double) getTime));
			tempoSegundo += (System.currentTimeMillis() - getTime);
			iteracoes++;
			if (tempoSegundo >= 1000) {
				System.out.println("fps medio = " + iteracoes);
				tempoSegundo = 0;
				iteracoes = 0;
			}
		}
	}

	public static int getFrameWidth() {
		return frameWidth;
	}

	public static int getFrameHeight() {
		return frameHeight;
	}

	private void pause(long TempoMiliSeconds) {
		try {
			Thread.sleep(TempoMiliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void initWindow() {
		MyComponent comp = new MyComponent();
		this.getContentPane().add(comp);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GameEngine.frameWidth, GameEngine.frameHeight);
		this.setVisible(true);

		addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						upKeyPressed = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						downKeyPressed = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						leftKeyPressed = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						rightKeyPressed = false;
					}
					if (e.getKeyChar() == 'p') {
						pPressed = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						enterPressed = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						escPressed = false;
					}
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						upKeyPressed = true;
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						downKeyPressed = true;
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						leftKeyPressed = true;
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						rightKeyPressed = true;
					}
					if (e.getKeyChar() == 'p') {
						pPressed = true;
					}
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						enterPressed = true;
					}
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						escPressed = true;
					}
				}
			}
		});
	}

}
