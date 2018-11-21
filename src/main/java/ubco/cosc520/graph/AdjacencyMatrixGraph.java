package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * A graph implementation representated as an adjacency matrix.
 */
class AdjacencyMatrixGraph implements Graph {

    /**
     * Internal representation of graph - Adjacency Matrix.
     */
    private final RealMatrix adjacencyMatrix;

    /**
     * Create the graph from a square inputMatrix of values {0,1}
     * 0 - represents no edge between nodes.
     * 1 - represents edge between nodes.
     *
     * @param inputMatrix The adjacency matrix representation.
     */
    AdjacencyMatrixGraph(final RealMatrix inputMatrix) {

        if (inputMatrix.getRowDimension() != inputMatrix.getColumnDimension()) {
            throw new IllegalArgumentException("Input matrix must be square");
        }

        for (int row = 0; row < inputMatrix.getRowDimension(); row++) {
            for (int col = 0; col < inputMatrix.getColumnDimension(); col++) {
                if (inputMatrix.getEntry(row, col) != 0d
                        && inputMatrix.getEntry(row, col) != 1d) {
                    throw new IllegalArgumentException(
                            "Input matrix must contain only 0s or 1s"
                    );
                }
            }
        }

        this.adjacencyMatrix = inputMatrix;
    }

    @Override
    public RealMatrix getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

}
