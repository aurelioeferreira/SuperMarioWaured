package senac.ti.sc.javagames.aurelio.sprites;

public abstract class Camera {
	
	public static int posX, posXFundo, centro,  posY, posYFundo = 0;

	
	public static void zeraPos() {
		posX = posY = posXFundo = posYFundo = centro = 0;
	}

}
