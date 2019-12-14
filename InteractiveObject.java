package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;


public abstract class InteractiveObject extends GameObject2D {

	public int posXMundo, posYMundo, posXTela, posYTela;

	public static Vector<InteractiveObject> interactiveObjects;
	public boolean testaCima, colideCima, testaBaixo, colideBaixo,
			testaDireita, colideDireita = false;
	public boolean testaEsquerda, colideEsquerda, caindo, subindo = false;
	public int tecla = 0;
	public boolean jaColidiuDireita, jaColidiuEsquerda, jaColidiuCima,
			jaColidiuBaixo = false;

	public InteractiveObject(String nome) {
		super(nome);
		if (interactiveObjects == null) {
			interactiveObjects = new Vector<InteractiveObject>();
		}
		interactiveObjects.add(this);
	}

	public abstract void update();

	public void updateCam() {
		posXTela = posXMundo - Camera.posX;
		posYTela = posYMundo - Camera.posY;
	}

	@Override
	public abstract void paint(Graphics g);

	@Override
	public abstract void updateKeyEvent(KeyEvent keyevent);

	@Override
	public abstract void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion);

	public boolean colisao(InteractiveObject go) {
		/*
		 * if ((((this.posXMundo + largura) >= go.posXMundo && (this.posXMundo +
		 * largura) <= (go.posXMundo + go.largura)) || ((this.posXMundo <=
		 * go.posXMundo + go.largura) && (this.posXMundo >= go.posXMundo))) &&
		 * (((this.posYMundo + altura) >= go.posYMundo && (this.posYMundo +
		 * altura) <= (go.posYMundo + go.altura)) || ((this.posYMundo <=
		 * go.posYMundo + go.altura) && (this.posYMundo >= go.posYMundo)))) {
		 * return true; } else { return false; }
		 */

		// as pontas deste objeto:
		int xDeste = this.posXMundo;
		int xLarguraDeste = this.posXMundo + this.largura;
		int yDeste = this.posYMundo;
		int yAlturaDeste = this.posYMundo + this.altura;
		// as pontas do outro objeto:
		int xDoOutro = go.posXMundo;
		int xLarguraDoOutro = go.posXMundo + go.largura;
		int yDoOutro = go.posYMundo;
		int yAlturaDoOutro = go.posYMundo + go.altura;

		// if abaixo testa se ponta1 deste(esquerda superior) colidiu
		if ((xDeste >= xDoOutro && xDeste <= xLarguraDoOutro)
				&& (yDeste >= yDoOutro && yDeste <= yAlturaDoOutro)) {
			return true;
		}
		// if abaixo testa se ponta2 deste(direita superior) colidiu
		if ((xLarguraDeste >= xDoOutro && xLarguraDeste <= xLarguraDoOutro)
				&& (yDeste >= yDoOutro && yDeste <= yAlturaDoOutro)) {
			return true;
		}
		// if abaixo testa se ponta3 deste(esquerda inferior) colidiu
		if ((xDeste >= xDoOutro && xDeste <= xLarguraDoOutro)
				&& (yAlturaDeste >= yDoOutro && yAlturaDeste <= yAlturaDoOutro)) {
			return true;
		}
		// if abaixo testa se ponta4 deste(direita inferior) colidiu
		if ((xLarguraDeste >= xDoOutro && xLarguraDeste <= xLarguraDoOutro)
				&& (yAlturaDeste >= yDoOutro && yAlturaDeste <= yAlturaDoOutro)) {
			return true;
		}
		// if abaixo testa se ponta1 do outro(esquerda superior) colidiu
		if ((xDoOutro >= xDeste && xDoOutro <= xLarguraDeste)
				&& (yDoOutro >= yDeste && yDoOutro <= yAlturaDeste)) {
			return true;
		}
		// if abaixo testa se ponta2 do outro(direita superior) colidiu
		if ((xLarguraDoOutro >= xDeste && xLarguraDoOutro <= xLarguraDeste)
				&& (yDoOutro >= yDeste && yDoOutro <= yAlturaDeste)) {
			return true;
		}
		// if abaixo testa se ponta3 do outro(esquerda inferior) colidiu
		if ((xDoOutro >= xDeste && xDoOutro <= xLarguraDeste)
				&& (yAlturaDoOutro >= yDeste && yAlturaDoOutro <= yAlturaDeste)) {
			return true;
		}
		// if abaixo testa se ponta4 do outro(direita inferior) colidiu
		if ((xLarguraDoOutro >= xDeste && xLarguraDoOutro <= xLarguraDeste)
				&& (yAlturaDoOutro >= yDeste && yAlturaDoOutro <= yAlturaDeste)) {
			return true;
		}
		return false;
	}

	public void setLadoColisao(InteractiveObject go) {
		// define o lado que o mario esta colidindo no objeto
		int direita = (this.posXMundo + this.largura) - go.posXMundo;
		int esquerda = (go.posXMundo + go.largura) - this.posXMundo;
		int cima = (this.posYMundo + this.altura) - go.posYMundo;
		int baixo = (go.posYMundo + go.altura) - this.posYMundo;
		int menor = menor(direita, esquerda, cima, baixo);
		if (menor == baixo) {
			colideBaixo = true;
			if (jaColidiuBaixo) {
				jaColidiuBaixo = false;
			}
		}
		if (menor == cima) {
			colideCima = true;
			if (jaColidiuCima) {
				jaColidiuCima = false;
			}
		}
		if (menor == esquerda) {
			colideEsquerda = true;
			if (jaColidiuEsquerda) {
				jaColidiuEsquerda = false;
			}
		}
		if (menor == direita) {
			colideDireita = true;
			if (jaColidiuDireita) {
				jaColidiuDireita = false;
			}
		}

	}
	

	public int menor(int direita, int esquerda, int cima, int baixo) {
		if (direita < esquerda && direita < cima && direita < baixo) {
			return direita;
		}
		if (esquerda < direita && esquerda < cima && esquerda < baixo) {
			return esquerda;
		}
		if (cima < direita && cima < esquerda && cima < baixo) {
			return cima;
		}
		if (baixo < direita && baixo < esquerda && baixo < cima) {
			return baixo;
		}
		return -1;
	}

	public void semColisao() {
		colideCima = colideBaixo = colideDireita = colideEsquerda = false;
		jaColidiuBaixo = jaColidiuCima = jaColidiuDireita = jaColidiuEsquerda = false;
	}

}
