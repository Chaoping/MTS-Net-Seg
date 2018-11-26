package ubco.cosc520.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;

public class Step {

  private double value = 0.0;
  private List<Integer> path = new ArrayList<>();

  public void addToPath(@NonNull Integer input) {
    path.add(input);
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public List<Integer> getPath() {
    return Collections.unmodifiableList(path);
  }

  public void setPath(@NonNull List<Integer> path) {
    this.path = path;
  }
}
