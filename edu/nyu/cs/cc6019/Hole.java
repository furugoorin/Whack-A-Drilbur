package edu.nyu.cs.cc6019;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The hole class
 * @author Crystal Chu
 * @version 0.1
 */

public class Hole {
	
	//will hold a reference to the App object, which inherits from PApplet and therefore handles all of the Processing-specific stuff
	private App app; 
	
	//make sure the image file is in the src folder 
	private final static String HOLE_IMAGE_PATH = "hole.png"; //hole image file
	private PImage img; //will hold the image to use for this alien 
	

	
	public int x, y; //position 
	
	public Hole(int x, int y, PApplet app) { 
		//set up initial properties for the hole 
		this.app = (App) app; //keep a reference to the PApplet class to handle all Processing-specific functions and variables
		
		//set position 
		this.x = x; //x position 
		this.y = y; //y position 
		
		//load the hole image and store in PImage variable 
		this.img = app.loadImage(Hole.HOLE_IMAGE_PATH);
		this.img.resize(0, 100);
		
	}
	
	/**
	 * Get the dimensions of this hole
	 * @return dimension of hole
	 */
	
	public void getSize() { 
		this.img.resize(0, 50);
	}
	
	
	/**
	 * Get the width of this hole, based on the  width of its image.
	 * @return int width of this image
	 */
	//public int getWidth() {
		//return this.img.width; //return the PImage object's width property
	//}
	
	/**
	 * Get the height of this hole, based on  the width of its image.
	 * @return int height of this image
	 */
	//public int getHeight() {
		//return this.img.height; //return the PImage object's height property
	//}
	

	
	/**
	 * Draws this hole to the PApplet screen.   Each hole draws itself to the main app  screen in this way.
	 */
	public void draw() {
		//draw the image using PApplet's  image method
		this.app.image(this.img, this.x, this.y);
	} 

}
	



