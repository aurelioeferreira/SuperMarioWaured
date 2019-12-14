package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mario extends InteractiveObject {

	BufferedImage mario = null;
	int velocidade = 8; // 8 original
	int spriteAtualX = 4;
	int spriteAtualY = 1;
	int larguraReal;
	boolean piscando = false;
	double veloPulo = 15.0; // 13 original
	int cont = 0; // quando cont = 6 o sprite(spriteAtualX) muda
	public boolean apanha, volta, pulando, morreu = false; // indo ou voltando na ordem
	// dos sprites
	public boolean viradoDireita = true;

	public int contApanha = 90, life = 5;

	public Mario(int posX) {
		super("Mario");
		loadImage();
		this.posXMundo = posX;
		this.posYMundo = 200;
		altura = mario.getHeight() / 4;
		larguraReal = (mario.getWidth() / 6); // testando menos largura
		largura = (mario.getWidth() / 6) - 12;
	}

	public void update() {
		if (viradoDireita && Camera.posX >= 18
				&& Camera.posX <= Chao.posXUltimo - GameEngine.getFrameWidth()
				&& this.posXTela >= (GameEngine.getFrameWidth() / 2) - 60) {
			Camera.centro++;
			Camera.posX += 3;
			Camera.posXFundo += 3 * 4;

		}
		if (!viradoDireita && Camera.posX >= 18
				&& Camera.posX <= Chao.posXUltimo - GameEngine.getFrameWidth()
				&& this.posXTela <= (GameEngine.getFrameWidth() / 2) + 60) {
			Camera.centro--;
			Camera.posX -= 3;
			Camera.posXFundo -= 3 * 4;

		}
		// apanha do inimigo:
		if (apanha) {
			if (contApanha == 90) {
				life--;
			}
			contApanha--;
		}
		if (contApanha == 0) {
			apanha = false;
			contApanha = 90;
		} // fim de apanha do inimigo

		// posicoes:
		if (posYMundo > GameEngine.getFrameHeight() + 30) {
			SpriteAure2.estado = SpriteAure2.emenu;
			morreu = true;
			posYMundo = 200;
			pulando = false;
			Camera.zeraPos();
		}
		if (spriteAtualX == 5) {
			volta = true;
		}
		if (spriteAtualX == 3) {
			volta = false;
		}
		if (cont >= 4) {
			if (!volta) {
				spriteAtualX++;
			} else {
				spriteAtualX--;
			}
			cont = 0;
		}
		if (!colideCima && !pulando) {
			pulando = true;
			veloPulo = 0.0;
		}
		// aqui o pulo acaba
		if (colideCima && veloPulo < 0.0) {
			pulando = false;
			if (GameEngine.pPressed) {
				veloPulo = 40.0;
				velocidade = 28;
			} else {
				veloPulo = 13.0; // 13
				velocidade = 8;
			}
			spriteAtualX = 4;
		}
		if (colideBaixo && veloPulo > 0.0) {
			veloPulo = 0.0;
		}
		if (pulando) {
			spriteAtualX = 2;
			posYMundo -= (int) veloPulo;
			veloPulo -= 0.5; // 0.5 original
		}
		if (pulando && veloPulo < 0) {
			caindo = true;
			subindo = false;
		}
		if (pulando && veloPulo > 0) {
			caindo = false;
			subindo = true;
		}
		if (pulando && veloPulo == 0) {
			subindo = caindo = true;
		}
		if (!pulando) {
			subindo = caindo = false;
		}

		// aqui comeca o update de keyevents
		/*if (GameEngine.escPressed) {
			SpriteAure2.estado = SpriteAure2.emenu;
			posYMundo = 200;
			pulando = false;
			Camera.zeraPos();
		}*/
		if (GameEngine.upKeyPressed) {
			if (colideCima) {
				pulando = true;
			}
		}
		if (GameEngine.downKeyPressed) {
			// ira se abaixar futuramente
		}
		if (GameEngine.rightKeyPressed && GameEngine.leftKeyPressed && !pulando) {
			spriteAtualX = 4;
		}
		if (GameEngine.rightKeyPressed ^ GameEngine.leftKeyPressed) {
			if (GameEngine.rightKeyPressed) {
				viradoDireita = true;
				spriteAtualY = 1;
				if (!colideDireita && this.posXMundo + this.largura <= Chao.posXUltimo) {
					posXMundo += velocidade;
					if (this.posXMundo > GameEngine.getFrameWidth() / 2
							&& Camera.posX < (Chao.posXUltimo - GameEngine
									.getFrameWidth())) {
						Camera.posX += velocidade;
						Camera.posXFundo += velocidade;
					}
					cont++;
				} else {
					if (!pulando)
						spriteAtualX = 4;
					else
						spriteAtualX = 2;
				}
			}
			if (GameEngine.leftKeyPressed) {
				viradoDireita = false;
				spriteAtualY = 3;
				if (posXMundo >= 4 && !colideEsquerda) {
					posXMundo -= velocidade;
					if (Camera.posX >= 18
							&& this.posXMundo <= Chao.posXUltimo
									- GameEngine.getFrameWidth() / 2) {
						Camera.posX -= velocidade;
						Camera.posXFundo -= velocidade;
					}
					cont++;
				} else {
					if (!pulando)
						spriteAtualX = 4;
					else
						spriteAtualX = 2;
				}
			}
		}
		if (!GameEngine.upKeyPressed && !GameEngine.downKeyPressed
				&& !GameEngine.rightKeyPressed && !GameEngine.leftKeyPressed
				&& !pulando) { // nenhuma tecla esta apertada e o personagem
			// esta no chao
			spriteAtualX = 4;
		} // fim dos keyevents
		if (posYMundo <= 260) {
			Camera.posY = (posYMundo - 260);
			Camera.posYFundo = (posYMundo - 260);
		}
		if (Camera.posX > Chao.posXUltimo - GameEngine.getFrameWidth()) {
			Camera.posX = Chao.posXUltimo - GameEngine.getFrameWidth();
			Camera.posXFundo = Chao.posXUltimo - GameEngine.getFrameWidth();
		}
		if (Camera.posX < 4) {
			Camera.posX = 4;
			Camera.posXFundo = 4;
		}
		updateCam();

	}

	public void paint(Graphics g) {
		// pinta mario sprites
		if (!(contApanha < 90)) {
			g.drawImage(mario.getSubimage(
					((int) (mario.getWidth() / 6) * spriteAtualX) + 8,
					(int) (mario.getHeight() / 4) * spriteAtualY, largura,
					(int) (mario.getHeight() / 4)), posXTela, posYTela, null);
		} else {
			if (!(contApanha % 3 == 0)) {
				g.drawImage(
						mario.getSubimage((int) (mario.getWidth() / 6)
								* spriteAtualX + 8,
								(int) (mario.getHeight() / 4) * spriteAtualY,
								largura, (int) (mario.getHeight() / 4)),
						posXTela, posYTela, null);

			}
		}
	}

	public void updateKeyEvent(KeyEvent keyevent) {

	}

	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {
	}

	// carrega a imagem do mario
	public void loadImage() {
		
		try {
			mario = ImageIO.read(getClass().getResource("mario3alpha.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
}
