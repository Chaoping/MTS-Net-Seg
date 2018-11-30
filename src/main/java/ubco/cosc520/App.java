package ubco.cosc520;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.util.List;
import lombok.extern.java.Log;
import ubco.cosc520.dynamicprogramming.BreakpointPenalty;
import ubco.cosc520.dynamicprogramming.Interval;
import ubco.cosc520.dynamicprogramming.NormalizedExponentialBreakpointPenalty;
import ubco.cosc520.dynamicprogramming.PathMapper;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphModifiedDistance;
import ubco.cosc520.graph.TwoGraphOperator;
import ubco.cosc520.graphbuilder.GraphBuilder;
import ubco.cosc520.graphbuilder.GraphTableBuilder;
import ubco.cosc520.matrix.MatrixLessThanThresholder;
import ubco.cosc520.matrix.SingleMatrixOperator;
import ubco.cosc520.timeseries.FileDataLoader;
import ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListComparator;

@Log
public class App {

  public static void main(String[] argv) {

    Args args = new Args();

    JCommander jCommander = JCommander.newBuilder()
        .addObject(args)
        .programName("MTS-Net-Seg")
        .build();

    try {
      jCommander.parse(argv);
    } catch (ParameterException e) {
      JCommander jct = e.getJCommander();
      jct.usage();
      return;
    }

    if (args.isHelp()) {
      jCommander.usage();
      return;
    }

    TwoGraphOperator<Double> distanceCalculator = new TwoGraphModifiedDistance();

    BreakpointPenalty breakpointPenalty = new NormalizedExponentialBreakpointPenalty(args.getV());
    PathMapper pathMapper = new PathMapper(distanceCalculator, breakpointPenalty);

    TimeSeriesList timeSeriesList = new FileDataLoader().load(args.getFile());

    SingleMatrixOperator matrixThresholder = new MatrixLessThanThresholder(args.getPValue());
    TimeSeriesListComparator pValueComparator = new PValuesTimeSeriesListComparator();
    GraphBuilder graphBuilder = new GraphBuilder(pValueComparator, matrixThresholder);
    Graph[][] graphs = GraphTableBuilder.tableFromTimeSeriesList(graphBuilder, timeSeriesList);
    List<Interval> path = pathMapper.dynamicProgramming(graphs);

    System.out.println(IntervalListToCSV.fromInterval(path));
  }

}
