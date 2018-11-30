package ca.ubco.cosc520.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;

public class TwoGraphSymDiffEdgeCountTest {

  @Test
  public void testSameMatrixReturnsZero() {
    double[][] m1 = {
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    };
    double[][] m2 = {
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    };

    testMatrices(m1, m2, 0);
  }

  @Test
  public void testOppositeMatrixReturnsZero() {
    double[][] m1 = {
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    };
    double[][] m2 = {
        {1, 1, 0},
        {1, 1, 0},
        {0, 0, 1}
    };
    testMatrices(m1, m2, 3);
  }

  private void testMatrices(double[][] m1, double[][] m2, double result) {
    Graph g1 = new UndirectedAdjacencyMatrixGraph(MatrixUtils.createRealMatrix(m1));
    Graph g2 = new UndirectedAdjacencyMatrixGraph(MatrixUtils.createRealMatrix(m2));

    TwoGraphOperator<Double> twoGraphSymDiff = new TwoGraphSymDiffEdgeCount();
    Double edgeNumOfSymDiff = twoGraphSymDiff.operate(g1, g2);

    assertThat(edgeNumOfSymDiff, is(result));
  }
}