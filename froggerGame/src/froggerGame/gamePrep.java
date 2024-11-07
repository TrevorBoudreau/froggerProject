package froggerGame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gamePrep extends JFrame {

	//declare copies of sprites
	private frogSprite frog;
	private carSprite car;
	
	//gui elements
	private Container content;
	private JLabel backgroundLabel, frogLabel;
	private ImageIcon frogImage, carImage, backgroundImage;
	
	public gamePrep() {
		
		//set up screen display
		setSize(gameProperties.SCREEN_WIDTH, gameProperties.SCREEN_HEIGHT);
		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		//display background graphic
		backgroundImage = new ImageIcon( getClass().getResource("/images/backgroundImage.png" ) );
		backgroundLabel = new JLabel();
		backgroundLabel.setIcon( backgroundImage );
		backgroundLabel.setSize( gameProperties.SCREEN_WIDTH, gameProperties.SCREEN_HEIGHT );
		backgroundLabel.setLocation( 0, 0 );
		
		//set up frog sprite
		frog = new frogSprite(gameProperties.SCREEN_WIDTH - 600, gameProperties.SCREEN_HEIGHT - 150, 100, 90,"frogSprite.png");
		frogLabel = new JLabel();
		frogImage = new ImageIcon( getClass().getResource("/images/" + frog.getImage() ) );
		frogLabel.setIcon( frogImage ); 
		frogLabel.setSize( frog.getWidth(), frog.getHeight() );
		frogLabel.setLocation( frog.getX(), frog.getY() );
		
		
		
		
		
		
		
		//populate game screen with sprites
		// !!ORDER MATTERS!!
		add(frogLabel);
		add(backgroundLabel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		gamePrep newGame = new gamePrep();
		newGame.setVisible(true);
		// KEEP MAIN TO THESE 2 LINES!! //
	}

}
 