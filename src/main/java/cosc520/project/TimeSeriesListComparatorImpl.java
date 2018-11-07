package cosc520.project;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

@Slf4j
public class TimeSeriesListComparatorImpl implements TimeSeriesListComparator {

    @Override
    public RealMatrix generateCorrelationMatrix(TimeSeriesList timeSeriesList) {
        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();

        PearsonsCorrelation pc = new PearsonsCorrelation(doubleMatrix);
        return pc.getCorrelationMatrix();
    }

    @Override
    public RealMatrix generateCorrelationPValues(TimeSeriesList timeSeriesList) {
        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();

        PearsonsCorrelation pc = new PearsonsCorrelation(doubleMatrix);
        return pc.getCorrelationPValues();
    }
}
