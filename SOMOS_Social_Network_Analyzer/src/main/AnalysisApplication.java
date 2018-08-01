package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

/**
 * @author Gustavo Marsillac
 * 
 * This class is a wrapper class to startup the Social Network Analysis
 * 
 *calls GUI
 */
public class AnalysisApplication {
	
	//creates program instance
	public AnalysisApplication() {
		init();
	}
	
	private void init() {
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		 // Creating a File object that represents the disk file.
        PrintStream o = new PrintStream(new File("SNA_output.txt"));
 
        // Store current System.out before assigning a new value
        PrintStream console = System.out;
 
        // Assign o to output stream
        System.setOut(o);
		
		GUI gui = new GUI();
		gui.runGUI();
	}
}
