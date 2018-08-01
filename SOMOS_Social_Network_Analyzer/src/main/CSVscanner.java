package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVscanner {
	private Graph graph;
	/*
	 * reads CSV into a graph
	 */
	public CSVscanner(File file, int graphSize) {
		Scanner scanner;
		graph = new Graph(graphSize, true); 
		int curSize = 1;
		boolean sizeSet = false;
		try {
			scanner = new Scanner(file);
			 scanner.useDelimiter(",");
			 int rowCounter = 0;
			 while (scanner.hasNextLine())
			 {
				 
			     String line = scanner.nextLine();
			     String[] fields = line.split(",");
			     int colCounter = 0;
			     for (String field: fields) {
			    	 //System.out.println("field: " + field);
			    	// System.out.println();
			    	 if(field.equals("1")) {
			    		 graph.addEdge(graph, rowCounter, colCounter);
			    		 //System.out.println("adding edge: " + rowCounter + ", " + colCounter);
			    	 }
			    	 colCounter++;
			    	 if (!sizeSet) {
			    		 curSize++;
			    		 //graph.setSize(curSize);
			    	 }
			     }
			     rowCounter++;
			     //System.out.println();
			     
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Graph getGraph() {
		return graph;
		
	}

}
