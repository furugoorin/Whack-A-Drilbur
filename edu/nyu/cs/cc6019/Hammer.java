package edu.nyu.cs.cc6019;

import processing.core.PApplet; 
import processing.core.PImage;

public class Hammer {
	
	/**
	 * 
	 * This class represents the hammer
	 * @author Crystal Chu
	 * @version 0.1
	 *
	 */
	
	//will hold a reference to the App object, which inherits from PApplet and therefore handles all the  Processing-specific stuff
	PApplet app;
	//make sure the image file is in the src  folder 
	private final static String HAMMER_IMAGE_PATH = "hammer.png"; //image file
	private PImage img; //will hold the image to use for the hammer 
	
	private int x, y; //position 
	private int speedX = 15; //speed in x direction, start out going to the right 
	private int speedY = 15; //speed in y direction, the y position is changed by this amount every time the hammer moves up/down
	
	
	public Hammer(PApplet app) { 
		//set up initial properties for this hammer 
		this.app = app; //keep a reference to the PApplet class to handle all Processing-specific functions and variables
		
		//load the image and store in PImage variable 
		this.img = this.app.loadImage(Hammer.HAMMER_IMAGE_PATH);
		
		//position it on the screen 
		this.x =  //x position centered on screen 
		this.y = this.app.height - App.APP_MARGIN - this.getHeight(); //y position close to the bottom of screen
	}
	
	/**
	 * Get the width of this hammer, based on  the width of its image.
	 */
	public int getWidth() {
		return this.img.width;
	}
	
	/**
	 * Get the height of this hammer, based  on the width of its image.
	 */
	public int getHeight() {
		return this.img.height;
	}

	/**
	 * Sets by how much this hammer moves  each frame.
	 * Setter for speedX property.
	 */
	
	public void setSpeed(int speedX) { 
		this.speedX = speedX;
	}
	
	/**
	 * Draws hammer to the PApplet screen.  Each hammer draws itself to the main app screen in this way.
	 */
	public void draw() { 
		//draw the image using Papplet's image method 
		this.app.image(this.img, this.x, this.y);
	}
	
	
	/**
	 * Set speed such that the hammer moves  to the right.
	 */
	public void goRight() {
		//set speed to a positive value
		int testWidth = this.x + speedX;
		if (testWidth < this.app.width - this.getWidth() - App.APP_MARGIN) { 
			this.x = testWidth;
		}
	}

	/**
	 * Set speed such that the hammer moves to the right.
	 */
	public void goLeft() {
		//set speed to a negative value
		int testWidth = this.x - speedX;
		if (testWidth > -(App.APP_MARGIN)) { 
			this.x = testWidth;
		}
	}

	/**
	 * Set speed such that the hammer moves to up.
	 */
	public void goUp() {
		//set speed to a negative value
		int testHeight = this.y - Math.abs(this.speedY);
		if (testHeight > 0) {
			this.y = testHeight;
		}	
	}
	
	/**
	 * Set speed such that the hammer moves to down.
	 */
	public void goDown() {
		//set speed to a negative value 
		int testHeight = this.y + Math.abs(this.speedY);
		if (testHeight < this.app.height - this.getHeight()) { 
			this.y = testHeight;
		}
	}
	
	/**
	 * Detects if the mole has been hit 
	 * @return boolean true if mole has been hit
	 */
	
	public boolean detectHit(Mole mole) { 
		
		if (this.x >= mole.x && this.x <= mole.x + mole.getWidth() && mole.isAlive()) {
			if (this.y <= mole.y && this.y >= mole.y - mole.getHeight()) { 
				return true;
				
			}
		}
		return false;
	}
	
	
	//public void hit() { 
		//
	//}
	

}

