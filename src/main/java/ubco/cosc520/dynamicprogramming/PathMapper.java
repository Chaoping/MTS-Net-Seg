package ubco.cosc520.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.NonNull;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphOperator;

/**
 * Produces the Dynamic Programming Path.
 */
public class PathMapper {

  private final TwoGraphOperator<Double> distanceCalculator;
  private final BreakpointPenalty breakpointPenalty;

  private static final int MIN_LENGTH = 5;

  @Inject
  public PathMapper(@NonNull TwoGraphOperator<Double> distanceCalculator, BreakpointPenalty breakpointPenalty) {
    this.distanceCalculator = distanceCalculator;
    this.breakpointPenalty = breakpointPenalty;
  }

  /**
   * Performs the dynamic programming algorithm on a table of {@link Graph} objects.
   * @param graphs The table of {@link Graph} objects
   * @return A {@link List} of {@link Integer}s representing the position of the splits.
   */
  public List<Interval> dynamicProgramming(@NonNull Graph[][] graphs) {

    int numTimePoints = graphs.length;

    Table dptable = new Table(numTimePoints);

    for (int i = MIN_LENGTH; i < numTimePoints; i++) {

      Step currentStep = dptable.get(i);

      for (int j = MIN_LENGTH; j < i-MIN_LENGTH; j++) {

        Step lastStep = dptable.get(j);
        List<Interval> lastPath = lastStep.getPath();
        Interval lastInterval = lastStep.getPath().get(lastPath.size() - 1);

        double newSegVal = lastStep.getValue()
            + distanceCalculator.operate(graphs[lastInterval.getStart()][lastInterval.getEnd()], graphs[j + 1][i])
            - breakpointPenalty.getPenalty(lastPath.size(), numTimePoints / MIN_LENGTH );

        // if better value can be found with a better segmentation
        if (newSegVal > currentStep.getValue()) {
          currentStep.setValue(newSegVal);
          currentStep.setPath(new ArrayList<>(dptable.get(j).getPath()));
          currentStep.addToPath(new Interval(j+1, i));
          currentStep.setGraph(graphs[j+1][i]);
        }
      }
    }

    return dptable.get(numTimePoints - 1).getPath();
  }


}
