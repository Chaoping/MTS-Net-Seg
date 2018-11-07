package cosc520.project;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TimeSeriesListImpl implements TimeSeriesList {

    private final List<double[]> seriesList = new ArrayList<>();

    public static TimeSeriesList fromDoubleList(List<double[]> doubleList) {
        TimeSeriesList ts = new TimeSeriesListImpl();
        doubleList.forEach(ts::add);
        return ts;
    }

    @Override
    public void add(double[] ts) {
        seriesList.add(ts);
    }

    @Override
    public double[] getByIndex(int idx) {
        return seriesList.get(idx);
    }

    @Override
    public List<double[]> getList() {
        return Collections.unmodifiableList(seriesList);
    }

    @Override
    public TimeSeriesList truncate(int start, int end) {
        return TimeSeriesListImpl.fromDoubleList(seriesList.stream()
                .map(doubles -> Arrays.copyOfRange(doubles, start, end))
                .collect(Collectors.toList()));
    }

    @Override
    public double[][] toDoubleMatrix() {
        int rows = seriesList.size();
        int cols = seriesList.get(0).length;

        //We need to transpose as we go to meet Apache Math convention
        double[][] doubleArray = new double[cols][rows];

        for (int row = 0; row < rows; row++) {
            double[] ts = seriesList.get(row);
            for(int col = 0; col < cols; col++) {
                doubleArray[col][row] = ts[col];
            }
        }
        return doubleArray;
    }
}
