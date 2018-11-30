package ca.ubco.cosc520;

import ca.ubco.cosc520.Args.BREAKPOINT_STRATEGY;
import ca.ubco.cosc520.Args.COMPARATOR_STRATEGY;
import ca.ubco.cosc520.Args.DIFFERENCE_STRATEGY;
import ca.ubco.cosc520.dynamicprogramming.BreakpointPenalty;
import ca.ubco.cosc520.dynamicprogramming.ConstantBreakpointPenalty;
import ca.ubco.cosc520.dynamicprogramming.ExponentialBreakpointPenalty;
import ca.ubco.cosc520.dynamicprogramming.Interval;
import ca.ubco.cosc520.dynamicprogramming.LinearBreakpointPenalty;
import ca.ubco.cosc520.dynamicprogramming.NormalizedExponentialBreakpointPenalty;
import ca.ubco.cosc520.dynamicprogramming.PathMapper;
import ca.ubco.cosc520.graph.Graph;
import ca.ubco.cosc520.graph.TwoGraphDistance;
import ca.ubco.cosc520.graph.TwoGraphModifiedDistance;
import ca.ubco.cosc520.graph.TwoGraphOperator;
import ca.ubco.cosc520.graphbuilder.GraphBuilder;
import ca.ubco.cosc520.graphbuilder.GraphTableBuilder;
import ca.ubco.cosc520.matrix.MatrixAbsoluteValueGreaterThanThresholder;
import ca.ubco.cosc520.matrix.MatrixLessThanThresholder;
import ca.ubco.cosc520.matrix.SingleMatrixOperator;
import ca.ubco.cosc520.timeseries.CorrelationTimeSeriesListComparator;
import ca.ubco.cosc520.timeseries.FileDataLoader;
import ca.ubco.cosc520.timeseries.PValuesTimeSeriesListComparator;
import ca.ubco.cosc520.timeseries.TimeSeriesList;
import ca.ubco.cosc520.timeseries.TimeSeriesListComparator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.util.List;
import lombok.NonNull;
import lombok.extern.java.Log;

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


    // Calculate the required parameters from args
    TwoGraphOperator<Double> distanceCalculator =
        getDistanceCalculator(args.getDifferenceStrategy());

    BreakpointPenalty breakpointPenalty =
        getBreakpointPenalty(args.getBreakpointStrategy(), args.getBreakpointV());

    TimeSeriesListComparator comparator = getComparator(args.getComparatorStrategy());

    SingleMatrixOperator matrixThresholder = getMatrixThresholder(
        args.getComparatorStrategy(),
        args.getComparatorV()
    );

    PathMapper pathMapper = new PathMapper(distanceCalculator, breakpointPenalty);

    // Load the file and truncate if necessary
    TimeSeriesList timeSeriesList = new FileDataLoader().load(args.getFile());

    if (args.getStart() != null && args.getEnd() != null ) {
      timeSeriesList.truncate(args.getStart(), args.getEnd());
    } else if (args.getStart() != null && args.getStart() > 0) {
      timeSeriesList = timeSeriesList.truncate(args.getStart(), timeSeriesList.getSeriesLength());
    } else if (args.getEnd() != null && args.getEnd() < timeSeriesList.getSeriesLength()) {
      timeSeriesList = timeSeriesList.truncate(0, args.getEnd());
    }

    // Operate
    GraphBuilder graphBuilder = new GraphBuilder(comparator, matrixThresholder);
    GraphTableBuilder graphTableBuilder = new GraphTableBuilder(graphBuilder);
    Graph[][] graphs = graphTableBuilder.tableFromTimeSeriesList( timeSeriesList);
    List<Interval> path = pathMapper.dynamicProgramming(graphs);

    System.out.println(IntervalListToCSV.fromInterval(path));
  }

  private static BreakpointPenalty getBreakpointPenalty(
      @NonNull BREAKPOINT_STRATEGY breakpointStrategy,
      double breakpointV
  ) {
    switch (breakpointStrategy) {
      case NORMALIZED_EXPONENTIAL:
        return new NormalizedExponentialBreakpointPenalty(breakpointV);
      case LINEAR:
        return new LinearBreakpointPenalty(breakpointV);
      case CONSTANT:
        return new ConstantBreakpointPenalty(breakpointV);
      case EXPONENTIAL:
        return new ExponentialBreakpointPenalty(breakpointV);
      default:
        throw new ParameterException("Breakpoint strategy not defined.");
    }
  }

  private static TwoGraphOperator<Double> getDistanceCalculator(
      @NonNull DIFFERENCE_STRATEGY difference_strategy
  ) {
    switch (difference_strategy) {
      case POSSIBLE_EDGE_DENOMINATOR:
        return new TwoGraphModifiedDistance();
      case UNION_DENOMINATOR:
        return new TwoGraphDistance();
      default:
        throw new ParameterException("Distance strategy not defined.");
    }
  }

  private static SingleMatrixOperator getMatrixThresholder(
      @NonNull COMPARATOR_STRATEGY comparator_strategy,
      double comparatorV
  ) {
    switch (comparator_strategy) {
      case CORRELATION:
        return new MatrixAbsoluteValueGreaterThanThresholder(comparatorV);
      case PVALUE:
        return new MatrixLessThanThresholder(comparatorV);
      default:
        throw new ParameterException("Comaprator strategy not defined.");
    }
  }

  private static TimeSeriesListComparator getComparator(
      @NonNull COMPARATOR_STRATEGY comparator_strategy
  ) {
    switch (comparator_strategy) {
      case CORRELATION:
        return new CorrelationTimeSeriesListComparator();
      case PVALUE:
        return new PValuesTimeSeriesListComparator();
      default:
        throw new ParameterException("Comaprator strategy not defined.");
    }
  }
}
