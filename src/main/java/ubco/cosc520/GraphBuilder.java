package ubco.cosc520;

import lombok.NonNull;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.UndirectedAdjacencyMatrixGraph;
import ubco.cosc520.matrix.MatrixLessThanThresholder;
import ubco.cosc520.matrix.SingleMatrixOperator;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;

/**
 * Static helper functions for initializing Graphs.
 */
public class GraphBuilder {

  private static final double PVALUE_THRESHOLD = 0.01;

  /**
   * Performs Comparison and Thresholding on a {@link TimeSeriesList}
   *     to turn it into a {@link Graph}.
   * @param timeSeriesList The {@link TimeSeriesList} to turn into a graph.
   * @return The resulting {@link Graph} object
   */
  public static Graph makeGraph(@NonNull TimeSeriesList timeSeriesList) {
    TimeSeriesListComparator timeSeriesListComparator = new PValuesTimeSeriesListComparator();
    RealMatrix pvalueMatrix = timeSeriesListComparator.compare(timeSeriesList);

    SingleMatrixOperator matrixThresholder = new MatrixLessThanThresholder(PVALUE_THRESHOLD);
    RealMatrix adjacencyMatrix = matrixThresholder.operate(pvalueMatrix);
    return new UndirectedAdjacencyMatrixGraph(adjacencyMatrix);
  }

  /**
   * Returns an unconnected graph with the specified number of nodes.
   * @param numberOfNodes The number of nodes in the Graph.
   * @return A {@link Graph} with the specified number of nodes and no edges.
   */
  public static Graph makeUnconnectedGraph(int numberOfNodes) {
    RealMatrix realMatrix = MatrixUtils.createRealIdentityMatrix(numberOfNodes);
    return new UndirectedAdjacencyMatrixGraph(realMatrix);
  }
}
