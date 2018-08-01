package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Uses Tarjan's algorithm to calculate the number of components
 * Also calculates the component ratio
 */
public class ComponentCalculator {

	//private Graph graph;
	private Stack<Integer> stack;
	private int[] low; //smallest index of any node known to be reachable from the current search root
	private boolean[] visited; //marks nodes as visited/unvisited
	private int count; 
	private int index; //index value assigned to nodes
	private int graphSize; //number of nodes in graph
	private List<List<Integer>> compList; //list of lists of nodes that make up components
	
	public ComponentCalculator(Graph graph) {
		//this.graph = graph;
		tarjansAlgorithm(graph);
        }
	
	private void tarjansAlgorithm(Graph graph){
		graphSize = graph.getGraphSize();
		index = 0;
		count = 0;
		stack = new Stack<Integer>();
		low = new int[graphSize];
		visited = new boolean[graphSize];
		compList = new ArrayList<>();
		for (int v = 0; v < graphSize; v++) {
			if(!visited[v]) {
				strongConnect(v);
			}
		}
	}
	
	private void strongConnect(int v) {
		/*
		 * v.index := index
    v.lowlink := index
    index := index + 1
    S.push(v)
    v.onStack := true
		 */
		low[v] = count++;
		visited[v] = true;
		stack.push(v);
		int min = low[v];
		
		
        for (int w = 0; w < graphSize; w++) {
            if (!visited[w]) {
                strongConnect(w);
            }
            
            if (low[w] < min){
                min = low[w];
            }
        }
        
        if (min < low[v]) { 
            low[v] = min; 
            return; 
        }
        List<Integer> component = new ArrayList<Integer>();
        int w;
        do
        {
            w = stack.pop();
            component.add(w);
            low[w] = graphSize;                

        } while (w != v);
        compList.add(component);
        System.out.println(compList); 
	}
	
	public List<List<Integer>> getComponents(){
		return compList;
	}
	
	public void printComponents() {
		System.out.println(compList);
	}
}
