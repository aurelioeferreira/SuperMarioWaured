package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Montanha extends NonInteractiveObject {

	static BufferedImage montanha;

	public Montanha(int posX, int posY) {
		super("Montanha");
		loadImage();
		distancia = 4;
		largura = montanha.getWidth();
		altura = montanha.getHeight();
		this.posYMundo = posY - altura * 2;
		this.posXMundo = posX;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(montanha, posXTela, posYTela, (int) (largura * 2),
				(int) (altura * 2), null);

	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			montanha = ImageIO.read(getClass().getResource("Montanha.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		updateCam();
	}

}