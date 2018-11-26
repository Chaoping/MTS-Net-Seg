package ubco.cosc520.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.NonNull;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphOperator;

public class PathMapper {

  private final TwoGraphOperator<Double> distanceCalculator;

  @Inject
  public PathMapper(@NonNull TwoGraphOperator<Double> distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  // this method
  public List<Integer> dynamicProgramming(@NonNull Graph[][] graphs) {

    int numTimePoints = graphs.length;

    // Dynamic programming table stores Step(w), the maximum value up to w
    List<Step> dptable = new ArrayList<>();

    for (int i = 0; i < numTimePoints; i++) {
      dptable.add(new Step());
    }

    // Step(0) = 0;
    dptable.get(0).setValue(0.0);
    dptable.get(0).addToPath(0);
    dptable.get(0).addToPath(0);

    // Step(1) = 0;
    dptable.get(1).setValue(0.0);
    dptable.get(1).addToPath(0);
    dptable.get(1).addToPath(1);

    // Step(i) = max(weight of the last edge + Step(before the last edge) - BP)
    for (int i = 2; i < numTimePoints; i++) {

      // by default, no new segments
      double step = dptable.get(i - 1).getValue();
      List<Integer> path = new ArrayList<>(dptable.get(i - 1).getPath());
      path.set(path.size() - 1, i); //

      // or there is a new segment
      for (int j = 0; j < i; j++) {
        //opt_j + weight_ji+ bp
        int lastSeg = dptable.get(j).getPath().get(dptable.get(j).getPath().size() - 2);
        double newSegVal = dptable.get(j).getValue()
            + distanceCalculator.operate(graphs[lastSeg][j], graphs[j + 1][i])
            + bp(1.0, dptable.get(j).getPath().size() - 1, numTimePoints - 2);

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

  private double bp(double v, int difference, int n) {
    return Math.exp(v * difference / n);
  }
}
