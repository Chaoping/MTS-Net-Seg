package ca.ubco.cosc520.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import lombok.extern.java.Log;
import org.junit.Test;

@Log
public class TwoGraphDistanceTest {

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

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphDistance();

    Double distance = distanceCalculator.operate(g1, g2);

    assertThat(distance, is(0.0));

  }

  @Test
  public void testOneEdgeSubtractedGivesOne() {
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

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphDistance();

    Double distance = distanceCalculator.operate(g1, g2);

    assertThat(distance, is(1.0));
  }

  @Test
  public void testOneEdgeSubtractedOneKeptGivesOneHalf() {
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

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphDistance();

    Double distance = distanceCalculator.operate(g1, g2);

    assertThat(distance, is(0.5));
  }

}