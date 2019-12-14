package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;

public class SpriteAure2 extends GameEngine {

	
	private static final long serialVersionUID = 1L;
	public static final int emenu = 1;
	public static final int ejogar = 2;
	public static int estado = 1;
	public static int estadoAnterior = 2;
	public static boolean showTutorial = false;
	
	Tutorial tutorial;

	Menu menu;
	Mario mario;
	Careta careta;
	Hud hud;
	Chao chao;

	public SpriteAure2() {
		super("Super Mario Waured", 900, 600);
	}

	public void draw(Graphics g) {
		for (int c = 0; c < GameObject2D.gameObjects.size(); c++) {
			GameObject2D.gameObjects.get(c).paint(g);
		}
	}

	public void update() {
		
		

		if (InteractiveObject.interactiveObjects != null) {
			if (mario != null) {
				mario.update();
				mario.semColisao();
			}
			
			for (int c2 = 0; c2 < InteractiveObject.interactiveObjects.size(); c2++) {
				// update do objeto
				if (InteractiveObject.interactiveObjects.get(c2) != null) {

					if (!(InteractiveObject.interactiveObjects.get(c2) instanceof Mario)) {
						InteractiveObject.interactiveObjects.get(c2).update();
					}
				}
			}
			for (int c2 = 0; c2 < InteractiveObject.interactiveObjects.size(); c2++) {
				if ((InteractiveObject.interactiveObjects.get(c2).posXTela
						+ InteractiveObject.interactiveObjects.get(c2).largura > 0)
						&& (InteractiveObject.interactiveObjects.get(c2).posXTela < this
								.getWidth())) {

					// testa se o objeto da colisao nao eh o proprio
					// mario:
					if (!(InteractiveObject.interactiveObjects.get(c2) instanceof Mario)) {

						// testa colisao:
						if (mario.colisao(InteractiveObject.interactiveObjects
								.get(c2))) {

							// testa se nao eh inimigo nem objetivo e seta lado
							// que colide:
							if (!(InteractiveObject.interactiveObjects.get(c2) instanceof Inimigo)
									&& !(InteractiveObject.interactiveObjects
											.get(c2) instanceof Objetivo)) {
								mario
										.setLadoColisao(InteractiveObject.interactiveObjects
												.get(c2));
								// correcao de posicao(para ficar na borda
								// do
								// objeto):
								if (mario.colideDireita
										&& !mario.jaColidiuDireita) {
									mario.jaColidiuDireita = true;
									mario.posXMundo = InteractiveObject.interactiveObjects
											.get(c2).posXMundo
											- mario.largura;
								}
								if (mario.colideEsquerda
										&& !mario.jaColidiuEsquerda) {
									mario.jaColidiuEsquerda = true;
									mario.posXMundo = InteractiveObject.interactiveObjects
											.get(c2).posXMundo
											+ InteractiveObject.interactiveObjects
													.get(c2).largura;
								}
								if (mario.colideCima && !mario.jaColidiuCima) {
									mario.jaColidiuCima = true;
									mario.posYMundo = InteractiveObject.interactiveObjects
											.get(c2).posYMundo
											- mario.altura;
								}
								if (mario.colideBaixo && !mario.jaColidiuBaixo) {
									mario.jaColidiuBaixo = true;
									mario.posYMundo = InteractiveObject.interactiveObjects
											.get(c2).posYMundo
											+ InteractiveObject.interactiveObjects
													.get(c2).altura;
								} // fim das correções de posição
							} else { // eh inimigo ou objetivo

								if (InteractiveObject.interactiveObjects
										.get(c2) instanceof Objetivo) {
									if (hud.segundos > 0)
										hud.ganhou = true;
									if (hud.segundos <= 0)
										hud.perdeu = true;
								}
								if (InteractiveObject.interactiveObjects
										.get(c2) instanceof Inimigo) {
									if (mario.posYMundo + mario.altura <= InteractiveObject.interactiveObjects
											.get(c2).posYMundo + 14
											&& !mario.apanha) {
										((Inimigo) InteractiveObject.interactiveObjects
												.get(c2)).morre();
										hud.pontuacao++;
										mario.veloPulo = 11.0;
									} else {
										if (((Inimigo) InteractiveObject.interactiveObjects
												.get(c2)).morreu == true) {

										} else {
											mario.apanha = true;
										}
									}
								}
							} // fim do else que testa se eh inimigo ou objetivo
						} // fim do if que testa colisao
					} // fim do if que testa se o objeto nao eh o mario
				} // fim do if que testa posXTela(posicao na tela)
			} // fim do for
			if (mario != null) {
				if (mario.morreu) {
					mario = null;
				}
			}
		} // fim do if externo

		// trata do hud:
		if (hud != null && mario != null) {
			hud.setInfos(mario.life);
		}
		
		if (NonInteractiveObject.nonInteractiveObjects != null) {
			for (int c = 0; c < NonInteractiveObject.nonInteractiveObjects
					.size(); c++) {
				NonInteractiveObject.nonInteractiveObjects.get(c).update();
			}
		}
		

		switch (estado) {
		case emenu:
			if (estadoAnterior != estado) {
				GameObject2D.gameObjects.clear();
				Chao.posXProximo = 0;
				Chao.posYProximo = 0;
				if (InteractiveObject.interactiveObjects != null) {
					InteractiveObject.interactiveObjects.clear();
				}
				new MarioImg(0);
				new Titulo(120);
				menu = new Menu();
			}
			if (showTutorial && tutorial == null) {
				tutorial = new Tutorial();
				
			}
			if (!showTutorial) {
				tutorial = null;
			}
			break;
		case ejogar:
			if (estadoAnterior != estado) {
				GameObject2D.gameObjects.clear();
				if (InteractiveObject.interactiveObjects != null) {
					InteractiveObject.interactiveObjects.clear();
				}
				new Fundo();

				// criando nuvens:
				new Nuvem(600, 100);
				new Nuvem(610, 120);
				new Nuvem(300, 180);
				new Nuvem(330, 180);
				new Nuvem(350, 170);
				new Nuvem(1200, 100);
				new Nuvem(1600, 140);
				new Nuvem(1900, 140);
				new Nuvem(1950, 140);
				new Nuvem(2100, 180);
				new Nuvem(2350, 160);
				new Nuvem(2360, 150);
				new Nuvem(2700, 100);
				new Nuvem(2900, 140);
				new Nuvem(3040, 160);
				new Nuvem(3040, 180);
				new Nuvem(3500, 100);
				new Nuvem(3700, 80);
				new Nuvem(3810, 30);

				// criando montanhas:
				new Montanha(300, GameEngine.getFrameHeight());
				new Montanha(700, GameEngine.getFrameHeight());
				new Montanha(1000, GameEngine.getFrameHeight());
				new Montanha(1100, GameEngine.getFrameHeight());
				new Montanha(1500, GameEngine.getFrameHeight());
				new Montanha(1580, GameEngine.getFrameHeight());
				new Montanha(2600, GameEngine.getFrameHeight());
				new Montanha(3400, GameEngine.getFrameHeight());

				// criando chaos:
				chao = new Chao(Chao.CHAO);
				for (int c = 0; c < 7; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.QUINA_ESQUERDA_FECHADA);
				new Chao(Chao.PAREDE_ESQUERDA);
				new Chao(Chao.QUINA_ESQUERDA_ABERTA);
				new Chao(Chao.CHAO);
				new Chao(Chao.CHAO);
				new Chao(Chao.CHAO);
				new Chao(Chao.QUINA_DIREITA_ABERTA);
				new Chao(Chao.PAREDE_DIREITA);
				new Chao(Chao.QUINA_DIREITA_FECHADA);
				for (int c = 1; c < 5; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.BURACO, 2);
				for (int c = 1; c < 5; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.QUINA_ESQUERDA_FECHADA);
				new Chao(Chao.PAREDE_ESQUERDA);
				new Chao(Chao.QUINA_ESQUERDA_ABERTA);
				for (int c = 1; c < 5; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.QUINA_ESQUERDA_FECHADA);
				new Chao(Chao.PAREDE_ESQUERDA);
				new Chao(Chao.QUINA_ESQUERDA_ABERTA);
				for (int c = 0; c < 2; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.QUINA_DIREITA_ABERTA);
				new Chao(Chao.PAREDE_DIREITA);
				new Chao(Chao.QUINA_DIREITA_FECHADA);
				for (int c = 1; c < 9; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.QUINA_DIREITA_ABERTA);
				new Chao(Chao.PAREDE_DIREITA);
				new Chao(Chao.QUINA_DIREITA_FECHADA);
				for (int c = 1; c < 6; c++) {
					new Chao(Chao.CHAO);
				}
				new Chao(Chao.QUINA_ESQUERDA_FECHADA);
				new Chao(Chao.PAREDE_PEQUENA_ESQUERDA); // testando
				new Chao(Chao.QUINA_ESQUERDA_ABERTA);
				chao(3);
				new Chao(Chao.QUINA_DIREITA_ABERTA);
				new Chao(Chao.PAREDE_PEQUENA_DIREITA);
				new Chao(Chao.QUINA_DIREITA_FECHADA);
				chao(3);
				new Chao(Chao.BURACO, 4);
				chao(3);
				escadaPequenaSobe(2);
				chao(2);
				escadaPequenaDesce(2);
				chao(6);
				escadaGrandeSobe(2);
				chao(2);
				escadaPequenaDesce(2);
				chao(5);
				escadaPequenaSobe(2);
				chao(2);
				escadaPequenaSobe(2);
				chao(1);

				// criando caixas:

				careta = new Careta(1080, 410);
				new Careta(500, 260);
				new Careta(500 + careta.largura, 260);
				new Careta(500 + careta.largura * 2, 260);
				new Careta(500 + careta.largura * 3, 260);
				new Careta(1080 - careta.largura, 410);
				new Careta(1080 - 2 * careta.largura, 410);
				new Careta(1080 - 3 * careta.largura, 410);
				new Careta(1080 - 3 * careta.largura, 290);
				new Careta(2181, GameEngine.getFrameHeight() - Careta.ALTURA
						- 20 - Chao.ALTURA_CHAO);
				new Careta(1210, 190);
				new Careta(1210 + careta.largura, 190);
				new Careta(1937, 400);
				new Careta(3126, 410);
				new Careta(3750, 210);
				new Careta(3902, 0);
				new Careta(4417, 210);
				new Careta(5707, 410);
				new Careta(7600, 500);
				new Careta(10091, 410);
				new Careta(10409, 210);
				new Careta(12509, 0);
				new Careta(12509 + careta.largura, 0);
				new Careta(12509 + careta.largura * 2, 0);
				new Careta(12509 + careta.largura * 3, 0);
				new Careta(12509 + careta.largura * 4, 0);

				new Inimigo(550, 400, 300);
				new Inimigo(1350, 200, 280);
				new Inimigo(2740, 400, 280);
				new Inimigo(3943, 0, 250);
				new Inimigo(4686, 200, 350);
				new Inimigo(5000, 200, 350);
				new Inimigo(6309, 260, 150);
				new Inimigo(6597, 260, 150);
				new Inimigo(7893, 400, 300);
				new Inimigo(9474, 400, 350);
				new Inimigo(11533, 250, 400);
				new Objetivo();
				mario = new Mario(this.getWidth() / 2);
				hud = new Hud();
				new AberturaFase();
				// GameEngine.pause = true;
			}
			break;
		}
		estadoAnterior = estado;
		update = true;
	} // fim do update

