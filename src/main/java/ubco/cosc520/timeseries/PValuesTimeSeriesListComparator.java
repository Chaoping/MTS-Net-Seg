package ubco.cosc520.timeseries;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class PValuesTimeSeriesListComparator implements TimeSeriesListComparator {

    /**
     * Produces the PValues matrix.
     * @param timeSeriesList The list of time series.
     * @return A similarity matrix.
     */
    public RealMatrix compare(final TimeSeriesList timeSeriesList) {
        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();

        PearsonsCorrelation pc = new PearsonsCorrelation(doubleMatrix);
        return pc.getCorrelationPValues();
    }
}
