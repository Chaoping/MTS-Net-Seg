package ubco.cosc520.dynamicprogramming;

public class ConstantBreakpointPenalty implements BreakpointPenalty {

  private final double v;

  public ConstantBreakpointPenalty(double v) {
    this.v = v;
  }

  public double getPenalty(int currentCuts, int maximumPossibleCuts) {
      return v;
  }

}
