package ubco.cosc520.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
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
  public List<Integer> dynamicProgramming(@NonNull Graph[][] graphs) {

    int numTimePoints = graphs.length;

    Table dptable = new Table(numTimePoints);

    for (int i = 4; i < numTimePoints; i++) {

      Step step = dptable.get(i);

      for (int j = 2; j < i-2; j++) {

        int lastSeg = dptable.get(j).getPath().get(dptable.get(j).getPath().size() - 1);

        double newSegVal = dptable.get(j).getValue()
            + distanceCalculator.operate(graphs[lastSeg][j], graphs[j + 1][i])
            - breakpointPenalty.getPenalty(dptable.get(j).getPath().size() - 1, numTimePoints - 2);

        // if better value can be found with a better segmentation
        if (newSegVal > step.getValue()) {
          step.setValue(newSegVal);
          step.setPath(new ArrayList<>(dptable.get(j).getPath()));
          step.addToPath(i);
        }
      }
    }

    return dptable.get(numTimePoints - 1).getPath();
  }


}
