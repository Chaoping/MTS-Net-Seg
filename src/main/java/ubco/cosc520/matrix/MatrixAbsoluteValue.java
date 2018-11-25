package ubco.cosc520.matrix;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Returns a graph with the absolute value of each value in {@code g}.
 */
public class MatrixAbsoluteValue
    extends BaseMatrixOperator implements SingleMatrixOperator {

  /**
   * @param g The matrix
   * @return A matrix with the values replaced by absolute values.
   */
  @Override
  public RealMatrix operate(final RealMatrix g) {
    return this.operate(g, Math::abs);
  }
}
