package ubco.cosc520.matrix;


import org.apache.commons.math3.linear.RealMatrix;

/**
 * Operation which can be performed on a matrix.
 *
 * @param <T> The return type of the operation
 */
public interface TwoMatrixOperator<T> {

    /**
     * Perform the operation on the matrix.
     *
     * @param g The first matrix for the operation.
     * @param h The second matrix for the operation.
     * @return A matrix with the operation applied.
     */
    T operate(RealMatrix g, RealMatrix h);

}
