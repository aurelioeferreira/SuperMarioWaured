package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MarioImg extends GameObject2D {

	BufferedImage marioImg = null;

	public MarioImg(int posX) {
		super("MarioImg");
		super.posX = posX;
		loadImage();
		posY = 0;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(marioImg, posX, posY, 900, 600, null);

	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			marioImg = ImageIO
					.read(getClass().getResource("mario_pintura.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {

	}

}
