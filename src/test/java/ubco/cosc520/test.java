package ubco.cosc520;

import java.util.List;
import lombok.extern.java.Log;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphDistance;
import ubco.cosc520.graph.TwoGraphOperator;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;

@Log
public class test {

  DynamicProgramming dynamicProgramming;

  TwoGraphOperator<Double> distanceCalculator;

  TimeSeriesListComparator comparator;

  private TimeSeriesList timeSeriesList;

  @Before
  public void before() {
    distanceCalculator = new TwoGraphDistance();
    dynamicProgramming = new DynamicProgramming(distanceCalculator);
    comparator = new PValuesTimeSeriesListComparator();
    timeSeriesList = DataLoader.fromFile("series.csv");

  }

  @Test
  public void test() {
    RealMatrix correlation = comparator.compare(timeSeriesList);

    log.info(correlation.toString());

    Graph[][] graphs = GraphTableBuilder.TableFromTimeSeriesList(timeSeriesList);
    List<Integer> path = dynamicProgramming.dynamicProgramming(graphs);

    log.info(path.toString());
  }
}
