package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Calculates the symmetric difference between two Graphs.
 */
public class TwoGraphSymDiffEdgeCount
        implements TwoGraphOperator<Integer> {

    /**
     * Calculates the symmetric difference between two Graphs.
     *
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the symmetric difference of G and H.
     */
    @Override
    public Integer operate(
            final Graph g,
            final Graph h
    ) {
        RealMatrix rmg = g.getAdjacencyMatrix();
        RealMatrix rmh = h.getAdjacencyMatrix();

        int size = rmg.getColumn(0).length;

        int edgeNumOfSymDiff = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //skip dignose line
                if (j == i)
                    continue;
                if ((rmg.getEntry(i, j) == 1 || rmh.getEntry(i, j) == 1) && (rmh.getEntry(i, j) != rmg.getEntry(i, j)))
                    edgeNumOfSymDiff++;
            }
        }
        edgeNumOfSymDiff /= 2;
        return edgeNumOfSymDiff;
    }
}
