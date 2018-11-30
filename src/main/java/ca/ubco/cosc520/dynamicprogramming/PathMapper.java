package ca.ubco.cosc520.dynamicprogramming;

import ca.ubco.cosc520.graph.Graph;
import ca.ubco.cosc520.graph.TwoGraphOperator;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

/**
 * Produces the Dynamic Programming Path.
 */
public class PathMapper {

  private static final int MIN_LENGTH = 5;
  private final BreakpointPenalty breakpointPenalty;

  public PathMapper(@NonNull BreakpointPenalty breakpointPenalty) {
    this.breakpointPenalty = breakpointPenalty;
  }

  /**
   * Performs the dynamic programming algorithm on a table of {@link Graph} objects.
   *
   * @param cutValues Three dimensional array representing cutValues.
   * First element in array indicates start of first interval
   * Second element in array indicates cut point (end of first interval, 1 before start of second)
   * Thirs element in array indicates end of second interval
   * @return A {@link List} of {@link Integer}s representing the position of the splits.
   */
  public List<Interval> dynamicProgramming(double[][][] cutValues) {

//    int numCuts = cutValues.length;
//
//    Table dptable = new Table(numCuts);
//
//    for (int i = MIN_LENGTH; i < numCuts; i++) {
//
//      Step currentStep = dptable.get(i);
//
//      for (int j = MIN_LENGTH; j < i - MIN_LENGTH; j++) {
//
//        Step lastStep = dptable.get(j);
//        List<Interval> lastPath = lastStep.getPath();
//        Interval lastInterval = lastStep.getPath().get(lastPath.size() - 1);
//
//        double newSegVal = lastStep.getValue()
//            - breakpointPenalty.getPenalty(lastPath.size(), numTimePoints / MIN_LENGTH);
//
//        // if better value can be found with a better segmentation
//        if (newSegVal > currentStep.getValue()) {
//          currentStep.setValue(newSegVal);
//          currentStep.setPath(new ArrayList<>(dptable.get(j).getPath()));
//          currentStep.addToPath(new Interval(j + 1, i));
//          currentStep.setGraph(graphs[j + 1][i]);
//        }
//      }
//    }

    return null;
  }


}
