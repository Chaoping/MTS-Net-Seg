package ubco.cosc520;

import java.util.List;
import lombok.NonNull;
import ubco.cosc520.dynamicprogramming.Interval;

public class IntervalListToCSV {
  public static String fromInterval(@NonNull List<Interval> intervalList) {
    StringBuilder sb = new StringBuilder("0,");
    intervalList.stream().forEach(interval -> {
      sb.append(interval.getEnd());
      sb.append(",");
    });
    return sb.deleteCharAt(sb.length() - 1).toString();
  }

}
