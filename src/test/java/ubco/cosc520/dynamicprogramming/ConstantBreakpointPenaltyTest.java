package ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConstantBreakpointPenaltyTest {

  @Test
  public void testResturnsSameValueForOne() {
    BreakpointPenalty breakpointPenalty = new ConstantBreakpointPenalty(1);
    assertThat(breakpointPenalty.getPenalty(1,1), is(1.0));
  }


  @Test
  public void testResturnsSameValueForDifferentParameters() {
    BreakpointPenalty breakpointPenalty = new ConstantBreakpointPenalty(1);
    assertThat(breakpointPenalty.getPenalty(10,10), is(1.0));
  }
}