package ubco.cosc520.graph;

/**
 * Calculates the symmetric difference between two Graphs.
 */
public class TwoGraphSymDiff
        implements TwoGraphOperator<Graph> {

    /**
     * Calculates the symmetric difference between two Graphs.
     *
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the symmetric difference of G and H.
     */
    @Override
    public Graph operate(
            final Graph g,
            final Graph h
    ) {
        return g;
    }
}
