package edu.nyu.cs.cc6019;

import processing.core.PApplet;
import java.util.Random;
import processing.core.PImage;

/**
 * The hole class
 * @author Crystal Chu
 * @version 0.1
 */

public class Mole {

	//will hold a reference to the App object, which inherits from PApplet and therefore handles all of the Processing-specific stuff
	private App app; 
	
	//make sure the image file is in the src folder 
	private final static String MOLE_IMAGE_PATH = "mole.png"; //hole image file
	private PImage img; //will hold the image to use for this alien 
	private Hole hole;
	
	public int x, y; //position 
	private boolean moleAlive = true; //flag to hold whether mole has been hit or not
	private boolean isOutside = false; 
	private int frames = 60;
	private Random random = new Random(); 
	
	public Mole(int x, int y, PApplet app) { 
		//set up initial properties for the mole 
		this.app = (App) app; //keep a reference to the PApplet class to handle all Processing-specific functions and variables
		
		//set position 
		this.x = x; //x position 
		this.y = y; //y position 
		
		//load the hole image and store in PImage variable 
		this.img = app.loadImage(Mole.MOLE_IMAGE_PATH);
		this.img.resize(0, 100);
		this.hole = new Hole(x, y, app);
	}
	/**
	 * Get the status of this mole
	 * @return boolean true if alive, boolean false if dead
	 */
	
	public boolean isAlive() { 
		return this.moleAlive;
	}
	
	/**
	 * Get the width of this mole, based on the  width of its image.
	 * @return int width of this image
	 */
	public int getWidth() {
		return this.img.width; //return the PImage object's width property
	}
	
	/**
	 * Get the height of this mole, based on  the width of its image.
	 * @return int height of this image
	 */
	public int getHeight() {
		return this.img.height; //return the PImage object's height property
	}
	
	/**
	 * Called repeatedly approximately 24 times  per second (Processing's default "frame  rate").  Used to update the animation and  enforce game play logic.
	 * Controls flickering of the moles
	 */
	public void draw() { 
		hole.draw();
		this.show();
		
		if (moleAlive && isOutside) { 
			if (frames != 0)  { //still counting down
				//draw the image using PApplet's image method 
				this.app.image(this.img, this.x, this.y); 
				frames--;
			}
			else { 
				isOutside = false;
				frames = 60;
			}
		}
		
	}
	
	/**
	 * Shows the mole on the screen
	 */

	public void show() { 
		//determine randomly whether mole will appear or not 
		int randInt = random.nextInt(199);
		//if randInt = 0, then toggle isOutside 
		if (moleAlive && randInt == 0) { 
			isOutside = true;
		}
	}
	
	/**
	 * Sets status of the mole 
	 * @return boolean isOutside 
	 */
	
	public boolean isOutside() { 
		return isOutside;
	}
	
	/**
	 * Sets status of the mole to !moleAlive
	 * @return boolean moleAlive = false 
	 */
	
	public void kill() { 
		this.moleAlive = false;
	}
	
	/**
	 * Kill this mole
	 * Simply sets this moles hit attribute to true and removes it from the  PApplet's list of moles
	 */
	public void hit() {
		this.app.getMoles().remove(this); //remove  this mole from list of moles
	}
}

