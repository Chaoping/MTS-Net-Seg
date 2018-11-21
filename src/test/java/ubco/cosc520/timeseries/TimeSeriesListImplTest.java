package ubco.cosc520.timeseries;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeSeriesListImplTest {

    private TimeSeriesList timeSeriesList;
    private Random random;

    @Before
    public void before() {
        timeSeriesList = new TimeSeriesListImpl();
        random = new Random();
    }

    @Test
    public void add() {
        assertThat(timeSeriesList.getList().size(), is(0));

        int testSize = 40;
        double[] d = getTestTimeSeriesOfSize(testSize);
        double[] d2 = getTestTimeSeriesOfSize(testSize);
        timeSeriesList.add(d);
        assertThat(timeSeriesList.getList().size(), is(1));
        assertThat(timeSeriesList.getList().get(0), is(d));


        timeSeriesList.add(d2);
        assertThat(timeSeriesList.getList().size(), is(2));
        assertThat(timeSeriesList.getList().get(0), is(d));
        assertThat(timeSeriesList.getList().get(1), is(d2));
    }

    @Test
    public void getByIndex() {
        add();
        assertThat(timeSeriesList.getList().get(1), is(timeSeriesList.getByIndex(1)));
    }

    @Test
    public void testTruncate() {
        add();
        TimeSeriesList truncated = timeSeriesList.truncate(10, 20);
        assertThat(truncated.getList().size(), is(2));
        assertThat(truncated.getByIndex(0).length, is(11));
        assertThat(truncated.getByIndex(1).length, is(11));
        assertThat(truncated.getByIndex(0), is(Arrays.copyOfRange(timeSeriesList.getByIndex(0), 10, 21)));
        assertThat(truncated.getByIndex(1), is(Arrays.copyOfRange(timeSeriesList.getByIndex(1), 10, 21)));
    }

    @Test
    public void toDoubleMatrix() {
        add();
        double[][] doubleMatrix = timeSeriesList.toDoubleMatrix();
        int rows = 2;
        int cols = 40;
        for (int row = 0; row < rows; row++) {
            double[] ts = timeSeriesList.getByIndex(row);
            for (int col = 0; col < cols; col++) {
                assertThat(doubleMatrix[col][row], is(ts[col]));
            }
        }
    }

    private double[] getTestTimeSeriesOfSize(int size) {
        double[] doubleArray = new double[size];

        for (int idx = 0; idx < size; idx++) {
            doubleArray[idx] = random.nextDouble();
        }
        return doubleArray;
    }
}