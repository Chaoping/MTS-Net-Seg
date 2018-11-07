package cosc520.project;

import org.apache.commons.math3.linear.RealMatrix;

public interface TimeSeriesListComparator {
    RealMatrix generateCorrelationMatrix(TimeSeriesList timeSeriesList);
    RealMatrix generateCorrelationPValues(TimeSeriesList timeSeriesList);

}
