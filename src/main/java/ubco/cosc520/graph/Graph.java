package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * A graph class.
 */
public interface Graph {

    /**
     * Creates a new graph from an adjacency matrix representation.
     * @param rm The adjacency matrix representation.
     * @return The graph.
     */
    static Graph fromAdjacencyMatrix(RealMatrix rm) {
        return new AdjacencyMatrixGraph(rm);
    }

    /**
     * @return The adjacency matrix representation of the graph.
     */
    RealMatrix getAdjacencyMatrix();
}
