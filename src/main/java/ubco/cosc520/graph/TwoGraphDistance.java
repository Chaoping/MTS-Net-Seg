package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Calculates the distance between two graphs.
 */
public class TwoGraphDistance
        implements TwoGraphOperator<Double> {

    /**
     * Calculates the distance between two graphs.
     *
     * @param g The first graph
     * @param h The second graph
     * @return The distance between two graphs.
     */
    @Override
    public Double operate(
            final Graph g,
            final Graph h
    ) {
        RealMatrix rmg = g.getAdjacencyMatrix();
        RealMatrix rmh = h.getAdjacencyMatrix();

        int size = rmg.getColumn(0).length;
        //calculate the total number of edges for symdiff graph of g and h

        //TODO: Use the other functions to calculate the distance.
        return 0d;

        //return (double) edgeNumOfSymDiff / (double) edgeNumOfUnion;

    }
}
