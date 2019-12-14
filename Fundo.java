package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Fundo extends GameObject2D {
	
	BufferedImage fundo = new BufferedImage(900, 600, BufferedImage.TYPE_INT_RGB);

	public Fundo() {
		super("Fundo");		
		loadImage();
	}
	
	public void update() {
	}

	public void paint(Graphics g) {
		g.drawImage(fundo, 0, 0, 900, 600,  null);
		
	}
	
	public void loadImage() {
		try {
			fundo = ImageIO.read(getClass().getResource("9694azul.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateKeyEvent(KeyEvent keyevent) {
		
	}

	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {
		
	}	

}
