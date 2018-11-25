package ubco.cosc520.timeseries;

import org.junit.Before;

public class PValuesTimeSeriesListComparatorTest extends BaseComparatorTest {

  @Before
  public void before() {
    timeSeriesListComparator = new PValuesTimeSeriesListComparator();
  }
}