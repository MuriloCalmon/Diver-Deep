import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Beneficio {
	public Random aleatorio = new Random();
	public int posX;
	public int posY;
	public int raio;
	public int raio2;
	BufferedImage img;
	Game game;	
	public Beneficio() {try {
		 img = ImageIO.read(getClass().getResource("imgs/tank1.png"));
	} catch (IOException e) {}
	posX = aleatorio.nextInt(Principal.LARGURA_TELA - 100);
	posY = aleatorio.nextInt(Principal.ALTURA_TELA - 100);
	raio = 20;
	
	}
}


