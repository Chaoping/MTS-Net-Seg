package ubco.cosc520.matrix;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MatrixOfDifferencesTest {


    @Test
    public void test() {
        double[][] inputDouble = {
                {1, 5, 10},
                {2, 10, 15},
                {3.5, 10, 17},
                {3, 89, 20},
                {4, 1000, 99},
                {31, 90, 4}
        };

        double[][] expectedDouble = {
                {4.0, 5.0},
                {8.0, 5.0},
                {6.5, 7.0},
                {86.0, -69.0},
                {996.0, -901.0},
                {59.0, -86.0}
        };

        RealMatrix inputMatrix = MatrixUtils.createRealMatrix(inputDouble);
        RealMatrix expectedMatrix = MatrixUtils.createRealMatrix(expectedDouble);

        SingleMatrixOperator differenceOperator = new MatrixOfDifferences();
        assertThat(differenceOperator.operate(inputMatrix), is(expectedMatrix));

    }
}