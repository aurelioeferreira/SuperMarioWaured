package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Titulo extends GameObject2D {

	BufferedImage titulo = null;

	public Titulo(int posX) {
		super("Titulo");
		super.posX = posX;
		loadImage();
		posY = -50;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(titulo, posX, posY, null);

	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			titulo = ImageIO
					.read(getClass().getResource("Super_Mario_Waured0000.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

	}

}
