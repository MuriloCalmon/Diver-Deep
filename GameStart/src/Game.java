import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel{
        Mergulhador mergulhador;
        public int v_padraomenor = -3;
        public int v_padraomaior = 3;
        private TubaraoPrimeiro tubaraoPrimeiro;
        private TubaraoSegundo tubaraoSegundo;
        private Beneficio beneficio;
        private Tesouro tesouro;
        private boolean k_cima = false;
        private boolean k_baixo = false;
        private boolean k_esquerdo = false;
        private boolean k_direito = false;
        Fundo bg;
        private BufferedImage imgAtual;
        private BufferedImage imgAtualtubarao1;
        private BufferedImage imgAtualtubarao2;
        DecimalFormat df = new DecimalFormat("0.0");
    
	public Game() {
		
		try {
			bg = new Fundo("imgs/bg1.png",0);
			
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}
		 	addKeyListener(new KeyListener() {
		 		
				@Override
				public void keyTyped(KeyEvent arg0) {
										
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: k_cima = false; break;
					case KeyEvent.VK_DOWN: k_baixo = false; break;
					case KeyEvent.VK_LEFT: k_esquerdo = false; break;
					case KeyEvent.VK_RIGHT: k_direito = false; break;
	
				}
			}
				@Override
				public void keyPressed(KeyEvent e) {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_UP: k_cima = true; break;
						case KeyEvent.VK_DOWN: k_baixo = true; break;
						case KeyEvent.VK_LEFT: k_esquerdo = true; break;
						case KeyEvent.VK_RIGHT: k_direito = true; break;

						}
				}
			});
            mergulhador = new Mergulhador();
            tubaraoPrimeiro = new TubaraoPrimeiro();
            tubaraoSegundo = new TubaraoSegundo();
            tubaraoSegundo.Start();
            tubaraoPrimeiro.Start();
            tubaraoPrimeiro.StartTarefaPontos();
            beneficio = new Beneficio();
            tesouro = new Tesouro();
            setFocusable(true);
            setLayout(null);
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    gameloop();
                }
            }).start();
            
	}
        // GAMELOOP -------------------------------
        public void gameloop(){
            while(true){ // representa as repeticoes quadro a quadro
                handlerEvents();
                update();
                render();
               
                try {
                    Thread.sleep(17);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
        public void handlerEvents(){
             mergulhador.velX = 0;
             mergulhador.velY = 0;
             imgAtual = mergulhador.bola_parada;
             imgAtualtubarao1 = tubaraoPrimeiro.esquerda;
             imgAtualtubarao2 = tubaraoSegundo.esquerda;
             
             if(tubaraoPrimeiro.velY < 0 && tubaraoPrimeiro.velX == 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.cima;
             }else if(tubaraoPrimeiro.velY > 0 && tubaraoPrimeiro.velX == 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.baixo;
             }else if(tubaraoPrimeiro.velY < 0 && tubaraoPrimeiro.velX > 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.direita_cima;
             }else if(tubaraoPrimeiro.velY > 0 && tubaraoPrimeiro.velX > 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.direita_baixo;
             }else if(tubaraoPrimeiro.velY < 0 && tubaraoPrimeiro.velX < 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.esquerda_cima;
             }else if(tubaraoPrimeiro.velY > 0 && tubaraoPrimeiro.velX < 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.esquerda_baixo;
             }else if(tubaraoPrimeiro.velY == 0 && tubaraoPrimeiro.velX > 0) {
            	 imgAtualtubarao1 = tubaraoPrimeiro.direita;
             }
             
             if(tubaraoSegundo.velY < 0 && tubaraoSegundo.velX == 0) {
            	 imgAtualtubarao2 = tubaraoSegundo.cima;
             }else if(tubaraoSegundo.velY > 0 && tubaraoSegundo.velX == 0) {
            	 imgAtualtubarao2 = tubaraoSegundo.baixo;
             }else if(tubaraoSegundo.velY < 0 && tubaraoSegundo.velX > 0) {
            	 imgAtualtubarao2 = tubaraoSegundo.direita_cima;
             }else if(tubaraoSegundo.velY > 0 && tubaraoSegundo.velX > 0) {
            	 imgAtualtubarao2= tubaraoSegundo.direita_baixo;
             }else if(tubaraoSegundo.velY < 0 && tubaraoSegundo.velX < 0) {
            	 imgAtualtubarao2 = tubaraoSegundo.esquerda_cima;
             }else if(tubaraoSegundo.velY > 0 && tubaraoSegundo.velX < 0) {
            	 imgAtualtubarao2 = tubaraoSegundo.esquerda_baixo;
             }else if(tubaraoSegundo.velY == 0 && tubaraoSegundo.velX > 0) {
            	 imgAtualtubarao2 = tubaraoSegundo.direita;
             }
             
             if(k_cima) {
            	 mergulhador.velY = v_padraomenor;
            	 imgAtual = mergulhador.cima;
            	 if(k_esquerdo) {
            		 mergulhador.velX = v_padraomenor;
            		 imgAtual = mergulhador.esquerda_cima;
            	 }if(k_direito) {
            		 mergulhador.velX = v_padraomaior;
            		 imgAtual = mergulhador.direita_cima;
            	 }
             }else if(k_baixo) {
            	 mergulhador.velY = v_padraomaior;
            	 imgAtual = mergulhador.baixo;
            	 if(k_esquerdo) {
            		 mergulhador.velX = v_padraomenor;
            		 imgAtual = mergulhador.esquerda_baixo;
            	 }if(k_direito) {
            		 mergulhador.velX = v_padraomaior;
            		 imgAtual = mergulhador.direita_baixo;
            	 }
             }else if(k_esquerdo) {
            	 mergulhador.velX = v_padraomenor;
            	 imgAtual = mergulhador.esquerda;
             }else if(k_direito) {
            	 mergulhador.velX = v_padraomaior;
            	 imgAtual = mergulhador.direita;
             }
        }
        public void update(){
            mergulhador.posX = mergulhador.posX + mergulhador.velX;
            mergulhador.posY = mergulhador.posY + mergulhador.velY;
            tubaraoPrimeiro.posX = tubaraoPrimeiro.posX + tubaraoPrimeiro.velX;
            tubaraoPrimeiro.posY = tubaraoPrimeiro.posY + tubaraoPrimeiro.velY;
            tubaraoSegundo.posX = tubaraoSegundo.posX + tubaraoSegundo.velX;
            tubaraoSegundo.posY = tubaraoSegundo.posY + tubaraoSegundo.velY;
            testeColisoes();

        }
        public void render(){
            repaint();
        }
        
        public void testeColisoes() {
        	
        	//muda a posicao do tubarao quando colide com as laterais da tela
        	if(mergulhador.posX + (mergulhador.raio * 2)>= Principal.LARGURA_TELA || mergulhador.posX <=0) {
        		mergulhador.posX = mergulhador.posX - mergulhador.velX;
        	}
        	if(tubaraoPrimeiro.posX+(tubaraoPrimeiro.raio*2)>= Principal.LARGURA_TELA || tubaraoPrimeiro.posX <=0){
        		tubaraoPrimeiro.velX = tubaraoPrimeiro.velX *-1;        		
        	}
        	if(tubaraoSegundo.posX+(tubaraoSegundo.raio*2)>= Principal.LARGURA_TELA || tubaraoSegundo.posX <=0){
        		tubaraoSegundo.velX = tubaraoSegundo.velX *-1;
        	}
        	if(mergulhador.posY + (mergulhador.raio * 2) >= Principal.ALTURA_TELA || mergulhador.posY <= 0) {
        		mergulhador.posY = mergulhador.posY - mergulhador.velY;
        	}
        	if(tubaraoPrimeiro.posY+(tubaraoPrimeiro.raio*2)>= Principal.ALTURA_TELA || tubaraoPrimeiro.posY <=0){
        		tubaraoPrimeiro.velY = tubaraoPrimeiro.velY *-1;
        	}
        	if(tubaraoSegundo.posY+(tubaraoSegundo.raio*2)>= Principal.ALTURA_TELA || tubaraoSegundo.posY <=0){
        		tubaraoSegundo.velY = tubaraoSegundo.velY *-1;
        	}

        	
        	int CatetoH = mergulhador.posX - tubaraoPrimeiro.posX;
        	int CatetoV = mergulhador.posY - tubaraoPrimeiro.posY;
        	double hipotenusa = Math.sqrt(Math.pow(CatetoH, 2) + Math.pow(CatetoV, 2));
        	//verifica quando ha colis�o do primeio tubarao com o mergulhador, se ouver, mostra a pontuacao e fecha o game
        	if(hipotenusa <= mergulhador.raio+tubaraoPrimeiro.raio) {
        		mergulhador.posX = mergulhador.posX - mergulhador.velX;
        		mergulhador.posY = mergulhador.posY - mergulhador.velY;
        		JOptionPane.showMessageDialog(null, "Seu tempo: " + df.format(tubaraoPrimeiro.pontuacao) + " segundos\n\n" + "Tesouros obtidos: " + tubaraoPrimeiro.pontos + "\n\nPontua��o total: " + df.format(tubaraoPrimeiro.pontos * tubaraoPrimeiro.pontuacao) , "Fim de jogo", JOptionPane.CLOSED_OPTION);
        		System.exit(0);
        	}
        	
        	//verifica quando ha colis�o do segundo tubarao com o mergulhador, se ouver, mostra a pontuacao e fecha o game
        	int CatetoH2 = mergulhador.posX - tubaraoSegundo.posX;
        	int CatetoV2 = mergulhador.posY - tubaraoSegundo.posY;
        	double hipotenusa2 = Math.sqrt(Math.pow(CatetoH2, 2) + Math.pow(CatetoV2, 2));
        	
        	if(hipotenusa2 <= mergulhador.raio+tubaraoSegundo.raio) {
        		mergulhador.posX = mergulhador.posX - mergulhador.velX;
        		mergulhador.posY = mergulhador.posY - mergulhador.velY;
        		JOptionPane.showMessageDialog(null, "Seu tempo: " + df.format(tubaraoPrimeiro.pontuacao) + " segundos\n\n" + "Tesouros obtidos: " + tubaraoPrimeiro.pontos + "\n\nPontua��o total: " + df.format(tubaraoPrimeiro.pontos * tubaraoPrimeiro.pontuacao) , "Fim de jogo", JOptionPane.CLOSED_OPTION);
        		System.exit(0);
        	}
        	
        	
        	int CatetoTesouroH = mergulhador.posX - tesouro.posX;
        	int CatetoTesouroV = mergulhador.posY - tesouro.posY;
        	double hipotenusaTesouro = Math.sqrt(Math.pow(CatetoTesouroH, 2) + Math.pow(CatetoTesouroV, 2));
        	
        	//verifica colisao com tesouro
        	if(hipotenusaTesouro <= mergulhador.raio + tesouro.raio) {
        		tubaraoPrimeiro.pontos++;
        		tesouro.posX = tesouro.aleatorio.nextInt(930);
        		tesouro.posY = tesouro.aleatorio.nextInt(500);
        	}
        	
        	int CatetoBeneficioH = mergulhador.posX - beneficio.posX;
        	int CatetoBeneficioV = mergulhador.posY - beneficio.posY;
        	double hipotenusaBeneficio = Math.sqrt(Math.pow(CatetoBeneficioH, 2) + Math.pow(CatetoBeneficioV, 2));
        	//Verifica e realiza a a��o quando pega o beneficio//bonus
        	if(hipotenusaBeneficio <= mergulhador.raio+beneficio.raio) {
        		System.out.println("Peguei o beneficio");
        		v_padraomaior = 5;
        		v_padraomenor = -5;
        		beneficio.posX = Principal.LARGURA_TELA + 20;
        		
        		Timer contador_beneficio = new Timer();
        		//tarefa respons�vel pelo volta do bonus 
        		TimerTask tarefa_cBeneficio = new TimerTask() {
        			
        			@Override
        			public void run() {
        				
        				beneficio.raio = 20;
        				beneficio.posX = beneficio.aleatorio.nextInt(Principal.LARGURA_TELA - 100);
        				beneficio.posY = beneficio.aleatorio.nextInt(Principal.ALTURA_TELA - 150);
        							
        			}
        		};
        		//tarefa respons�vel pelo term�no do bonus
        		TimerTask tarefa_cBeneficio_tempo = new TimerTask() {
        			
        			@Override
        			public void run() {
        				
        				v_padraomaior = 3;
        				v_padraomenor = -3;
        				
    
        				        							
        			}
        		};
        	//conta o tempo para o bonus aparecer novamente
        	contador_beneficio.schedule(tarefa_cBeneficio, 15000);
        	//conta o tempo para o bonus acabar
        	contador_beneficio.schedule(tarefa_cBeneficio_tempo, 7000);
        	
        	        		
        	}
        }
        
        
	// METODO SOBRESCRITO ---------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform af01 = new AffineTransform();
		
		af01.translate(bg.posX, bg.posY);
		g2d.drawImage(bg.img, af01, null);
		
		
		g.drawImage(imgAtual, mergulhador.posX, mergulhador.posY, null);
		g.drawImage(imgAtualtubarao1,tubaraoPrimeiro.posX,tubaraoPrimeiro.posY,null);
		g.drawImage(imgAtualtubarao2, tubaraoSegundo.posX, tubaraoSegundo.posY,null);
		g.drawImage(beneficio.img,beneficio.posX, beneficio.posY,null);
		g.drawImage(tesouro.img, tesouro.posX, tesouro.posY, null);
			
	}

}

