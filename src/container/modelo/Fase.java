package container.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Stars> stars;
	private boolean emJogo;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon reference = new ImageIcon("res\\bg-black.png");
		fundo = reference.getImage();
		player = new Player();
		player.load();
		addKeyListener(new TecladoAdapter());
		timer = new Timer(5, this);
		timer.start();
		inicializaInimigos();
		inicializaEstrelas();
		emJogo = true;
	}

	public void inicializaInimigos() {
		int cordenadas[] = new int[40];
		enemy1 = new ArrayList<Enemy1>();
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 800);
			int y = (int) (Math.random() * 600 + 0);
			enemy1.add(new Enemy1(x, y));
		}
	}
	
	public void inicializaEstrelas() {
		int cordenadas[] = new int[100];
		stars = new ArrayList<Stars>();
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 1050 + 800);
			int y = (int) (Math.random() * 768 + 0);
			stars.add(new Stars(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		if (emJogo) {

			graphics.drawImage(fundo, 0, 0, null);
			
			for (int k = 0; k < stars.size(); k++) {
				Stars s = stars.get(k);
				s.load();
				graphics.drawImage(s.getImage(), s.getX(), s.getY(), this);
			}
			
			graphics.drawImage(player.getImage(), player.getX(), player.getY(), this);

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graphics.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			for (int j = 0; j < enemy1.size(); j++) {
				Enemy1 in = enemy1.get(j);
				in.load();
				graphics.drawImage(in.getImage(), in.getX(), in.getY(), this);
			}
		}else {
			ImageIcon gameOver = new ImageIcon("res\\bg-game-over.png");
			graphics.drawImage(gameOver.getImage(), 0, 0, null);
		}

		g.dispose();
	}

	public void checarColisao() {
		Rectangle formaNave = player.getBound();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		
		for(int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBound();
			if(formaNave.intersects(formaEnemy1)) {
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false;
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for(int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBound();
			for(int k = 0; k < enemy1.size(); k++) {
				Enemy1 tempEnemy1 = enemy1.get(k);
				
				formaEnemy1 = tempEnemy1.getBound();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		if(player.isTurbo()) {
			timer.setDelay(1);
		}else {
			timer.setDelay(5);
		}
		
		for(int k = 0; k < stars.size(); k++) {
			Stars on = stars.get(k);
			if(on.isVisivel()) {
				on.update();
			}else {
				stars.remove(k);
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}

		for (int j = 0; j < enemy1.size(); j++) {
			Enemy1 in = enemy1.get(j);

			if (in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(j);
			}
		}
		checarColisao();
		repaint();
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}
}
