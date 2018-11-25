package ubco.cosc520;

import java.util.List;
import org.junit.Test;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.timeseries.TimeSeriesList;

public class test {

  @Test
  public void test() {
    TimeSeriesList timeSeriesList = DataLoader.fromFile("series.csv");
    Graph[][] graphs = GraphTableBuilder.TableFromTimeSeriesList(timeSeriesList);
    DynamicProgramming dynamicProgramming = new DynamicProgramming();
    List<Integer> path = dynamicProgramming.dynamicProgramming(graphs);

  }
}
