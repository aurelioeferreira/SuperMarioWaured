package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Inimigo extends InteractiveObject {

	static BufferedImage inimigo, inimigoFlip;
	public boolean morreu, viradoDireita, acelerando, subindo;
	public int velocidadeY = 60;
	public int amplitudeMovimento, distancia = 0;
	private int contMorte = 120;

	public Inimigo(int posX, int posY, int am) {
		super("Inimigo");
		loadImage();
		this.largura = inimigo.getWidth();
		this.altura = inimigo.getHeight();
		this.posXMundo = posX;
		this.posYMundo = posY;
		amplitudeMovimento = am;
	}

	@Override
	public void paint(Graphics g) {
		if (!viradoDireita)
			g.drawImage(inimigo, posXTela, posYTela, null);
		else
			g.drawImage(inimigoFlip, posXTela, posYTela, null);

	}

	@Override
	public void update() {
		

		if (morreu) {
			contMorte--;

			if (contMorte == 0) {
				InteractiveObject.interactiveObjects.remove(this);
				GameObject2D.gameObjects.remove(this);
			}
			posYMundo += 10;
		} else {
			// movimento X:
			if (distancia <= 0) {
				viradoDireita = true;
			}
			if (distancia >= amplitudeMovimento) {
				viradoDireita = false;
			}
			if (viradoDireita) {
				posXMundo++;
				distancia++;
			} else {
				posXMundo--;
				distancia--;
			}

			// movimento Y:
			if (velocidadeY == 0) {
				if (subindo) {
					subindo = false;
				} else {
					subindo = true;
				}
			}
			if (velocidadeY == 0) {
				acelerando = true;
			}
			if (velocidadeY == 60) {
				acelerando = false;
			}

			if (acelerando) {
				velocidadeY += 2;
			} else {
				velocidadeY -= 2;
			}

			if (subindo) {
				posYMundo -= velocidadeY / 10;
			} else {
				posYMundo += velocidadeY / 10;
			}
		}
		updateCam();
	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			inimigo = ImageIO.read(getClass().getResource("inimigo1.png"));
		} catch (IOException e) {

		}
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-inimigo.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		inimigoFlip = op.filter(inimigo, null);
	}

	public void morre() {
		morreu = true;

	}

}
