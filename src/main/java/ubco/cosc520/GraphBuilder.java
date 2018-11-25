package ubco.cosc520;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.UndirectedAdjacencyMatrixGraph;
import ubco.cosc520.matrix.MatrixLessThanThresholder;
import ubco.cosc520.matrix.SingleMatrixOperator;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;

public class GraphBuilder {

  private static final double PVALUE_THRESHOLD = 0.01;

  public static Graph makeGraph(TimeSeriesList timeSeriesList) {
    TimeSeriesListComparator timeSeriesListComparator = new PValuesTimeSeriesListComparator();
    RealMatrix pvalueMatrix = timeSeriesListComparator.compare(timeSeriesList);

    SingleMatrixOperator matrixThresholder = new MatrixLessThanThresholder(PVALUE_THRESHOLD);
    RealMatrix adjacencyMatrix = matrixThresholder.operate(pvalueMatrix);
    return new UndirectedAdjacencyMatrixGraph(adjacencyMatrix);
  }

  public static Graph makeEmptyGraph(int numberOfNodes) {
    RealMatrix realMatrix = MatrixUtils.createRealIdentityMatrix(numberOfNodes);
    return new UndirectedAdjacencyMatrixGraph(realMatrix);
  }
}
