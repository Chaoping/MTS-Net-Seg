package ubco.cosc520.matrix;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MatrixSizeComparatorTest {

    @Test
    public void testSameSize() {
        double[][] gd = {
                {1, 0, -2, 80},
                {2, 2, 2, 2},
                {2, 3, 9, 0}
        };

        double[][] hd = {
                {0, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };

        RealMatrix g = MatrixUtils.createRealMatrix(gd);
        RealMatrix h = MatrixUtils.createRealMatrix(hd);

        TwoMatrixOperator<Boolean> comparator = new MatrixSizeComparator();
        assertThat(comparator.operate(g, h), is(true));
    }

    @Test
    public void testDifferentSizeColumns() {
        double[][] gd = {
                {1, 0, -2},
                {2, 2, 2},
                {2, 3, 9}
        };

        double[][] hd = {
                {0, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };

        RealMatrix g = MatrixUtils.createRealMatrix(gd);
        RealMatrix h = MatrixUtils.createRealMatrix(hd);

        TwoMatrixOperator<Boolean> comparator = new MatrixSizeComparator();
        assertThat(comparator.operate(g, h), is(false));
    }

    @Test
    public void testDifferentSizeRows() {
        double[][] gd = {
                {1, 0, -2, 80},
                {2, 2, 2, 2}
        };

        double[][] hd = {
                {0, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };

        RealMatrix g = MatrixUtils.createRealMatrix(gd);
        RealMatrix h = MatrixUtils.createRealMatrix(hd);

        TwoMatrixOperator<Boolean> comparator = new MatrixSizeComparator();
        assertThat(comparator.operate(g, h), is(false));
    }
}