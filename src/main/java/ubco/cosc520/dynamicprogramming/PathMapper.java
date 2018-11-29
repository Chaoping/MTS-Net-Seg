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

    // Step(i) = max(weight of the last edge + Step(before the last edge) - BP)
    for (int i = 2; i < numTimePoints; i++) {

      // by default, no new segments
      double step = dptable.get(i - 1).getValue();
      List<Integer> path = new ArrayList<>(dptable.get(i - 1).getPath());
      path.set(path.size() - 1, i); //

      // or there is a new segment
      for (int j = 0; j < i; j++) {

        int lastSeg = dptable.get(j).getPath().get(dptable.get(j).getPath().size() - 2);

        double newSegVal = dptable.get(j).getValue()
            + distanceCalculator.operate(graphs[lastSeg][j], graphs[j + 1][i])
            - breakpointPenalty.getPenalty(dptable.get(j).getPath().size() - 1, numTimePoints - 2);

        // if better value can be found with a better segmentation
        if (newSegVal > step) {
          step = newSegVal;
          path = new ArrayList<>(dptable.get(j).getPath());
          path.add(i);
        }

        dptable.get(i).setValue(step);
        dptable.get(i).setPath(path);
      }
    }

    return dptable.get(numTimePoints - 1).getPath();
  }


}
