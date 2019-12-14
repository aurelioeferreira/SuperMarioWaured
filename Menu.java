package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Menu extends GameObject2D {

	public boolean jogar, sair, tutorial;
	private int px, py;
	public static BufferedImage botao, botao2;

	public Menu() {
		super("Menu");
		loadImage();
		px = GameEngine.getFrameWidth() / 2 - botao.getWidth() / 2;
		py = GameEngine.getFrameHeight() / 2 - botao.getHeight() / 2 - 70;
	}

	private void loadImage() {
		try {
			botao = ImageIO
					.read(getClass().getResource("botaomenu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			botao2 = ImageIO
					.read(getClass().getResource("botaomenu2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		g.setFont(new Font("Tahoma", 12, 28));
		g.setColor(Color.YELLOW);

		if (!jogar) {
			g.drawImage(botao, px, py, null);
		} else {
			g.drawImage(botao2, px, py, null);
		}
		g.drawString("Jogar", px + 45, py + 26);

		if (!tutorial) {
			g.drawImage(botao, px, py + 39, null);
		} else {
			g.drawImage(botao2, px, py + 39, null);
		}
		g.drawString("Tutorial", px + 35, py + 65);
		
		if (!sair) {
			g.drawImage(botao, px, py + 78, null);
		} else {
			g.drawImage(botao2, px, py + 78, null);
		}
		g.drawString("Sair", px + 54, py + 70 + botao.getHeight());

	}

	public void updateKeyEvent(KeyEvent keyevent) {

	}

	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {
		if ((motion.getX() >= px && motion.getX() <= px
				+ botao.getWidth())
				&& (motion.getY() >= py && motion.getY() <= py
						+ botao.getHeight())) {
			jogar = true;
			if (mouseevent.getID() == MouseEvent.MOUSE_PRESSED && !SpriteAure2.showTutorial) {
				SpriteAure2.estado = SpriteAure2.ejogar;
			}

		} else {
			jogar = false;
		}

		if ((motion.getX() >= px && motion.getX() <= px
				+ botao.getWidth())
				&& (motion.getY() >= py + 78 && motion.getY() <= py
						+ botao.getHeight() + 78)) {
			if (mouseevent.getID() == MouseEvent.MOUSE_PRESSED && !SpriteAure2.showTutorial) {
				System.exit(0);
			}

			sair = true;
		} else {
			sair = false;
		}
		
		if ((motion.getX() >= px && motion.getX() <= px
				+ botao.getWidth())
				&& (motion.getY() >= py + 39 && motion.getY() <= py
						+ botao.getHeight() + 39)) {
			if (mouseevent.getID() == MouseEvent.MOUSE_PRESSED) {
				SpriteAure2.showTutorial = true;
			}

			tutorial = true;
		} else {
			tutorial = false;
		}
	}

}
