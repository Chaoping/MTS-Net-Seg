package ca.ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ExponentialBreakpointPenaltyTest {

  private final double tuningParameter;

  public ExponentialBreakpointPenaltyTest(Double tuningParameter) {
    this.tuningParameter = tuningParameter;
  }


  @Parameterized.Parameters
  public static Collection provideParameters() {
    return Arrays.asList(1.0, 2.0, 3.5);
  }

  @Test
  public void testCurrentCutsMatchesMaximumCuts() {
    ExponentialBreakpointPenalty exponentialBreakpointPenalty = new ExponentialBreakpointPenalty(
        tuningParameter);
    double penalty = exponentialBreakpointPenalty.getPenalty(1, 1);
    assertThat(penalty, is(Math.exp(tuningParameter)));
  }

  @Test
  public void testNoCurrentCuts() {
    ExponentialBreakpointPenalty exponentialBreakpointPenalty = new ExponentialBreakpointPenalty(
        tuningParameter);
    double penalty = exponentialBreakpointPenalty.getPenalty(0, 1);
    assertThat(penalty, is(1.0));
  }
}