package ubco.cosc520.graph;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Returns a graph with the absolute value of each value in {@code g}.
 */
public class GraphAbsoluteValue
        extends BaseSingleGraphOperator implements SingleGraphOperator {

    /**
     * @param g The graph
     * @return A graph with the values replaced by absolute values.
     */
    @Override
    public RealMatrix operate(final RealMatrix g) {
        return this.operate(g, Math::abs);
    }
}
