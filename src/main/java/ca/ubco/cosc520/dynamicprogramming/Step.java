package ca.ubco.cosc520.dynamicprogramming;

import ca.ubco.cosc520.graph.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Step {

  private double value;
  private List<Interval> path = new ArrayList<>();

  @Getter
  @Setter
  private Graph graph;

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
