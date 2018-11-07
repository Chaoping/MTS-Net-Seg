package ubco.cosc520.graph;

/**
 * A Graph, consisting of Nodes and Edges.
 */
public interface Graph {
    /**
     * Constructs a new graph from the Adjacency Matrix representation.
     * @return a new Graph instance.
     */
    Graph fromAdjacencyMatrix();
}
