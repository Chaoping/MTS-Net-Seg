package ubco.cosc520.graph;


import org.apache.commons.math3.linear.RealMatrix;

import java.util.function.DoubleUnaryOperator;

/**
 * Common lambda to apply operation to each cell in a graph.
 */
abstract class BaseSingleGraphOperator {

    /**
     * Performs the operation on the graph.
     * @param g The first graph
     * @return The result of the operation.
     */
    RealMatrix operate(RealMatrix g, DoubleUnaryOperator operator) {
        RealMatrix r = g.copy();
        for (int row = 0; row < r.getRowDimension(); row++) {
            for (int col = 0; col < r.getColumnDimension(); col++) {
                r.setEntry(row, col, operator.applyAsDouble(r.getEntry(row,col)));
            }
        }
        return r;
    }

}
