package ubco.cosc520.graph;


import org.apache.commons.math3.linear.RealMatrix;

/**
 * Operations which can be performed between 2 graphs.
 * @param <T> - The return type of the operation.
 */
public interface TwoGraphOperator<T> {

    /**
     * Performs the operation on the two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return The result of the operation.
     */
    T operate(RealMatrix g, RealMatrix h);

}
