package ubco.cosc520;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphDistance;
import ubco.cosc520.graph.TwoGraphOperator;
import ubco.cosc520.matrix.MatrixOfDifferences;
import ubco.cosc520.matrix.MatrixRandomizer;
import ubco.cosc520.matrix.SingleMatrixOperator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListImpl;

@RunWith(MockitoJUnitRunner.class)
public class DynamicProgrammingTest {

  @Mock
  Random random;

  Graph[][] graphs;

  @Before
  public void before() {

    double[][] gd = {
        {1, 5, 10},
        {2, 10, 15},
        {3.5, 10, 17},
        {3, 89, 20},
        {4, 1000, 99},
        {31, 90, 4}
    };

    when(random.nextInt(anyInt())).thenReturn(0);

    RealMatrix rm = MatrixUtils.createRealMatrix(gd);
    SingleMatrixOperator matrixDifferenceCalculator = new MatrixOfDifferences();
    SingleMatrixOperator matrixRandomizer = new MatrixRandomizer(matrixDifferenceCalculator, random);
    TimeSeriesList timeSeriesList = TimeSeriesListImpl
        .fromDoubleArray(matrixRandomizer.operate(rm).getData());

    graphs = GraphTableBuilder.TableFromTimeSeriesList(timeSeriesList);


  }

  @Test
  public void test() {
    TwoGraphOperator<Double> distanceCalculator = new TwoGraphDistance();
    DynamicProgramming dynamicProgramming = new DynamicProgramming(distanceCalculator);
    List<Integer> path = dynamicProgramming.dynamicProgramming(graphs);
    assertThat(path, is(notNullValue()));
    assertThat(path.size(), is(greaterThan(1)));
  }

}