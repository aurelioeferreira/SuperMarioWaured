package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Nuvem extends NonInteractiveObject {

	static BufferedImage nuvem;
	private int velocidade = 0;

	public Nuvem(int posX, int posY) {
		super("Nuvem");
		loadImage();
		distancia = 4;
		largura = nuvem.getWidth();
		altura = nuvem.getHeight();
		this.posYMundo = posY;
		this.posXMundo = posX;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(nuvem, posXTela, posYTela, null);

	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			nuvem = ImageIO
					.read(getClass().getResource("nuvem.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		
		if (!GameEngine.pause) {
			velocidade++;
			if (velocidade == 4) {
				posXMundo--;
				velocidade = 0;
			}
		}
		updateCam();
	}

}
