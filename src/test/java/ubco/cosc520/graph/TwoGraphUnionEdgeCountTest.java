package ubco.cosc520.graph;

import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

        testMatrices(m1, m2, 2);
    }

    @Test
    public void testOppositeMatrixGivesZero() {
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

        TwoGraphOperator<Double> twoGraphUnionEdgeCount = new TwoGraphUnionEdgeCount();
        Double edgeNumOfUnion = twoGraphUnionEdgeCount.operate(g1, g2);

        assertThat(edgeNumOfUnion, is(result));
    }
}