package ubco.cosc520.graph;


/**
 * Operations which can be performed between 2 graphs.
 * @param <T> - The return type of the operation.
 */
public interface TwoAdjacencyMatrixGraphOperator<T> {

    /**
     * Performs the operation on the two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return The result of the operation.
     */
    T operate(AdjacencyMatrixGraph g, AdjacencyMatrixGraph h);

}
