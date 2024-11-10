package froggerGame;

import javax.swing.JLabel;

public class logSprite extends sprite implements Runnable {
	
	private Boolean isMoving;
	private Thread thread;
	private JLabel logLabel;
	private frogSprite frog;
	private JLabel frogLabel;
	private int stepSpeed, stepDirection;

	public logSprite() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public logSprite(int x, int y, int height, int width, String image, Boolean isMoving) {
		super(x, y, height, width, image);
		this.isMoving = isMoving;
		// TODO Auto-generated constructor stub
	}
	
	public Boolean getIsMoving() {
		return isMoving;
	}

	public void setIsMoving(Boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public JLabel getLogLabel() {
		return logLabel;
	}

	public int getStepDirection() {
		return stepDirection;
	}
	public void setStepDirection(int stepDirection) {
		this.stepDirection = stepDirection;
	}
	public void setLogLabel(JLabel logLabel) {
		this.logLabel = logLabel;
	}
	
	public frogSprite getFrog() {
		return frog;
	}

	public void setFrog(frogSprite frog) {
		this.frog = frog;
	}

	public JLabel getFrogLabel() {
		return frogLabel;
	}

	public void setFrogLabel(JLabel frogLabel) {
		this.frogLabel = frogLabel;
	}

	public int getStepSpeed() {
		return stepSpeed;
	}

	public void setStepSpeed(int stepSpeed) {
		this.stepSpeed = stepSpeed;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("log tick");
		while (this.getIsMoving() == true) {
			
			int temp = this.getX();
			
			//set direction of moving log
			if ( getStepDirection() == 1 ) {
				 temp += stepSpeed;
			} else if (getStepDirection() == 2 ) {
				 temp -= stepSpeed;
			}
			
			//check if log goes offscreen and wrap to other side
			if ( temp >= gameProperties.SCREEN_WIDTH && stepDirection == 1 ) {
				temp = this.getWidth() * -1;
			} else if ( temp + this.getWidth() <= 0 && stepDirection == 2 ) {
				temp = gameProperties.SCREEN_WIDTH;
			}
			
			//update location of car and label
			this.setX(temp);
			logLabel.setLocation( this.getX(), this.getY() );
			
			//collision text
			this.detectCollision();
			
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			
	}
	
	//method to start thread
	public void runThread() {
			
		if ( this.getIsMoving() == false ) {
			System.out.println("log thread");
			this.setIsMoving(true);
			thread = new Thread(this, "log thread");
			thread.start();
		}
			
	}
	

	private void detectCollision() {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}
