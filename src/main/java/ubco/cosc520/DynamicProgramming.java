package ubco.cosc520;

import java.util.ArrayList;
import java.util.List;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphDistance;

public class DynamicProgramming {

  // this method
  public List<Integer> dynamicProgramming(Graph[][] graphs) {

    int numTimePoints = graphs.length;

    // Dynamic programming table stores OPT(w), the maximum value up to w
    List<OPT> dptable = new ArrayList<>();

    for (int i = 0; i < numTimePoints; i++) {
      dptable.add(new OPT());
    }

    // OPT(0) = 0;
    dptable.get(0).setValue(0.0);
    dptable.get(0).addToPath(0);
    dptable.get(0).addToPath(0);

    // OPT(1) = 0;
    dptable.get(1).setValue(0.0);
    dptable.get(1).addToPath(0);
    dptable.get(1).addToPath(1);

    // OPT(i) = max(weight of the last edge + OPT(before the last edge) - BP)
    for (int i = 2; i < numTimePoints; i++) {

      // by default, no new segments
      double opt = dptable.get(i - 1).getValue();
      List<Integer> optPath = new ArrayList<>(dptable.get(i - 1).getPath());
      optPath.set(optPath.size() - 1, i); //

      // or there is a new segment
      for (int j = 0; j < i; j++) {
        //opt_j + weight_ji+ bp
        int lastSeg = dptable.get(j).getPath().get(dptable.get(j).getPath().size() - 2);
        double newSegVal = dptable.get(j).getValue() +
            new TwoGraphDistance().operate(graphs[lastSeg][j], graphs[j + 1][i]) +
            bp(1.0, dptable.get(j).getPath().size() - 1, numTimePoints - 2);

        // if better value can be found with a better segmentation
        if (newSegVal > opt) {
          opt = newSegVal;
          optPath = new ArrayList<>(dptable.get(j).getPath());
          optPath.add(i);
        }

        dptable.get(i).setValue(opt);
        dptable.get(i).setPath(optPath);
      }
    }

    return dptable.get(numTimePoints - 1).getPath();
  }

  double bp(double v, int d_w, int n) {
    return Math.exp(v * d_w / n);
  }
}
