package ubco.cosc520.graph;

import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TwoGraphUnionEdgeCountTest {

    @Test
    public void testSameMatrixGivesEdgeCount() {
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

        Graph g1 = new UndirectedAdjacencyMatrixGraph(MatrixUtils.createRealMatrix(m1));
        Graph g2 = new UndirectedAdjacencyMatrixGraph(MatrixUtils.createRealMatrix(m2));

        TwoGraphOperator<Integer> operator = new TwoGraphUnionEdgeCount();
        Integer edgeNumOfSymDiff = operator.operate(g1, g2);

        assertThat(edgeNumOfSymDiff, is(2));
    }
}