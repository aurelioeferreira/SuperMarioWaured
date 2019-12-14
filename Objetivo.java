package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Objetivo extends InteractiveObject {
	
	BufferedImage objetivo = null;

	public Objetivo() {
		super("Objetivo");
		loadImage();
		largura = objetivo.getWidth();
		altura = objetivo.getHeight();
		posXMundo = Chao.posXUltimo - 270;
		posYMundo = Chao.posYProximo - altura;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(objetivo, posXTela, posYTela, null);
		
	}

	@Override
	public void update() {
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
			objetivo = ImageIO.read(getClass().getResource("objetivo2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	// classe do objetivo onde o mario deve chegar
	// fica no final da fase

}
