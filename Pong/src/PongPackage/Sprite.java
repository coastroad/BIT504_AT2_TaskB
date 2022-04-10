package PongPackage;

import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	
	
	//declare private variables
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private Color colour;
	private int initialXPosition;
	private int initialYPosition;
	
	
	
	//getters
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public int getXVelocity() {
		return xVelocity;
	}
	
	public int getYVelocity() {
		return yVelocity;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	//setters
	public void setXPosition(int x) {
		this.xPosition = x;
	}
	
	public void setYPosition(int y) {
		this.yPosition = y;
	}
	
	public void setXVelocity(int xv) {
		this.xVelocity = xv;
	}
	
	//3.17
	
	public void setXPosition(int newX, int panelWidth) {
		xPosition = newX;
		
		//xPosition is off the left of the screen, move it back to the edge of the screen.
		if(xPosition < 0) {
			xPosition = 0;
		}
		//xPosition is off the right of the screen, move it back to the edge of the screen.
		else if(xPosition + width > panelWidth) {
			xPosition = panelWidth - width;
		}
	}
	
	public void setYPosition(int newY, int panelHeight) {
		yPosition = newY;
		
		//yPosition is off the top of the screen. move it to the top of the screen
		if(yPosition < 0) {
			yPosition = 0;
		}
		
		//yPosition is off the bottom of the screen, move it to the bottom of the screen
		else if(yPosition + height > panelHeight) {
			yPosition = panelHeight - height;
		}
	}
	
	
	
	public void setYVelocity(int yv) {
		this.yVelocity = yv;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	
	//getter and setter for color
	public Color getColour() {return colour;}
	
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	
	//set initial x and y positions
	public void setInitialPosition(int newX, int newY) {
		initialXPosition = newX;
		initialYPosition = newY;
	}
	
	//reset sprite to its initial position
	public void resetToInitialPosition() {
		setXPosition(initialXPosition);
		setYPosition(initialYPosition);
	}
	
	
	//create rectangle
	public Rectangle getRectangle() {
		return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
	}
	
	

}
