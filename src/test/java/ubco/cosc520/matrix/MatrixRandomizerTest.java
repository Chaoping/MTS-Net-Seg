package ubco.cosc520.matrix;

import lombok.extern.java.Log;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

@Log
public class MatrixRandomizerTest {

  private SingleMatrixOperator matrixRandomizer;

  @Before
  public void before() {
    SingleMatrixOperator matrixDifferenceCalculator = new MatrixOfDifferences();
    matrixRandomizer = new MatrixRandomizer(matrixDifferenceCalculator);
  }

  @Test
  public void test() {
    double[][] gd = {
        {1, 5, 10},
        {2, 10, 15},
        {3.5, 10, 17},
        {3, 89, 20},
        {4, 1000, 99},
        {31, 90, 4}
    };

    RealMatrix rm = MatrixUtils.createRealMatrix(gd);

    log.info(matrixRandomizer.operate(rm).toString());
  }
}