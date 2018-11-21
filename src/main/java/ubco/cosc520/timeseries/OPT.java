package ubco.cosc520.timeseries;

import java.util.ArrayList;
import java.util.List;

public class OPT {
    double value = 0.0;
    List<Integer> path = new ArrayList<>();

    public void set(double value) {
        this.value = value;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }
}
