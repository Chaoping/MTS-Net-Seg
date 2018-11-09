package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

public class TwoGraphUnion implements TwoGraphOperator {

    /**
     * Calculates the union between two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return  A new graph, which is the union of G and H.
     */
    @Override
    public RealMatrix operate(RealMatrix g, RealMatrix h) {
        TwoGraphOperator<Boolean> sizeComparator = new TwoGraphSizeComparator();
        if (!sizeComparator.operate(g,h)) {
            throw new IllegalArgumentException("Graphs must be of the same size");
        }

        return g;
    }
}
