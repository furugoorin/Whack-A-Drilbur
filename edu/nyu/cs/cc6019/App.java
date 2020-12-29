package edu.nyu.cs.cc6019;

import java.util.ArrayList; 

import edu.nyu.cs.cc6019.Hole; 
import edu.nyu.cs.cc6019.Hammer; 
import edu.nyu.cs.cc6019.Mole; 
import processing.core.*;

public class App extends PApplet {
	
	/**
	 * Basic controller for Whack the Mole
	 * @author Crystal Chu
	 * @version 0.1
	 */
	
	//window size of the app 
	private final int w = 800;
	private final int h = 700; 
	private int molesHit = 0;
	private int time = 30 * 1000;
	private int timeRemain = time / 1000;
	private boolean gameOver = false;
	
	
	//make constants for some common colors 
	public final int BLACK = this.color(0,0,0);
	public final int WHITE = this.color(255,255,255);
	
	//make constants for some common spacing 
	public final static int HOLE_SPACING = 70; 
	public final static int APP_MARGIN = 15; 
	public final static int NUM_HOLES = 12;
	
	//variable to hold the hammer 
	private Hammer hammer; 
	
	//make sure the image file is in the src  folder 
	private final static String BACKGROUND_IMAGE_PATH = "background.jpg"; //image file
	private PImage img; //will hold the image to use for the background

	
	//an array list that will hold the moles currently on the screen 
	private ArrayList<Mole> moles = new ArrayList<Mole>(); 
	
	//an array list that will hold the moles that are still alive 
	private ArrayList<Mole> stillAlive = new ArrayList<Mole>();
	
	
	//setters and getters 

	
	/**
	 * Getter for the ArrayList of Mole objects currently on the screen
	 * @return ArrayList of Mole objects
	 */
	
	public ArrayList<Mole> getMoles() { 
		return this.moles;
	}
	
	/**
	 * Called once to set up window
	 */
	
	public void settings() { 
		this.size(this.w, this.h); //set window size 
	}
	
	/**
	 * Called once on load. Used to create the  window and "global" settings.
	 */
	
	public void setup() { 
		
		//initialize hammer 
		this.hammer = new Hammer(this); //pass reference to this App object
		
		//initialize all holes 
		int x = App.APP_MARGIN + 20; //x position of the first hole 
		int y = App.APP_MARGIN; //y position of the first hole 
		
		//loop as many times are there are holes 
		for (int i = 0; i < App.NUM_HOLES; i++) { 
			
			//create a new hole for each element of the array 
			Mole moles = new Mole(x, y, this); //pass the x,y coords and a reference to this App class 
			this.stillAlive.add(moles); //add hole to array list 
			
			//update x so the next hole we draw appears further to the right
			x += moles.getWidth() + App.HOLE_SPACING;
			
			//stay within bounds of the screen 
			if (x > this.width - moles.getWidth() - App.APP_MARGIN) { 
				//move down the next line before drawing next hole 
				x = App.APP_MARGIN + 20; //reset back to left side of screen 
				y += moles.getHeight() + App.HOLE_SPACING; //update y so the next row of holes appears further down the screen
				
			}
		}
	}
	
	/**
	 * Called repeatedly approximately 24 times  per second (Processing's default "frame  rate").  Used to update the animation and  enforce game play logic.
	 */
	
	public void draw() { 
		//load the image and store in PImage variable 
		this.img = this.loadImage(this.BACKGROUND_IMAGE_PATH);
		this.background(this.img); //sets background 
		
		//draw the moles
		for (Mole mole : stillAlive) {
			mole.draw();
			if (mole.isOutside()) {
				this.moles.add(mole);
			}
			else if (moles.contains(mole)) {
				moles.remove(mole);
			}
		}
		
		//draw the hammer 
		this.hammer.draw(); //have the hammer draw itself to the screen 
		
		textSize(32);
		fill(255);
		String s = "You hit " + molesHit + " moles";
		text(s, w/2, h/2); //display how many moles were hit
		
		s = "Time remaining: ";
		
		
		if (!gameOver) { 
			timeRemain = (time - millis()) / 1000;
		}
		
		text(s + timeRemain, APP_MARGIN + 30, APP_MARGIN + 30);
		
		if (timeRemain == 0) { 
			text("GAME OVER", w/2 + 50, h/2 + 50);
			gameOver = true;
		}
	}
	
	/**
	 * Detects key presses
	 */
	
	public void keyPressed() { 
		if (!gameOver) {
			//the variable key holds a char representing the key that was pressed 
			
			if (this.key == PConstants.CODED) { 
				switch (this.keyCode) { 
				case PConstants.LEFT: 
					this.hammer.goLeft(); //turn left 
					break; 
				case PConstants.RIGHT: 
					this.hammer.goRight(); //turn right 
					break; 
				case PConstants.UP: 
					this.hammer.goUp(); //turn up 
					break; 
				case PConstants.DOWN: 
					this.hammer.goDown(); //turn down
					break; 
				}
			}
			//space key does not have a code 
			else if (key == ' ') { 
				//handle space key 
				//check if the hammer is at the position of the mole (within boundaries of the hole)
				Mole dead = null;
				
				for (Mole mole : moles) {
					if (this.hammer.detectHit(mole)) { 
						mole.kill();
						
						this.stillAlive.remove(mole);
						dead = mole;
						
					}
				}
				if (dead != null) { 
					moles.remove(dead);
					molesHit++;
					
				}
				
			}
		}
		
	}
	
		
	
	

	/**
	 * Automatically called to start your program.  This method calls PApplet's main method and passes it the class name of this class.
	 * @param args Command-line arguments (ignored)
	 */
	
	
	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.cc6019.App");

	}

}
