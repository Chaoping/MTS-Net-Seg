package ubco.cosc520;

import ubco.cosc520.graph.Graph;
import ubco.cosc520.timeseries.TimeSeriesList;

public class GraphTableBuilder {

    public static Graph[][] TableFromTimeSeriesList(TimeSeriesList timeSeriesList) {
        return GraphTableBuilder.TableFromTimeSeriesList(timeSeriesList, 2);
    }

    public static Graph[][] TableFromTimeSeriesList(TimeSeriesList timeSeriesList, int minLength) {

        int timePoints = timeSeriesList.getSeriesLength();

        Graph[][] graphs = new Graph[timePoints][timePoints];

        for (int start = 0; start < timePoints; start++) {
            for (int end = start + 1; end < timePoints; end++) {
                if (end - start < minLength) {
                    graphs[start][end] = GraphBuilder.makeEmptyGraph(timeSeriesList.getNumberOfSeries());
                } else {
                    graphs[start][end] = GraphBuilder.makeGraph(timeSeriesList.truncate(start, end));
                }
            }
        }
        return graphs;
    }
}
