package ubco.cosc520.timeseries;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 * Calculates the correlation matrix between two graphs.
 */
public class CorrelationTimeSeriesListComparator
        implements TimeSeriesListComparator {

    /**
     * Produces the correlation matrix.
     *
     * @param timeSeriesList The list of time series.
     * @return A similarity matrix.
     */
    public RealMatrix compare(final TimeSeriesList timeSeriesList) {
        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();

        PearsonsCorrelation pc = new PearsonsCorrelation(doubleMatrix);
        return pc.getCorrelationMatrix();
    }
}
