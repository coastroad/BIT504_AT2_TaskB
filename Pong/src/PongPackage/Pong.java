
package PongPackage;

import javax.swing.JFrame;


public class Pong extends JFrame {

	//constructor
	public Pong() {
		setTitle("Pong");
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pong();
	}

}
