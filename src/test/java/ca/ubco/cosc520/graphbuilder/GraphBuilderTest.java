package ca.ubco.cosc520.graphbuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;
import ca.ubco.cosc520.graph.Graph;

public class GraphBuilderTest {

  @Test
  public void testMakeFullyConnectedGraph() {
    Graph graph = GraphBuilder.makeFullyConnectedGraph(10);
    RealMatrix rm = graph.getAdjacencyMatrix();
    double[][] d = rm.getData();
    for (int row = 0; row < d.length; row++) {
      for (int col = 0; col < d[0].length; col++) {
        assertThat(d[row][col], is(1.0));
      }
    }
  }

  @Test
  public void testMakeUnconnectedGraph() {
    Graph graph = GraphBuilder.makeUnconnectedGraph(10);
    RealMatrix rm = graph.getAdjacencyMatrix();
    double[][] d = rm.getData();
    for (int row = 0; row < d.length; row++) {
      for (int col = 0; col < d[0].length; col++) {
        if (row == col) {
          assertThat(d[row][col], is(1.0));
        } else {
          assertThat(d[row][col], is(0.0));
        }
      }
    }
  }
}