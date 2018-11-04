import java.util.List;

public class Graph {
	int[][] adjacentMatrix;
	int numNodes;
	
	// Constructor I: Create a Graph with a matrix
	Graph(double[][] rawInput, int start, int end){
		numNodes = rawInput.length;
		adjacentMatrix = new int[numNodes][numNodes];
		
		// threshold of correlation significance
		double alpha = 0.01;
		
		// fill in the matrix
		
		for(int i = 0; i< numNodes; i++) {
			for(int j = i+1; j < numNodes; j++) {
				adjacentMatrix[i][j] = isLinked(rawInput, start, end, i, j);
			}
		}
		
		// fill the diagonals with 1s
		for (int i = 0; i< numNodes; i++) adjacentMatrix[i][i] = 1;
		
		// fill the lower half using the same values
		
		for (int i =0; i < numNodes ; i++) {
			for(int j= 0; j<i; j++) {
				adjacentMatrix[i][j] = adjacentMatrix[j][i];
			}
		}
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