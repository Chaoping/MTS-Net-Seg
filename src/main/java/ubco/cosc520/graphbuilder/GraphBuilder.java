package ubco.cosc520.graphbuilder;

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

  private final TimeSeriesListComparator timeSeriesListComparator;
  private final SingleMatrixOperator matrixToGraphOperator;

  public GraphBuilder(
      TimeSeriesListComparator timeSeriesListComparator,
      SingleMatrixOperator matrixToGraphOperator
  ) {
    this.timeSeriesListComparator = timeSeriesListComparator;
    this.matrixToGraphOperator = matrixToGraphOperator;
  }

  /**
   * Performs Comparison and Thresholding on a {@link TimeSeriesList}
   *     to turn it into a {@link Graph}.
   * @param timeSeriesList The {@link TimeSeriesList} to turn into a graph.
   * @return The resulting {@link Graph} object
   */
  public Graph makeGraph(@NonNull TimeSeriesList timeSeriesList) {
    RealMatrix matrix = timeSeriesListComparator.compare(timeSeriesList);
    RealMatrix adjacencyMatrix = matrixToGraphOperator.operate(matrix);
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

  /**
   * Returns a fully-connected graph with the specified number of nodes.
   * @param numberOfNodes The number of nodes in the Graph.
   * @return A {@link Graph} with the specified number of nodes and no edges.
   */
  public static Graph makeFullyConnectedGraph(int numberOfNodes) {
    RealMatrix realMatrix = MatrixUtils.createRealIdentityMatrix(numberOfNodes);
    MatrixLessThanThresholder thresholder = new MatrixLessThanThresholder(1000);
    return new UndirectedAdjacencyMatrixGraph(thresholder.operate(realMatrix));
  }
}
