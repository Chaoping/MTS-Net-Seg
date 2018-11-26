package ubco.cosc520.timeseries;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.Random;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

/**
 * An abstract class useful for building tests for comparators of {@link TimeSeriesList}.
 */
public abstract class BaseComparatorTest {

  TimeSeriesListComparator timeSeriesListComparator;
  private TimeSeriesList timeSeriesList;
  private Random random;
  private int testDataLength = 40;
  private int testDataSetCount = 10;
  private double[][] testData = new double[testDataSetCount][testDataLength];

  /**
   * Initialize required veriables before testing.
   */
  @Before
  public void baseBefore() {
    timeSeriesList = new TimeSeriesListImpl();
    random = new Random();

    for (int ds = 0; ds < testDataSetCount; ds++) {
      double[] d = getTestTimeSeriesOfSize(testDataLength);
      testData[ds] = d;
      timeSeriesList.add(d);
    }
    assertThat(timeSeriesList.getList().size(), is(testDataSetCount));
  }


  @Test
  public void testGenerateCorrelationMatrix() {
    RealMatrix rm = timeSeriesListComparator.compare(timeSeriesList);
    for (int row = 0; row < testDataSetCount; row++) {
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
