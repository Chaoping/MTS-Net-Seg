package ubco.cosc520;

import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesListComparatorImpl;
import ubco.cosc520.timeseries.TimeSeriesListImpl;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TimeSeriesListComparatorImplTest {

    private TimeSeriesList timeSeriesList;
    private Random random;

    private int testDataLength = 40;
    private int testDataSetCount = 10;
    private double[][] testData = new double[testDataSetCount][testDataLength];

    @Before
    public void before() {
        timeSeriesList = new TimeSeriesListImpl();
        random = new Random();

        for(int ds = 0; ds < testDataSetCount; ds++) {
            double[] d = getTestTimeSeriesOfSize(testDataLength);
            testData[ds] = d;
            timeSeriesList.add(d);
        }
        assertThat(timeSeriesList.getList().size(), is(testDataSetCount));
    }


    @Test
    public void testGenerateCorrelationMatrix() {
        TimeSeriesListComparator timeSeriesListComparator = new TimeSeriesListComparatorImpl();
        RealMatrix rm = timeSeriesListComparator.generateCorrelationMatrix(timeSeriesList);
        for(int row = 0; row < testDataSetCount; row++) {
            assertThat(rm.getEntry(row, row), is(1d));
            for (int row2 = 0; row2 < testDataSetCount; row2++) {
                assertThat(rm.getEntry(row, row2), is(greaterThanOrEqualTo(-1d)));
                assertThat(rm.getEntry(row, row2), is(lessThanOrEqualTo(1d)));
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