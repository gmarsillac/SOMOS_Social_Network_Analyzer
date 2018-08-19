package main;

import java.util.LinkedList;
import java.util.ListIterator;

public class DistanceCalculator {
	
	private Graph graph;
	private LinkedList[] adjListArray;
	private int[][] dists;
	private int graphSize;
	private int diameter = 0;;
	private double avgDistance = 0;
	
	public DistanceCalculator(Graph graph, boolean printMatrix) {
		this.graph = graph;
		graphSize = graph.getGraphSize();
		
	    adjListArray = graph.getAdjListArray().clone();
	    run(printMatrix);
	}
	
	//Calculate shortest distance matrix using Floyd-Warshall Algorithm 
	private void run(boolean printMatrix) {
		dists = new int[graphSize][graphSize];
		for (int i = 0; i < graphSize; i++) {
			//gives distance weight of one to edges in adjacency List
			for (Object j : adjListArray[i]) {
				dists[i][(int) j] = 1;
			}
		}
		//pick a vertex to check if its on the shortest path between source and destination
		 for (int k = 0; k < graphSize; k++)
	        {
	            // Pick all vertices as source one by one
	            for (int i = 0; i < graphSize; i++)
	            {
	                // Pick all vertices as destination for the
	                // above picked source
	                for (int j = 0; j < graphSize; j++)
	                {
	     
	                	if(i != k && dists[i][k] == 0) {
	                		dists[i][k] = 99999;
	                	}
	                	if (k != j && dists[k][j] == 0){
	                		dists[k][j] = 99999;
	                	}
	                	if(i != j && dists[i][j] == 0) {
	                		dists[i][j] = 99999;
	                	}
	                    // If vertex k is on the shortest path from
	                    // i to j, then update the value of dist[i][j]
	                    if (dists[i][k] + dists[k][j] < dists[i][j]){
	                        dists[i][j] = dists[i][k] + dists[k][j];
	                        }
	                    //System.out.println("i: "+ i + ", k:"+ k + "j: "+ j + ", " + dists[i][k] + ", " + dists[k][j] + ", " + dists[i][j]);
	                    //printSolution(dists, true, false);
	                }
	            }
	        }
		printDistanceMatrix(dists, printMatrix);
	}
	
	private void printDistanceMatrix(int dist[][], boolean printMatrix)
	    {
	        if(printMatrix) {
	        	System.out.println("The following matrix shows the shortest distances between every pair of vertices:");
	        }
	        int sum = 0;
	        int count = 0;
	        for (int i=0; i<graphSize; ++i)
	        {
	            for (int j=0; j<graphSize; ++j)
	            {
	                if (dist[i][j]==99999)
	                {
	                	if (printMatrix) {
	                		System.out.print("INF ");
	                	}
	                }
	                else {
	                	if (printMatrix) {
	                		System.out.print(dist[i][j]+"   ");
	                	}
	                    if(dist[i][j]>diameter) {
	                    	diameter = dist[i][j];
	                    }
	                    sum += dist[i][j];
	                    count++;
	                }
	            }
	            if(printMatrix) {
	            System.out.println();
	            }
	        }
	        avgDistance = (double)sum/count;
	    }
	
	public double getDistance() {
		return avgDistance;
	}
	public int getDiameter() {
		return diameter;
	}
 
 
}

