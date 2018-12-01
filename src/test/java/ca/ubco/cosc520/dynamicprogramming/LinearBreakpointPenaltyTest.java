package ca.ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LinearBreakpointPenaltyTest {

  @Test
  public void testResturnsSameValueForOne() {
    BreakpointPenalty breakpointPenalty = new LinearBreakpointPenalty(1);
    assertThat(breakpointPenalty.getPenalty(1, 1), is(1.0));
  }


  @Test
  public void testResturnsSameValueForDifferentEqualParameters() {
    BreakpointPenalty breakpointPenalty = new LinearBreakpointPenalty(1);
    assertThat(breakpointPenalty.getPenalty(10, 10), is(1.0));
  }

  @Test
  public void testResturnsSameValueForDifferentParameters() {
    BreakpointPenalty breakpointPenalty = new LinearBreakpointPenalty(1);
    assertThat(breakpointPenalty.getPenalty(5, 10), is(0.5));
  }
}