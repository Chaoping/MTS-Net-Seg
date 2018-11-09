package ubco.cosc520.graph;


import org.apache.commons.math3.linear.RealMatrix;

/**
 * Operation which can be performed on a graph.
 */
public interface SingleGraphOperator {

    RealMatrix operate(RealMatrix g);

}
