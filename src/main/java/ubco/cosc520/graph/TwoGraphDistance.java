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
        Double symDiffEdgeCount = new TwoGraphSymDiffEdgeCount().operate(g, h);
        Double unionEdgeCount = new TwoGraphUnionEdgeCount().operate(g, h);


        return symDiffEdgeCount / unionEdgeCount;

    }
}
