package ubco.cosc520.timeseries;

import org.junit.Before;

public class CorrelationTimeSeriesListComparatorTest extends BaseComparatorTest {

  @Before
  public void before() {
    timeSeriesListComparator = new CorrelationTimeSeriesListComparator();
  }

}