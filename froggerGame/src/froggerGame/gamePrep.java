package froggerGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class gamePrep extends JFrame implements KeyListener, ActionListener {
	
	//GUI elements
	//sprites, labels, icons
	private Container content;
	private frogSprite frog;
	private carSprite car[][];
	private JLabel backgroundLabel, frogLabel, carLabel[][];
	private ImageIcon frogImage, carImage, carImageFlipped, backgroundImage;
	//button
	private JButton restartBtn;
	
	public gamePrep() {
		
		//set up screen display
		setSize(gameProperties.SCREEN_WIDTH, gameProperties.SCREEN_HEIGHT);
		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		//display background graphic
		backgroundImage = new ImageIcon( getClass().getResource(gameProperties.BACKGROUND_IMAGE ) );
		backgroundLabel = new JLabel();
		backgroundLabel.setIcon( backgroundImage );
		backgroundLabel.setSize( gameProperties.SCREEN_WIDTH, gameProperties.SCREEN_HEIGHT );
		backgroundLabel.setLocation( 0, 0 );
		
		//set up frog sprite
		frog = new frogSprite(400, 800, 100, 90, gameProperties.FROG_IMAGE);
		frogLabel = new JLabel();
		frogImage = new ImageIcon( getClass().getResource( frog.getImage() ) );
		frogLabel.setIcon( frogImage ); 
		frogLabel.setSize( frog.getWidth(), frog.getHeight() );
		frogLabel.setLocation( frog.getX(), frog.getY() );
		
		//set up a multi-array of car sprites
		carImage = new ImageIcon( getClass().getResource(gameProperties.CAR_IMAGE) ) ;
		carImageFlipped = new ImageIcon( getClass().getResource(gameProperties.CAR_IMAGE_FLIPPED) );
		car = new carSprite[4][3];
		carLabel = new JLabel[4][3];
			//will loop through all the cars
		for ( int i = 0; i < car.length; i++ ) {
			int temp = 300;//temp local variable for adjusting height during car initialization
			
			for ( int j = 0; j < car[i].length; j++ ) {
				
				car[i][j] = new carSprite( (i * 300), gameProperties.SCREEN_HEIGHT - temp, 100, 100, gameProperties.CAR_IMAGE, false);
				car[i][j].setFrog(frog);
				carLabel[i][j] = new JLabel();
				
				if (j != 1 ) {
					car[i][j].setStepSpeed(gameProperties.STEP_FAST);
					car[i][j].setStepDirection(1);
					carLabel[i][j].setIcon(carImage);
				} else {
					car[i][j].setStepSpeed(gameProperties.STEP_SLOW);
					car[i][j].setStepDirection(2);
					carLabel[i][j].setIcon(carImageFlipped);
				}
				
				carLabel[i][j].setSize( car[i][j].getWidth(), car[i][j].getHeight() );
				carLabel[i][j].setLocation( car[i][j].getX(), car[i][j].getY() );
				
				car[i][j].setCarLabel( carLabel[i][j] );
				car[i][j].setFrogLabel(frogLabel);
				
				temp += 100;
			}
		}	
		
		//set up a multi-array of log sprites
		
		//set up restart button
		restartBtn = new JButton("Continue?");
		restartBtn.setSize(100,100);
		restartBtn.setLocation(gameProperties.SCREEN_WIDTH - 150, gameProperties.SCREEN_HEIGHT - 175);
		restartBtn.setBackground(Color.BLACK);
		restartBtn.setForeground(Color.GREEN);
		restartBtn.setFocusable(false);
		restartBtn.setVisible(false);
		restartBtn.addActionListener(this);
		
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		//populate game screen with sprites
		// !!ORDER MATTERS!!
		add(frogLabel);
		for ( int i = 0; i < car.length; i++ ) {
			for ( int j = 0; j < car[i].length; j++ ) {
				add( carLabel[i][j] );
			}
		}
		add(restartBtn);
		add(backgroundLabel);
		
		//start car and log threads
		for ( int i = 0; i < car.length; i++ ) {
			for ( int j = 0; j < car[i].length; j++ ) {
				car[i][j].runThread();
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		gamePrep newGame = new gamePrep();
		newGame.setVisible(true);
		// KEEP MAIN TO THESE 2 LINES!! //
	}
	
	public void gameWin() {
		//stop ongoing threads
		for ( int i = 0; i < car.length; i++ ) {
			for ( int j = 0; j < car[i].length; j++ ) {
				car[i][j].stopThread();
			}
		}
		
		//prevent player from moving
		content.setFocusable(false);
		
		//show visibility button
		restartBtn.setVisible(true);
		
		//update score
		
	}
	
	public void gameLose() {
		//stop ongoing threads
		for ( int i = 0; i < car.length; i++ ) {
			for ( int j = 0; j < car[i].length; j++ ) {
				car[i][j].stopThread();
			}
		}
				
		//prevent player from moving
		content.setFocusable(false);
				
		//show visibility button
		restartBtn.setVisible(true);
		
		//update score
		
	}
	
	public void gameStart() {
		
		//let frog be controllable
		content.setFocusable(true);
		content.requestFocusInWindow();  //DOES NOT WORK WITHOUT THIS LINE!!
		
		//hide visibility button
		restartBtn.setVisible(false);
		
		//reset frogs position to start
		frog.setX(400);
		frog.setY(800);
		frogLabel.setLocation(frog.getX(), frog.getY());
		
		//restart threads for cars and logs
		for ( int i = 0; i < car.length; i++ ) {
			int temp = 300;//temp local variable for adjusting height during car initialization
			
			for ( int j = 0; j < car[i].length; j++ ) {
				car[i][j].setX(i * 300);
				car[i][j].setY(gameProperties.SCREEN_HEIGHT - temp);
				car[i][j].setFrog(frog);
				car[i][j].setCarLabel( carLabel[i][j] );
				car[i][j].setFrogLabel(frogLabel);
				
				carLabel[i][j].setLocation( car[i][j].getX(), car[i][j].getY() );
				
				car[i][j].runThread();
				
				temp += 100;
			}
		}
		
		frogLabel.setIcon( frogImage );
		
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//IF ANY CAR HAS STOPPED, END GAME
		//temp variable to break out of nested loop
		boolean breakOut = false;
		
		for ( int i = 0; i < car.length; i++ ) {
			for ( int j = 0; j < car[i].length; j++ ) {
			
				if ( car[i][j].getIsMoving() == false ) {
					gameLose();
					
					breakOut = true;
					break;
				}
				
				if (breakOut == true) { break; }
			}
		}
		
		if (breakOut == false) {
		
			//current x and y of frog before step
			int x = frog.getX();
			int y = frog.getY();
			
			//new x or y for each direction key (UP, DOWN, LEFT, RIGHT)
			if ( e.getKeyCode()==KeyEvent.VK_UP) {
				
				//MOVE UP ONE STEP
				y -= gameProperties.STEP;
				
				//Wrap character to other side if he goes off screen
				if (y + frog.getHeight() < 0) { y = gameProperties.SCREEN_HEIGHT; }
				
			} else if ( e.getKeyCode()==KeyEvent.VK_DOWN) {
				
				//MOVE DOWN ONE STEP
				y += gameProperties.STEP;
				
				//Wrap character to other side if he goes off screen
				if (y >= gameProperties.SCREEN_HEIGHT) { y = -1 * frog.getHeight(); }
				
			} else if ( e.getKeyCode()==KeyEvent.VK_LEFT) {
		
				//MOVE LEFT ONE STEP
				x -= gameProperties.STEP;
				
				//Wrap character to other side if he goes off screen
				if (x + frog.getWidth() < 0) { x = gameProperties.SCREEN_WIDTH; }
				
			} else if ( e.getKeyCode()==KeyEvent.VK_RIGHT) {
				
				//MOVE RIGHT ONE STEP
				x += gameProperties.STEP;
				
				//Wrap character to other side if he goes off screen
				if (x >= gameProperties.SCREEN_WIDTH) { x = -1 * frog.getWidth(); }
				
			} else {
				
				//for all other keys, DONT move character 
				return;
			}
			
			//move the frog to new spot with new x and y
			frog.setX(x);
			frog.setY(y);
			
			//System.out.println("frog x: " + frog.getX() + " hitbox x: " + frog.getHitboxX() + "frog y: " + frog.getY() + " hitbox y: " + frog.getHitboxY());	
			
			//move the label with it
			frogLabel.setLocation( frog.getX() , frog.getY() );
			
		}
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("btn clicked");
		
		if (e.getSource() == restartBtn){
			gameStart();
		}
		
	}
		
	

}
 