package ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class BreakpointPenaltyTest {

  @Test
  public void testCurrentCutsMatchesMaximumCuts() {
    BreakpointPenalty breakpointPenalty = new BreakpointPenalty(1);
    double penalty = breakpointPenalty.getPenalty(1,1);
    assertThat(penalty, is(Math.exp(1) - 1.0));
  }

  @Test
  public void testNoCurrentCuts() {
    BreakpointPenalty breakpointPenalty = new BreakpointPenalty(1);
    double penalty = breakpointPenalty.getPenalty(0,1);
    assertThat(penalty, is(0.0));
  }
}