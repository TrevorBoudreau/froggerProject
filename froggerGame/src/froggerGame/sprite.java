package froggerGame;

//import java.awt.Rectangle;

/*
 * 
 * 
 * 
 * 
 */

public class sprite {

	protected int x, y;
	protected int height, width;
	protected String image;
	//protected Rectangle r;
	
	//default constructor
	public sprite() {
		super();
		
	}

	//secondary constructor
	public sprite(int x, int y, int height, int width, String image) {
		super();
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.image = image;
	}

	//auto-generated getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
