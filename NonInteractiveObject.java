package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;


public abstract class NonInteractiveObject extends GameObject2D {

	public int posXMundo, posYMundo, posXTela, posYTela;
	public int distancia = 1;
	public static Vector<NonInteractiveObject> nonInteractiveObjects;

	public NonInteractiveObject(String nome) {
		super(nome);
		if (nonInteractiveObjects == null) {
			nonInteractiveObjects = new Vector<NonInteractiveObject>();
		}
		nonInteractiveObjects.add(this);
	}

	@Override
	public abstract void paint(Graphics g);

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {
	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {
	}

	public abstract void update();

	public void updateCam() {
		posXTela = posXMundo - (int) (Camera.posXFundo / distancia);
		posYTela = posYMundo - Camera.posYFundo;
	}

}
