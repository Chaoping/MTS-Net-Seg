package ca.ubco.cosc520.dynamicprogramming;

public class NormalizedExponentialBreakpointPenalty extends ExponentialBreakpointPenalty {

  private final double v;

  public NormalizedExponentialBreakpointPenalty(double v) {
    super(v);
    this.v = v;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
    return super.getPenalty(currentCuts, maximumPossibleCuts) - 1;
  }

}
