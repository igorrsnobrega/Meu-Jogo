package container;

import javax.swing.JFrame;

import container.modelo.Fase;

public class Container extends JFrame{
	
	public Container() {
		configTela();
	}
	
	@Override
	public String getTitle() {
		return "Meu Jogo";
	}

	public static void main(String[] args) {
		new Container();
	}
	
	public void configTela() {
		add(new Fase());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

}
