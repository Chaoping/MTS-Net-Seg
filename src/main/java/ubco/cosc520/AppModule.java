package ubco.cosc520;

import dagger.Module;
import dagger.Provides;
import java.util.Random;
import javax.inject.Singleton;
import ubco.cosc520.dynamicprogramming.BreakpointPenalty;
import ubco.cosc520.dynamicprogramming.ExponentialBreakpointPenalty;
import ubco.cosc520.graph.TwoGraphDistance;
import ubco.cosc520.graph.TwoGraphOperator;
import ubco.cosc520.matrix.MatrixOfDifferences;
import ubco.cosc520.matrix.SingleMatrixOperator;

@Module
public class AppModule {

  @Provides
  public TwoGraphOperator<Double> provideDistanceCalculator() {
    return new TwoGraphDistance();
  }

  @Provides
  public SingleMatrixOperator provideMatrixDifferenceCalculator() {
    return new MatrixOfDifferences();
  }

  @Provides
  @Singleton
  public Random provideRandom() {
    return new Random();
  }

  @Provides
  public BreakpointPenalty provideBreakpointPenalty() {
    return new ExponentialBreakpointPenalty(1.0);
  }
}
