package cosc520.project;

import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class Graph {
	List<Node> V;
	List<Edge> E;

	boolean[][] adjacentMatrix;
	int numNodes;
	
	// Constructor I: Create a main.java.Graph with a matrix
	Graph(double[][] rawInput, int start, int end){
		numNodes = rawInput.length;
		adjacentMatrix = new boolean[numNodes][numNodes];
		
		// threshold of correlation significance
		double alpha = 0.01;
		
		// fill in the matrix
		
		for(int i = 0; i< numNodes; i++) {
			for(int j = i+1; j < numNodes; j++) {
				adjacentMatrix[i][j] = isLinked(null, start, end, i, j);
			}
		}
		
		// fill the diagonals with 1s
		for (int i = 0; i< numNodes; i++) {
			adjacentMatrix[i][i] = true;
		}
		
		// fill the lower half using the same values
		for (int i =0; i < numNodes ; i++) {
			for(int j= 0; j<i; j++) {
				adjacentMatrix[i][j] = adjacentMatrix[j][i];
			}
		}
	}

	static boolean isLinked(double[][] rawInput, int start, int end, int i, int j) {
		
		double[][] convertedMatrix = new double[end - start + 1][2];
		if(end-start<2) {
			return true;
		}else {
			for (int c = 0; c < end - start + 1; c++) {
				convertedMatrix[c][0] = rawInput[i][c];
				convertedMatrix[c][1] = rawInput[j][c];
			}//end of for of constructing converted matrix
			
			PearsonsCorrelation pc = new PearsonsCorrelation(convertedMatrix);
			RealMatrix cMatrix=pc.getCorrelationMatrix();
			RealMatrix pMatrix=pc.getCorrelationPValues();
			
			//test part,output the calculating result of correalation and correlation pvalue
			System.out.println(pMatrix.getEntry(0,1)+" "+pMatrix.getEntry(1,0));
			System.out.println(cMatrix.getEntry(0,1)+" "+cMatrix.getEntry(1,0));
			
			//0.05 is significance level
			if(pMatrix.getEntry(0, 1)>0.05)
				return false;
			else
				return true;	
		}
	}//end of isLInked


	static Graph symDiff(Graph G, Graph H) {
		// TODO
		return null;
	}

	static Graph union(Graph G, Graph H) {
		// TODO
		return null;
	}

	static double distance(Graph G, Graph H) {
		// TODO
		return 0.0;
	}
	
	public static void main(String[] args) {
		double[][] t= {
				{1,0,-2,80},
				{2,2,2,2},
				{2,3,9,0}
		};
		
		System.out.println();
		isLinked(t, 1, 3, 0, 2);
	}
}

class Node {
	public String name;
	public List<Edge> connections;

	public List<Node> getNeighbors() {
		// TODO
		return null;
	}
}

class Edge {
	public Node a;
	public Node b;
}

