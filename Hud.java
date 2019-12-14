package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Hud extends GameObject2D {

	private int life;
	public int pontuacao = 0;
	public int tempo = 0;
	public int segundos = 40; // 37 original
	public int tamanhoFonte = 600;
	private int contPause = 120;
	private int cor = 140;
	public boolean ganhou, perdeu, voltaCor, result, finalizou;
	private boolean canPause = true;
	private int resultado = -1;
	private int contResultado = -1;

	public Hud() {
		super("Hud");

	}

	@Override
	public void paint(Graphics g) {
		if (GameEngine.escPressed) {
			SpriteAure2.estado = SpriteAure2.emenu;
			GameEngine.pause = false;
			Camera.zeraPos();
		}
		Font f = new Font("Verdana", 1, 20);
		g.setFont(f);
		g.setColor(new Color(165, 205, 0));
		g.drawString("Life: " + getLife(), 50, 30);
		g.setColor(Color.ORANGE);
		g
				.drawString("Tempo: " + segundos,
						GameEngine.getFrameWidth() - 150, 30);
		g.setColor(Color.MAGENTA);
		g.drawString("Pontuação: " + pontuacao, 50, 60);

		if (life <= 0) {
			g.setFont(new Font("Verdana", 2, 100));
			g.setColor(Color.RED);
			g.drawString("Morreu", GameEngine.getFrameWidth() / 2 - 100,
					GameEngine.getFrameHeight() / 2 - 10);
		}

		if (life == 0 && !GameEngine.pause) {
			GameEngine.pause = true;
			contPause = 120;
		}
		if (tamanhoFonte <= 60 && !GameEngine.pause) {
			GameEngine.pause = true;
			contPause = 120;
		}
		if (GameEngine.pause && (life == 0 || ganhou || perdeu)) {
			contPause--;
			if (contPause == 0 && (finalizou || life == 0)) {
				SpriteAure2.estado = SpriteAure2.emenu;
				Camera.zeraPos();
				GameEngine.pause = false;
			}
			if (contPause == 0 && !result && (ganhou || perdeu)) {
				contPause = 120;
				result = true;
			}
		}


		if (ganhou) {
			if (resultado == -1) {
				resultado = life * segundos * pontuacao;
			}
			if (contResultado < resultado) {
				contResultado++;

				if (contResultado == resultado) {
					GameEngine.pause = true;
				}
			}
			if (!result) {
				g.setColor(Color.ORANGE);
				g.setFont(new Font("Arial Black", 1, 25));
				g.drawString("Resultado final: Life * Pontuacao * Tempo = "
						+ contResultado, GameEngine.getFrameWidth() / 2 - 330,
						GameEngine.getFrameHeight() / 2 - 20);
			} else {
				g.setColor(Color.GREEN);
				if (tamanhoFonte > 60) {
					tamanhoFonte -= 10;
				} else {
					finalizou = true;
				}
				if (resultado == 0) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("Arial Black", 1, tamanhoFonte));
					g.drawString("0 ponto . . .", GameEngine.getFrameWidth() / 2
							- (-100 + tamanhoFonte * 5), GameEngine
							.getFrameHeight()
							/ 2 - (50 + tamanhoFonte / 20));
				}
				if (resultado <= 60 && resultado > 0) {
					g.setFont(new Font("Arial Black", 1, tamanhoFonte));
					g.drawString("Ganhou ! ! !", GameEngine.getFrameWidth() / 2
							- (-100 + tamanhoFonte * 5), GameEngine
							.getFrameHeight()
							/ 2 - (50 + tamanhoFonte / 20));
				}
				if (resultado > 60 && resultado <= 100) {
					g.setFont(new Font("Arial Black", 1, tamanhoFonte));
					g.drawString("ÓTIMO ! ! !", GameEngine.getFrameWidth() / 2
							- (-100 + tamanhoFonte * 5), GameEngine
							.getFrameHeight()
							/ 2 - (50 + tamanhoFonte / 20));
				}
				if (resultado > 100 && resultado <= 160) {
					g.setColor(Color.YELLOW);
					g.setFont(new Font("Arial Black", 1, tamanhoFonte));
					g.drawString("EXCELENTE ! ! !", GameEngine.getFrameWidth()
							/ 2 - (-100 + tamanhoFonte * 5), GameEngine
							.getFrameHeight()
							/ 2 - (50 + tamanhoFonte / 20));
				}
				if (resultado > 160 && resultado <= 200) {
					g.setColor(Color.YELLOW);
					g.setFont(new Font("Arial Black", 1, tamanhoFonte));
					g.drawString("INCRÍVEL ! ! !", GameEngine.getFrameWidth()
							/ 2 - (-100 + tamanhoFonte * 5), GameEngine
							.getFrameHeight()
							/ 2 - (50 + tamanhoFonte / 20));
				}
				if (resultado > 200) {
					g.setColor(Color.RED);
					g.setFont(new Font("Arial Black", 1, tamanhoFonte));
					g.drawString("THE BEST ! ! !", GameEngine.getFrameWidth()
							/ 2 - (-100 + tamanhoFonte * 5), GameEngine
							.getFrameHeight()
							/ 2 - (50 + tamanhoFonte / 20));
				}
			}
		}

		if (perdeu) {
			g.setColor(Color.LIGHT_GRAY);
			if (tamanhoFonte > 60) {
				tamanhoFonte -= 10;
			}
			else {
				finalizou = true;
			}
			g.setFont(new Font("Arial Black", 1, tamanhoFonte));
			g.drawString("Perdeu . . .", GameEngine.getFrameWidth() / 2
					- (-100 + tamanhoFonte * 5), GameEngine.getFrameHeight()
					/ 2 - (50 + tamanhoFonte / 20));
		}

		// pause no enter
		if (!GameEngine.enterPressed) {
			canPause = true;
		}
		if (GameEngine.enterPressed && canPause
				&& (life > 0 && !perdeu && !ganhou)) {
			canPause = false;
			if (GameEngine.pause) {
				GameEngine.pause = false;
			} else {
				GameEngine.pause = true;
			}
		}
		if (GameEngine.pause && (life > 0 && !perdeu && !ganhou)) {
			g.setFont(new Font("Verdana", 1, 25));
			g.setColor(Color.ORANGE);
			if (cor >= 251) {
				voltaCor = true;
			}
			if (cor <= 130) {
				voltaCor = false;
			}
			if (voltaCor) {
				cor -= 3;
			} else {
				cor += 3;
			}
			g.setColor(new Color(cor, cor, 180));
			g.drawString(" - PAUSE - ", GameEngine.getFrameWidth() / 2 - 55,
					GameEngine.getFrameHeight() / 2 - 10);
		}

	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void setInfos(int life) {
		this.life = life;
		if ((!ganhou || perdeu) && segundos > 0)
			tempo++;
		if (tempo == 60) {
			segundos--;
			tempo = 0;
		}
	}

	public int getLife() {
		return this.life;
	}

}
