package ubco.cosc520.dynamicprogramming;

import java.util.List;
import lombok.extern.java.Log;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import ubco.cosc520.GraphTableBuilder;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphDistance;
import ubco.cosc520.graph.TwoGraphOperator;
import ubco.cosc520.timeseries.ClasspathFileDataLoader;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;

@Log
public class PathMapperTest {

  PathMapper pathMapper;

  TwoGraphOperator<Double> distanceCalculator;

  TimeSeriesListComparator comparator;

  private TimeSeriesList timeSeriesList;

  /**
   * Initialize required veriables before testing.
   */
  @Before
  public void before() {
    distanceCalculator = new TwoGraphDistance();
    pathMapper = new PathMapper(distanceCalculator);
    comparator = new PValuesTimeSeriesListComparator();
    timeSeriesList = ClasspathFileDataLoader.fromFile("series.csv");

  }

  @Test
  public void test() {
    RealMatrix correlation = comparator.compare(timeSeriesList);

    log.info(correlation.toString());

    Graph[][] graphs = GraphTableBuilder.tableFromTimeSeriesList(timeSeriesList);
    List<Integer> path = pathMapper.dynamicProgramming(graphs);

    log.info(path.toString());
  }
}
