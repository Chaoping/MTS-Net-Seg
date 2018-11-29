package ubco.cosc520.dynamicprogramming;

public class BreakpointPenalty {

  private final double v;

  public BreakpointPenalty(double v) {
    this.v = v;
  }

  public double getPenalty(int depth, int maximumPossibleCuts) {
      return Math.exp(v * depth / maximumPossibleCuts);
  }

}
