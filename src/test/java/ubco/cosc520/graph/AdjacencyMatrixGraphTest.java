package ubco.cosc520.graph;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

public class AdjacencyMatrixGraphTest {

    @Test
    public void validMatrixMakesGraph() {
        double[][] gd = {
                {0,0,1},
                {0,0,1},
                {1,1,0}
        };
        RealMatrix g = MatrixUtils.createRealMatrix(gd);

        new AdjacencyMatrixGraph(g);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonSquareMatrixThrowsError() {
        double[][] gd = {
                {0,0,1},
                {0,0,1},
                {1,1,0},
                {0,0,0}
        };
        RealMatrix g = MatrixUtils.createRealMatrix(gd);
        new AdjacencyMatrixGraph(g);
    }

    @Test(expected = IllegalArgumentException.class)
    public void matrixWithIncorrectValuesThrowsError() {
        double[][] gd = {
                {0,0,100},
                {0,0,1},
                {1,1,0}
        };
        RealMatrix g = MatrixUtils.createRealMatrix(gd);
        new AdjacencyMatrixGraph(g);
    }
}