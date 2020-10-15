package myTestingPApplet;

import processing.core.PApplet;

/** 
 * Just drawing stuffs with the processing library
 * Getting used to the PApplet interface
 * 
 * @author Oyetunji Ibrahim (SydeWalKa)
 * 
 *
 */

public class BabyFace extends PApplet {
	
	public void setup(){
		size(500,500);
		background(200,0,200);
	}
	
	public void draw(){
		//fill and draw the head
		fill(255,255,0);
		ellipse(width/2,height/2,400,400);
		//fill and draw both eyes
		fill(255,255,255);
		ellipse(width/3,height/3,50,50);
		ellipse(width/1,height/3,50,50);
		//ToDo: fill and draw the mouth
	}
}
