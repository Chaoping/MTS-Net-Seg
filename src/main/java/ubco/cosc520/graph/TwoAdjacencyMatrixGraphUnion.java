package ubco.cosc520.graph;

/**
 * Calculates the untion between two graphs.
 */
public class TwoAdjacencyMatrixGraphUnion
        implements TwoAdjacencyMatrixGraphOperator<Graph> {

    /**
     * Calculates the union between two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return  A new graph, which is the union of G and H.
     */
    @Override
    public Graph operate(
            final AdjacencyMatrixGraph g,
            final AdjacencyMatrixGraph h
    ) {
        return g;
    }
}
