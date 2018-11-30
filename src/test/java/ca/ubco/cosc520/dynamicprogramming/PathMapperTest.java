package ca.ubco.cosc520.dynamicprogramming;

import ca.ubco.cosc520.graph.Graph;
import ca.ubco.cosc520.graph.TwoGraphModifiedDistance;
import ca.ubco.cosc520.graph.TwoGraphOperator;
import ca.ubco.cosc520.graphbuilder.GraphBuilder;
import ca.ubco.cosc520.graphbuilder.GraphTableBuilder;
import ca.ubco.cosc520.matrix.MatrixLessThanThresholder;
import ca.ubco.cosc520.matrix.SingleMatrixOperator;
import ca.ubco.cosc520.timeseries.ClasspathFileDataLoader;
import ca.ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ca.ubco.cosc520.timeseries.TimeSeriesList;
import ca.ubco.cosc520.timeseries.TimeSeriesListComparator;
import java.util.List;
import lombok.extern.java.Log;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

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
    distanceCalculator = new TwoGraphModifiedDistance();
    breakpointPenalty = new NormalizedExponentialBreakpointPenalty(1);
    pathMapper = new PathMapper(distanceCalculator, breakpointPenalty);
    comparator = new PValuesTimeSeriesListComparator();
    timeSeriesList = new ClasspathFileDataLoader().load("series.csv");

  }

  @Test
  public void test() {
    RealMatrix correlation = comparator.compare(timeSeriesList);

    log.info(correlation.toString());

    SingleMatrixOperator matrixThresholder = new MatrixLessThanThresholder(0.01);
    TimeSeriesListComparator pValueComparator = new PValuesTimeSeriesListComparator();
    GraphBuilder graphBuilder = new GraphBuilder(pValueComparator, matrixThresholder);
    Graph[][] graphs = GraphTableBuilder.tableFromTimeSeriesList(graphBuilder, timeSeriesList);
    List<Interval> path = pathMapper.dynamicProgramming(graphs);

    log.info(path.toString());
  }
}
