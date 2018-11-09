package ubco.cosc520.graph;


import org.apache.commons.math3.linear.RealMatrix;

/**
 * Operation which can be performed on a graph.
 */
public interface SingleGraphOperator {

    /**
     * Perform the operation on the matrix.
     * @param g The matrix for the operation.
     * @return A matrix with the operation applied.
     */
    RealMatrix operate(RealMatrix g);

}
