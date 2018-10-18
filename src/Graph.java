import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;

public class Graph {
	List<Node> V;
	List<Edge> E;
	
	// Constructor I: Create a Graph with a matrix
	Graph(RealMatrix M){
		//TODO
	}
	
	static Graph symDiff(Graph G, Graph H) {
		//TODO
		return null;
	}
	
	static Graph union(Graph G, Graph H) {
		//TODO
		return null;
	}
	
	static double distance (Graph G, Graph H) {
		//TODO
		return 0.0;
	}
}


class Node {
    public String name;
    public List<Edge> connections;
    
    public List<Node> getNeighbors() {
    	//TODO
    	return null;
    }
}


class Edge {
	public Node a;
	public Node b;	
}