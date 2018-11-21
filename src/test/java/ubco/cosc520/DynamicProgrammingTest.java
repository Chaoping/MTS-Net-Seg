package ubco.cosc520;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.UndirectedAdjacencyMatrixGraph;
import ubco.cosc520.matrix.MatrixLessThanThresholder;
import ubco.cosc520.matrix.SingleMatrixOperator;
import ubco.cosc520.simulator.CreateSimulatedData;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesListImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DynamicProgrammingTest {

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

        int timePoints = 3;
        int numSeries = 6;
        int minLength = 2;

        CreateSimulatedData createSimulatedData = new CreateSimulatedData(gd);
        TimeSeriesList timeSeriesList = TimeSeriesListImpl.fromDoubleArray(createSimulatedData.getSimulatedData());

        Graph[][] graphs = new Graph[timePoints][timePoints];

        for (int start = 0; start < timePoints; start++) {
            for (int end = start + 1; end < timePoints; end++) {
                if (end - start < minLength) {
                    graphs[start][end] = makeEmptyGraph(numSeries);
                } else {
                    graphs[start][end] = makeGraph(timeSeriesList.truncate(start, end));
                }
            }
        }
        assertThat(null, is(1));
    }

    @Test
    public void test() {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();

    }

    private Graph makeGraph(TimeSeriesList timeSeriesList) {
        TimeSeriesListComparator timeSeriesListComparator = new PValuesTimeSeriesListComparator();
        RealMatrix pvalueMatrix = timeSeriesListComparator.compare(timeSeriesList);

        SingleMatrixOperator matrixThresholder = new MatrixLessThanThresholder(0.01);
        RealMatrix adjacencyMatrix = matrixThresholder.operate(pvalueMatrix);
        return new UndirectedAdjacencyMatrixGraph(adjacencyMatrix);
    }

    private Graph makeEmptyGraph(int numberOfNodes) {
        RealMatrix realMatrix = MatrixUtils.createRealIdentityMatrix(numberOfNodes);
        return new UndirectedAdjacencyMatrixGraph(realMatrix);
    }
}