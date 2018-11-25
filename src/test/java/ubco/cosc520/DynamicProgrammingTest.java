package ubco.cosc520;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.simulator.CreateSimulatedData;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListImpl;

public class DynamicProgrammingTest {

  Graph[][] graphs;

  @Before
  public void before() {

    double[][] gd = {
        {1, 5, 10},
        {2, 10, 15},
        {3.5, 10, 17},
        {3, 89, 20},
        {4, 1000, 99},
        {31, 90, 4}
    };

    CreateSimulatedData createSimulatedData = new CreateSimulatedData(gd);
    TimeSeriesList timeSeriesList = TimeSeriesListImpl
        .fromDoubleArray(createSimulatedData.getSimulatedData());

    graphs = GraphTableBuilder.TableFromTimeSeriesList(timeSeriesList);

  }

  @Test
  public void test() {
    DynamicProgramming dynamicProgramming = new DynamicProgramming();
    List<Integer> path = dynamicProgramming.dynamicProgramming(graphs);
    assertThat(path, is(notNullValue()));
    assertThat(path.size(), is(greaterThan(1)));
  }

}