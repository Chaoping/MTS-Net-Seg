package ca.ubco.cosc520.dynamicprogramming;

public class ExponentialBreakpointPenalty implements BreakpointPenalty {

  private final double v;

  public ExponentialBreakpointPenalty(double v) {
    this.v = v;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
      return Math.exp(v * currentCuts / maximumPossibleCuts);
  }

}
