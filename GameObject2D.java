package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject2D {

	String nome;
	public boolean delete = false;
	public int posX, posY, largura, altura;

	public static List<GameObject2D> gameObjects = new ArrayList<GameObject2D>();

	public GameObject2D(String nome) {
		this.nome = nome;
		gameObjects.add(this);
	}

	public void delete() {
		this.delete = true;
	}

	public void adiciona() {
		this.delete = false;
	}

	public abstract void paint(Graphics g);

	public abstract void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion);

	public abstract void updateKeyEvent(KeyEvent keyevent);
}
