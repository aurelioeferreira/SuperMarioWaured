package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Careta extends InteractiveObject {

	static BufferedImage careta = null;
	static boolean carregouImagens = false;
	public static int ALTURA = 44;
	public static int LARGURA = 44;

	public Careta(int posX, int posY) {
		super("Careta");
		if (!Careta.carregouImagens) {
			loadImage();
			carregouImagens = true;
		}
		largura = careta.getWidth();
		altura = careta.getHeight();
		this.posYMundo = posY;
		this.posXMundo = posX;
	}

	public void update() {
		updateCam();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(careta, posXTela, posYTela, null);
	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			careta = ImageIO.read(getClass().getResource("box.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
