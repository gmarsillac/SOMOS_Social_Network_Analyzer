package main;

import java.util.LinkedList;

/*
 * Finds the number of components in the graph and also calculates component ratio
 */
public class ComponentCalculator {
	
	private boolean[] visited;
	private LinkedList[] adjListArray;	
	private int componentCount;
	
	public ComponentCalculator(Graph graph) {
			visited = new boolean[graph.getGraphSize()];
			adjListArray = graph.getAdjListArray().clone();
			componentCount = 0;
			for (int v = 0; v < graph.getGraphSize(); v++) {
				if (!visited[v]) {
					DFS(v);
					componentCount++;
				}
			}
        }
	
	/*
	 * recursive depth first search
	 */
	private void DFS(int v) {
		visited[v] = true;
		for(Object j : adjListArray[v]) {
			if(!visited[(int)j]) {
				DFS((int)j);
			}
		}
	}
	
	public int getComponentCount() {
		return componentCount;
	}
	
	public double getComponentRatio() {
		return (double)componentCount/adjListArray.length;
	}
	
}
