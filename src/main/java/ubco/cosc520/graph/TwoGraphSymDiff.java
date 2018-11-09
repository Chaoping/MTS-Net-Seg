package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Calculates the symmetric difference between two Graphs.
 */
public class TwoGraphSymDiff implements TwoGraphOperator<RealMatrix> {

    /**
     * Calculates the symmetric difference between two Graphs.
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the symmetric difference of G and H.
     */
    @Override
    public RealMatrix operate(final RealMatrix g, final RealMatrix h) {
        TwoGraphOperator<Boolean> sizeComparator = new TwoGraphSizeComparator();
        if (!sizeComparator.operate(g, h)) {
            throw new IllegalArgumentException("Graphs must be the same size");
        }

        return g;
    }
}
