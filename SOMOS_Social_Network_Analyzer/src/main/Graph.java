package main;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	private int size;
	private LinkedList[] adjListArray; //graph will be stored as an adjacency list
	private int totalExistingTies = -1;
	private boolean directed; //denotes a directed or undirected graph
	
	//if size is not given use a hard coded size
	public Graph(boolean dir) {
		size = 5; //hard coded size of the graph TODO: change when there is an input
		directed = dir;
		adjListArray = new LinkedList[size];
		
		//initialize each index in the array to a linked list that will hold the indexes adjacent to the represented node
		for(int i = 0; i < size ; i++){
			adjListArray[i] = new LinkedList<>();
		}
	}
	
	//constructor with a given size graph
	public Graph(int graphSize, boolean dir) {
		directed = dir;
		size = graphSize;
		adjListArray = new LinkedList[size];
		
		//initialize each index in the array to a linked list that will hold the indexes adjacent to the represented node
		for(int i = 0; i < size ; i++){
			adjListArray[i] = new LinkedList<>();
		}
	}
	
	// Adds an edge to the graph
	public void addEdge(Graph graph, int src, int dest)
	{
		if(directed) {
			// Add an edge from src to dest. 
			graph.adjListArray[src].addFirst(dest);
		}
		else {
			// Add an edge from src to dest. 
			graph.adjListArray[src].addFirst(dest);
				
			// Since graph is undirected, add an edge from dest to src also
			graph.adjListArray[dest].addFirst(src);
		}
	}
	
	public LinkedList[] getAdjListArray() {
		return adjListArray;
	}
	
	public int getGraphSize() {
		return size;
	}
	
	//calculates the total number of ties in the network
	private void setTotalExistingTies() {
		int size = this.getGraphSize();
		int sum = 0;
		for(int i = 0; i < size; i++ ) {
			sum += adjListArray[i].size();
		}
		totalExistingTies = sum;
	}
	
	private int getTotalExistingTies() {
		if (totalExistingTies<0) {
			this.setTotalExistingTies();
		}
		return totalExistingTies;
	}
	
	//calculates the average density in the graph: existing ties / possible ties
	public double densityCalc() {
		if (directed) {
			return (double)getTotalExistingTies()/(size*(size-1));
		}
		else {
			return (double)getTotalExistingTies()/(size*(size-1)/2);
		}
	}
	

	public double averageDegreeCalc() {
		double avgOutdegree = (double)getTotalExistingTies()/size;
		return avgOutdegree;
	}
		
	public int nodeOutdegreeCalc(int node) {
		return adjListArray[node].size();
	}
	
	public int nodeIndegreeCalc(int node) {
		int sum = 0;
		for(int i = 0; i < size ; i++){
			if (adjListArray[i].contains(node)) {
				sum++;
			}
		}
		return sum;
	}
	
	//calculates freeman centralization for the graph
	public double freemanCentralization() {
		//find max degree
		int maxDegree = 0;
		for (int i = 0; i< getGraphSize(); i++) {
			if (adjListArray[i].size() > maxDegree) {
				maxDegree = adjListArray[i].size();
			}
		}
		//perform calculation for freeman centralization
		int sum = 0;
		for (int i = 0; i< getGraphSize(); i++) {
			sum += (maxDegree - adjListArray[i].size());
		}
		double result = (double) sum/((getGraphSize() - 1) * (getGraphSize() - 2) );
		return result;
	}
	
}
