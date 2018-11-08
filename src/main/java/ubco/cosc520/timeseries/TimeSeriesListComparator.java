package ubco.cosc520.timeseries;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Compares time series and produces a similarity matrix.
 */
public interface TimeSeriesListComparator {

    /**
     * Produces the correlation matrix.
     * @param timeSeriesList The list of time series.
     * @return A similarity matrix.
     */
    RealMatrix generateCorrelationMatrix(TimeSeriesList timeSeriesList);

    /**
     * Produces the PValues matrix.
     * @param timeSeriesList The list of time series.
     * @return A similarity matrix.
     */
    RealMatrix generateCorrelationPValues(TimeSeriesList timeSeriesList);

}
