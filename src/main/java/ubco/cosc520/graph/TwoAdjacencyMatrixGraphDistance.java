package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Calculates the distance between two graphs.
 */
public class TwoAdjacencyMatrixGraphDistance
        implements TwoAdjacencyMatrixGraphOperator<Double> {

    /**
     * Calculates the distance between two graphs.
     *
     * @param g The first graph
     * @param h The second graph
     * @return The distance between two graphs.
     */
    @Override
    public Double operate(
            final AdjacencyMatrixGraph g,
            final AdjacencyMatrixGraph h
    ) {
        RealMatrix rmg = g.getAdjacencyMatrix();
        RealMatrix rmh = h.getAdjacencyMatrix();

        int size = rmg.getColumn(0).length;
        //calculate the total number of edges for symdiff graph of g and h
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


        //calculate total number of edges for union graph of g and h
        int edgeNumOfUnion = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //skip dignose line
                if (j == i)
                    continue;
                if ((rmg.getEntry(i, j) == 1 || rmh.getEntry(i, j) == 1))
                    edgeNumOfSymDiff++;
            }
        }
        edgeNumOfUnion /= 2;

        return (double) edgeNumOfSymDiff / (double) edgeNumOfUnion;
    }
}
