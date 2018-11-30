package ca.ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ExponentialBreakpointPenaltyTest {

  private final double v;

  public ExponentialBreakpointPenaltyTest(Double v) {
    this.v = v;
  }


  @Parameterized.Parameters
  public static Collection provideV() {
    return Arrays.asList(1.0, 2.0, 3.5);
  }

  @Test
  public void testCurrentCutsMatchesMaximumCuts() {
    ExponentialBreakpointPenalty exponentialBreakpointPenalty = new ExponentialBreakpointPenalty(v);
    double penalty = exponentialBreakpointPenalty.getPenalty(1,1);
    assertThat(penalty, is(Math.exp(v)));
  }

  @Test
  public void testNoCurrentCuts() {
    ExponentialBreakpointPenalty exponentialBreakpointPenalty = new ExponentialBreakpointPenalty(v);
    double penalty = exponentialBreakpointPenalty.getPenalty(0,1);
    assertThat(penalty, is(1.0));
  }
}