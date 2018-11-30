package ubco.cosc520.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GraphPossibleEdgeCountTest {

  private final int numberOfNodes;
  private final int expectedAnswer;

  public GraphPossibleEdgeCountTest(List<Integer> parameters) {
    this.numberOfNodes = parameters.get(0);
    this.expectedAnswer = parameters.get(1);
  }

  @Parameterized.Parameters
  public static Collection provideParameters() {
    return Arrays.asList(
        Arrays.asList(2, 1),
        Arrays.asList(3, 3),
        Arrays.asList(4, 6)
    );
  }

  @Test
  public void testTwoNodeGraphGivesTwo() {
    RealMatrix rm = MatrixUtils.createRealIdentityMatrix(numberOfNodes);

    Graph g1 = new UndirectedAdjacencyMatrixGraph(rm);

    SingleGraphOperator<Double> possibleEdgeCounter = new GraphPossibleEdgeCount();

    Double distance = possibleEdgeCounter.operate(g1);

    assertThat(distance, is((double)expectedAnswer));
  }

}