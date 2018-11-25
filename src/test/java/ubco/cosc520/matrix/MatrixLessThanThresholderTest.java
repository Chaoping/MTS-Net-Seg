package ubco.cosc520.matrix;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

public class MatrixLessThanThresholderTest {

  @Test
  public void testThreshold() {
    double[][] input = {
        {1, 0, -2, 80},
        {2, 2, 2, 2},
        {2, 3, 0.5, 0}
    };

    double[][] output = {
        {1, 1, 1, 0},
        {1, 1, 1, 1},
        {1, 0, 1, 1}
    };

    RealMatrix rm = MatrixUtils.createRealMatrix(input);

    SingleMatrixOperator graphThresholder = new MatrixLessThanThresholder(2d);

    RealMatrix result = graphThresholder.operate(rm);

    assertThat(rm, is(MatrixUtils.createRealMatrix(input)));
    assertThat(result, is(MatrixUtils.createRealMatrix(output)));
  }
}