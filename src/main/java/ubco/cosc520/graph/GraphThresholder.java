package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

public class GraphThresholder extends BaseSingleGraphOperator implements SingleGraphOperator  {

    private final double threshold;

    public GraphThresholder(double threshold) {
        this.threshold = threshold;
    }

    /**
     *
     * @param g The first graph
     * @return A graph with 1 if the value of the cell is >= the threshold, 0 otherwise.
     */
    @Override
    public RealMatrix operate(RealMatrix g) {
        return this.operate(g, (double d) -> {
            if (d >= threshold) {
                return 1d;
            } else {
                return 0d;
            }
        });
    }
}
