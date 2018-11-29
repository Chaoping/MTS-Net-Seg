package ubco.cosc520.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;

public class Step {

  private double value;
  private List<Interval> path = new ArrayList<>();

  public Step(int initialValue, int pathEnd) {
    this.value = initialValue;
    Interval interval = new Interval(0, pathEnd);
    this.path.add(interval);
  }

  public void addToPath(@NonNull Interval interval) {
    path.add(interval);
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
