package ubco.cosc520.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class Table {

  private final List<Step> dptable;

  public Table(int size) {

    if (size <= 0) {
      throw new IllegalArgumentException("Size must be greater than 2");
    }

    // Dynamic programming table stores Step(w), the maximum value up to w
    dptable = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      dptable.add(new Step(Integer.MIN_VALUE, i));
    }
  }

  public Step get(int idx) {
    return dptable.get(idx);
  }

}
