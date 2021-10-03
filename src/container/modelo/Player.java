package container.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image image;
	private int altura, largura;
	private List <Tiro> tiros;
	private boolean isVisivel, isTurbo;
	private Timer timer;
	
	public Player() {
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		isTurbo = true;
		tiros = new ArrayList<Tiro>();
		timer = new Timer(5000, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isTurbo) {
			turbo();
			isTurbo = false;
		}
	}
	
	public void load() {
		ImageIcon reference = new ImageIcon("res\\player.png");
		image = reference.getImage();
		altura = image.getHeight(null);
		largura = image.getWidth(null);
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public Rectangle getBound() {
		   return new Rectangle(x,y,largura, altura);
	   }
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x + largura, y + (altura /2)));
	}
	
	public void turbo() {
		isTurbo = true;
	}
	
	public void keyPressed(KeyEvent tecla) {
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_UP) {
			dy = -3;
		}else if(cod == KeyEvent.VK_DOWN) {
			dy = 3;
		}else if(cod == KeyEvent.VK_LEFT) {
			dx = -3;
		}else if(cod == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}
	
	public void keyReleased(KeyEvent tecla) {
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_SPACE) {
			tiroSimples();
		}
		
		if(cod == KeyEvent.VK_ENTER) {
			turbo();
		}
		
		if(cod == KeyEvent.VK_UP) {
			dy = 0;
		}
		
		if(cod == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		
		if(cod == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if(cod == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public boolean isTurbo() {
		return isTurbo;
	}

}
