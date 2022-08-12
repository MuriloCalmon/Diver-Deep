import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Fundo {
	public BufferedImage img; 
	public double posX;
	public double posY;
	public double velX;
	public double velY;
	
	public Fundo(String nome, double posX) {
		try {
			img = ImageIO.read(getClass().getResource(nome));
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}
		this.posX = posX;
		posY = 0;
	}
	
}
