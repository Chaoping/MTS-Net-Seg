package ubco.cosc520;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OPT {

  private double value = 0.0;
  private List<Integer> path = new ArrayList<>();

  public void addToPath(Integer input) {
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

  public void setPath(List<Integer> path) {
    this.path = path;
  }
}
