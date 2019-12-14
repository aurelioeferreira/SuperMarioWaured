package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AberturaFase extends NonInteractiveObject {

	static BufferedImage abertura;

	public AberturaFase() {
		super("Abertura");
		loadImage();
		altura = 600;
	}

	public void paint(Graphics g) {
		g.drawImage(abertura, 0, 0, 900, altura, null);

	}

	public void update() {
		if (altura > 0) {
			altura -= 7;
		}
	}

	public void loadImage() {
		try {
			abertura = ImageIO.read(getClass().getResource("abertura.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
