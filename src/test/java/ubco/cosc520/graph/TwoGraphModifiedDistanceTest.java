package ubco.cosc520.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class TwoGraphModifiedDistanceTest {

  @Test
  public void testEmptyGraphsGiveZero() {
    double[][] d1 = {
        {1,0},
        {0,1}
    };

    double[][] d2 = {
        {1,0},
        {0,1}
    };

    Graph g1 = new UndirectedAdjacencyMatrixGraph(d1);
    Graph g2 = new UndirectedAdjacencyMatrixGraph(d2);

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphModifiedDistance();

    Double distance = distanceCalculator.operate(g1, g2);

    assertThat(distance, is(0.0));

  }

  @Test
  public void testOneEdgeSubtractedGivesOneThird() {
    double[][] d1 = {
        {1,0,1},
        {0,1,0},
        {1,0,1},
    };

    double[][] d2 = {
        {1,0,0},
        {0,1,0},
        {0,0,1}
    };

    Graph g1 = new UndirectedAdjacencyMatrixGraph(d1);
    Graph g2 = new UndirectedAdjacencyMatrixGraph(d2);

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphModifiedDistance();

    Double distance = distanceCalculator.operate(g1, g2);

    assertThat(distance, is(1.0/3.0));
  }

  @Test
  public void testOneEdgeSubtractedOneKeptGivesOneThird() {
    double[][] d1 = {
        {1,1,1},
        {1,1,0},
        {1,0,1},
    };

    double[][] d2 = {
        {1,1,0},
        {1,1,0},
        {0,0,1}
    };

    Graph g1 = new UndirectedAdjacencyMatrixGraph(d1);
    Graph g2 = new UndirectedAdjacencyMatrixGraph(d2);

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphModifiedDistance();

    Double distance = distanceCalculator.operate(g1, g2);

    assertThat(distance, is(1.0/3.0));
  }

}