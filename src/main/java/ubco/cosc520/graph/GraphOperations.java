package ubco.cosc520.graph;


/**
 * Operations which can be performed between 2 graphs.
 */
public interface GraphOperations {

    /**
     * Calculates the symmetric difference between two Graphs.
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the symmetric difference of G and H.
     */
    Graph symDiff(Graph g, Graph h);


    /**
     * Calculates the union between two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return  A new graph, which is the union of G and H.
     */
    Graph union(Graph g, Graph h);


    /**
     * Calculates the distance between two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the distance between two graphs.
     */
    double distance(Graph g, Graph h);

}
