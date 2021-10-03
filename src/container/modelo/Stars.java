package container.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {
   private Image image;
   private int x, y;
   private int largura, altura;
   private boolean isVisivel;
   
   private static int VELOCIDADE = 3;
   
   public Stars(int x, int y) {
	   this.x = x;
	   this.y = y;
	   isVisivel = true;
   }
   
   public void load() {
	   ImageIcon reference = new ImageIcon("res\\stars.png");
	   image = reference.getImage();
	   this.largura = image.getWidth(null);
	   this.altura = image.getHeight(null);
   }
   
   public void update() {
	   if(this.x < 0) {
		   this.x = largura;
		   Random a = new Random();
		   int m = a.nextInt(300);
		   this.x = m + 800;
		   Random r = new Random();
		   int n = r.nextInt(300);
		   this.y = n;
	   }else {
		   this.x -= VELOCIDADE;
	   }
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
