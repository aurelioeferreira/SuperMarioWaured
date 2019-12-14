package senac.ti.sc.javagames.aurelio.sprites;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Chao extends InteractiveObject {
	
	
	static BufferedImage chao = null;
	static BufferedImage paredeDireita = null;
	static BufferedImage paredeEsquerda = null;
	static BufferedImage quinaDireitaFechada = null;
	static BufferedImage quinaEsquerdaFechada = null;
	static BufferedImage quinaDireitaAberta = null;
	static BufferedImage quinaEsquerdaAberta = null;
	static BufferedImage preenchimento = null;
	static BufferedImage preenchimentoPequeno = null;
	static BufferedImage paredePequenaDireita = null;
	static BufferedImage paredePequenaEsquerda = null;
	static boolean carregouImagens = false;

	private int quantidadePreenchimentos = 0;
	private int tipo = 0;
	public static final int CHAO = 1;
	public static final int PAREDE_DIREITA = 2;
	public static final int PAREDE_ESQUERDA = 3;
	public static final int QUINA_DIREITA_FECHADA = 4;
	public static final int QUINA_ESQUERDA_FECHADA = 5;
	public static final int QUINA_DIREITA_ABERTA = 6;
	public static final int QUINA_ESQUERDA_ABERTA = 7;
	public static final int PAREDE_PEQUENA_DIREITA = 8;
	public static final int PAREDE_PEQUENA_ESQUERDA = 9;
	public static final int BURACO = 10;

	public static final int LARGURA_QUINA = 48;
	public static final int ALTURA_QUINA = 48;
	public static final int LARGURA_CHAO = 150;
	public static final int ALTURA_CHAO = 48;
	public static final int LARGURA_PAREDE = 48;
	public static final int ALTURA_PAREDE = 150;
	public static final int ALTURA_PAREDE_PEQUENA = 75;

	// posicao onde colocar o proximo chao
	public static int posXProximo = 0;
	public static int posYProximo = 0;
	public static int posXUltimo = 0; // final do mapa

	public Chao(int tipo, int quantidadeBuracos) { // cria buracos
		super("buraco");
		this.tipo = tipo;
		if (tipo == Chao.BURACO) {
			Chao.posXProximo += quantidadeBuracos * Chao.LARGURA_CHAO;
		}
	}

	public Chao(int tipo) { // cria outros tipos de chao
		super("Chao");
		if (!Chao.carregouImagens) {
			loadImage();
			Chao.carregouImagens = true;
		}

		this.tipo = tipo;
		if (Chao.posXProximo == 0 && Chao.posYProximo == 0) {
			this.posYMundo = GameEngine.getFrameHeight() - Chao.ALTURA_CHAO
					- 20;
			this.posXMundo = 0;
			Chao.posXProximo = Chao.LARGURA_CHAO;
			Chao.posYProximo = this.posYMundo;
		} else {
			this.posXMundo = Chao.posXProximo;
			this.posYMundo = Chao.posYProximo;
		}
		if (tipo == Chao.CHAO) {
			largura = chao.getWidth();
			altura = chao.getHeight();
			Chao.posXProximo = this.posXMundo + this.largura;
			Chao.posYProximo = this.posYMundo;
			if (this.posYMundo + this.altura < GameEngine.getFrameHeight()) {
				int espacoVazio = GameEngine.getFrameHeight()
						- (this.posYMundo + this.altura);
				quantidadePreenchimentos = (espacoVazio / Chao.ALTURA_CHAO) + 1;
			}
		}
		if (tipo == Chao.QUINA_ESQUERDA_FECHADA) {
			largura = quinaEsquerdaFechada.getWidth();
			altura = quinaEsquerdaFechada.getHeight();
			Chao.posXProximo = this.posXMundo;
			Chao.posYProximo = this.posYMundo - Chao.ALTURA_PAREDE;
			if (this.posYMundo + this.altura < GameEngine.getFrameHeight()) {
				int espacoVazio = GameEngine.getFrameHeight()
						- (this.posYMundo + this.altura);
				quantidadePreenchimentos = (espacoVazio / Chao.ALTURA_QUINA) + 1;
			}
		}
		if (tipo == Chao.PAREDE_ESQUERDA) {
			largura = paredeEsquerda.getWidth();
			altura = paredeEsquerda.getHeight();
			Chao.posXProximo = this.posXMundo;
			Chao.posYProximo = this.posYMundo - Chao.ALTURA_QUINA;
			
		}
		if (tipo == Chao.QUINA_ESQUERDA_ABERTA) {
			largura = quinaEsquerdaAberta.getWidth();
			altura = quinaEsquerdaAberta.getHeight();
			Chao.posXProximo = this.posXMundo + this.largura;
			Chao.posYProximo = this.posYMundo;
		}
		if (tipo == Chao.QUINA_DIREITA_ABERTA) {
			largura = quinaDireitaAberta.getWidth();
			altura = quinaDireitaAberta.getHeight();
			Chao.posXProximo = this.posXMundo;
			Chao.posYProximo = this.posYMundo + this.altura;
		}
		if (tipo == Chao.PAREDE_DIREITA) {
			largura = paredeDireita.getWidth();
			altura = paredeDireita.getHeight();
			Chao.posXProximo = this.posXMundo;
			Chao.posYProximo = this.posYMundo + this.altura;
		}
		if (tipo == Chao.QUINA_DIREITA_FECHADA) {
			largura = quinaDireitaAberta.getWidth();
			altura = quinaDireitaAberta.getHeight();
			Chao.posXProximo = this.posXMundo + this.largura;
			Chao.posYProximo = this.posYMundo;
			if (this.posYMundo + this.altura < GameEngine.getFrameHeight()) {
				int espacoVazio = GameEngine.getFrameHeight()
						- (this.posYMundo + this.altura);
				quantidadePreenchimentos = (espacoVazio / Chao.ALTURA_QUINA) + 1;
			}
		}
		if (tipo == Chao.PAREDE_PEQUENA_DIREITA) {
			largura = paredePequenaDireita.getWidth();
			altura = paredePequenaDireita.getHeight();
			Chao.posXProximo = this.posXMundo;
			Chao.posYProximo = this.posYMundo + Chao.ALTURA_PAREDE_PEQUENA;
		}
		if (tipo == Chao.PAREDE_PEQUENA_ESQUERDA) {
			largura = paredePequenaEsquerda.getWidth();
			altura = paredePequenaEsquerda.getHeight();
			this.posYMundo += altura;
			Chao.posXProximo = this.posXMundo;
			Chao.posYProximo = this.posYMundo - Chao.ALTURA_QUINA;
		}
		posXUltimo = this.posXMundo + this.largura;
	}

	public void update() {
		updateCam();
	}

	@Override
	public void paint(Graphics g) {

		if (tipo == Chao.CHAO) {
			g.drawImage(chao, posXTela, posYTela, null);
			if (quantidadePreenchimentos > 0) {
				for (int c = 0; c < quantidadePreenchimentos; c++) {
					g.drawImage(preenchimento, this.posXTela,
							(this.posYTela + this.altura)
									+ (c * Chao.ALTURA_CHAO), null);
				}
			}
		}
		if (tipo == Chao.PAREDE_DIREITA) {
			g.drawImage(paredeDireita, posXTela, posYTela, null);
		}
		if (tipo == Chao.PAREDE_ESQUERDA) {
			g.drawImage(paredeEsquerda, posXTela, posYTela, null);
			
		}
		if (tipo == Chao.QUINA_DIREITA_FECHADA) {
			g.drawImage(quinaDireitaFechada, posXTela, posYTela, null);
			if (quantidadePreenchimentos > 0) {
				for (int c = 0; c < quantidadePreenchimentos; c++) {
					g.drawImage(preenchimento, this.posXTela,
							(this.posYTela + this.altura)
									+ (c * Chao.ALTURA_QUINA), null);
				}
			}
		}
		if (tipo == Chao.QUINA_ESQUERDA_FECHADA) {
			g.drawImage(quinaEsquerdaFechada, posXTela, posYTela, null);
			if (quantidadePreenchimentos > 0) {
				for (int c = 0; c < quantidadePreenchimentos; c++) {
					g.drawImage(preenchimento, this.posXTela,
							(this.posYTela + this.altura)
									+ (c * Chao.ALTURA_QUINA), null);
				}
			}
		}
		if (tipo == Chao.QUINA_DIREITA_ABERTA) {
			g.drawImage(quinaDireitaAberta, posXTela, posYTela, null);
		}
		if (tipo == Chao.QUINA_ESQUERDA_ABERTA) {
			g.drawImage(quinaEsquerdaAberta, posXTela, posYTela, null);
		}
		if (tipo == Chao.PAREDE_PEQUENA_DIREITA) {
			g.drawImage(paredePequenaDireita, posXTela, posYTela, null);
		}
		if (tipo == Chao.PAREDE_PEQUENA_ESQUERDA) {
			g.drawImage(paredePequenaEsquerda, posXTela, posYTela, null);
		}
	}

	@Override
	public void updateKeyEvent(KeyEvent keyevent) {

	}

	@Override
	public void updateMouseEvent(MouseEvent mouseevent, MouseEvent motion) {

	}

	public void loadImage() {
		try {
			chao = ImageIO.read(getClass().getResource("chao2.jpg"));
			paredeDireita = ImageIO.read(getClass().getResource(
					"paredeDireita.jpg"));
			paredeEsquerda = ImageIO.read(getClass().getResource(
					"paredeEsquerda.jpg"));
			quinaDireitaFechada = ImageIO.read(getClass().getResource(
					"quinaDireitaFechada.jpg"));
			quinaEsquerdaFechada = ImageIO.read(getClass().getResource(
					"quinaEsquerdaFechada.jpg"));
			quinaDireitaAberta = ImageIO.read(getClass().getResource(
					"quinaDireitaAberta.jpg"));
			quinaEsquerdaAberta = ImageIO.read(getClass().getResource(
					"quinaEsquerdaAberta.jpg"));
			preenchimento = ImageIO.read(getClass().getResource(
					"preenchimento.jpg"));
			preenchimentoPequeno = ImageIO.read(getClass().getResource(
					"preenchimento_pequeno.jpg"));
			paredePequenaDireita = paredeDireita.getSubimage(0, 0,
					paredeDireita.getWidth(), paredeDireita.getHeight() / 2);
			paredePequenaEsquerda = paredeEsquerda.getSubimage(0, 0,
					paredeEsquerda.getWidth(), paredeEsquerda.getHeight() / 2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
