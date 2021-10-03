package container.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy1 {
   private Image image;
   private int x, y;
   private int largura, altura;
   private boolean isVisivel;
   
   private static int VELOCIDADE = 3;
   
   public Enemy1(int x, int y) {
	   this.x = x;
	   this.y = y;
	   isVisivel = true;
   }
   
   public void load() {
	   ImageIcon reference = new ImageIcon("res\\enemy1.png");
	   image = reference.getImage();
	   this.largura = image.getWidth(null);
	   this.altura = image.getHeight(null);
   }
   
   public void update() {
	   this.x -= VELOCIDADE;
   }
   
   public Rectangle getBound() {
	   return new Rectangle(x,y,largura, altura);
   }

	public boolean isVisivel() {
		return isVisivel;
	}
	
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
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
	
	
   
}
