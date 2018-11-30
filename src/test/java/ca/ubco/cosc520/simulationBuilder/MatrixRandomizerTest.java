package ca.ubco.cosc520.simulationBuilder;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import ca.ubco.cosc520.matrix.MatrixOfDifferences;
import ca.ubco.cosc520.matrix.SingleMatrixOperator;
import java.util.Random;
import lombok.extern.java.Log;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@Log
@RunWith(MockitoJUnitRunner.class)
public class MatrixRandomizerTest {

  private SingleMatrixOperator matrixRandomizer;

  @Mock
  private Random random;

  @Before
  public void before() {
    SingleMatrixOperator matrixDifferenceCalculator = new MatrixOfDifferences();
    matrixRandomizer = new MatrixRandomizer(matrixDifferenceCalculator, random);
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

    when(random.nextInt(anyInt())).thenReturn(0);
    log.info(matrixRandomizer.operate(rm).toString());
  }
}