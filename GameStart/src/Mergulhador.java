import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mergulhador {
	
	public BufferedImage direita;
	public BufferedImage cima;
	public BufferedImage baixo;
	public BufferedImage esquerda;
	public BufferedImage bola_parada;
	public BufferedImage esquerda_cima;
	public BufferedImage esquerda_baixo;
	public BufferedImage direita_cima;
	public BufferedImage direita_baixo;
    public int posX;
    public int posY;
    public int raio;
    public int velX;
    public int velY;


    public Mergulhador() {
    	try {
    		bola_parada = ImageIO.read(getClass().getResource("imgs/mergulhador1parado.png"));
			esquerda = ImageIO.read(getClass().getResource("imgs/mergulhador1esquerda.png"));
			cima = ImageIO.read(getClass().getResource("imgs/mergulhador1cima.png"));
			direita = ImageIO.read(getClass().getResource("imgs/mergulhadordireito.png"));
			baixo = ImageIO.read(getClass().getResource("imgs/mergulhador1baixo.png"));
			esquerda_cima = ImageIO.read(getClass().getResource("imgs/mergulhador1esquerdacima.png"));
			esquerda_baixo = ImageIO.read(getClass().getResource("imgs/mergulhador1esquerdabaixo.png"));
			direita_cima = ImageIO.read(getClass().getResource("imgs/mergulhador1direitacima.png"));
			direita_baixo = ImageIO.read(getClass().getResource("imgs/mergulhador1direitabaixo.png"));
		} catch (IOException e) {
			System.out.println("Erro ao carregar imagens");
			e.printStackTrace();
		}

        posX = 100;
        posY = 100;
        raio = 50;
        velX = 0;
        velY = 0;
    }
    
}

