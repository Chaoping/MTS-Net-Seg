package ubco.cosc520.timeseries;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 * A suite of methods for comparing time series.
 */
public class TimeSeriesListComparatorImpl implements TimeSeriesListComparator {

    @Override
    public final RealMatrix generateCorrelationMatrix(
            final TimeSeriesList timeSeriesList) {

        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();

        PearsonsCorrelation pc = new PearsonsCorrelation(doubleMatrix);
        return pc.getCorrelationMatrix();
    }

    @Override
    public final RealMatrix generateCorrelationPValues(
            final TimeSeriesList timeSeriesList) {

        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();

        PearsonsCorrelation pc = new PearsonsCorrelation(doubleMatrix);
        return pc.getCorrelationPValues();
    }
}
