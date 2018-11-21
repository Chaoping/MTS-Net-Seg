package ubco.cosc520.timeseries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A list of multiple time series data.
 * All of the time series must be of the same length.
 * Time series data is expected and returned as an array of doubles.
 */
public class TimeSeriesListImpl implements TimeSeriesList {

    /**
     * Internal arraylist to store series.
     */
    private final List<double[]> seriesList = new ArrayList<>();

    /**
     * Initialize a new TimeSeriesList from a list of doubles.
     *
     * @param doubleList The list of doubles.
     * @return A new TimeSeriesList
     */
    static TimeSeriesList fromDoubleList(
            final List<double[]> doubleList) {

        TimeSeriesList ts = new TimeSeriesListImpl();
        doubleList.forEach(ts::add);
        return ts;
    }

    @Override
    public final void add(final double[] ts) {
        seriesList.add(ts);
    }

    @Override
    public final double[] getByIndex(final int idx) {
        return seriesList.get(idx);
    }

    @Override
    public final List<double[]> getList() {
        return Collections.unmodifiableList(seriesList);
    }

    @Override
    public final TimeSeriesList truncate(final int start, final int end) {
        return TimeSeriesListImpl.fromDoubleList(seriesList.stream()
                .map(doubles -> Arrays.copyOfRange(doubles, start, end))
                .collect(Collectors.toList()));
    }

    @Override
    public final double[][] toDoubleMatrix() {
        int rows = seriesList.size();
        int cols = seriesList.get(0).length;

        //We need to transpose as we go to meet Apache Math convention
        double[][] doubleArray = new double[cols][rows];

        for (int row = 0; row < rows; row++) {
            double[] ts = seriesList.get(row);
            for (int col = 0; col < cols; col++) {
                doubleArray[col][row] = ts[col];
            }
        }
        return doubleArray;
    }
}
