package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Produces a new graph with thresholding applied.
 */
public class GraphThresholder
        extends BaseSingleGraphOperator implements SingleGraphOperator  {

    /**
     * The threshold to compare against.
     */
    private final double thresh;

    /**
     * @param threshold  The threshold to compare against.
     */
    public GraphThresholder(final double threshold) {
        this.thresh = threshold;
    }

    /**
     *
     * @param g The first graph
     * @return A graph with each cell replaced:
     *          1 if the value of the cell is >= the threshold
     *          0 otherwise.
     */
    @Override
    public RealMatrix operate(final RealMatrix g) {
        return this.operate(g, (double d) -> {
            if (d >= thresh) {
                return 1d;
            } else {
                return 0d;
            }
        });
    }
}
