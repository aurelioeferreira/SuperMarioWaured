package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Tutorial extends NonInteractiveObject {

	private boolean para, ok;

	public Tutorial() {
		super("tutorial");
		altura = 400;
		largura = 600;
		posX = GameEngine.getFrameWidth();
		posY = GameEngine.getFrameHeight() / 2 - altura / 2;
	}

	public void update() {
		
		if (!para) {
			posX -= 30;
		}
		if (posX < 0 - largura) {
			SpriteAure2.showTutorial = false;
			GameObject2D.gameObjects.remove(this);
			NonInteractiveObject.nonInteractiveObjects.remove(this);
		}

		if (posX >= (GameEngine.getFrameWidth() / 2) - (largura / 2) - 1
				&& posX <= (GameEngine.getFrameWidth() / 2) - (largura / 2) + 28) {
			posX = (GameEngine.getFrameWidth() / 2) - (largura / 2);
			para = true;
		}

		if (GameEngine.enterPressed && para) {
			para = false;
		}

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(posX, posY, largura, altura);
		g.setColor(Color.YELLOW);
		g.drawRect(posX - 4, posY - 4, largura + 8, altura + 8);
		g.drawRect(posX - 2, posY - 2, largura + 4, altura + 4);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 2, 25));
		g.drawString("Como jogar", posX + 230, posY + 50);
		g.setFont(new Font("Verdana", 1, 15));
		g.drawString("Use as setas do teclado:", posX + 30, posY + 100);
		g.drawString("- Seta pra cima: pula", posX + 30, posY + 120);
		g.drawString("- Seta pra esquerda: corre pra esquerda", posX + 30, posY + 140);
		g.drawString("- Seta pra direita: corre pra direita", posX + 30, posY + 160);
		g.drawString("- Enter: pausa o jogo", posX + 30, posY + 180);
		g.drawString("- ESC: volta ao menu inicial", posX + 30, posY + 200);
		
		g.setFont(new Font("Verdana", 2, 25));
		g.drawString("Objetivo", posX + 240, posY + 250);
		g.setFont(new Font("Verdana", 1, 15));
		g.drawString("- Chegar ao final da fase dentro do tempo limite", posX + 30, posY + 280);
		g.drawString("- Não morrer pros inimigos(Observe seu LIFE)", posX + 30, posY + 300);
		g.drawString("- Cuidado para não cair nos buracos!", posX + 30, posY + 320);
		
		
		if (!ok) {
			g.drawImage(Menu.botao, posX + largura / 2 - 65, posY + altura - 50, null);
		} else {
			g.drawImage(Menu.botao2, posX + largura / 2 - 65, posY + altura - 50, null);
		}
		g.setColor(Color.YELLOW);
		g.drawString("OK", posX + largura / 2, posY + 23 + altura - 50);
		
	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {
		int posXBotao = posX  + largura / 2 - 65;
		int posYBotao = posY + altura - 50;
		if ((motion.getX() >= posXBotao && motion.getX() <= posXBotao
				+ Menu.botao.getWidth())
				&& (motion.getY() >= posYBotao && motion.getY() <= posYBotao
						+ Menu.botao.getHeight())) {
			ok = true;
			if (mouseevent.getID() == MouseEvent.MOUSE_PRESSED) {
				para = false;			
			}

		} else {
			ok = false;
		}

	}
	


}
