package container.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {
   private Image image;
   private int x, y;
   private int largura, altura;
   private boolean isVisivel;
   
   private static final int LARGURA = 938;
   private static int VELOCIDADE = 5;
   
   public Tiro(int x, int y) {
	   this.x = x;
	   this.y = y;
	   isVisivel = true;
   }
   
   public void load() {
	   ImageIcon reference = new ImageIcon("res\\tiro-simples.png");
	   image = reference.getImage();
	   this.largura = image.getWidth(null);
	   this.altura = image.getHeight(null);
   }
   
   public void update() {
	   this.x += VELOCIDADE;
	   if(this.x > LARGURA) {
		   isVisivel= false;
	   }
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
	
	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}
	
	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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
