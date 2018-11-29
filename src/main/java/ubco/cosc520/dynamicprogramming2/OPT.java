package ubco.cosc520.dynamicprogramming2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;

public class OPT {

  private double value = 0.0;
  private List<Interval> path = new ArrayList<>();

  public void addToPath(@NonNull Interval input) {
    path.add(input);
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public List<Interval> getPath() {
    return Collections.unmodifiableList(path);
  }

  public void setPath(@NonNull List<Interval> path) {
    this.path = path;
  }
}
