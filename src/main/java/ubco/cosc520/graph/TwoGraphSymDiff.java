package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

public class TwoGraphSymDiff implements TwoGraphOperator<RealMatrix> {

    /**
     * Calculates the symmetric difference between two Graphs.
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the symmetric difference of G and H.
     */
    @Override
    public RealMatrix operate(RealMatrix g, RealMatrix h){
        TwoGraphOperator<Boolean> sizeComparator = new TwoGraphSizeComparator();
        if (!sizeComparator.operate(g,h)) {
            throw new IllegalArgumentException("Graphs must be of the same size");
        }

        return g;
    }
}
