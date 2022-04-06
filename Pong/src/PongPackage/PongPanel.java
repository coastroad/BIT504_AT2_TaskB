package PongPackage;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	//variables
	
	private static final Color BACKGROUND_COLOUR =  Color.BLACK;
	
	//constructor
	public PongPanel() {
		
		
		//sets the JPanel background colour to black
		setBackground(BACKGROUND_COLOUR);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
