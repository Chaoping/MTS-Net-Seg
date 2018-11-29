package ubco.cosc520.dynamicprogramming;

import java.util.List;
import lombok.extern.java.Log;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import ubco.cosc520.graphbuilder.GraphTableBuilder;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphDistance;
import ubco.cosc520.graph.TwoGraphOperator;
import ubco.cosc520.timeseries.ClasspathFileDataLoader;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;

@Log
public class PathMapperTest {

  private PathMapper pathMapper;
  private TwoGraphOperator<Double> distanceCalculator;
  private TimeSeriesListComparator comparator;
  private TimeSeriesList timeSeriesList;
  private BreakpointPenalty breakpointPenalty;

  /**
   * Initialize required veriables before testing.
   */
  @Before
  public void before() {
    distanceCalculator = new TwoGraphDistance();
    breakpointPenalty = new NormalizedExponentialBreakpointPenalty(1);
    pathMapper = new PathMapper(distanceCalculator, breakpointPenalty);
    comparator = new PValuesTimeSeriesListComparator();
    timeSeriesList = ClasspathFileDataLoader.fromFile("series.csv");

  }

  @Test
  public void test() {
    RealMatrix correlation = comparator.compare(timeSeriesList);

    log.info(correlation.toString());

    Graph[][] graphs = GraphTableBuilder.tableFromTimeSeriesList(timeSeriesList);
    List<Interval> path = pathMapper.dynamicProgramming(graphs);

    log.info(path.toString());
  }
}
