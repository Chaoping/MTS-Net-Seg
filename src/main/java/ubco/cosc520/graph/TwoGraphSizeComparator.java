package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Compares the size of 2 graphs.
 */
public class TwoGraphSizeComparator implements TwoGraphOperator<Boolean> {

    /**
     * Compare the size of two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return {@code true} if graphs have the same dimensions.
     *         {@code false} if graphs have different dimensions.
     */
    @Override
    public Boolean operate(final RealMatrix g, final RealMatrix h) {
        return g.getColumnDimension() == h.getColumnDimension()
                && g.getRowDimension() == h.getRowDimension();
    }
}
