package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

public class TwoGraphDistance implements TwoGraphOperator<Double> {

    /**
     * Calculates the distance between two graphs.
     * @param g The first graph
     * @param h The second graph
     * @return A new graph, which is the distance between two graphs.
     */
    @Override
    public Double operate(RealMatrix g, RealMatrix h){
        return 0d;
    }
}
