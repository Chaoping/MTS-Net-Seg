package ubco.cosc520.timeseries;

import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphDistance;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {
    // this method
    public List<Integer> dynamicProgramming(Graph[][] graphs) {

        int numTimePoints = graphs.length;

        // Dynamic programming table stores OPT(w), the maximum valueo up to w
        List<OPT> dptable = new ArrayList<>(numTimePoints);

        // OPT(0) = 0;
        dptable.get(0).set(0.0);
        dptable.get(0).path.add(0);
        
        // OPT(1) = 0;
        dptable.get(1).set(0.0);
        dptable.get(1).path.add(0);
        dptable.get(1).path.add(1);

        // OPT(i) = max(weight of the last edge + OPT(before the last edge) - BP)
        for (int i = 2; i < numTimePoints; i++) {

            // by default, no new segments
            double opt = dptable.get(i - 1).value;
            List<Integer> optPath = new ArrayList<>(dptable.get(i - 1).path);
            optPath.set(optPath.size()-1, i); //

            // or there is a new segment
            for (int j = 0; j < i; j++) {
                //opt_j + weight_ji+ bp
                int lastSeg = dptable.get(j).path.get(dptable.get(j).path.size() - 2);
                double newSegVal = dptable.get(j).value +
                        new TwoGraphDistance().operate(graphs[lastSeg][j], graphs[j + 1][i]) +
                        bp(1.0, dptable.get(j).path.size() - 1, numTimePoints - 2);

                // if better value can be found with a better segmentation
                if (newSegVal > opt) {
                    opt = newSegVal;
                    optPath = new ArrayList<>(dptable.get(j).path);
                    optPath.add(i);
                }


                dptable.get(i).value = opt;
                dptable.get(i).path = optPath;
            }

            return dptable.get(numTimePoints).path;

        }


        return null;
    }

    double bp(double v, int d_w, int n) {
        return Math.exp(v * d_w / n);
    }
}