	public void init() {
		new MarioImg(0);
		menu = new Menu();
		new Titulo(120);
	}

	public void escadaPequenaSobe(int quantidade) {
		for (int c = 0; c < quantidade; c++) {
			new Chao(Chao.QUINA_ESQUERDA_FECHADA);
			new Chao(Chao.PAREDE_PEQUENA_ESQUERDA);
			new Chao(Chao.QUINA_ESQUERDA_ABERTA);
			new Chao(Chao.CHAO);
		}
	}

	public void escadaPequenaDesce(int quantidade) {
		for (int c = 0; c < quantidade; c++) {
			new Chao(Chao.QUINA_DIREITA_ABERTA);
			new Chao(Chao.PAREDE_PEQUENA_DIREITA);
			new Chao(Chao.QUINA_DIREITA_FECHADA);
			new Chao(Chao.CHAO);
		}
	}

	public void escadaGrandeSobe(int quantidade) {
		for (int c = 0; c < quantidade; c++) {
			new Chao(Chao.QUINA_ESQUERDA_FECHADA);
			new Chao(Chao.PAREDE_ESQUERDA);
			new Chao(Chao.QUINA_ESQUERDA_ABERTA);
			new Chao(Chao.CHAO);
		}
	}

	public void escadaGrandeDesce(int quantidade) {
		for (int c = 0; c < quantidade; c++) {
			new Chao(Chao.QUINA_DIREITA_ABERTA);
			new Chao(Chao.PAREDE_DIREITA);
			new Chao(Chao.QUINA_DIREITA_FECHADA);
			new Chao(Chao.CHAO);
		}
	}

	public void chao(int quantidade) {
		for (int c = 0; c < quantidade; c++) {
			new Chao(Chao.CHAO);
		}
	}

	public static void main(String args[]) {
		new SpriteAure2();
	}

}
