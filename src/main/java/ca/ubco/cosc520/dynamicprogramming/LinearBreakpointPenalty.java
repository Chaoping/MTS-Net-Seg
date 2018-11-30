package ca.ubco.cosc520.dynamicprogramming;

public class LinearBreakpointPenalty implements BreakpointPenalty {

  private final double v;

  public LinearBreakpointPenalty(double v) {
    this.v = v;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    return v * currentCuts / maximumPossibleCuts;
  }

}
