package ca.ubco.cosc520.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

public class UndirectedAdjacencyMatrixGraphTest {

  @Test
  public void validMatrixMakesGraph() {
    double[][] gd = {
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    };
    RealMatrix g = MatrixUtils.createRealMatrix(gd);

    new UndirectedAdjacencyMatrixGraph(g);
  }

  @Test
  public void testGetAdjacencyMatrix() {
    double[][] gd = {
        {1, 0, 1},
        {0, 1, 1},
        {1, 1, 1}
    };
    RealMatrix g = MatrixUtils.createRealMatrix(gd);

    Graph graph = new UndirectedAdjacencyMatrixGraph(g);
    assertThat(graph.getAdjacencyMatrix(), is(g));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nonSquareMatrixThrowsError() {
    double[][] gd = {
        {0, 0, 1},
        {0, 0, 1},
        {1, 1, 0},
        {0, 0, 0}
    };
    RealMatrix g = MatrixUtils.createRealMatrix(gd);
    new UndirectedAdjacencyMatrixGraph(g);
  }

  @Test(expected = IllegalArgumentException.class)
  public void matrixWithIncorrectValuesThrowsError() {
    double[][] gd = {
        {0, 0, 100},
        {0, 0, 1},
        {1, 1, 0}
    };
    RealMatrix g = MatrixUtils.createRealMatrix(gd);
    new UndirectedAdjacencyMatrixGraph(g);
  }

  @Test(expected = IllegalArgumentException.class)
  public void matrixWithZerosOnTheDiagonalThrowsError() {
    double[][] gd = {
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
    };
    RealMatrix g = MatrixUtils.createRealMatrix(gd);
    new UndirectedAdjacencyMatrixGraph(g);
  }


  @Test(expected = IllegalArgumentException.class)
  public void nonSymmetricMatrixThrowsError() {
    double[][] gd = {
        {1, 0, 1},
        {0, 1, 0},
        {0, 0, 1}
    };
    RealMatrix g = MatrixUtils.createRealMatrix(gd);
    new UndirectedAdjacencyMatrixGraph(g);
  }
}