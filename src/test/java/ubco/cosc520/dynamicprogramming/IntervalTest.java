package ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

  @Test
  public void testToString() {
    Interval interval = new Interval(0,1);
    assertThat(interval.toString(), is("Interval(start=0, end=1)"));
  }
}